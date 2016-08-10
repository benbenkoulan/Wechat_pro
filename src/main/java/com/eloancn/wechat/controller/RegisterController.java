package com.eloancn.wechat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.eloancn.wechat.common.constant.Constant;
import com.eloancn.wechat.common.utils.JedisUtil;
import com.eloancn.wechat.common.utils.RequestUtil;
import com.eloancn.wechat.common.utils.Tools;
import com.eloancn.wechat.entities.InfoData;
import com.eloancn.wechat.entities.JSONData;
import com.eloancn.wechat.entities.ScanParam;
import com.eloancn.wechat.model.WechatUser;
import com.eloancn.wechat.service.WechatUserService;

/**   
* @ClassName:RegisterController.java 
* @Function com.eloancn.wechat.controller 
* @author: liben
* @date 2015年9月16日 下午4:46:20 
*/
@Controller
@RequestMapping(value = "/register")
public class RegisterController extends BaseController {
	
	@Resource(name = "wechatUserService") 
	private WechatUserService wechatUserService;
	
	@RequestMapping(value = "/reg")
	public ModelAndView register(HttpServletRequest request) {
		ModelAndView mv = this.getModelAndView(request , "注册");
		mv.setViewName("register");
		return mv;
	}
	
	@RequestMapping(value = "/reg_ajax" , produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONData regAjax(HttpServletRequest request ,
			@RequestParam("phone") String phone , @RequestParam("password") String password,
			@RequestParam("mcode") String code , @RequestParam("password1") String password1 ,
			@RequestParam("inviteCode") String inviteCode,@RequestParam("verificationCode") String verificationCode) {
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("mobile", phone));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("mobileCode", code));
		params.add(new BasicNameValuePair("repassword", password1));
		params.add(new BasicNameValuePair("refranchiseecode", inviteCode));
		try{
			ScanParam sp = new ScanParam(openId, "620");
			WechatUser scanUser = wechatUserService.getWechatUserInfoByScan(sp);
			if(null != scanUser && Tools.notEmpty(scanUser.getTAG())){
				params.add(new BasicNameValuePair("jlsign", "gg_ydylqb"));
			} else {
				String channel = (String)JedisUtil.getObjectFromRedis(openId + Constant.CHANNEL, String.class);
				params.add(new BasicNameValuePair("reffer", channel));
			}
			JSONObject registerObj = RequestUtil.getJSONObjectByHttpClient(Constant.REGISTER, params, openId);
			//注册成功
			if(Constant.REQUESTSUCCESS.equals(registerObj.getString(Constant.JSONDATATIP))) {
				JedisUtil.deleteObjectFromRedis(openId + Constant.CHANNEL);
				JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
				//自动登录
				params.clear();
				params.add(new BasicNameValuePair("loginName" , phone));
				params.add(new BasicNameValuePair("password" , new String(Base64.encodeBase64(password.getBytes()))));
				params.add(new BasicNameValuePair("rand", verificationCode));//添加注册成功后传入图形验证码，防止登陆失败
				try{
					JSONObject loginObj = RequestUtil.getJSONObjectByHttpClient("/v2/login2_1.action", params, openId);
					if(Constant.REQUESTSUCCESS.equals(loginObj.getString(Constant.JSONDATATIP))) {
						//获取登录成功数据
						JSONObject dataObj = loginObj.getJSONObject("data");
						//登录成功:将登录成功返回的数据放到userinfo里
						userInfo.put("app_secret", dataObj.getString("app_secret"));
						userInfo.put("out_uid", dataObj.getString("id"));
						//更新userinfo
						JedisUtil.saveObjectToRedis(openId , userInfo , Constant.EXPIRETIME);
						//调用addWXUser接口新增或更新微信用户
						params.clear();
						params.add(new BasicNameValuePair("nikename" , (null != userInfo.getString("nickname") ? new String(Base64.encodeBase64(userInfo.getString("nickname").getBytes())) : "")));
						params.add(new BasicNameValuePair("sex" , userInfo.getString("sex")));
						params.add(new BasicNameValuePair("city" , userInfo.getString("city")));
						params.add(new BasicNameValuePair("uid" , phone));
						RequestUtil.getJSONObjectByHttpClient(Constant.ADDWXUSER, params, openId);
						//将手机号存入本地数据库
						WechatUser wu = new WechatUser();
		        wu.setID(userInfo.getIntValue("out_uid"));
		        wu.setPHONE(phone);
		        wu.setAPP_SECRET(userInfo.getString("app_secret"));
		        wu.setOUT_UID(userInfo.getString("out_uid"));
		        wu.setIS_ADD_WXUSER("1");
		        wu.setIS_BIND("1");
		        int res = wechatUserService.setWechatUserLogin(wu);
		        if(res > 0) {
		        	data.setInfo("注册成功，保存本地失败");
		        } else {
		        	userInfo.put("is_add_wxuser", 1);
		        	userInfo.put("is_bind", 1);
							JedisUtil.saveObjectToRedis(openId , userInfo , Constant.EXPIRETIME);
							data.setStatus(1);
							data.setInfo("注册成功");
							data.setUrl("/html/account/myaccount.html?id=" + openId);
		        }
					} else {
						data.setInfo("注册成功,自动登录失败,请退出重新登录.");
					}
				} catch (Exception e){
					logger.error("---openId--" + openId +"--reg_ajax----zidongdenglu-----" + e.getMessage());	
					data.setInfo("注册成功，自动登录失败,请退出重新登录.");
				}
			} else {
				data.setInfo(registerObj.getString(Constant.JSONDATATIP));
			}
		} catch (Exception ex){
			logger.error("---openId--" + openId +"--reg_ajax----zhuce-----" + ex.getMessage());	
			data.setInfo("注册失败");
		} finally{
    	data.setWeizhi(".passError1");
		}
		return data;
	}
	
	@RequestMapping(value = "/get_code" , produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONData getCode(HttpServletRequest request ,
				@RequestParam("phone") String phone , 
				@RequestParam("verification_code") String verificationCode){
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("mobile", phone));
		try {
			JSONObject checkMobile = RequestUtil.getJSONObjectByHttpClient(Constant.CHECKMOBILE, params, openId);
			String tip = checkMobile.getString(Constant.JSONDATATIP);
			if(Constant.REQUESTSUCCESS.equals(tip)){
				params.add(new BasicNameValuePair("mobile_code_type", "1"));
				params.add(new BasicNameValuePair("verification_code", verificationCode));
				JSONObject jsonObj = RequestUtil.getJSONDataByHttpClient(Constant.CATCHREGCODE, params, openId);
				if(!Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))){
					data.setInfo(jsonObj.getString(Constant.JSONDATATIP));
					data.setWeizhi(".verificationCodeError");
				} else {
					data.setStatus(1);
					if(Constant.isDev){
						data.setInfo(Constant.CODESENDSUCCESS + jsonObj.getString("data"));
					} else {
						data.setInfo(Constant.CODESENDSUCCESS);
					}
					data.setWeizhi(".codeError");
				}
			} else {
				data.setInfo(tip);
				data.setWeizhi(".phoneError");
			}
		} catch (Exception e) {
			logger.error("---openId--" + openId +"--get_code----huoquzhuceyanzhengma-----" + e.getMessage());	
			data.setInfo("获取验证码失败");
		}
		return data;
	}
}
