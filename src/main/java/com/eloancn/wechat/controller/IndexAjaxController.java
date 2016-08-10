package com.eloancn.wechat.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eloancn.wechat.common.constant.Constant;
import com.eloancn.wechat.common.utils.DateUtil;
import com.eloancn.wechat.common.utils.JedisUtil;
import com.eloancn.wechat.common.utils.PublicUtil;
import com.eloancn.wechat.common.utils.RequestUtil;
import com.eloancn.wechat.common.utils.Tools;
import com.eloancn.wechat.entities.InfoData;
import com.eloancn.wechat.entities.JSONData;
import com.eloancn.wechat.entities.ObjectData;
import com.eloancn.wechat.model.WechatUser;
import com.eloancn.wechat.model.YeePay;
import com.eloancn.wechat.service.WechatUserService;

/**   
* @ClassName:IndexAjaxController.java 
* @Function 异步数据操作
* @author: liben
* @date 2015年9月25日 上午10:29:11 
*/
@Controller
@RequestMapping(value="/index/ajax")
public class IndexAjaxController extends BaseController {

	@Resource(name = "wechatUserService") 
	private WechatUserService wechatUserService;
	
	@RequestMapping(value="/ycb_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData ycbAjax(@RequestParam("page") String page , HttpServletRequest request) {
		String openId = (String)request.getAttribute("openId");
		JSONData data = null;
		try {
			data = RequestUtil.getJSONData(Constant.LOADWMPSLIST , page , openId);
		} catch (Exception e) {
			logger.error("---openId--" + openId +"--ycb_ajax---------" + e.getMessage());	
		}
		return data;
	}
	
	@RequestMapping(value="/cy_ycb" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData cyYcb(@RequestParam("page") String page, @RequestParam(value="status" , required=false) String status,
			HttpServletRequest request){
		String openId = (String)request.getAttribute("openId");
		JSONData data = null;
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("page", page));
			if(!Tools.isEmpty(status)){
				params.add(new BasicNameValuePair("status", status));
			}
			data = RequestUtil.getJSONData(Constant.CANYUWMPSLIST , params , openId);
		} catch (Exception e) {
			logger.error("---openId--" + openId +"--cy_ycb---------" + e.getMessage());	
		}
		return data;
	}
	
	@RequestMapping(value="/zqlb_ajax",produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData zqlbAjax(@RequestParam("page") String page,
			@RequestParam("id") String id,@RequestParam(value="type" , required=false) String type,
			HttpServletRequest request){
		JSONData data = new ObjectData();
		String openId = (String)request.getAttribute("openId");
		JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
		String userId = userInfo.getString("out_uid");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("uid", userId));
		params.add(new BasicNameValuePair("page", page));
		String url = null;
		if(!Tools.isEmpty(type)){
			url = Constant.LOADTENDERLIST;
		} else {
			url = Constant.LOADTENDERDETAIL;
		}
		data.setStatus(1);
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(url, params, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))){
				data.setInfo(getLenderDetails(jsonObj));
			}
		} catch (Exception e) {
			data.setInfo(null);
			logger.error("---openId--" + openId +"--zqlb_ajax---------" + e.getMessage());	
		}
		return data;
	}
	
	@RequestMapping(value="/hd_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData hdAjax(@RequestParam("page") String page, HttpServletRequest request,
			@RequestParam(value="tabType") String tabType){
		String openId = (String)request.getAttribute("openId");
		String requestUrl = null;
		JSONData data = null;
		if("1".equals(tabType)) {
			requestUrl = Constant.WXREDBAG;
		} else if("2".equals(tabType)) {
			requestUrl = Constant.GETUSERDEDUCTION;
		} else {
			requestUrl = Constant.LISTEXPRIENCES;
		}
		try {
			data = RequestUtil.getJSONData(requestUrl, page, openId);
		} catch (Exception e) {
			logger.error("---openId--" + openId +"--hd_ajax---------" + e.getMessage());	
		}
		return data;
	}
	
	@RequestMapping(value="/my_tb_cy" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData myTbCy(@RequestParam("page") String page , HttpServletRequest request ){
		String openId = (String)request.getAttribute("openId");
		if(Tools.isEmpty(page)){
			page = "1";
		}
		JSONData data = null;
		try {
			data = RequestUtil.getJSONData(Constant.FINDALLLENDINGMONEY, page, openId);
		} catch (Exception e) {
			logger.error("---openId--" + openId +"--my_tb_cy---------" + e.getMessage());	
		}
		return data;
	}
	
	@RequestMapping(value="/my_tb_hx" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData myTbHx(@RequestParam("page") String page , HttpServletRequest request) {
		String openId = (String)request.getAttribute("openId");
		if(Tools.isEmpty(page)){
			page = "1";
		}
		JSONData data = null;
		try {
			data = RequestUtil.getJSONData(Constant.FINDWAITCOLLECTION, page, openId);
		} catch (Exception e) {
			logger.error("---openId--" + openId +"--my_tb_hx---------" + e.getMessage());	
		}
		return data;
	}
	
	@RequestMapping(value="/log_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData logAjax(@RequestParam("page") String page , HttpServletRequest request ,
			@RequestParam(value="startdate" , required=false) String startdate , @RequestParam(value="enddate" , required=false) String enddate ,
			@RequestParam(value="operateType") String operateType) {
		String openId = (String)request.getAttribute("openId");
		if(Tools.isEmpty(page)){
			page = "1";
		}
		String[] operateTypes = new String[]{"1" , "2" , "3" , "4" , "5"};
		if(Arrays.binarySearch(operateTypes, operateType) == -1) {
			operateType = "1";
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("page", page));
		params.add(new BasicNameValuePair("operateType", operateType));
		if(!Tools.isEmpty(startdate)) {
			params.add(new BasicNameValuePair("startdate", startdate));
		}
		if(!Tools.isEmpty(enddate)) {
			params.add(new BasicNameValuePair("enddate", enddate));
		}
		JSONData data = null;
		try {
			data = RequestUtil.getJSONData(Constant.LOADFUNDSINFO, params, openId);
		} catch (Exception e) {
			logger.error("---openId--" + openId +"--log_ajax---------" + e.getMessage());	
		}
		return data;
	}
	
	@RequestMapping(value="/unbind" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData unbind(HttpServletRequest request) {
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.LOGOUT, null, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
				//清除数据库微信user登录信息
				WechatUser wu = new WechatUser();
				wu.setID(userInfo.getIntValue("id"));
				wechatUserService.updateWechatUser(wu);
				//退出登录删除所有redis缓存
				Set<String> keys = Constant.rediskeys.keySet();
    		for(String key : keys){
    			JedisUtil.deleteObjectFromRedis(openId + key);
    		}
				data.setInfo(Constant.LOGGOUTTIP);
				data.setUrl("/index/bind?openId=" + openId);
				data.setStatus(1);
			} else {
				data.setInfo(jsonObj.getString(Constant.JSONDATATIP));
			}
		} catch (Exception e) {
			data.setInfo(Constant.LOGGOUTERROR);
			logger.error("---openId--" + openId +"--unbind---------" + e.getMessage());	
		} 
		return data;
	}
	
	@RequestMapping(value="/find_pwd_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData findPwdAjax(HttpServletRequest request , @RequestParam("phone") String phone ,
			@RequestParam("pwd") String pwd , @RequestParam("pwd1") String pwd1 , 
			@RequestParam("mcode") String code){
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		if(!Tools.checkMobileNumber(phone)) {
			data.setInfo(Constant.VALIDATEPHONEERROR);
			data.setWeizhi(".phoneError");
		} else if(Tools.isEmpty(code)) {
			data.setInfo(Constant.VALIDATECODEERROR);
			data.setWeizhi(".codeError");
		} else if(Tools.isEmpty(pwd)) {
			data.setInfo(Constant.VALIDATEPWDERROR1);
			data.setWeizhi(".passError");
		} else if(!pwd.equals(pwd1)) {
			data.setInfo(Constant.VALIDATEPWDERROR2);
			data.setWeizhi(".passError1");
		} else {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("mobile", phone));
			params.add(new BasicNameValuePair("mobileCode", code));
			params.add(new BasicNameValuePair("findtype", "1"));
			params.add(new BasicNameValuePair("password", pwd));
			params.add(new BasicNameValuePair("confirm_password", pwd1));
			try {
				JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.UPDATEMEMBERPWD, params, openId);
				if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
					data.setStatus(1);
					data.setInfo(Constant.FINDPWDTIP);
					data.setUrl("/html/account/myaccount.html?id=" + openId);
				} else {
					data.setInfo(jsonObj.getString(Constant.JSONDATATIP));
					data.setWeizhi(".passError1");
				}
			} catch (Exception e) {
				logger.error("---openId--" + openId +"--find_pwd_ajax---------" + e.getMessage());	
				data.setInfo(Constant.FINDPWDERROR);
				data.setWeizhi(".passError1");
			}
		}
		return data;
	} 
	
	@RequestMapping(value="/findPayPwd_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData findPayPwdAjax(HttpServletRequest request , @RequestParam("idcard") String idcard , 
			@RequestParam("mcode") String code , @RequestParam("paypwd") String paypwd , 
			@RequestParam("paypwd1") String paypwd1 , @RequestParam("phone") String phone){
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		if(!Tools.checkIdCard(idcard)){
			data.setInfo(Constant.VALIDATEIDCARDERROR);
			data.setWeizhi(".idNumError");
		} else if(Tools.isEmpty(code)) {
			data.setInfo(Constant.VALIDATECODEERROR2);
			data.setWeizhi(".codeError");
		} else if(Tools.isEmpty(paypwd)) {
			data.setInfo(Constant.VALIDATEPAYPWD);
			data.setWeizhi("passError");
		} else if(!paypwd.equals(paypwd1)) {
			data.setInfo(Constant.VALIDATEPWDERROR2);
			data.setWeizhi(".passError1");
		} else if(!Tools.checkMobileNumber(phone)) {
			data.setInfo(Constant.INVALIDOPERATION);
			data.setWeizhi(".passError1");
		} else {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("mobile", phone));
			params.add(new BasicNameValuePair("mobileCode", code));
			params.add(new BasicNameValuePair("idcard", idcard));
			params.add(new BasicNameValuePair("paypassword", paypwd));
			params.add(new BasicNameValuePair("confirm_paypassword", paypwd1));
			try {
				JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.FINDPAYPWD, params, openId);
				if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
					data.setStatus(1);
					data.setInfo(Constant.MODIFYPAYPWDTIP);
					data.setUrl("/html/account/myaccount.html?id=" + openId);
				} else {
					data.setInfo(jsonObj.getString(Constant.JSONDATATIP));
					data.setWeizhi(".passError1");
				}
			} catch (Exception e) {
				logger.error("---openId--" + openId +"--findPayPwd_ajax---------" + e.getMessage());	
				data.setInfo(Constant.MODIFYPAYPWDERROR);
				data.setWeizhi(".passError1");
			}
		}
		return data;
	}
	
	@RequestMapping(value="/edit_pwd" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData editPwd(HttpServletRequest request , @RequestParam("pwd") String pwd,
			@RequestParam("pwd1") String pwd1 , @RequestParam("pwd2") String pwd2){
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		if(Tools.isEmpty(pwd)) {
			data.setInfo(Constant.VALIDATEPWDERROR3);
			data.setWeizhi(".passError2");
		} else if(Tools.isEmpty(pwd1)){
			data.setInfo(Constant.VALIDATEPWDERROR1);
			data.setWeizhi(".passError");
		} else if(!pwd1.equals(pwd2)) {
			data.setInfo(Constant.VALIDATEPWDERROR2);
			data.setWeizhi(".passError1");
		} else {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("oldpassword", pwd));
			params.add(new BasicNameValuePair("newpassword", pwd1));
			try {
				JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.EDITPASSWORD, params, openId);
				if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
					//退出登录删除所有redis缓存
					Set<String> keys = Constant.rediskeys.keySet();
	    		for(String key : keys){
	    			JedisUtil.deleteObjectFromRedis(openId + key);
	    		}
					data.setStatus(1);
					data.setUrl("/index/bind?openId=" + openId);
					data.setInfo(Constant.MODIFYPWDTIP);
				} else {
					data.setInfo(jsonObj.getString(Constant.JSONDATATIP));
					data.setWeizhi(".passError1");
				}
			} catch (Exception e) {
				logger.error("---openId--" + openId +"--edit_pwd---------" + e.getMessage());	
				data.setInfo(Constant.MODIFYPWDERROR);
				data.setWeizhi(".passError1");
			}
		}
		return data;
	}
	
	/*
	 * 检查是否可投资体验期翼存宝
	 * */
	@RequestMapping(value="/check_expirence_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData checkExpirenceAjax(HttpServletRequest request){
		JSONData data = new InfoData();
		String openId = (String)request.getAttribute("openId");
		try{
			//查看是否体验过
			JSONObject tiyanqi = JedisUtil.getJSONObjectFromRedis(openId + Constant.EXPRIENCED);
			if(null != tiyanqi){
				String expirenced = tiyanqi.getString("exprienced");
				if("1".equals(expirenced)){
					data.setInfo(Constant.TIYANQITIP);
				} else {
					data.setStatus(1);
				}
			} else {
				data.setStatus(1);
			}
		} catch(Exception e){
			logger.error("---openId--" + openId +"--check_expirence_ajax---------" + e.getMessage());
			data.setStatus(1);
		}
		return data;
	}
	
	@RequestMapping(value="/invested_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData investedAjax(HttpServletRequest request , @RequestParam("page") String page){
		String openId = (String)request.getAttribute("openId");
		if(Tools.isEmpty(page)) {
			page = "1";
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("page", page));
		JSONData jsonData = null;
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.LOADALLRECORD, params, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
				JSONObject dataObj = jsonObj.getJSONObject("data");
				JSONObject investSumObj = new JSONObject();
				investSumObj.put("total", dataObj.getLongValue("total"));
				investSumObj.put("allUserBuyAmount", dataObj.getString("allUserBuyAmount"));
				investSumObj.put("page", dataObj.getIntValue("page"));
				investSumObj.put("records", dataObj.getLongValue("records"));
				investSumObj.put("totallUserIncomeal", dataObj.getString("allUserIncome"));
				investSumObj.put("allDueingPrincipal", dataObj.getString("allDueingPrincipal"));
				investSumObj.put("rows", dataObj.getIntValue("rows"));
				JSONArray dataArr = dataObj.getJSONArray("data");
				Iterator<Object> it = dataArr.iterator();
				JSONArray data = new JSONArray();
				JSONObject isVip = checkVip(openId);
				while(it.hasNext()){
					JSONObject pro = (JSONObject)it.next();
					data.add(parseInvestProject(pro , isVip, openId));
				}
				investSumObj.put("data", data);
				jsonData = new ObjectData();
				jsonData.setStatus(1);
				jsonData.setInfo(investSumObj);
			} else {
				jsonData = new InfoData();
				jsonData.setInfo(jsonObj.getString(Constant.JSONDATATIP));
			}
		} catch (Exception e) {
			logger.error("---openId--" + openId +"--invested_ajax---------" + e.getMessage());
			jsonData = new InfoData();
			jsonData.setInfo(Constant.DATAERROR);
		}
		return jsonData;
	}
	
	@RequestMapping(value="/auth_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData authAjax(HttpServletRequest request, @RequestParam("idNum") String idNum , 
			@RequestParam("name") String name , @RequestParam("viewName") String viewName){
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		if(Tools.isEmpty(name)) {
			data.setInfo(Constant.VALIDATENAMEERROR);
		} else if(!Tools.checkIdCard(idNum)) {
			data.setInfo(Constant.VALIDATEIDCARDERROR);
		} else {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("idcard", idNum));
			params.add(new BasicNameValuePair("realname", name));
			try {
				JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.IDCARDAUTH, params, openId);
				String tip = jsonObj.getString("tip");
				if(Constant.REQUESTSUCCESS.equals(tip)) {
					//继续判断支付密码是否设置
					data = checkBindAjax(request , viewName); 
					int status = data.getStatus();
					//如果没有问题，则返回操作页面
					if(status == 1){
						data.setInfo(Constant.AUTHTIP);
						data.setUrl("/html/account/myaccount.html?id=" + openId);
					} else {
						String info = (String)data.getInfo();
						if(!Constant.NOTAUTH.equals(info) && status != 2){
							data.setInfo(new StringBuffer(Constant.AUTHTIP).append(",").append(info).toString());
						}
						data.setStatus(1);
					}
				} else {
					data.setInfo(tip);
				}
			} catch (Exception e) {
				logger.error("---openId--" + openId +"--auth_ajax---------" + e.getMessage());	
				data.setInfo(Constant.AUTHERROR);
			}
		}
		return data;
	}
	
	@RequestMapping(value="/setPayPwd_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData setPayPwdAjax(HttpServletRequest request , 
			@RequestParam("login_pwd") String pwd , @RequestParam("viewName") String viewName,
			@RequestParam("pay_pwd") String payPwd , @RequestParam("pay_pwd1") String payPwd1){
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		if(Tools.isEmpty(pwd)) {
			data.setInfo(Constant.VALIDATELOGINPWD);
			data.setWeizhi(".passError");
		} else if(Tools.isEmpty(payPwd)) {
			data.setInfo(Constant.VALIDATEPAYPWD);
			data.setWeizhi(".passError1");
		} else if(!payPwd.equals(payPwd1)) {
			data.setInfo(Constant.VALIDATEPWDERROR2);
			data.setWeizhi(".passError2");
		} else {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("paypassword", payPwd));
			params.add(new BasicNameValuePair("password", pwd));
			try {
				JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.SETPAYPASSWORD, params, openId);
				if(Constant.REQUESTSUCCESS.equals(jsonObj.getString("tip"))) {
					data.setInfo(Constant.SETPAYPWDTIP);
					data.setUrl("/html/account/myaccount.html?id=" + openId);
					data.setStatus(1);
				} else {
					data.setWeizhi(".passError");
					data.setInfo(jsonObj.getString(Constant.JSONDATATIP));
				}
			} catch (Exception e) {
				logger.error("---openId--" + openId +"--setPayPwd_ajax---------" + e.getMessage());	
				data.setInfo(Constant.SETPAYPWDERROR);
			}
		}
		return data;
	}
	
	@RequestMapping(value = "/get_code" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData getCode(@RequestParam("phone") String phone,
			@RequestParam("verification_code") String verificationCode , 
			HttpServletRequest request) {
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		if(!Tools.checkMobileNumber(phone)) {
			data.setInfo(Constant.VALIDATEPHONEERROR);
			data.setWeizhi(".phoneError");
		} else {
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("mobile", phone));
				JSONObject checkMobile = RequestUtil.getJSONObjectByHttpClient(Constant.CHECKMOBILE, params, openId);
				String tip = checkMobile.getString(Constant.JSONDATATIP);
				if(!Constant.REQUESTSUCCESS.equals(tip)){
					params.add(new BasicNameValuePair("mobile_code_type", "1"));
					params.add(new BasicNameValuePair("verification_code", verificationCode));
					JSONObject jsonObj = RequestUtil.getJSONDataByHttpClient(Constant.CATCHREGCODE, params, openId);
					if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
						data.setStatus(1);
						data.setInfo(Constant.CODESENDSUCCESS);
						data.setWeizhi(".codeError");
					} else {
						data.setInfo(Constant.IMAGECODEERROR);
						data.setWeizhi(".verificationCodeError");
					}
				} else {
					data.setInfo(Constant.VALIDATEPHONEERROR);
					data.setWeizhi(".phoneError");
				}
			} catch (Exception e) {
				logger.error("---openId--" + openId +"--get_code---------" + e.getMessage());	
				data.setInfo(Constant.SENDCODEERROR);
			}
		}
		return data;
	}
	
	@RequestMapping(value="/upPayPwd_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData upPayPwdAjax(HttpServletRequest request , @RequestParam("pwd") String pwd,
			@RequestParam("mcode") String code , @RequestParam("paypwd") String paypwd ,
			@RequestParam("paypwd1") String paypwd1) {
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		if(Tools.isEmpty(pwd)) {
			data.setInfo(Constant.VALIDATEPAYPWDERROR1);
			data.setWeizhi(".passError2");
		} else if(Tools.isEmpty(code)) {
			data.setInfo(Constant.VALIDATECODEERROR);
			data.setWeizhi(".codeError");
		} else if(Tools.isEmpty(paypwd)) {
			data.setInfo(Constant.VALIDATEPAYPWDERROR);
			data.setWeizhi(".passError");
		} else if(!paypwd.equals(paypwd1)) {
			data.setInfo(Constant.VALIDATEPWDERROR2);
			data.setWeizhi(".passError1");
		} else {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("oldpaypassword", pwd));
			params.add(new BasicNameValuePair("newpaypassword", paypwd));
			params.add(new BasicNameValuePair("checkCode", code));
			try {
				JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.EDITPAYPASSWORD, params, openId);
				if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))){
					data.setInfo(Constant.MODIFYPAYPWDTIP);
					data.setUrl("/html/account/myaccount.html?id=" + openId);
					data.setStatus(1);
				} else {
					data.setInfo(jsonObj.getString(Constant.JSONDATATIP));
					data.setWeizhi(".passError1");
				}
			} catch (Exception e) {
				logger.error("---openId--" + openId +"--upPayPwd_ajax---------" + e.getMessage());	
				data.setInfo(Constant.MODIFYPAYPWDERROR);
			}
			
		}
		return data;
	}
	
	@RequestMapping(value="/detail_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData detailAjax(HttpServletRequest request , @RequestParam("id") String id,
			@RequestParam("page") String page){
		String openId = (String)request.getAttribute("openId");
		if(Tools.isEmpty(page)){
			page = "2";
		}
		JSONData data = null;
		//获取投资产品详情
		JSONObject detail = getDetail(openId, id, page);
		if(null == detail.get("status")) {
			data = new InfoData();
			data.setInfo(detail.getString("tip"));
		} else {
			data = new ObjectData();
			data.setInfo(detail);
			data.setStatus(1);
		}
		return data;
	}
	
	@RequestMapping(value="/check_bind_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData checkBindAjax(HttpServletRequest request , @RequestParam(value = "viewName" , required = false) String viewName){
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		if(Tools.isEmpty(viewName)) {
			viewName = "invested";
		}
		try {
			//验证是否绑定身份证
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.NETWORKAUTHRECORD, null , openId);
			String tip = jsonObj.getString(Constant.JSONDATATIP);
			if(Constant.REQUESTSUCCESS.equals(tip)) {
				JSONObject dataObj = jsonObj.getJSONObject("data");
				if(Constant.NOT.equals(dataObj.getString("idcard"))) {
					String time = dataObj.getString("idCardAuthCount");
					//认证次数已经结束
					if("0".equals(time)){
						data.setInfo(Constant.AUTHTIMEOUT);
						data.setStatus(2);
					} else {
						data.setInfo(Constant.NOTAUTH);
						data.setUrl("/index/authenticate?openId=" + openId + "&viewName=" + viewName);
					}
				} else if(Constant.NOT.equals(dataObj.getString("paypassword"))) {
					data.setInfo(Constant.NOTSETPAYPASSWORD);
					data.setUrl("/index/set_pay_pwd?openId=" + openId + "&viewName=" + viewName);
				} else {
					data.setStatus(1);
				}
			} else if(Constant.NOTSUCSCRIBEELOANCN.equals(tip)) {
				data.setInfo(Constant.LOGGEDIN);
				data.setUrl("/index/bind?openId=" + openId + "&viewName=" + viewName);
			}	else if(Constant.NOTLOGGEDIN.equals(tip)) {
				data.setInfo(Constant.LOGGEDIN);
				data.setUrl("/index/bind?openId=" + openId + "&viewName=" + viewName);
			} else {
				data.setInfo(tip);
				data.setUrl("/index/bind?openId=" + openId);
			}
		} catch (Exception e) {
			logger.error("---openId--" + openId +"--check_bind_ajax---------" + e.getMessage());
			data.setInfo(Constant.DATAERROR);
		}
		return data;
	}
	
	@RequestMapping(value="/calculated_interest" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData calculatedInterest(HttpServletRequest request , 
			@RequestParam("amount") String amount,
			@RequestParam("contractDays") String contractDays, 
			@RequestParam("interestRate") String interestRate){
		String openId = (String)request.getAttribute("openId");
		if(Tools.isEmpty(contractDays)) {
			contractDays = "0";
		} else {
			contractDays = String.valueOf(Integer.parseInt(contractDays));
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("investAmount", amount));
		params.add(new BasicNameValuePair("contractDays", contractDays));
		params.add(new BasicNameValuePair("interestRate", interestRate));
		JSONData data = new InfoData();
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.PROCEEDSCULATOR, params, openId);
			data.setStatus(1);
			data.setWeizhi("#shouyi");
			data.setInfo(jsonObj.getString("data"));
		} catch (Exception e) {
			logger.error("---openId--" + openId +"--calculated_interest---------" + e.getMessage());
		}
		return data;
	}
	
	@RequestMapping(value="/touzi_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData touziAjax(HttpServletRequest request , @RequestParam("wid") String wid , 
			@RequestParam("amount") String amount , 
			@RequestParam(value="type" , required=false) String type){
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		data = checkBindAjax(request, null);
		try {
			if(data.getStatus() == 1){
			//判断金额是否合法
				if(null == amount || "".equals(amount)){
					data.setInfo(Constant.VALIDATEAMOUNTERROR);
					data.setStatus(0);
				} else {
					double amountValue = Double.parseDouble(amount);
					if(amountValue < 100 || amountValue % 100 != 0){
						data.setInfo(Constant.VALIDATEAMOUNTERROR);
						data.setStatus(0);
					} else {
						//判断投资项目ID是否合法
						if(Tools.isEmpty(wid)) {
							data.setInfo(Constant.INVALIDOPERATION);
							data.setStatus(0);
						} else {
							int widValue = Integer.parseInt(wid);
							if(widValue < 0){
								data.setInfo(Constant.INVALIDOPERATION);
								data.setStatus(0);
							} else {
								JSONObject tokenObj = RequestUtil.getJSONDataByHttpClient(Constant.TOKEN, null, openId);
								List<NameValuePair> params = new ArrayList<NameValuePair>();
								params.add(new BasicNameValuePair("wid", wid));
								params.add(new BasicNameValuePair("amount", amount));
								params.add(new BasicNameValuePair("token", tokenObj.getString("data")));
								JSONObject tiyanqi = new JSONObject();
								JSONObject addObj = null;
								if(Tools.isEmpty(type)){
									addObj = RequestUtil.getJSONObjectByHttpClient(Constant.ADDWMPSBUYRECORD, params, openId);
								} else {
									addObj = RequestUtil.getJSONObjectByHttpClient(Constant.ADDWMPSEXPRIENCEBUYRECORD, params, openId);
									tiyanqi.put("exprienced", "1");
								}
								if(Constant.REQUESTSUCCESS.equals(addObj.getString(Constant.JSONDATATIP))) {
									data.setInfo(Constant.INVESTTIP);
									data.setStatus(1);
									String url = addObj.getJSONObject("data").getString("winningUrl");
									if(!Tools.isEmpty(url)){
										data.setUrl(url);
									} else {
										data.setUrl(Constant.getOPENWEIXINURL());
									}
									JedisUtil.saveObjectToRedis(openId + Constant.EXPRIENCED, tiyanqi, Constant.EXPIRETIME);
								} else {
									data.setInfo(addObj.getString(Constant.JSONDATATIP));
									data.setStatus(0);
								}
							}
						}
					}
				}
			} else {
				return data;
			}
		} catch (Exception e) {
			logger.error("---openId--" + openId +"--touzi_ajax---------" + e.getMessage());	
			data.setInfo(Constant.INVESTERROR);
			data.setStatus(0);
		}
		return data;
	}
	
	@RequestMapping(value="/auth_mobile_code" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData authMobileCode(HttpServletRequest request ,@RequestParam("phone") String phone){
		String openId = (String)request.getAttribute("openId");
		JSONData data = null;
		//检查手机号是否符合格式
		if(!Tools.checkMobileNumber(phone)){
			data = new InfoData();
			data.setInfo(Constant.VALIDATEPHONEERROR);
			data.setWeizhi(".phoneError");
		} else {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("mobile", phone));
			try {
				//发送验证码
				JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.GAINCODEMOBILEAUTH, params, openId);
				if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))){
					data = new ObjectData();
					data.setStatus(1);
					data.setWeizhi(".codeError");
					data.setInfo(jsonObj.getJSONObject("data"));
				} else {
					data = new InfoData();
					data.setInfo(jsonObj.getString(Constant.JSONDATATIP));
					data.setWeizhi(".phoneError");
				}
			} catch (Exception e) {
				logger.error("---openId--" + openId +"--auth_mobile_code---------" + e.getMessage());	
				data = new InfoData();
				data.setInfo(Constant.SENDCODEERROR);
			}
		}
		return data;
	}
	@RequestMapping(value="/auth_mobile" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData authCode(HttpServletRequest request ,
			@RequestParam("phone") String phone,
			@RequestParam("mcode") String code){
		String openId = (String)request.getAttribute("openId");
		JSONData data = null;
		if(!Tools.checkMobileNumber(phone)){
			data = new InfoData();
			data.setInfo(Constant.VALIDATEPHONEERROR);
			data.setWeizhi(".phoneError");
		} else if(Tools.isEmpty(code)){
			data = new InfoData();
			data.setInfo(Constant.VALIDATECODEERROR);
			data.setWeizhi(".codeError");
		} else {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("mobile", phone));
			params.add(new BasicNameValuePair("mobilecode", code));
			try {
				JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.SAVEMOBILEAUTH, params, openId);
				if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))){
					data = new ObjectData();
					data.setStatus(1);
					data.setInfo(jsonObj.getJSONObject("data"));
					data.setUrl("/html/account/myaccount.html?id=" + openId);
				}	else {
					data = new InfoData();
					data.setInfo(jsonObj.getString(Constant.JSONDATATIP));
				}
			} catch (Exception e) {
				logger.error("---openId--" + openId +"--auth_mobile---------" + e.getMessage());	
			}
			data.setWeizhi(".codeError");
		}
		return data;
	}
	
	@RequestMapping(value="/save_info" , produces="application/json;charset=utf-8")
	public @ResponseBody String saveInfo(HttpServletRequest request , 
			@RequestParam("name") String name ,
			@RequestParam("card") String card , 
			@RequestParam("mcode") String code){
		String openId = (String)request.getAttribute("openId");
		JSONObject addCard = JedisUtil.getJSONObjectFromRedis(openId + Constant.ADDCARD);
		addCard.put("name", name);
		addCard.put("card", card);
		addCard.put("code", code);
		JedisUtil.saveObjectToRedis(openId + Constant.ADDCARD, addCard , Constant.EXPIRETIME);
		return "{\"code\":\"1\"}";
	}
	
	@RequestMapping(value="/check_session_bank" , produces="application/json;charset=utf-8")
	public @ResponseBody String checkSessionBank(@RequestParam(value="openId") String openId){
		JSONObject addCard = JedisUtil.getJSONObjectFromRedis(openId + Constant.ADDCARD);
		int code = 0;
		if(!Tools.isEmpty(addCard.getString("type"))) {
			code = 1;
		}
		return "{\"code\":" + code + "}";
	}
	
	@RequestMapping(value="/save_card" , produces="application/json;charset=utf-8")
	public @ResponseBody String saveCard(HttpServletRequest request , @RequestParam("type") String type) {
		String openId = (String)request.getAttribute("openId");
		//临时保存银行卡类型
		JSONObject addCard = JedisUtil.getJSONObjectFromRedis(openId + Constant.ADDCARD);
		String session_type = addCard.getString("type");
		//如果银行卡类型更改，清空银行卡号
		if(Tools.notEmpty(session_type) && !type.equals(session_type)) {
			addCard.put("card", "");
		}
		if((!Tools.isEmpty(session_type)) && !type.equals(session_type)) {
			addCard.put("bank", null);
		}
		addCard.put("type", type);
		JedisUtil.saveObjectToRedis(openId + Constant.ADDCARD, addCard , Constant.EXPIRETIME);
		return "{\"code\":\"1\"}";
	}
	
	@RequestMapping(value="/save_province" , produces="application/json;charset=utf-8")
	public @ResponseBody String saveProvince(HttpServletRequest request, @RequestParam("province") String province){
		String openId = (String)request.getAttribute("openId");
		JSONObject addCard = JedisUtil.getJSONObjectFromRedis(openId + Constant.ADDCARD);
		String session_province = addCard.getString("province");
		//如果省份更改，清空银行卡号
		if(Tools.notEmpty(session_province) && !province.equals(session_province)) {
			addCard.put("card", "");
		}
		addCard.put("province", province);
		JedisUtil.saveObjectToRedis(openId + Constant.ADDCARD, addCard , Constant.EXPIRETIME);
		return "{\"code\":\"1\"}";
	}
	
	@RequestMapping(value="/save_city" , produces="application/json;charset=utf-8")
	public @ResponseBody String saveCity(HttpServletRequest request , @RequestParam("city") String city){
		String openId = (String)request.getAttribute("openId");
		JSONObject addCard = JedisUtil.getJSONObjectFromRedis(openId + Constant.ADDCARD);
		String session_city = addCard.getString("city");
		//如果城市更改，清空银行卡号
		if(Tools.notEmpty(session_city) && !city.equals(session_city)) {
			addCard.put("card", "");
		}
		addCard.put("city", city);
		JedisUtil.saveObjectToRedis(openId + Constant.ADDCARD, addCard , Constant.EXPIRETIME);
		return "{\"code\":\"1\"}";
	}
	
	@RequestMapping(value="/save_bank" , produces="application/json;charset=utf-8")
	public @ResponseBody String saveBank(HttpServletRequest request, @RequestParam("bank") String bank) {
		String openId = (String)request.getAttribute("openId");
		JSONObject addCard = JedisUtil.getJSONObjectFromRedis(openId + Constant.ADDCARD);
		String session_bank = addCard.getString("bank");
		//如果银行更改，清空银行卡号
		if(Tools.notEmpty(session_bank) && !bank.equals(session_bank)) {
			addCard.put("card", "");
		}
		addCard.put("bank", bank);
		JedisUtil.saveObjectToRedis(openId + Constant.ADDCARD, addCard , Constant.EXPIRETIME);
		return "{\"code\":\"1\"}";
	}
	
	@RequestMapping(value="/add_card_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData addCardAjax(HttpServletRequest request , 
			@RequestParam("card") String card,
			@RequestParam("mcode") String code) {
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		if(!Tools.checkBankNo(card)) {
			data.setWeizhi(".cardError1");
			data.setInfo(Constant.VALIDATEADDCARDERROR);
		} else if(Tools.isEmpty(code)){
			data.setWeizhi(".codeError");
			data.setInfo(Constant.VALIDATECODEERROR2);
		} else {
			JSONObject addCard = JedisUtil.getJSONObjectFromRedis(openId + Constant.ADDCARD);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("bankname", addCard.getString("bank")));
			params.add(new BasicNameValuePair("account", card));
			params.add(new BasicNameValuePair("checkCode", code));
			params.add(new BasicNameValuePair("city", addCard.getString("city")));
			params.add(new BasicNameValuePair("province", addCard.getString("province")));
			params.add(new BasicNameValuePair("type", addCard.getString("type")));
			//设置需要检查验证码标志
			params.add(new BasicNameValuePair("needCheckCode", "1"));
			JSONObject jsonObj = null;
			try {
				jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.CREATEPACCOUNT, params, openId);
				if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
					//添加银行卡成功，清除添加银行卡的缓存
					JedisUtil.deleteObjectFromRedis(openId + Constant.ADDCARD);
					data.setInfo(Constant.ADDCARDTIP);
					data.setStatus(1);
					data.setUrl("/index/tixian?openId=" + openId);
				} else {
					data.setInfo(jsonObj.getString(Constant.JSONDATATIP));
					data.setWeizhi(".codeError");
				}
			} catch (Exception e) {
				logger.error("---openId--" + openId +"--add_card_ajax---------" + e.getMessage());
				data.setInfo(Constant.ADDCARDERROR);
				data.setWeizhi(".codeError");
			}
		}
		return data;
	}
	
	@RequestMapping(value = "/unbind_card_ld" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData unbindCardLd(HttpServletRequest request,
			@RequestParam("id") String id, @RequestParam("uid") String uid,
			@RequestParam("cardno") String cardno, @RequestParam("orderid") String orderid){
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id",id));
		params.add(new BasicNameValuePair("uid",uid));
		params.add(new BasicNameValuePair("cardno",cardno));
		params.add(new BasicNameValuePair("order_id",orderid));
		try {
			JSONObject umPayObj = RequestUtil.getJSONObjectByHttpClient(Constant.UMPAYBANKCARDUNBIND, params, openId);
			if(Constant.REQUESTSUCCESS.equals(umPayObj.getString(Constant.JSONDATATIP))){
				data.setStatus(1);
				data.setInfo(Constant.UNBINDCARDTIP);
				data.setUrl("/index/czhi?openId=" + openId);
			} else {
				data.setInfo(umPayObj.getString(Constant.JSONDATATIP));
			}
		} catch (Exception e) {
			data.setInfo(Constant.UNBINDCARDERROR);
			logger.error("---openId--" + openId +"--unbind_card_ld---------" + e.getMessage());
		}
		return data;
	}
	
	@RequestMapping(value = "/unbind_llpay" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData unbindLLPAY(HttpServletRequest request , 
			@RequestParam("no_agree") String no_agree){
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("no_agree", no_agree));
		try {
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.BANKCARDUNBIND, params, openId);
			if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))){
				data.setStatus(1);
				data.setInfo(Constant.UNBINDCARDTIP);
			} else{
				data.setInfo(jsonObj.getString(Constant.JSONDATATIP));
			}
		} catch (Exception e) {
			logger.error("---openId--" + openId +"--unbind_llpay---------" + e.getMessage());	
			data.setInfo(Constant.UNBINDCARDERROR);
		}
		return data;
	}
	
	@RequestMapping(value = "/save_czhi" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData saveCzhi(HttpServletRequest request , 
			@RequestParam("money") String money) {
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		try {
			//验证是否绑定身份证
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.NETWORKAUTHRECORD, null , openId);
			String tip = jsonObj.getString(Constant.JSONDATATIP);
			if(!Constant.REQUESTSUCCESS.equals(tip)) {
				data.setInfo(tip);
				data.setWeizhi(".investError");
			} else if(Constant.NOT.equals(jsonObj.getJSONObject("data").getString("idcard"))) {
				data.setInfo(Constant.NOTAUTH);
				data.setStatus(2);
				data.setUrl("/index/authenticate?openId=" + openId);
			} else if(Constant.NOT.equals(jsonObj.getJSONObject("data").getString("paypassword"))) {
				data.setInfo(Constant.NOTSETPAYPASSWORD);
				data.setStatus(2);
				data.setUrl("/index/set_pay_pwd?openId=" + openId + "&viewName=czhi");
			} else {
				double m = Double.valueOf(money);
				if(m <= 0){
					data.setWeizhi(".cardError1");
					data.setInfo(Constant.VALIDATEAMOUNTERROR);
				} else {
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("tranAmt", String.valueOf(money)));
					JSONObject tokenObj = RequestUtil.getJSONDataByHttpClient(Constant.TOKEN, null, openId);
					params.add(new BasicNameValuePair("token", tokenObj.getString("data")));
					JSONObject orderObj = RequestUtil.getJSONObjectByHttpClient(Constant.QUERYORDER, params, openId);
					if(Constant.REQUESTSUCCESS.equals(orderObj.getString(Constant.JSONDATATIP))) {
						JSONObject dataObj = orderObj.getJSONObject("data");
						JSONObject czhi = JedisUtil.getJSONObjectFromRedis(openId + Constant.CZHI);
						czhi.put("dt_order", dataObj.getString("dt_order"));
						czhi.put("no_order", dataObj.getString("no_order"));
						czhi.put("money", money);
						JedisUtil.saveObjectToRedis(openId + Constant.CZHI, czhi , Constant.EXPIRETIME);
						data.setUrl("/index/bind_card?openId=" + openId);
						data.setStatus(1);
					} else {
						data.setInfo(orderObj.getString(Constant.JSONDATATIP));
					}
				}
			}
		} catch (Exception e) {
			data.setInfo(Constant.CZERROR);
			logger.error("---openId--" + openId +"--save_czhi---------" + e.getMessage());	
		}
		
		return data;
	}
	
	@RequestMapping(value = "/save_czhi2" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData saveCzhi2(HttpServletRequest request , 
			@RequestParam("no_agree") String noAgree ,
			@RequestParam("money") String money,
			@RequestParam(value="cardtype") String cardtype,
			@RequestParam(value="bankname") String bankname,
			@RequestParam(value="cardno") String cardno){
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		try {
			//验证是否绑定身份证
			JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.NETWORKAUTHRECORD, null , openId);
			String tip = jsonObj.getString(Constant.JSONDATATIP);
			if(!Constant.REQUESTSUCCESS.equals(tip)) {
				data.setInfo(tip);
				data.setWeizhi(".investError");
			} else if(Constant.NOT.equals(jsonObj.getJSONObject("data").getString("idcard"))) {
				data.setInfo(Constant.NOTAUTH);
				data.setStatus(2);
				data.setUrl("/index/authenticate?openId=" + openId);
			} else if(Constant.NOT.equals(jsonObj.getJSONObject("data").getString("paypassword"))) {
				data.setInfo(Constant.NOTSETPAYPASSWORD);
				data.setStatus(2);
				data.setUrl("/index/set_pay_pwd?openId=" + openId + "&viewName=czhi");
			} else {
				if(Double.valueOf(money) <= 0){
					data.setInfo(Constant.VALIDATEAMOUNTERROR);
					data.setWeizhi(".cardError1");
				} else {
					//获取充值类型，1 连连支付  2联动优势 3易宝支付
					JSONObject netData = jsonObj.getJSONObject("data");
					String rechargeType = netData.getString("rechargeType");
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("tranAmt", money));
					//获取充值token
					JSONObject tokenObj = RequestUtil.getJSONDataByHttpClient(Constant.TOKEN, null, openId);
					params.add(new BasicNameValuePair("token", tokenObj.getString("data")));
					//充值订单生成
					if("2".equals(rechargeType)){//联动优势
						params.add(new BasicNameValuePair("cardno",cardno));
						params.add(new BasicNameValuePair("cardtype",cardtype));
					}
					JSONObject orderObj = RequestUtil.getJSONObjectByHttpClient(Constant.QUERYORDER, params, openId);
					if(Constant.REQUESTSUCCESS.equals(orderObj.getString(Constant.JSONDATATIP))) {
						JSONObject orderData = orderObj.getJSONObject("data");
						JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
						JSONObject verifyInfo = RequestUtil.getJSONObjectByHttpClient(Constant.FINDUSERVERIFYINFO, null, openId);
						JSONObject verifyData = verifyInfo.getJSONObject("data");
						if("1".equals(rechargeType)){
							JSONObject czhi =JedisUtil.getJSONObjectFromRedis(openId + Constant.CZHI);
							czhi.put("dt_order", orderData.getString("dt_order"));
							czhi.put("no_order", orderData.getString("no_order"));
							czhi.put("money", money);
							JedisUtil.saveObjectToRedis(openId + Constant.CZHI, czhi , Constant.EXPIRETIME);
							data.setUrl("/index/llpay?openId=" + openId);
						} else if("3".equals(rechargeType)){//易宝充值
							params.clear();
							BigDecimal d = new BigDecimal(money).multiply(new BigDecimal(100));
							params.add(new BasicNameValuePair("amount",d.setScale(0, BigDecimal.ROUND_DOWN).toString()));
							params.add(new BasicNameValuePair("orderid",orderData.getString("no_order")));
							params.add(new BasicNameValuePair("transtime",String.valueOf(DateUtil.getCurrentTime())));
							params.add(new BasicNameValuePair("currency","156"));
							params.add(new BasicNameValuePair("productcatalog","30"));
							params.add(new BasicNameValuePair("productname","翼龙贷充值"));
							params.add(new BasicNameValuePair("productdesc","翼龙贷充值"));
							params.add(new BasicNameValuePair("identitytype","3"));
							params.add(new BasicNameValuePair("identityid",userInfo.getString("out_uid")));
							params.add(new BasicNameValuePair("terminaltype","0"));
							params.add(new BasicNameValuePair("terminalid","44-45-53-54-00-00"));
							params.add(new BasicNameValuePair("userip",PublicUtil.getRequestIp(request)));
							params.add(new BasicNameValuePair("userua",request.getHeader("user-agent")));
							params.add(new BasicNameValuePair("paytypes",""));
							params.add(new BasicNameValuePair("orderexpdate","60"));
							params.add(new BasicNameValuePair("cardno",cardno));
							params.add(new BasicNameValuePair("idcardtype","01"));
							params.add(new BasicNameValuePair("idcard",verifyData.getString("idcard")));
							params.add(new BasicNameValuePair("owner",verifyData.getString("username")));
							JSONObject result = RequestUtil.getJSONObjectByHttpClient(Constant.YEEPAY, params, openId);
							if(Constant.REQUESTSUCCESS.equals(result.getString("tip"))){
								data.setUrl(result.getString("data"));
								data.setInfo("U");
								data.setStatus(1);
							} else {
								data.setInfo(result.getString(Constant.JSONDATATIP));
								data.setWeizhi(".cardError1");
							}
						} else if("2".equals(rechargeType)){
							params.clear();
							params.add(new BasicNameValuePair("order_id",orderData.getString("no_order")));
							BigDecimal d = new BigDecimal(money).multiply(new BigDecimal(100));
							params.add(new BasicNameValuePair("amount",d.setScale(0, BigDecimal.ROUND_DOWN).toString()));
							params.add(new BasicNameValuePair("amt_type",Constant.AMTTYPE));
							params.add(new BasicNameValuePair("mer_priv",""));
							params.add(new BasicNameValuePair("expire_time",""));
							params.add(new BasicNameValuePair("risk_expand",""));
							params.add(new BasicNameValuePair("goods_id",""));
							JSONObject umpayObj = RequestUtil.getJSONObjectByHttpClient(Constant.UMPAYGETORDERID, params, openId);
							if(Constant.REQUESTSUCCESS.equals(umpayObj.getString(Constant.JSONDATATIP))){
								String tradeNo = umpayObj.getString("data");
								params.clear();
								params.add(new BasicNameValuePair("cardId",cardno));
								params.add(new BasicNameValuePair("tradeNo",tradeNo));
								params.add(new BasicNameValuePair("merCustId",userInfo.getString("out_uid")));
								params.add(new BasicNameValuePair("identityType","IDENTITY_CARD"));
								params.add(new BasicNameValuePair("identityCode",verifyData.getString("idcard")));
								params.add(new BasicNameValuePair("cardHolder",verifyData.getString("username")));
								params.add(new BasicNameValuePair("payType","DEBITCARD"));
								params.add(new BasicNameValuePair("gateId",cardtype));
								params.add(new BasicNameValuePair("canModifyFlag","0"));
								params.add(new BasicNameValuePair("mobileId",""));
								JSONObject toPayObj = RequestUtil.getJSONObjectByHttpClient(Constant.UMPAYORDERTOPAY, params, openId);
								if(Constant.REQUESTSUCCESS.equals(toPayObj.getString(Constant.JSONDATATIP))){
									data.setStatus(1);
									data.setInfo("U");
									data.setUrl(toPayObj.getString("data"));
								} else {
									data.setWeizhi(".cardError1");
									data.setInfo(toPayObj.getString(Constant.JSONDATATIP));
								}
							} else {
								data.setInfo(umpayObj.getString(Constant.JSONDATATIP));
								data.setWeizhi(".cardError1");
							}
						} else {
							data.setStatus(1);
							data.setUrl("/index/czhi?openId=" + openId);
						}
					} else {
						data.setWeizhi(".cardError1");
						data.setInfo(orderObj.getString(Constant.JSONDATATIP));
					}
				}
			}
		} catch (Exception e) {
			data.setInfo(Constant.CZERROR);
			logger.error("---openId--" + openId +"--save_czhi2---------" + e.getMessage());	
		}
		
		return data;
	}
	
	@RequestMapping(value = "/tixian_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData tixinAjax(HttpServletRequest request ,
			@RequestParam("money") String money , 
			@RequestParam("paypassword") String paypassword , 
			@RequestParam("bankname") String bankname ,
			@RequestParam(value="province",required=false) String province , 
			@RequestParam(value="city",required=false) String city , 
			@RequestParam(value="randCode",required=false) String randCode,
			@RequestParam(value="mobile",required=false) String mobile,
			@RequestParam("personAccountId") String personAccountId,
			@RequestParam("feiy") String feiy,
			@RequestParam("sjdz") String sjdz,
			@RequestParam("txje") String txje){
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
			try {
				//验证是否绑定身份证
				JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.NETWORKAUTHRECORD, null , openId);
				String tip = jsonObj.getString(Constant.JSONDATATIP);
				if(!Constant.REQUESTSUCCESS.equals(tip)) {
					data.setInfo(tip);
					data.setWeizhi(".investError");
				} else if(Constant.NOT.equals(jsonObj.getJSONObject("data").getString("idcard"))) {
					data.setInfo(Constant.NOTAUTH);
					data.setStatus(2);
					data.setUrl("/index/authenticate?openId=" + openId);
				} else if(Constant.NOT.equals(jsonObj.getJSONObject("data").getString("paypassword"))) {
					data.setInfo(Constant.NOTSETPAYPASSWORD);
					data.setStatus(2);
					data.setUrl("/index/set_pay_pwd?openId=" + openId + "&viewName=tixian");
				} else {
					JSONObject tokenObj = RequestUtil.getJSONDataByHttpClient(Constant.TOKEN, null, openId);
					JSONObject loadObj = RequestUtil.getJSONObjectByHttpClient(Constant.LOADSTATINFO, null, openId);
					if(Constant.REQUESTSUCCESS.equals(loadObj.getString(Constant.JSONDATATIP))) {
						JSONObject dataObj = loadObj.getJSONObject("data");
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("money", money));
						params.add(new BasicNameValuePair("paypassword", paypassword));
						params.add(new BasicNameValuePair("bankname", bankname));
						params.add(new BasicNameValuePair("province", province));
						params.add(new BasicNameValuePair("city", city));
						params.add(new BasicNameValuePair("balance", dataObj.getString("balance")));
						params.add(new BasicNameValuePair("personAccountId", personAccountId));
						params.add(new BasicNameValuePair("token", tokenObj.getString("data")));
						params.add(new BasicNameValuePair("mobile", mobile));
						if(!Tools.isEmpty(randCode)){
		      		params.add(new BasicNameValuePair("verification_code", randCode));
		    		} else {
		    			params.add(new BasicNameValuePair("verification_code", ""));
		    		}
						JSONObject askObj = RequestUtil.getJSONObjectByHttpClient(Constant.ASKFORWITHDRAW, params, openId);
						if(Constant.REQUESTSUCCESS.equals(askObj.getString(Constant.JSONDATATIP))) {
							data.setStatus(1);
							data.setInfo(Constant.TIXIANTIP);
							data.setUrl("/html/account/myaccount.html?id=" + openId);
						} else {
							data.setInfo(askObj.getString(Constant.JSONDATATIP));
						}
					} else {
						data.setInfo(loadObj.getString(Constant.JSONDATATIP));
					}
				}
			} catch (Exception e) {
				logger.error("---openId--" + openId +"--tixian_ajax---------" + e.getMessage());
				data.setInfo(Constant.TIXIANERROR);
			}
		return data;
	}
	
	@RequestMapping(value = "/start_liandong" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData startLianDong(HttpServletRequest request,
			@RequestParam("amount") String amount,
			@RequestParam("cardId") String cardId,
			@RequestParam("cardType") String cardType
			){
		String openId = (String)request.getAttribute("openId");
		JSONData infoData = new InfoData();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tranAmt",amount));
		params.add(new BasicNameValuePair("cardno",cardId));
		params.add(new BasicNameValuePair("cardtype",cardType));
		try{
			JSONObject czhi = JedisUtil.getJSONObjectFromRedis(openId + Constant.CZHI);
			JSONObject orderObj = RequestUtil.getJSONObjectByHttpClient(Constant.QUERYORDER, params, openId);
			if(Constant.REQUESTSUCCESS.equals(orderObj.getString(Constant.JSONDATATIP))){
				JSONObject orderData = orderObj.getJSONObject("data");
				czhi.put("liandong_order", orderData.getString("no_order"));
			}
			params.clear();
			params.add(new BasicNameValuePair("order_id",czhi.getString("liandong_order"	)));
			params.add(new BasicNameValuePair("amount",String.valueOf(Double.valueOf(amount) * 100).split("\\.")[0]));
			params.add(new BasicNameValuePair("amt_type",Constant.AMTTYPE));
			params.add(new BasicNameValuePair("mer_priv",""));
			params.add(new BasicNameValuePair("expire_time",""));
			params.add(new BasicNameValuePair("risk_expand",""));
			params.add(new BasicNameValuePair("goods_id",""));
			JSONObject umpayObj = RequestUtil.getJSONObjectByHttpClient(Constant.UMPAYGETORDERID, params, openId);
			if(Constant.REQUESTSUCCESS.equals(umpayObj.getString(Constant.JSONDATATIP))){
				infoData.setStatus(1);
				infoData.setInfo(umpayObj.get("data"));
			} else {
				infoData.setInfo(umpayObj.getString(Constant.JSONDATATIP));
				infoData.setWeizhi(".bankErro1");
			}
		} catch(Exception e){
			logger.error("---openId--" + openId +"--tixian_ajax---------" + e.getMessage());
			infoData.setInfo(Constant.CZERROR);
			infoData.setWeizhi(".bankErro1");
		}
		return infoData;
	}
	
	@RequestMapping(value = "/liandong_pay" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData liandongPay(HttpServletRequest request,
			@RequestParam("cardId") String cardId,							//银行卡号
			@RequestParam("tradeNo") String tradeNo,						//商户API下单时，平台响应的trade_no
			@RequestParam("merCustId") String merCustId,				//用户在商户端的唯一标识
			@RequestParam("identityType") String identityType,	//仅支持身份证
			@RequestParam("identityCode") String identityCode,	//证件号
			@RequestParam("cardHolder") String cardHolder,			//持卡人姓名
			@RequestParam("payType") String payType,						//payType和gateId同传或不同传
			@RequestParam("gateId") String gateId,							//payType和gateId同传或不同传,详见规范银行列表
			@RequestParam("canModifyFlag") String canModifyFlag	//如传0，则不允许用户修改商户上传的支付要素
			){
		String openId = (String)request.getAttribute("openId");
		JSONData infoData = new InfoData();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("cardId",cardId));
		params.add(new BasicNameValuePair("tradeNo",tradeNo));
		params.add(new BasicNameValuePair("merCustId",merCustId));
		params.add(new BasicNameValuePair("identityType",identityType));
		params.add(new BasicNameValuePair("identityCode",identityCode));
		params.add(new BasicNameValuePair("cardHolder",cardHolder));
		params.add(new BasicNameValuePair("payType",payType));
		params.add(new BasicNameValuePair("gateId",gateId));
		params.add(new BasicNameValuePair("canModifyFlag","0"));
		params.add(new BasicNameValuePair("mobileId",""));
		try{
			JSONObject umPayObj = RequestUtil.getJSONObjectByHttpClient(Constant.UMPAYORDERTOPAY, params, openId);
			if(Constant.REQUESTSUCCESS.equals(umPayObj.getString(Constant.JSONDATATIP))){
				infoData.setStatus(1);
				infoData.setUrl(umPayObj.getString("data"));
			} else {
				infoData.setInfo(umPayObj.getString(Constant.JSONDATATIP));
				infoData.setWeizhi(".cardError1");
			}
		} catch(Exception e){
			infoData.setWeizhi(".cardError1");
			infoData.setInfo(Constant.CZERROR);
			logger.error("---openId--" + openId +"--liandong_pay---------" + e.getMessage());
		}
		return infoData;
	}
	
	@RequestMapping(value="/yibao_czhi" ,consumes = "application/json", produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData yibaoCzhi(HttpServletRequest request, @RequestBody YeePay yp){
		String openId = yp.getOpenId();
		JSONData infoData = new InfoData();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("amount",String.valueOf((int)(yp.getAmount() * 100))));
		params.add(new BasicNameValuePair("orderid",yp.getOrderid()));
		params.add(new BasicNameValuePair("transtime",String.valueOf(yp.getTranstime())));
		params.add(new BasicNameValuePair("currency",String.valueOf(yp.getCurrency())));
		params.add(new BasicNameValuePair("productcatalog",String.valueOf(yp.getProductcatalog())));
		params.add(new BasicNameValuePair("productname",yp.getProductname()));
		params.add(new BasicNameValuePair("productdesc",yp.getProductdesc()));
		params.add(new BasicNameValuePair("identitytype",String.valueOf(yp.getIdentitytype())));
		params.add(new BasicNameValuePair("identityid",String.valueOf(yp.getIdentityid())));
		params.add(new BasicNameValuePair("terminaltype",String.valueOf(yp.getTerminaltype())));
		params.add(new BasicNameValuePair("terminalid",yp.getTerminalid()));
		params.add(new BasicNameValuePair("userip",yp.getUserip()));
		params.add(new BasicNameValuePair("userua",yp.getUserua()));
		params.add(new BasicNameValuePair("paytypes",yp.getPaytypes()));
		params.add(new BasicNameValuePair("orderexpdate",yp.getOrderexpdate()));
		params.add(new BasicNameValuePair("cardno",yp.getCard()));
		params.add(new BasicNameValuePair("idcardtype",yp.getIdcardtype()));
		params.add(new BasicNameValuePair("idcard",yp.getIdcard()));
		params.add(new BasicNameValuePair("owner",yp.getOwner()));
		try{
			JSONObject result = RequestUtil.getJSONObjectByHttpClient(Constant.YEEPAY, params, openId);
			if(Constant.REQUESTSUCCESS.equals(result.getString("tip"))){
				infoData.setUrl(result.getString("data"));
				infoData.setStatus(1);
			}
		} catch(Exception e){
			logger.error("---openId--" + openId +"--yibao_czhi---------" + e.getMessage());
			infoData.setInfo(Constant.CZERROR);
		}
		return infoData;
	}
}
