/**
 * Project Name:wechat-20160517
 * File Name:WechatMergeInterceptor.java
 * Package Name:com.eloancn.wechat.common.interceptor
 * Date:2016年5月31日下午6:52:45
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
*/
/**
 * Project Name:wechat-20160517
 * File Name:WechatMergeInterceptor.java
 * Package Name:com.eloancn.wechat.common.interceptor
 * Date:2016年5月31日下午6:52:45
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.eloancn.wechat.common.utils.Tools;
import com.eloancn.wechat.common.utils.WechatUtil;

/**
 * ClassName: WechatMergeInterceptor <br/>
 * Function: 合并页面拦截. <br/>
 * date: 2016年5月31日 下午6:52:45 <br/>
 *
 * @author liben
 * @version 
 */
public class WechatMergeInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		String code = request.getParameter("code");
		if(!Tools.isEmpty(code)){
			JSONObject obj = WechatUtil.getAccessTokenByCode(code);	
			String openId = obj.getString("openid");
			StringBuffer url = new StringBuffer(request.getRequestURL());
			url.append("?id=");
			url.append(openId);
			response.sendRedirect(url.toString());
			return false;
		}
		return true;
	}

	
}

