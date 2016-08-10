/**
 * Project Name:wechat-20160505
 * File Name:WechatViewInterceptor.java
 * Package Name:com.eloancn.wechat.common.interceptor
 * Date:2016年5月17日上午10:09:35
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
*/
/**
 * Project Name:wechat-20160505
 * File Name:WechatViewInterceptor.java
 * Package Name:com.eloancn.wechat.common.interceptor
 * Date:2016年5月17日上午10:09:35
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.common.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eloancn.wechat.common.constant.Constant;

/**
 * ClassName: WechatViewInterceptor <br/>
 * Function: 跳转微信地址. <br/>
 * date: 2016年5月17日 上午10:09:35 <br/>
 *
 * @author liben
 * @version 
 */
public class WechatViewInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException{
		String requestUrl = request.getServletPath();
		int index = requestUrl.indexOf("/view/");
		String viewName = requestUrl.substring(index + 6);
		String redirectUrl = Constant.getOPENWEIXINURL(viewName);
		response.sendRedirect(redirectUrl);
		return false;
	}
}

