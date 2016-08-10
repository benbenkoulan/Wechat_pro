package com.eloancn.wechat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eloancn.wechat.common.utils.WechatUtil;
import com.eloancn.wechat.core.CoreSevice;
import com.eloancn.wechat.service.KeyWordsService;
import com.eloancn.wechat.service.UserMsgService;
import com.eloancn.wechat.service.WechatUserService;

@Controller
@RequestMapping("/connector/*")
public class ConnectController extends BaseController {
	
	@Resource(name = "wechatUserService") 
	private WechatUserService wechatUserService;
	
	@Resource(name = "keyWordsService")
	private KeyWordsService keyWordsService;
	
	@Resource(name = "userMsgService")
	private UserMsgService userMsgService;
	
	//连接微信公众平台
	@RequestMapping(value="/conn/{publicAccountName}" , method=RequestMethod.GET, produces="text/plain")
	public void ConnectWechat(@PathVariable("publicAccountName") String publicAccountName ,
			@RequestParam("signature") String signature , @RequestParam("timestamp") String timestamp ,
			@RequestParam("nonce") String nonce , @RequestParam("echostr") String echostr, 
			HttpServletResponse response) throws IOException {
		signature = null != signature ? signature : "";
		timestamp = null != timestamp ? timestamp : "";
		nonce = null != nonce ? nonce : "";
		echostr = null != echostr ? echostr : "ERROR";
		//验证微信回调地址
		response.setHeader("Content-Type", "text/plain");
		PrintWriter out = response.getWriter();
		if(WechatUtil.CheckSignature(signature, timestamp, nonce)) {
			//验证成功，原样返回echostr,表示接入成功。
			out.print(echostr);
		} else {
			out.print("error");
		}
		out.close();
	}
	
	@RequestMapping(value="/jsticket", produces="application/json;charset=utf-8")
	@ResponseBody
	public String jsticket(@RequestParam(value="isExpired" , required=false) boolean isExpired) throws Exception{
		return new StringBuffer("{\"ticket\":\"").append(WechatUtil.getJSAPITicket(isExpired)).append("\"}").toString();
	}
	
	@RequestMapping(value="/qrticket_temp",produces="application/json;charset=utf-8")
	@ResponseBody
	public String qrticket(@RequestParam(value="scene_id",required=false) String scene_id) throws UnsupportedEncodingException{
		return URLEncoder.encode(WechatUtil.getQRTicketTemp(scene_id),"utf-8");
	}
	
	@RequestMapping(value="/qrticket_limit",produces="application/json;charset=utf-8")
	@ResponseBody
	public String qrticketLimit(@RequestParam(value="scene_str",required=false) String scene_str) throws UnsupportedEncodingException{
		return URLEncoder.encode(WechatUtil.getQRTicketLimit(scene_str),"utf-8");
	}
	
	//接受微信消息，并处理
	@RequestMapping(value = "/conn/{publicAccountName}" , method=RequestMethod.POST, produces="application/xml;charset=UTF-8")
	public void RetrieveWechatMessage(HttpServletRequest request, HttpServletResponse response) {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
		String responseText = null;
		PrintWriter out = null;
		try{
			request.setCharacterEncoding("UTF-8");  
	    response.setCharacterEncoding("UTF-8");  
			response.setHeader("Content-Type", "application/xml");
			out = response.getWriter();
			//处理微信消息
			responseText = CoreSevice.ProcessRequest(request , wechatUserService , keyWordsService , userMsgService);
		} catch(Exception e){
			responseText = "";
		}
		out.print(responseText);
		out.close();
	}
}
