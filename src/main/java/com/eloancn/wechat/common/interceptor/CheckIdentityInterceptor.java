/**
 * Project Name:wechat-20160517
 * File Name:CheckIdentityInterceptor.java
 * Package Name:com.eloancn.wechat.common.interceptor
 * Date:2016年6月6日下午1:29:18
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eloancn.wechat.common.constant.Constant;
import com.eloancn.wechat.common.utils.Tools;

/**
 * ClassName: CheckIdentityInterceptor <br/>
 * Function: 检查是否绑定. <br/>
 * date: 2016年6月6日 下午1:29:18 <br/>
 * @author liben
 * @version 
 */
public class CheckIdentityInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		String id = request.getParameter("id");
		if(Tools.isEmpty(id)){
			id = request.getParameter("openId");
		}
		if(Tools.isEmpty(id)){
			id = (String)request.getAttribute("openId");
		}
		String platform = request.getParameter("platform");
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		int code = Tools.checkIdentity(id, platform);
		StringBuffer sb = new StringBuffer(Constant.getWechatBaseURL());
		/*StringBuffer sb = new StringBuffer("http://localhost:8080");*/
		String url = null;
		if(code == 1){//没有身份认证
			url = "/wechat/index/authenticate?openId=";
		} else if(code == 2){//没有设置支付密码
			url = "/wechat/index/set_pay_pwd?openId=";
		} else if(code == 4){//没有登录
			url = "/wechat/index/bind?openId=";
		}
		if(Tools.notEmpty(url)){
			sb.append(url);
			sb.append(id);
			sb.append("&platform=");
			sb.append(platform);
			response.sendRedirect(sb.toString());
			return false;
		}
		return true;
	}
}

