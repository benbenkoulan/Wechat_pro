/**
 * Project Name:wechat-20160517
 * File Name:LogginInterceptor.java
 * Package Name:com.eloancn.wechat.common.interceptor
 * Date:2016年5月19日上午11:09:03
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
*/
/**
 * Project Name:wechat-20160517
 * File Name:LogginInterceptor.java
 * Package Name:com.eloancn.wechat.common.interceptor
 * Date:2016年5月19日上午11:09:03
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
 * ClassName: LogginInterceptor <br/>
 * Function: 登录拦截. <br/>
 * date: 2016年5月19日 上午11:09:03 <br/>
 *
 * @author liben
 * @version 
 */
public class LogginInterceptor extends HandlerInterceptorAdapter {

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
		if(!Tools.checkBind(id,platform)){
			StringBuffer sb = new StringBuffer(Constant.getWechatBaseURL());
			sb.append("/wechat/index/bind?openId=" + id);
			response.sendRedirect(sb.toString());
			/*response.sendRedirect("http://localhost:8080/wechat/index/bind?openId=" + id);*/
			return false;
		}
		return true;
	}
}

