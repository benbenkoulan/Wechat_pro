/**
 * @Title: WeCoreController.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */

package com.eloancn.code.core.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eloancn.code.common.controller.BaseController;
import com.eloancn.code.core.service.IWeCoreService;

/**
 * @ClassName: WeCoreController
 * @Description: 微信核心controller.
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 下午1:13:28
 */
@Controller
@RequestMapping("/core")
public class WeCoreController extends BaseController {
    /**
     * 记录日志.
     */
    private Logger log = LoggerFactory.getLogger(WeCoreController.class);
    /**
     * 核心服务
     */
    @Resource
    private IWeCoreService weCoreService;

    /**
     * @Description: 微信接入方法
     * @param request 请求信息
     * @param response 相应信息
     * @throws
     */
    @RequestMapping("/handle")
    public void handleMess(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            if (request.getMethod().equals("GET")) {// 确认请求来自微信服务器
                response = weCoreService.cheackSign(request, response);
            } else {// 处理微信服务器发来的消息
                response = weCoreService.handleMess(request, response);
            }
        } catch (Exception e) {
            log.info("微信接入方法失败!"+e.getMessage(),e);
        }
    }

}
