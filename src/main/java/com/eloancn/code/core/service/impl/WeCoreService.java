/**
 * @Title: WeCoreService.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */

package com.eloancn.code.core.service.impl;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eloancn.code.common.service.BaseService;
import com.eloancn.code.core.service.IWeCoreService;
import com.eloancn.code.core.util.SignUtil;

/**
 * @ClassName: WeCoreService
 * @Description:微信核心service.
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 下午1:14:45
 */

public class WeCoreService extends BaseService implements IWeCoreService {

    /**
     * 处理微信服务器发来的消息.
     * <p>Title: handleMess</p>
     * <p>Description: 处理微信服务器发来的消息</p>
     * @param request 请求信息
     * @param response 相应信息
     * @return 处理信息
     * @throws Exception 异常
     * @see
     */
    public HttpServletResponse handleMess(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 处理微信服务器发来的消息.
     * <p>Title: cheackSign</p>
     * <p>Description: 处理微信服务器发来的消息</p>
     * @param request 请求信息
     * @param response 相应信息
     * @return 处理结果
     * @throws Exception 异常
     * @see
     */
    public HttpServletResponse cheackSign(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // 微信加密签名  
        String signature = request.getParameter("signature");  
        // 时间戳  
        String timestamp = request.getParameter("timestamp");  
        // 随机数  
        String nonce = request.getParameter("nonce");  
        // 随机字符串  
        String echostr = request.getParameter("echostr");
        PrintWriter out= response.getWriter();
        
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
            out.print(echostr); 
        }  
        out.close();  
        out = null;  
        return response;
    }

}
