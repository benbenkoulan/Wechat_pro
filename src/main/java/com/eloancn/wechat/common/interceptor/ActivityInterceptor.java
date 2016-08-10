/**
 * Project Name:Wechat_160219
 * File Name:ActivityInterceptor.java
 * Package Name:com.eloancn.wechat.common.interceptor
 * Date:2016年4月24日下午4:54:42
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
*/
/**
 * Project Name:Wechat_160219
 * File Name:ActivityInterceptor.java
 * Package Name:com.eloancn.wechat.common.interceptor
 * Date:2016年4月24日下午4:54:42
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.common.interceptor;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.eloancn.wechat.common.constant.Constant;
import com.eloancn.wechat.common.utils.RequestUtil;
/**
 * ClassName: ActivityInterceptor <br/>
 * Function: 拦截判断从哪个平台进入. <br/>
 * date: 2016年4月24日 下午4:54:42 <br/>
 * @author liben
 * @version 
 */
public class ActivityInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException{
		RequestUtil.localPort = String.valueOf(request.getLocalPort());
		String redirectUrl = Constant.getOPENWEIXINURL("activity/index");
		response.sendRedirect(redirectUrl);
		return false;
	}
}

