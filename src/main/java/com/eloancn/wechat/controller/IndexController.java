package com.eloancn.wechat.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eloancn.wechat.common.constant.Constant;
import com.eloancn.wechat.common.utils.DateUtil;
import com.eloancn.wechat.common.utils.JedisUtil;
import com.eloancn.wechat.common.utils.LLPayUtil;
import com.eloancn.wechat.common.utils.PublicUtil;
import com.eloancn.wechat.common.utils.RequestUtil;
import com.eloancn.wechat.common.utils.Tools;
import com.eloancn.wechat.entities.PaymentInfo;
/**
 * 
 * ClassName: IndexController <br/>
 * Function: 页面初始化 <br/>
 * date: 2 Sep 2015 11:41:28 <br/>
 * @author liben
 * @version
 */

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {
	
	/**
	 * 首页面
	 * @throws Exception 
	 */
	@RequestMapping("/index.html")
	public ModelAndView index(HttpServletRequest request) throws Exception{
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "我的账户");
		//初始化UserInfo
		JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
		//用户已登录 ，加载账户信息
		JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.LOADSTATINFO, null, openId);
		if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
			JSONObject jsonData = jsonObj.getJSONObject("data");
			userInfo.put("yld_head_photo", jsonData.getString("photo"));
			userInfo.put("out_uid", jsonData.getString("id"));
			JedisUtil.saveObjectToRedis(openId , userInfo , Constant.EXPIRETIME);
			mv.addObject("data", jsonData);
			mv.addObject("isvip", checkVip(openId));
		}
		mv.setViewName("index");
		return mv;
	}
	
	/**
	 * bind:进入用户登录页面 <br/>
	 * @author liben
	 * @param request
	 * @param viewName
	 * @return
	 */
	@RequestMapping("/bind")
	public ModelAndView bind(HttpServletRequest request , 
			@RequestParam(value="viewName" , required=false) String viewName){
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "用户登录");
		mv.addObject("viewName", viewName);
		String referer = (String)JedisUtil.getObjectFromRedis(openId + Constant.REFERER, String.class);
		if(!Tools.isEmpty(referer)) {
			//本次referer已经设置，清除redis
			JedisUtil.deleteObjectFromRedis(openId + Constant.REFERER);
		}
		mv.addObject("referer", referer);
		JSONObject params = new JSONObject();
		Map paramsMap = request.getParameterMap();
		for(Object key : paramsMap.keySet().toArray()){
			if(Constant.skipParams.containsKey(key)){
				continue;
			}	else {
				params.put((String) key, ((String[])paramsMap.get(key))[0]);
			}
		}	
		mv.addObject("params", JSONObject.toJSONString(params));
		mv.setViewName("bind");
		return mv;
	}
	
	@RequestMapping("/ycb_show")
	public ModelAndView myycb(HttpServletRequest request){
		ModelAndView mv = this.getModelAndView(request , "我的翼农计划");
		mv.setViewName("myycb");
		return mv;
	}
	
	@RequestMapping("/ycb_detail")
	public ModelAndView ycbDetail(@RequestParam("id") String id ,@RequestParam("phases") String phases,
			@RequestParam("vip") String vip,@RequestParam("effectiveamount") String effectiveamount ,
			@RequestParam("realEarn") String realEarn,@RequestParam("interdate") String interdate ,
			@RequestParam(value="s0",required=false) String s0 ,@RequestParam(value="s1",required=false) String s1,
			@RequestParam(value="s2",required=false) String s2 ,HttpServletRequest request){
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "翼农计划详情");
		mv.addObject("phases", phases);
		mv.addObject("s0", s0);
		mv.addObject("s1", s1);
		mv.addObject("s2", s2);
		mv.addObject("vip", vip);
		mv.addObject("effectiveamount", effectiveamount);
		mv.addObject("realEarn", realEarn);
		mv.addObject("id", id);
		mv.addObject("interdate", interdate);
		JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
		StringBuffer sb = new StringBuffer(Constant.getBaseURL())
													.append(Constant.LICAIXIEYI_USER)
													.append("?uid=")
													.append(userInfo.getString("out_uid"))
													.append("&wid=")
													.append(id);
		mv.addObject("licaiUrl", sb.toString());
		String scfxDate = DateUtil.getAfterDayDate(DateUtil.fomatDate(interdate), "1", "yyyy-MM-dd");
		mv.addObject("scfxDate", scfxDate);
		String tcDate = DateUtil.getAfterDayDate(DateUtil.fomatDate(interdate), phases, "yyyy-MM-dd");
		mv.addObject("tcDate", tcDate);
		try {
			JSONObject init = RequestUtil.getJSONObjectByHttpClient(Constant.INIT, null, openId);
			if(Constant.REQUESTSUCCESS.equals(init.getString(Constant.JSONDATATIP))) {
				String tenderDetailUrl = init.getJSONObject("data").getString("tenderDetailUrl");
				if(!Tools.isEmpty(tenderDetailUrl)){
					mv.addObject("tenderDetailUrl", new StringBuffer(tenderDetailUrl).append("?tenderId=").toString());
				}
			}
		} catch (Exception e) {
			logger.error("-----ycb_detail---------" + e.getMessage());	
		}
		mv.setViewName("ycbdetail");
		return mv;
	}
	
	/**
	 * zhaiquan:进入债权详情页面. <br/>
	 * @author liben
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/zhaiquan")
	public ModelAndView zhaiquan(HttpServletRequest request , 
			@RequestParam(value="id",required=false) String id){
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request, "债权详情");
		JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
		String userId = userInfo.getString("out_uid");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("uid", userId));
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.LOADTENDERDETAIL, params, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))){
				//获取债权列表
				JSONArray temData = getLenderDetails(jsonObj);
				mv.addObject("data", temData);
			}
			JSONObject init = RequestUtil.getJSONObjectByHttpClient(Constant.INIT, null, openId);
			if(Constant.REQUESTSUCCESS.equals(init.getString(Constant.JSONDATATIP))) {
				JSONObject dataObj = init.getJSONObject("data");
				String tenderDetailUrl = dataObj.getString("tenderDetailUrl");
				if(null != tenderDetailUrl){
					mv.addObject("tenderDetailUrl", new StringBuffer(tenderDetailUrl).append("?tenderId=").toString());
				}
			}
		} catch (Exception e) {
			logger.error("-----zhaiquan---------" + e.getMessage());
		}
		mv.setViewName("zhaiquan");
		return mv;
	}
		
	
	/**
	 * myHd:进入我的活动页面. <br/>
	 * @author liben
	 * @param tabType
	 * @return
	 */
	@RequestMapping("/my_hd")
	public ModelAndView myHd(HttpServletRequest request) {
		ModelAndView mv = this.getModelAndView(request , "我的活动");
		mv.setViewName("myhd");
		return mv;
	}
	
	@RequestMapping("/hdjl")
	public ModelAndView hdjl(HttpServletRequest request) {
		ModelAndView mv = this.getModelAndView(request);
		mv.setViewName("hdjl");
		return mv;
	}
	
	@RequestMapping("/toubiao")
	public ModelAndView touBiao(HttpServletRequest request){
		ModelAndView mv = this.getModelAndView(request , "我的投标");
		mv.setViewName("toubiao");
		return mv;
	}
	
	@RequestMapping("/deal_log")
	public ModelAndView dealLog(HttpServletRequest request){
		ModelAndView mv = this.getModelAndView(request , "交易记录");
		int year = Calendar.getInstance().get(Calendar.YEAR);
		StringBuffer buffer = new StringBuffer();
		String temp = null;
		for(int i = 0; i < 10; i++){
			temp = String.valueOf((year - i));
			buffer.append("<option value=" + temp + ">" + temp + "</option>");
		}
		mv.addObject("str", buffer.toString());
		mv.setViewName("deallog");
		return mv;
	}
	
	@RequestMapping("/user_safe")
	public ModelAndView userSafe(HttpServletRequest request){
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "账户安全");
		//检查账户授权信息
		JSONObject auth = this.checkAuth(openId);
		mv.addObject("check", (null != auth ? auth : new JSONObject()));
		JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
		mv.addObject("userInfo", userInfo);
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.FINDUSERVERIFYINFO, null, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				mv.addObject("data", jsonObj.getJSONObject("data"));
			}
		} catch (Exception e) {
			logger.error("-----user_safe---------" + e.getMessage());	
		}
		mv.setViewName("usersafe");
		return mv;
	}
	
	@RequestMapping("/up_pwd")
	public ModelAndView upPwd(HttpServletRequest request){
		ModelAndView mv = this.getModelAndView(request , "修改密码");
		mv.setViewName("uppwd");
		return mv;
	}
	
	@RequestMapping("/authenticate")
	public ModelAndView authenticate(HttpServletRequest request , 
			@RequestParam(value="viewName" , required=false) String viewName) {
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "身份证认证");
		JSONObject auth = this.checkAuth(openId);
		JSONObject data = null != auth ? auth : new JSONObject();
		mv.addObject("number", data.get("idCardAuthCount"));
		mv.addObject("viewName", viewName);
		mv.setViewName("authenticate");
		return mv;
	}
	
	@RequestMapping("/set_pay_pwd")
	public ModelAndView setPayPwd(HttpServletRequest request , 
			@RequestParam(value="viewName" , required=false) String viewName) {
		if(Tools.isEmpty(viewName)){
			viewName = "invested";
		}
		ModelAndView mv = this.getModelAndView(request , "设置支付密码");
		mv.addObject("viewName", viewName);
		mv.setViewName("setpaypwd");
		return mv;
	}
	
	@RequestMapping("/find_pay_pwd")
	public ModelAndView findPayPwd(HttpServletRequest request) {
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "忘记支付密码");
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.FINDUSERVERIFYINFO, null, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				mv.addObject("data", jsonObj.getJSONObject("data"));
			}
		} catch (Exception e) {
			logger.error("-----find_pay_pwd---------" + e.getMessage());	
		}
		mv.setViewName("findpaypwd");
		return mv;
	}
	
	@RequestMapping("/up_pay_pwd")
	public ModelAndView upPayPwd(HttpServletRequest request) {
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "修改支付密码");
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.FINDUSERVERIFYINFO, null, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				mv.addObject("data", jsonObj.getJSONObject("data"));
			}
		} catch (Exception e) {
			logger.error("-----up_pay_pwd---------" + e.getMessage());	
		}
		mv.setViewName("uppaypwd");
		return mv;
	}
	
	@RequestMapping("/invested")
	public ModelAndView invested(HttpServletRequest request){
		String openId = (String)request.getAttribute("openId");
		JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
		ModelAndView mv = this.getModelAndView(request , "投资");
		mv.addObject("userinfo", userInfo);
		try {
			JSONArray jsonArray = RequestUtil.getJSONArrayByHttpClient(Constant.INITBANNER, null, openId);
			if(!jsonArray.isEmpty()) {
				mv.addObject("banner", jsonArray);
			} else {
				mv.addObject("banner", new JSONArray());
			}
		} catch (Exception e) {
			logger.error("-----invested---------" + e.getMessage());	
		}
		//设置体验期标志
		JSONObject tiyanqi = JedisUtil.getJSONObjectFromRedis(openId + Constant.EXPRIENCED);
		if(null != tiyanqi){
			mv.addObject("exprienced", tiyanqi.getString("exprienced"));
		} else {
			mv.addObject("exprienced", "0");
		}
		mv.setViewName("invested");	
		return mv;
	}
	
	@RequestMapping("/invested2")
	public ModelAndView invested2(HttpServletRequest request){
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "投资");
		try {
			JSONArray jsonArray = RequestUtil.getJSONArrayByHttpClient(Constant.INITBANNER, null, openId);
			if(!jsonArray.isEmpty()) {
				mv.addObject("banners", jsonArray);
			} else {
				mv.addObject("banners", new JSONArray());
			}
		} catch (Exception e) {
			logger.error("-----invested---------" + e.getMessage());	
		}
		mv.setViewName("invested2");	
		return mv;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(HttpServletRequest request ,
			@RequestParam(value="id") String id) {
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "翼农计划详情");
		if(id == null || "".equals(id)) {
			request.setAttribute("openId", openId);
			return invested(request);
		}
		//获取投资产品详情
		JSONObject data = getDetail(openId, id, "1");
		BigDecimal maxAmount = new BigDecimal(data.getString("maxAmount"));
		BigDecimal amount = new BigDecimal(data.getString("amount"));
		BigDecimal multiple = new BigDecimal("100");
		BigDecimal multiple2 = new BigDecimal("10000");
		data.put("percentage", amount.divide(maxAmount,2,BigDecimal.ROUND_HALF_EVEN).multiply(multiple).toBigInteger());
		data.put("restAmount", maxAmount.subtract(amount).toPlainString());
		data.put("maxAmount", maxAmount.divide(multiple2,2,BigDecimal.ROUND_HALF_EVEN).toPlainString());
		data.put("amount", amount.divide(multiple2,2,BigDecimal.ROUND_HALF_EVEN).toPlainString());
		String interDate = data.getString("strInterdate");
		String strPhases = data.getString("strPhases");
		if(!Tools.isEmpty(interDate) && !Tools.isEmpty(strPhases)){
			data.put("firstInterDate", DateUtil.getAfterDayDate(DateUtil.fomatDate(interDate), "1", "yyyy-MM-dd"));
			data.put("outDate", DateUtil.getAfterDayDate(DateUtil.fomatDate(interDate), strPhases, "yyyy-MM-dd"));
		}
		String strInterestrate = data.getString("strInterestrate");
		if(!Tools.isEmpty(strInterestrate)){
			String[] interestrates = strInterestrate.split("\\+");
			for(int i=0,length=interestrates.length;i<length;i++){
				data.put("s" + i, interestrates[i]);
			}
		}
		if(null != data.get("experience") && data.getIntValue("experience") == 1){
			mv.addObject("href", "tiyanqitouzi");
		} else {
			//非体验期用户可以显示vip
			try {//获取vip信息
				JSONObject isVip = checkVip(openId);
				mv.addObject("isvip", isVip);
			} catch (Exception e) {
				logger.error("-----detail---isvip------" + e.getMessage());	
			}
			mv.addObject("href", "touzi");
		}
		mv.addObject("tenderDetailUrl", new StringBuffer(Constant.getBaseURL()).append("/tender/tenderDetailBefore.jsp?tenderId=").toString());
		mv.addObject("data", data);
		mv.addObject("id", id);
		mv.setViewName("detail");
		return mv;
	}
	
	/**
	 * touzi:进入投资页面. <br/>
	 * @author liben
	 * @param openId
	 * @param id
	 * @return
	 */
	@RequestMapping("/touzi")
	public ModelAndView touzi(HttpServletRequest request , 
			@RequestParam("id") String id) {
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "我要投资");
		if(Tools.isEmpty(id)) {
			return invested(request);
		}
		//获取投资产品详情
		JSONObject data = getDetail(openId, id, "1");
		try {
			//加载用户统计信息
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.LOADSTATINFO, null, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				mv.addObject("info", jsonObj.getJSONObject("data"));
			}
			if(null == data.get("experience") || data.getIntValue("experience") != 1){
				//非体验期用户可以显示vip
				//获取vip信息
				JSONObject isVip = checkVip(openId);
				mv.addObject("isvip", isVip);
			}
		} catch (Exception e) {
			logger.error("-----touzi---------" + e.getMessage());	
		}
		mv.addObject("licaiUrl", Constant.getBaseURL() + Constant.LICAIXIEYI_NOUSER);
		mv.addObject("data", data);
		mv.addObject("id", id);
		mv.setViewName("touzi");
		return mv;
	}
	
	@RequestMapping("/tiyanqitouzi")
	public ModelAndView tiyanqiTouzi(HttpServletRequest request , 
			@RequestParam("id") String id){
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "我要投资");
		if(Tools.isEmpty(id)) {
			return invested(request);
		}
		//获取投资产品详情
		JSONObject data = getDetail(openId, id, "1");
		try {
			//加载用户统计信息
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.LOADSTATINFO, null, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				mv.addObject("info", jsonObj.getJSONObject("data"));
			}			
		} catch (Exception e) {
			logger.error("-----tiyanqitouzi---------" + e.getMessage());	
		}
		mv.addObject("licaiUrl", Constant.getBaseURL() + Constant.LICAIXIEYI_NOUSER);
		mv.addObject("data", data);
		mv.addObject("id", id);
		mv.setViewName("tiyanqitouzi");
		return mv;
	}
	
	/**
	 * mobileAuth:进入手机号码认证页面. <br/>
	 * @author liben
	 * @param openId
	 * @return
	 */
	@RequestMapping("/mobile_auth")
	public ModelAndView mobileAuth(HttpServletRequest request) {
		ModelAndView mv = this.getModelAndView(request , "手机号码认证");
		mv.setViewName("mobileauth");
		return mv;
	}
	
	/**
	 * czhi:进入充值页面. <br/>
	 * @author liben
	 * @param openId
	 * @return
	 */
	@RequestMapping(value="/czhi")
	public ModelAndView czhi(HttpServletRequest request){
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "充值");
		try {
			JSONObject netObj = RequestUtil.getJSONObjectByHttpClient(Constant.NETWORKAUTHRECORD, null, openId);
			if(Constant.REQUESTSUCCESS.equals(netObj.getString(Constant.JSONDATATIP))){
				JSONObject netData = netObj.getJSONObject("data");
				//获取充值类型，1 连连支付  2联动优势 3易宝支付
				String rechargeType = netData.getString("rechargeType");
				mv.addObject("type", rechargeType);
				if("1".equals(rechargeType) || "3".equals(rechargeType)){
					JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.QUERYBANKCARDLIST, null, openId);
					if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))){
						JSONObject jsonData = jsonObj.getJSONObject("data");
						JSONObject agreement = jsonData.getJSONArray("agreement_list").getJSONObject(0);
						//将充值信息保存到redis中
						JSONObject czhi = JedisUtil.getJSONObjectFromRedis(openId + Constant.CZHI);
						czhi.put("no_agree", agreement.getString("no_agree"));
						JedisUtil.saveObjectToRedis(openId + Constant.CZHI , czhi , Constant.EXPIRETIME);
						mv.addObject("data", jsonData);
						mv.addObject("card_type", Constant.cardTypes.get(agreement.getString("bank_code")));
					}
				} else if("2".equals(rechargeType)){
					JSONObject ldObj = RequestUtil.getJSONObjectByHttpClient(Constant.QUERYMOBILELDYS, null, openId);
					if(Constant.REQUESTSUCCESS.equals(ldObj.getString(Constant.JSONDATATIP))){
						JSONArray dataArray = ldObj.getJSONArray("data");
						if(!dataArray.isEmpty()){
							JSONObject cardObj = (JSONObject)dataArray.get(0);
							Integer card_type = Constant.LDYSBANKS.get(cardObj.getString("cardtype"));
							mv.addObject("card_type", card_type);
							String cardNo = cardObj.getString("cardno");
							cardObj.put("cardno_end", cardNo.substring(cardNo.length()-4));
							mv.addObject("data", cardObj);
						}
					}
				}
				//加载用户统计信息
				JSONObject statInfo = RequestUtil.getJSONObjectByHttpClient(Constant.LOADSTATINFO, null, openId);
				if(Constant.REQUESTSUCCESS.equals(statInfo.getString(Constant.JSONDATATIP))) {
					mv.addObject("info", statInfo.getJSONObject("data"));
				}
			}
		} catch (Exception e) {
			logger.error("-----czhi---------" + e.getMessage());	
		}
		mv.setViewName("czhi");
		return mv;
	}
	
	/**
	 * tixian:进入提现页面. <br/>
	 * @author liben
	 * @param openId
	 * @param cardId
	 * @return
	 */
	@RequestMapping("/tixian")
	public ModelAndView tixian(HttpServletRequest request , 
			@RequestParam(value="cardId" , required=false) String cardId){
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "提现");
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.INITWITHDRAWPAGE, null, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				JSONObject dataObj = jsonObj.getJSONObject("data");
				JSONObject txObj = null;
				if(Tools.isEmpty(cardId)){
					txObj = dataObj.getJSONObject("personalAccount");
				} else {
					JSONArray personalArray = dataObj.getJSONArray("personalAccountList");
					if(null != personalArray && !personalArray.isEmpty()){
						Iterator it = personalArray.iterator();
						while(it.hasNext()){
							JSONObject accountObj = (JSONObject)it.next();
							if(cardId.equals(accountObj.getString("id"))){
								txObj = accountObj;
								break;
							}
						}
					}
				}
				if(null != txObj) {
					txObj.put("canWithDrawMoney", String.format("%.2f", txObj.getDoubleValue("canWithDrawMoney")));
				}
				mv.addObject("tx_info", txObj);
				mv.addObject("data", dataObj);
			}
			JSONObject loadObj = RequestUtil.getJSONObjectByHttpClient(Constant.LOADSTATINFO, null, openId);
			if(Constant.REQUESTSUCCESS.equals(loadObj.getString(Constant.JSONDATATIP))) {
				JSONObject loadData = loadObj.getJSONObject("data"); 
				String mobile = loadData.getString("mobile");
				if(!Tools.isEmpty(mobile)){
					loadData.put("mobile2", new StringBuffer(mobile.substring(0, 3)).append("****").append(mobile.substring(mobile.length()-4)).toString());
				}
				mv.addObject("info", loadData);
			}
		} catch (Exception e) {
			logger.error("-----tixian---------" + e.getMessage());	
		}
		mv.setViewName("tixian");
		return mv;
	}
	
	@RequestMapping("/tixian_new")
	public ModelAndView tixianNew(HttpServletRequest request , 
			@RequestParam(value="cardId" , required=false) String cardId){
		ModelAndView mv = tixian(request, cardId);
		mv.setViewName("tixian_new");
		return mv;
	}
	
	/**
	 * addCard:进入添加银行卡页面. <br/>
	 * @author liben
	 * @param openId
	 * @param type
	 * @return
	 */
	@RequestMapping("/add_card")
	public ModelAndView addCard(HttpServletRequest request , 
			@RequestParam(value="type" , required=false) String type) {
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "添加银行卡");
		JSONObject auth = this.checkAuth(openId);
		if(null != auth){
			if(!"已".equals(auth.getString("idcard"))) {
				return authenticate(request , "add_card");
			}
		}
		try {
			JSONObject init = RequestUtil.getJSONObjectByHttpClient(Constant.INIT, null, openId);
			if(Constant.REQUESTSUCCESS.equals(init.getString(Constant.JSONDATATIP))) {
				JSONObject dataObj = init.getJSONObject("data");
				JSONObject bankObj = dataObj.getJSONObject("select_bank");
				if(!Tools.isEmpty(type)) {
					String bankName = bankObj.getString(type);
					mv.addObject("bank", bankName);
				}
			}
			JSONObject loadObj = RequestUtil.getJSONObjectByHttpClient(Constant.LOADSTATINFO, null, openId);
			if(Constant.REQUESTSUCCESS.equals(loadObj.getString(Constant.JSONDATATIP))) {
				JSONObject dataObj = loadObj.getJSONObject("data");
				String mobile = dataObj.getString("mobile");
				String coverPhone = mobile.substring(0, 3) + "****" + mobile.substring(mobile.length()-4);
				mv.addObject("info", dataObj);
				mv.addObject("cover_phone", coverPhone);
			}
		} catch (Exception e) {
			logger.error("-----add_card---------" + e.getMessage());	
		}
		JSONObject addCard =JedisUtil.getJSONObjectFromRedis(openId + Constant.ADDCARD);
		mv.addObject("add_card", addCard);
		mv.setViewName("addcard");
		return mv;
	}
	
	@RequestMapping("/card_list")
	public ModelAndView cardList(HttpServletRequest request){
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "选择银行");
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.INIT, null, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				JSONObject bankObj = jsonObj.getJSONObject("data").getJSONObject("select_bank");
				Map<String , String> bankMap = new HashMap<String , String>();
				for(Object bankType : bankObj.keySet().toArray()) {
					bankMap.put((String)bankType, bankObj.getString((String)bankType));
				}
				mv.addObject("bank", bankMap);
			}
		} catch (Exception e) {
			logger.error("-----card_list---------" + e.getMessage());	
		}
		mv.setViewName("cardlist");
		return mv;
	}
	
	@RequestMapping("/province")
	public ModelAndView province(HttpServletRequest request){
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "选择省份");
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.PROVINCE, null, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				Map<String , String> provinceMap = new HashMap<String , String>();
				JSONObject provinceObj = jsonObj.getJSONObject("data");
				for(Object provinceType : provinceObj.keySet().toArray()) {
					provinceMap.put((String)provinceType, provinceObj.getString((String)provinceType));
				}
				mv.addObject("province", Tools.sortMapByValue(provinceMap));
			}
		} catch (Exception e) {
			logger.error("-----province---------" + e.getMessage());	
		}
		mv.setViewName("province");
		return mv;
	}
	
	@RequestMapping("/city")
	public ModelAndView city(HttpServletRequest request , 
			@RequestParam(value="pid") String pid) {
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "选择城市");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("key", pid));
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.CITY, params, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				List<String> cityList = new ArrayList<String>();
				JSONArray cityArr = jsonObj.getJSONArray("data");
				for(int i=0,max=cityArr.size();i<max;i++) {
					cityList.add((String)cityArr.get(i));
				}
				mv.addObject("city", Tools.sortList(cityList));
			}
		} catch (Exception e) {
			logger.error("-----city---------" + e.getMessage());	
		}
		mv.setViewName("city");
		return mv;
	}
	
	@RequestMapping("/check_bank")
	public ModelAndView checkBank(HttpServletRequest request) {
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "选择开户行");
		JSONObject addCard =JedisUtil.getJSONObjectFromRedis(openId + Constant.ADDCARD);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("cityname", addCard.getString("city")));
		params.add(new BasicNameValuePair("pname", addCard.getString("province")));
		params.add(new BasicNameValuePair("type", addCard.getString("type")));
		JSONObject jsonObj;
		try {
			jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.BANKDETAIL, params, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				mv.addObject("bank", jsonObj.getJSONArray("data"));
			}
		} catch (Exception e) {
			logger.error("-----check_bank---------" + e.getMessage());	
		}
		mv.addObject("type", addCard.getString("type"));
		mv.setViewName("checkbank");
		return mv;
	}
	
	@RequestMapping("/check_card")
	public ModelAndView checkCard(HttpServletRequest request){
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "选择银行卡");
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.INITWITHDRAWPAGE, null, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				JSONObject dataObj = jsonObj.getJSONObject("data");
				JSONArray tempArray = dataObj.getJSONArray("personalAccountList");
				JSONArray dataArray = new JSONArray();
				if(tempArray != null && !tempArray.isEmpty()) {
						Iterator it = tempArray.iterator();
						while(it.hasNext()){
							dataArray.add((JSONObject)it.next());
						}
				} 
				mv.addObject("data", dataArray);
			}
		} catch (Exception e) {
			logger.error("-----check_card---------" + e.getMessage());	
		}
		mv.setViewName("checkcard");
		return mv;
	}
	
	@RequestMapping("/bind_card")
	public ModelAndView bindCard(HttpServletRequest request){
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "绑定银行卡");
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.FINDUSERVERIFYINFO, null, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				mv.addObject("data", jsonObj.getJSONObject("data"));
			}
			JSONObject czhi = JedisUtil.getJSONObjectFromRedis(openId + Constant.CZHI);
			JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
			mv.addObject("no_order", czhi.getString("no_order"));
			mv.addObject("money", czhi.getBigDecimal("money"));
			mv.addObject("transtime", DateUtil.getCurrentTime());
			mv.addObject("identityid", userInfo.getString("out_uid"));
			mv.addObject("userip", PublicUtil.getRequestIp(request));//"10.0.16.252"
			mv.addObject("userua", request.getHeader("user-agent"));
			JSONObject netObj = RequestUtil.getJSONObjectByHttpClient(Constant.NETWORKAUTHRECORD, null, openId);
			if(Constant.REQUESTSUCCESS.equals(netObj.getString(Constant.JSONDATATIP))){
				JSONObject netData = netObj.getJSONObject("data");
				//获取充值类型，1 连连支付  2联动优势 3易宝支付
				String czhiUrl = null;
				Integer rechargeType = netData.getInteger("rechargeType");
				mv.addObject("type", rechargeType);
				if(rechargeType == 1){
					czhiUrl = "/index/llpay";
				} else if(rechargeType == 2){
					czhiUrl = "/index/ajax/start_liandong";
				} else if(rechargeType == 3){
					czhiUrl = "/index/ajax/yibao_czhi";
				}
				mv.addObject("czhiUrl", czhiUrl);
			}
		} catch (Exception e) {
			logger.error("-----bind_card---------" + e.getMessage());	
		}
		mv.setViewName("bindcard");
		return mv;
	}
	
	@RequestMapping("/bind_card2")
	public ModelAndView bindCard2(HttpServletRequest request){
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "绑定银行卡");
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.FINDUSERVERIFYINFO, null, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				mv.addObject("data", jsonObj.getJSONObject("data"));
			}
			JSONObject cardObj = RequestUtil.getJSONObjectByHttpClient(Constant.QUERYBANKCARDLIST, null , openId);
			if(Constant.REQUESTSUCCESS.equals(cardObj.getString(Constant.JSONDATATIP))) {
				mv.addObject("bind_card", cardObj.getJSONObject("data"));
			}
			JSONObject czhi = JedisUtil.getJSONObjectFromRedis(openId + Constant.CZHI);
			JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
			mv.addObject("no_order", czhi.getString("no_order"));
			mv.addObject("money", czhi.getBigDecimal("money"));
			mv.addObject("transtime", DateUtil.getCurrentTime());
			mv.addObject("identityid", userInfo.getString("out_uid"));
			mv.addObject("userip", PublicUtil.getRequestIp(request));
			mv.addObject("userua", request.getHeader("user-agent"));
			JSONObject netObj = RequestUtil.getJSONObjectByHttpClient(Constant.NETWORKAUTHRECORD, null, openId);
			if(Constant.REQUESTSUCCESS.equals(netObj.getString(Constant.JSONDATATIP))){
				JSONObject netData = netObj.getJSONObject("data");
				//获取充值类型，1 连连支付  2易宝支付
				mv.addObject("type", netData.get("rechargeType"));
			}
		} catch (Exception e) {
			logger.error("-----bind_card2---------" + e.getMessage());	
		}
		mv.setViewName("bindcard2");
		return mv;
	}
	
	@RequestMapping("/llpay")
	public ModelAndView llpay(HttpServletRequest request , 
			@RequestParam(value="card" , required=false) String card){
		String openId = (String)request.getAttribute("openId");
		ModelAndView mv = this.getModelAndView(request , "连连支付");
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.LOADSTATINFO, null, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				JSONObject czhi =JedisUtil.getJSONObjectFromRedis(openId + Constant.CZHI);
				JSONObject data = jsonObj.getJSONObject("data");
				czhi.put("uid", data.getString("id"));
				czhi.put("realname", data.getString("realname"));
				czhi.put("idcard", data.getString("idcard"));
				JedisUtil.saveObjectToRedis(openId + Constant.CZHI , czhi , Constant.EXPIRETIME);
				PaymentInfo pi = new PaymentInfo();
				pi.setVersion(Constant.LLPAYVERSION);
				pi.setOid_partner(Constant.OIDPARTNER);
				pi.setUser_id(czhi.getString("uid"));
				pi.setSign_type(Constant.SIGNTYPE);
				pi.setBusi_partner(Constant.BUSIPARTNER);
				pi.setNo_order(czhi.getString("no_order"));
				pi.setDt_order(LLPayUtil.getCurrentDateTimeStr());
				pi.setName_goods("");
				pi.setInfo_order("");
				if(!Tools.isEmpty(card)) {
					pi.setCard_no(card);
				}
				pi.setMoney_order(czhi.getString("money"));
				pi.setNotify_url(Constant.BASE_URL + Constant.NOTIFY_URL);
				pi.setUrl_return(Constant.getURLReturn() + "?openId=" + openId);
				pi.setUserreq_ip(LLPayUtil.getIpAddr(request));
				pi.setUrl_order("");
				pi.setValid_order("10080");
				pi.setPay_type("D");
				pi.setTimestamp(LLPayUtil.getCurrentDateTimeStr());
				pi.setRisk_item("");
				pi.setApp_request(Constant.APPREQUEST);
				pi.setNo_agree(czhi.getString("no_agree"));
				pi.setId_no(czhi.getString("idcard"));
				pi.setAcct_name(czhi.getString("realname"));
				pi.setCard_no("");
				String sign = LLPayUtil.addSign(JSON.parseObject(JSON.toJSONString(pi)), Constant.TRADERPRIKEY, Constant.MD5KEY);
				pi.setSign(sign);
				mv.addObject("req_data", JSON.toJSONString(pi));
				mv.setViewName("llpay");
				return mv;
			} 
		} catch (Exception e) {
			logger.error("-----llpay---------" + e.getMessage());	
		}
		return czhi(request);
	}
	
	@RequestMapping("/error")
	public ModelAndView error(HttpServletRequest request , 
			@RequestParam(value="errorcode" , required=false) String errorcode){
		ModelAndView mv = new ModelAndView();
		mv.addObject("static_server",  Constant.getStaticServer());
		if(Tools.isEmpty(errorcode)){
			errorcode = "";
		}
		mv.addObject("returnUrl", Constant.getOPENWEIXINURL());
		mv.addObject("errorMsg", Constant.WECHATERRORS.get(errorcode));
		mv.setViewName("error");
		return mv;
	}
}
