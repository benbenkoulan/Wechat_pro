package com.eloancn.wechat.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eloancn.wechat.common.constant.Constant;
import com.eloancn.wechat.common.utils.DateUtil;
import com.eloancn.wechat.common.utils.JedisUtil;
import com.eloancn.wechat.common.utils.RequestUtil;
import com.eloancn.wechat.common.utils.Tools;

public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("static_server",  Constant.getStaticServer());
		mv.addObject("baseUrl", Constant.getBaseURL());
		return mv;
	}
	

	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(HttpServletRequest request) {
		ModelAndView mv = this.getModelAndView();
		String openId = (String)request.getAttribute("openId");
		mv.addObject("openId", openId);
		return mv;
	}
	
	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView(HttpServletRequest request,String pageTitle) {
		ModelAndView mv = this.getModelAndView();
		String openId = (String)request.getAttribute("openId");
		mv.addObject("openId", openId);
		mv.addObject("pageTitle", pageTitle);
		return mv;
	}
	
	/*
	 * 检查是否绑定账户
	 * */
	public ModelAndView checkBind(HttpServletRequest request, String viewName) {
		ModelAndView mv = getModelAndView(request , "用户登录");
		String openId = (String)request.getAttribute("openId");
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.FINDUSERVERIFYINFO, null, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				mv.setViewName("bind");
				mv.addObject("viewName", viewName);
			}
		} catch (Exception e) {
			logger.error("---openId--" + openId +"--checkBind----FINDUSERVERIFYINFO-----" + e.getMessage());
		}
		return mv;
	}
	
	/*
	 * 检查账户授权信息
	 * **/
	public JSONObject checkAuth(String openId) {
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.NETWORKAUTHRECORD, null, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				return jsonObj.getJSONObject("data");
			}
		} catch (Exception e) {
			logger.error("-----checkAuth---------" + e.getMessage());	
		}
		return null;
	}
	
	/*
	 * 检查账户vip信息
	 */
	public JSONObject checkVip(String openId) throws Exception{
		JSONObject isVip = JedisUtil.getJSONObjectFromRedis(openId + Constant.ISVIP);
		if(null != isVip.get("strRatio")){
			return isVip;
		}
		JSONObject ratioObj = RequestUtil.getJSONObjectByHttpClient(Constant.LOADRADIOBYUID, null , openId);
		if(Constant.REQUESTSUCCESS.equals(ratioObj.getString(Constant.JSONDATATIP))){
			double ratio = ratioObj.getDoubleValue("data");
			if(ratio > 0){
				//将vip信息存入到redis中
				isVip.put("vip", true);
				isVip.put("strRatio" , ratio);
			} else {
				isVip.put("vip", false);
				isVip.put("strRatio", ratio);
			}
			JedisUtil.saveObjectToRedis(openId + Constant.ISVIP, isVip, Constant.EXPIRETIME);
		}
		return isVip;
	}
	
	public JSONObject getVipInfo(String id,String platform) throws Exception{
		String isVipKey = null;
		JSONObject isVip = new JSONObject();
		if(!Tools.checkBind(id, platform)){
			isVip.put("vip", false);
			isVip.put("strRatio", 0);
			return isVip;
		}
		JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(id);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("app_secret", userInfo.getString("app_secret")));
		params.add(new BasicNameValuePair("platform", platform));
		platform = Tools.notEmpty(platform) ? Constant.PLATFORM : platform;
		if(Constant.PLATFORM.equals(platform)){
			isVipKey = id + Constant.ISVIP;
			isVip = JedisUtil.getJSONObjectFromRedis(isVipKey);	
			params.add(new BasicNameValuePair("user_id", userInfo.getString("out_uid")));
			params.add(new BasicNameValuePair("openid", id));
			params.add(new BasicNameValuePair("accountno", Constant.getAppId()));
		} else {
			isVipKey = id;
			params.add(new BasicNameValuePair("user_id", id));
			isVip.put("strRatio", userInfo.getString("strRatio")); 
		}
		if(Tools.notEmpty(isVip.getString("strRatio"))){
			return isVip;
		}
		String responseText = RequestUtil.sendGETRequest(getUrl(Constant.LOADRADIOBYUID), params);
		JSONObject json = (JSONObject)JSON.parse(responseText);
		JSONObject jsonObj = json.getJSONObject("jsonData");
		double ratio = 0;
		if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))){
			try{
				ratio = jsonObj.getDoubleValue("data");
			} catch(Exception e){
				
			}
			if(ratio > 0){
				//将vip信息存入到redis中
				isVip.put("vip", true);
				isVip.put("strRatio" , ratio);
			} else {
				isVip.put("vip", false);
				isVip.put("strRatio", 0);
			}
			JedisUtil.saveObjectToRedis(isVipKey, isVip, Constant.EXPIRETIME);
		}
		return isVip;
	}
	
	public String getUrl(String url){
		StringBuffer sb = new StringBuffer(Constant.getBaseURL());
		sb.append("/mobile");
		sb.append(url);
		return sb.toString();
	}
	
	public String getSesameUrl(String url){
		StringBuffer sb = new StringBuffer(Constant.getSesameBaseURL());
		sb.append(url);
		return sb.toString();
	}
	
	/*
	 * 获取债权列表详情
	 */
	@SuppressWarnings("rawtypes")
	public JSONArray getLenderDetails(JSONObject jsonObj){
		JSONArray data = jsonObj.getJSONArray("data");
		JSONArray temData = new JSONArray();
		Iterator it = data.iterator();
		while(it.hasNext()){
			JSONObject dt = (JSONObject)it.next();
			double interest = dt.getDoubleValue("interestrate");
			dt.put("interestrate", String.format("%.2f" , interest * 100));
			temData.add(dt);
		}
		return temData;
	}

	/*
	 * 解析投资项目
	 * **/
	public JSONObject parseInvestProject(JSONObject pro , JSONObject isvip , String openId){
		JSONObject investObj = new JSONObject();
		investObj.put("qixian", Tools.intval(pro.getString("strPhases")));
		if(pro.getIntValue("phasesType") == 1) {
			investObj.put("qx_type", "天");
		} else if(pro.getIntValue("phasesType") == 2) {
			investObj.put("qx_type", "月");
		}
		String doubleValue = String.format("%.2f", pro.getDoubleValue("amount") / 10000);
		if("00".equals(doubleValue.split("\\.")[1])) {
			investObj.put("amount", Double.parseDouble(doubleValue.split("\\.")[0]));
		} else {
			investObj.put("amount", Double.parseDouble(doubleValue));
		}
		int status = pro.getIntValue("status");
		investObj.put("status", status);
		if(status == 4) {
			investObj.put("wenan", "已结束");
		} else if(pro.getDoubleValue("maxAmount") <= pro.getDoubleValue("amount")) {
			investObj.put("wenan", "已抢光");
		} else if(status == 1) {
			investObj.put("wenan", "我要投资");
		} else {
			investObj.put("wenan", "已结束");
		}
		investObj.put("SLMaxAmount", pro.getDouble("SLMaxAmount"));
		investObj.put("title", pro.getString("title"));
		investObj.put("d_url", "/index/detail?openId=" + openId + "&id=" + pro.getString("id"));
		//体验期翼存宝标志
		int exp = pro.getIntValue("experience");
		String[] temList = pro.getString("strInterestrate").split("\\+");
		if(temList.length > 1 && exp != 1) {
			investObj.put("ewai", temList[1]);
			investObj.put("strInterestrate", temList[0]);
		} else {
			investObj.put("strInterestrate", pro.getString("strInterestrate").split("\\%")[0]);
		}
		if(1 == exp){
			investObj.put("type", 1);
			investObj.put("tz_url", "/index/tiyanqitouzi?openId=" + openId + "&id=" + pro.getString("id"));
		} else {
			if(null != isvip){
				investObj.put("vip", isvip.getBooleanValue("vip"));
				investObj.put("strRatio", isvip.getDoubleValue("strRatio"));
			}
			//非体验期
			investObj.put("type", 0);
			investObj.put("tz_url", "/index/touzi?openId=" + openId + "&id=" + pro.getString("id"));
		}
		return investObj;
	}
	
	public JSONObject getDetail(String openId , String id , String page){
			//获取投资产品详情
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id", id));
			params.add(new BasicNameValuePair("page", page));
			JSONObject jsonObj = null;
			try {
				jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.LOADWMPSRECORDDETAIL, params, openId);
			} catch (Exception e) {
				logger.error("-----getDetail---id---" + id + "---" + e.getMessage());	
			}
			JSONObject data = new JSONObject();
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString("tip"))) {
				data = jsonObj.getJSONObject("data");
				String strPhases = data.getString("strPhases");
				if(!Tools.isEmpty(strPhases)){
					//替换中文
					data.put("strPhases", Tools.replaceChinese(strPhases));
				}
				long timeStamp = (System.currentTimeMillis() + data.getLongValue("strDongTime") * 1000);
				data.put("end_date", DateUtil.formatDate(timeStamp));
				int status = data.getIntValue("status");
				switch(status) {
				case 2:data.put("img", "tnd_detail_ico1.png");break;
				case 3:data.put("img", "tnd_detail_ico2.png");break;
				case 4:data.put("img", "tnd_detail_ico3.png");break;
				default:
					data.put("img", "");
				}
				return data;
			} else {
				data.put("tip", jsonObj.getString("tip"));
				return data;
			}
		}

		/**
		 * 获取翼农计划详情
		 * */
		public JSONObject getWmpsDetail(JSONObject data1){
			JSONObject wmp1 = new JSONObject();
			wmp1.put("icon", data1.getString("icon"));
			wmp1.put("productname", "翼农计划");
			wmp1.put("type", "1");//1:翼农计划，2:芝麻开花，3:转让标
			wmp1.put("period",data1.getString("title"));
			String[] temList = data1.getString("strInterestrate").split("\\+");
			if(temList.length > 1) {
				wmp1.put("ewai", temList[1]);
				wmp1.put("interest", temList[0]);
			} else {
				wmp1.put("interest", data1.getString("strInterestrate").split("\\%")[0]);
			}
			String doubleValue = String.format("%.2f", data1.getDoubleValue("amount") / 10000);
			if("00".equals(doubleValue.split("\\.")[1])) {
				wmp1.put("raised", Double.parseDouble(doubleValue.split("\\.")[0]));
			} else {
				wmp1.put("raised", Double.parseDouble(doubleValue));
			}
			double maxAmount = data1.getDoubleValue("SLMaxAmount");
			if(maxAmount > 1){
				BigDecimal sum= new BigDecimal(maxAmount);
				wmp1.put("sum", sum.setScale(0,BigDecimal.ROUND_DOWN));
			}else{
				wmp1.put("sum", maxAmount);
			}
			wmp1.put("term", data1.getString("strPhases"));
			wmp1.put("number", data1.getString("yuGouNum"));
			wmp1.put("lasttime", data1.getString("strDongTime"));
			String pid = data1.getString("id");
			wmp1.put("pid", pid);
			wmp1.put("detail_href", "/wechat/html/wmps/detail.html?pid=" + pid);
			return wmp1;
		}
		
		/**
		 * 获取芝麻开花详情
		 * **/
		public JSONObject getSesameDetail(JSONObject dataObj){
			JSONObject sesame1 = new JSONObject();
			sesame1.put("productname", "芝麻开花");
			sesame1.put("type", "2");//1:翼农计划，2:芝麻开花，3:转让标
			sesame1.put("period",dataObj.getString("title"));
			BigDecimal maxRate = new BigDecimal(dataObj.getString("maxRate"));
			sesame1.put("maxRate",maxRate.setScale(1, BigDecimal.ROUND_DOWN));
			sesame1.put("minRate",dataObj.getString("minRate"));
			sesame1.put("investPeriod", dataObj.getString("investPeriod"));
			sesame1.put("closedPeriod", dataObj.getString("closed_period"));
			BigDecimal mutiple = new BigDecimal(10000);
			BigDecimal raised = new BigDecimal(dataObj.getString("total_amount"));
			sesame1.put("raised", raised.divide(mutiple).setScale(2, BigDecimal.ROUND_DOWN));
			sesame1.put("raised_amount", raised.setScale(0, BigDecimal.ROUND_DOWN));
			BigDecimal sum = new BigDecimal(dataObj.getString("max_amount"));
			sesame1.put("sum", sum.divide(mutiple).setScale(0,BigDecimal.ROUND_DOWN));
			sesame1.put("sum_amount", sum.setScale(0, BigDecimal.ROUND_DOWN));
			sesame1.put("number", dataObj.getString("total_user_number"));
			sesame1.put("lasttime", dataObj.getString("surplusSeconds"));
			String pid = dataObj.getString("id");
			sesame1.put("pid", pid);
			sesame1.put("detail_href", "/wechat/html/sesame/detail.html?pid=" + pid);
			return sesame1;
		}
		
		public String getTenderBeforeUrl(){
			StringBuffer tenderUrl = new StringBuffer(Constant.getBaseURL());
			tenderUrl.append("/tender/tenderDetailBefore.jsp?tenderId=");
			return tenderUrl.toString();
		}
		
		public String getTenderUrl(){
			StringBuffer tenderUrl = new StringBuffer(Constant.getBaseURL());
			tenderUrl.append("/tender/tenderDetail.jsp?tenderId=");
			return tenderUrl.toString();
		}
		
		public String getMyAccountUrl(String id){
			StringBuffer tenderUrl = new StringBuffer(Constant.getWechatBaseURL());
			tenderUrl.append("/wechat/html/account/myaccount.html?id=");
			tenderUrl.append(id);
			return tenderUrl.toString();
		}
		
		public String getMyAccountUrlNoContext(String id){
			StringBuffer tenderUrl = new StringBuffer(Constant.getWechatBaseURL());
			tenderUrl.append("/wechat/html/account/myaccount.html?id=");
			tenderUrl.append(id);
			return tenderUrl.toString();
		}
		
		public List<NameValuePair> getParams(String id,String platform){
			if(Tools.isEmpty(id)){
				id = "";
			}
			JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(id);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("platform", platform));
			params.add(new BasicNameValuePair("app_secret", userInfo.getString("app_secret")));
			if(Constant.PLATFORM.equals(platform)){
				params.add(new BasicNameValuePair("openid", id));
				params.add(new BasicNameValuePair("user_id", userInfo.getString("out_uid")));
				params.add(new BasicNameValuePair("userId", userInfo.getString("out_uid")));
				params.add(new BasicNameValuePair("accountno", Constant.getAppId()));
			} else {
				params.add(new BasicNameValuePair("userId", id));
			}
			return params;
		}
		public List<NameValuePair> getParamsWithoutUserId(String id,String platform){
			if(Tools.isEmpty(id)){
				id = "";
			}
			JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(id);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("platform", platform));
			params.add(new BasicNameValuePair("app_secret", userInfo.getString("app_secret")));
			if(Constant.PLATFORM.equals(platform)){
				params.add(new BasicNameValuePair("openid", id));
				params.add(new BasicNameValuePair("user_id", userInfo.getString("out_uid")));
				params.add(new BasicNameValuePair("accountno", Constant.getAppId()));
			} else {
				params.add(new BasicNameValuePair("user_id", id));
			}
			return params;
		}
}
