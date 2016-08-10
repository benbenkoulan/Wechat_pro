package com.eloancn.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.eloancn.wechat.common.constant.Constant;
import com.eloancn.wechat.common.encrypt.DESUtilPass;
import com.eloancn.wechat.common.utils.JedisUtil;
import com.eloancn.wechat.common.utils.RequestUtil;
import com.eloancn.wechat.common.utils.Tools;
import com.eloancn.wechat.entities.InfoData;
import com.eloancn.wechat.entities.JSONData;
import com.eloancn.wechat.model.WechatUser;
import com.eloancn.wechat.service.WechatUserService;

import org.apache.commons.codec.binary.Base64;

import org.springframework.stereotype.Controller;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
* @ClassName:LoginController.java
* @Function com.eloancn.wechat.controller
* @author: liben
* @date 2015年9月18日 上午11:45:21
*/
@Controller
@RequestMapping(value = "/bind")
public class BindController extends BaseController {
		
		@Resource(name = "wechatUserService") 
		private WechatUserService wechatUserService;
		
    @RequestMapping(value = "/bind_ajax", produces = "application/json;charset=UTF-8")
    public @ResponseBody JSONData bind(HttpServletRequest request , 
    			@RequestParam("phone") String phone,@RequestParam("pwd") String pwd, 
					@RequestParam(value="ranCode" , required=false) String ranCode){
		String openId = (String)request.getAttribute("openId");
		JSONData data = new InfoData();
		//登录
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("loginName", phone));
			params.add(new BasicNameValuePair("password", DESUtilPass.encrypt(pwd)));
			params.add(new BasicNameValuePair("openid", openId));
			params.add(new BasicNameValuePair("platform", Constant.PLATFORM));
			if(!Tools.isEmpty(ranCode)){
				params.add(new BasicNameValuePair("rand", ranCode));
			}
			String responseText = RequestUtil.sendGETRequest(Constant.getAppUserBaseURL() + Constant.APPUSER_LOGIN, params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = JSONObject.parseObject(responseText);
				if("0000".equals(json.getString("code"))){
					JSONObject dataObj = (JSONObject) json.get("data");
					//登录成功，清空该微信号的所有缓存
					Set<String> keys = Constant.rediskeys.keySet();
					for(String key : keys){
						JedisUtil.deleteObjectFromRedis(openId + key);
					}
					//设置用户登录userinfo
					JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
					userInfo.put("app_secret", dataObj.getString("app_secret"));
					userInfo.put("out_uid", dataObj.getString("id"));
					//更新userinfo
					JedisUtil.saveObjectToRedis(openId , userInfo , Constant.EXPIRETIME);
					//设置体验期标志
					/*JSONObject tiyanqi = new JSONObject();
					tiyanqi.put("exprienced", dataObj.getBoolean("experienced") ? "1" : "0");
					JedisUtil.saveObjectToRedis(openId + Constant.EXPRIENCED, tiyanqi, Constant.EXPIRETIME);*/
					params.clear();
					params.add(new BasicNameValuePair("nikename", (!Tools.isEmpty(userInfo.getString("nickname")) ? new String(Base64.encodeBase64(userInfo.getString("nickname").getBytes())) : "")));
					params.add(new BasicNameValuePair("sex", userInfo.getString("sex")));
					params.add(new BasicNameValuePair("city", userInfo.getString("city")));
					params.add(new BasicNameValuePair("uid", phone));
					//添加微信用户
					RequestUtil.getJSONObjectByHttpClient(Constant.ADDWXUSER, params, openId);
					//设置添加微信用户标志
					userInfo.put("is_add_wxuser", 1);
					//设置绑定标志
					userInfo.put("is_bind", 1);
					//将手机号，用户登录信息保存到数据库
					WechatUser wu = new WechatUser();
					wu.setID(userInfo.getIntValue("id"));
					wu.setPHONE(phone);
					wu.setAPP_SECRET(userInfo.getString("app_secret"));
					wu.setOUT_UID(userInfo.getString("out_uid"));
					wu.setIS_ADD_WXUSER("1");
					wu.setIS_BIND("1");
					wechatUserService.setWechatUserLogin(wu);
					//更新userinfo
					JedisUtil.saveObjectToRedis(openId , userInfo , Constant.EXPIRETIME);
					//拼接返回地址
					StringBuffer path = new StringBuffer("/html/account/myaccount.html?id=");
					path.append(openId);
					path.append("&paltform=");
					path.append(Constant.PLATFORM);
					//设置登录成功标志
					data.setUrl(path.toString());
					data.setInfo(Constant.LOGINTIP);
					data.setStatus(1);
				} else {
					data.setInfo(json.getString("message"));
					data.setWeizhi(".passError");
				}
			}
		} catch (Exception e) {
			logger.error("----openId----" + openId + "---bind_ajax---------" + e.getMessage());	
			data.setInfo(Constant.LOGINERROR);
			data.setWeizhi(".passError");
		}
		return data;
    }

    @RequestMapping(value = "/findpwd")
    public ModelAndView findPwd(HttpServletRequest request) {
			ModelAndView mv = this.getModelAndView(request , "忘记密码");
      mv.setViewName("findpwd");
      return mv;
    }  
}
