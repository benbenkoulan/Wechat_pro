/**
 * Project Name:wechat-20150909
 * File Name:WechatCzhiReturnInterceptor.java
 * Package Name:com.eloancn.wechat.common.interceptor
 * Date:2016年1月27日下午4:21:25
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
*/
/**
 * Project Name:wechat-20150909
 * File Name:WechatCzhiReturnInterceptor.java
 * Package Name:com.eloancn.wechat.common.interceptor
 * Date:2016年1月27日下午4:21:25
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.eloancn.wechat.common.constant.Constant;

/**
 * ClassName: WechatCzhiReturnInterceptor <br/>
 * Function: 微信充值返回 <br/>
 * date: 2016年1月27日 下午4:21:25 <br/>
 *
 * @author liben
 * @version 
 */
public class WechatCzhiReturnInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		response.sendRedirect(Constant.getOPENWEIXINURL());
		return false;
	}
	
}

