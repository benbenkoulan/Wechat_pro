/**
 * Project Name:wechat-20150909
 * File Name:CodeAndOpenIdInterceptor.java
 * Package Name:com.eloancn.wechat.common.interceptor
 * Date:2015年12月1日下午6:03:21
 * Copyright (c) 2015, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.common.interceptor;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.eloancn.wechat.common.constant.Constant;
import com.eloancn.wechat.common.utils.JedisUtil;
import com.eloancn.wechat.common.utils.RequestUtil;
import com.eloancn.wechat.common.utils.Tools;
import com.eloancn.wechat.common.utils.WechatUtil;

/**
 * ClassName: CodeAndOpenIdInterceptor <br/>
 * Function: 拦截code获取OpenId <br/>
 *
 * @author liben
 * @version 
 */
public class CodeAndOpenIdInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 拦截code获取openid
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		String openId = null;
		String code = request.getParameter("code");
		RequestUtil.localPort = String.valueOf(request.getLocalPort());
		//如果code不为空，说明是从微信菜单进来的
		if(!Tools.isEmpty(code)){
			JSONObject obj = WechatUtil.getAccessTokenByCode(code);	
			openId = obj.getString("openid");
		} else {
			openId = request.getParameter("openId");
		}
		//openId为空，微信用户报错
		if(Tools.isEmpty(openId)){
			//转发到错误页面
			response.sendRedirect(request.getContextPath() + "/index/error?errorcode=1");
			return false;
		} else {
			request.setAttribute("openId", openId);
		}
		List<String> params = new ArrayList<String>();
		Object state = request.getParameter("state");
		if(null != state && !"".equals(state)){
			//将渠道参数和活动参数放到state中
			String[] paramArray = URLDecoder.decode((String)state, "utf-8").split(Constant.SEPARATOR);
			for(String param : paramArray){
				params.add(param);
			}
		} else {
			//获取推广链接中的渠道参数
			//jl_sign,ch_sign,act_sign
			String jsSign = request.getParameter("jl_sign");
			if(!Tools.isEmpty(jsSign)){
				params.add("jl_sign=" + jsSign);
			}
			String chSign = request.getParameter("ch_sign");
			if(!Tools.isEmpty(chSign)){
				params.add("ch_sign=" + chSign);
			}
			String actSign = request.getParameter("act_sign");
			if(!Tools.isEmpty(actSign)){
				params.add("act_sign=" + actSign);
			}
		}
		if(!params.isEmpty()){
			StringBuffer paramsStr = new StringBuffer(request.getRequestURL() + "?");
			for(int i=0,max=params.size();i<max;i++){
				if(i == max-1){
					paramsStr.append(params.get(i));
				} else {
					paramsStr.append(params.get(i) + "&");
				}
			}
			String encodeUrl = URLEncoder.encode(paramsStr.toString(), "utf-8");
			JedisUtil.saveObjectToRedis((String)request.getAttribute("openId") + Constant.CHANNEL, encodeUrl, Constant.EXPIRETIME);
		}
		return true;
	}
}

