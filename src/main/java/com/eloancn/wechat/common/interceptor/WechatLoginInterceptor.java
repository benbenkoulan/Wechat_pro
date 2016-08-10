package com.eloancn.wechat.common.interceptor;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.eloancn.wechat.common.constant.Constant;
import com.eloancn.wechat.common.utils.JedisUtil;
import com.eloancn.wechat.common.utils.RequestUtil;
import com.eloancn.wechat.common.utils.Tools;
import com.eloancn.wechat.common.utils.WechatUtil;
import com.eloancn.wechat.model.WechatUser;
import com.eloancn.wechat.service.WechatUserService;

/**   
* @ClassName:WechatLoginInterceptor.java 
* @Function 验证微信用户是否登录
* @author: liben
* @date 2015年9月16日 上午10:42:20 
*/
public class WechatLoginInterceptor extends HandlerInterceptorAdapter {
	
	@Resource(name = "wechatUserService") 
	private WechatUserService wechatUserService;
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		String openId = (String)request.getAttribute("openId");
		try {
			String requestURI = request.getRequestURI();
			//只拦截index下的请求，解析请求地址
			String viewName = requestURI.substring(requestURI.lastIndexOf("/index/") + 7);
			//如果进入投资列表页面无需登录
			if("invested".equals(viewName)){
				return true;
			}
			//初始化UserInfo
			JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
			//没有登录
			if(Tools.isEmpty(userInfo.getString("app_secret")) || Tools.isEmpty(userInfo.getString("out_uid"))) {
				userInfo = WechatUtil.getWechatUserByOpenId(openId);
				if(null != userInfo) {
					//保存当前微信用户信息到redis中
					JedisUtil.saveObjectToRedis(openId, userInfo, Constant.EXPIRETIME);
				}
			} else {
				//检查微信是否绑定翼龙钱包
				if(checkBind(openId)) {
					saveUserInfo(openId);
					return true;
				}
			}
			//绑定参数
			StringBuffer bindPath = new StringBuffer(request.getContextPath() + "/index/bind?openId=" + openId + "&viewName=" + viewName);
			Map params = request.getParameterMap();
			for(Object key : params.keySet().toArray()) {
				if(Constant.skipParams.containsKey(key)) {
					continue;
				} else{
					String value = ((String[])params.get(key))[0];
					bindPath.append("&" + key + "=" + value);
				}
			}
			//将请求地址来源存入redis，避免登录之后回退再退入登录页面
			JedisUtil.saveObjectToRedis(userInfo.getString("out_uid") + Constant.REFERER, request.getHeader("referer"), Constant.EXPIRETIME);
			//转到登录页面
			response.sendRedirect(bindPath.toString());
		} catch (Exception e) {
			//转发到错误页面
			response.sendRedirect(request.getContextPath() + "/index/error?errorcode=0");
		}
		return false;
	}
	
	/*
	 *保存微信用户信息 
	 * */
	public void saveUserInfo(String openId) {
		try {
			WechatUser wu = wechatUserService.getWechatUserInfo(openId);
			if(wu == null) {
				wu = WechatUtil.getWechatUser(openId);
				//保存wxuser
				Integer id = wechatUserService.saveWechatUser(wu);
				wu.setID(id);
			}
			JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
			userInfo.put("id", wu.getID());
			//保存当前微信用户信息到redis中
			JedisUtil.saveObjectToRedis(openId , userInfo , Constant.EXPIRETIME);
		} catch (Exception e) {
			/*logger.error("-----saveUserInfo---------" + e.getMessage());*/
		}
	}
	
	/*
	 * 查看是否绑定
	 * */
	public boolean checkBind(String openId) throws Exception{
		JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(Constant.FINDUSERVERIFYINFO, null, openId);
		if(!Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
			return false;
		} 
		return true;
	}
}
