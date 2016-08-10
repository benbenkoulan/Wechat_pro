/**
 * @Title: IWeCoreService.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */

package com.eloancn.code.core.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: IWeCoreService
 * @Description: 微信核心service接口.
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 下午2:43:30
 */

public interface IWeCoreService {
    /**
     * @Description:处理微信服务器发来的消息
     * @param request 请求信息
     * @param response 返回信息
     * @return 返回信息
     * @throws Exception 异常抛出
     * @throws 异常
     */
    HttpServletResponse handleMess(HttpServletRequest request,
            HttpServletResponse response) throws Exception;
    /**
     * @Description:处理微信服务器发来的消息
     * @param request 请求信息
     * @param response 返回信息
     * @return 返回信息
     * @throws Exception 异常抛出
     * @throws 异常
     */
    HttpServletResponse cheackSign(HttpServletRequest request,
            HttpServletResponse response)throws Exception;
}
