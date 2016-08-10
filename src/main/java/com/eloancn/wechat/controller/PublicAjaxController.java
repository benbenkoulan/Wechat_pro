/**
 * Project Name:wechat-20160517
 * File Name:PublicAjaxController.java
 * Package Name:com.eloancn.wechat.controller
 * Date:2016年5月18日上午10:42:03
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.controller;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eloancn.wechat.common.constant.Constant;
import com.eloancn.wechat.common.utils.RequestUtil;
import com.eloancn.wechat.common.utils.Tools;
import com.eloancn.wechat.entities.InfoData;
import com.eloancn.wechat.entities.JSONData;
import com.eloancn.wechat.entities.ObjectData;

/**
 * ClassName: PublicAjaxController <br/>
 * Function: 公共页面异步请求. <br/>
 * date: 2016年5月18日 上午10:42:03 <br/>
 * @author liben
 * @version 
 */
@Controller
@RequestMapping("/public/ajax/")
public class PublicAjaxController extends BaseController{
	
	@RequestMapping("index/init")
	@ResponseBody
	public JSONData index(@RequestParam(value="id",required=false) String id ,@RequestParam(required=false,value="platform") String platform){
		JSONData data = new ObjectData();
		JSONObject json = new JSONObject();
		JSONArray products = new JSONArray();
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		data.setStatus(1);
		List<NameValuePair> params = getParams(id, platform);
		params.add(new BasicNameValuePair("page", "1"));
		try{
			String responseText = RequestUtil.sendGETRequest(getUrl(Constant.LOADALLRECORD), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json1 = JSONObject.parseObject(responseText);
				JSONObject jsonObj1 = (JSONObject) json1.get("jsonData");
				JSONObject dataObj1 = jsonObj1.getJSONObject("data");
				JSONArray dataArr1 = dataObj1.getJSONArray("data");
				if(!dataArr1.isEmpty()){
					JSONObject data1 = dataArr1.getJSONObject(0);
					JSONObject wmp1 = getWmpsDetail(data1);
					String detail_href1 = wmp1.getString("detail_href");
					wmp1.put("detail_href", detail_href1 + "&id=" + id + "&platform=" + platform);
					products.add(wmp1);
					if(dataArr1.size() > 1){
						JSONObject data2 = dataArr1.getJSONObject(1);
						JSONObject wmp2 = getWmpsDetail(data2);
						String detail_href2 = wmp2.getString("detail_href");
						wmp2.put("detail_href", detail_href2 + "&id=" + id + "&platform=" + platform);
						products.add(wmp2);
					}
				}
			}
		}catch(Exception e){
			logger.error("---------/public/ajax/index--1----" + e.getMessage() + "-----------");
		}
		try{
			params.add(new BasicNameValuePair("pageNo", "1"));
			String responseText2 = RequestUtil.sendGETRequest(getSesameUrl(Constant.SESAMELIST),params);
			if(Tools.notEmpty(responseText2)){
				JSONObject json2 = JSONObject.parseObject(responseText2);
				JSONObject jsonObj2 = (JSONObject) json2.get("data");
				JSONArray dataArr2 = jsonObj2.getJSONArray("result");
				if(!dataArr2.isEmpty()){
					JSONObject dataObj = dataArr2.getJSONObject(0);
					JSONObject sesame1 = getSesameDetail(dataObj);
					String detail_href = sesame1.getString("detail_href");
					sesame1.put("detail_href", detail_href + "&id=" + id + "&platform=" + platform);
					products.add(sesame1);
				}
			}
		} catch (Exception e){
			logger.error("---------/public/ajax/index--2----" + e.getMessage() + "-----------");
		}
		try{
			String responseText3 = RequestUtil.sendGETRequest(getUrl(Constant.INITBANNER),params);
			if(Tools.notEmpty(responseText3)){
				JSONObject json3 = JSONObject.parseObject(responseText3);
				json.put("banners", json3.getJSONArray("data"));
			}
			json.put("vip", getVipInfo(id,platform));
		}catch(Exception e){
			logger.error("---------/public/ajax/index--3----" + e.getMessage() + "-----------");
		}
		json.put("products", products);
		data.setInfo(json);
		return data;
	}
	
	@RequestMapping("check_bind")
	@ResponseBody
	public JSONData checkBind(@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData data = new InfoData();
		if(!Tools.checkBind(id, platform)){
			data.setUrl("/wechat/index/bind?openId=" + id);
		} else {
			data.setStatus(1);
		}
		return data;
	}
	
	@RequestMapping("check")
	@ResponseBody
	public JSONData check(@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData data = new InfoData();
		List<NameValuePair> params = getParams(id, platform);
		try{
			String responseText = RequestUtil.sendGETRequest(getUrl(Constant.NETWORKAUTHRECORD), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				JSONObject jsonObj = json.getJSONObject("jsonData");
				String tip = jsonObj.getString(Constant.JSONDATATIP);
				if(Constant.REQUESTSUCCESS.equals(tip)){
					JSONObject dataObj = jsonObj.getJSONObject("data");
					if(Constant.NOT.equals(dataObj.getString("idcard"))) {
						String time = dataObj.getString("idCardAuthCount");
						//认证次数已经结束
						if("0".equals(time)){
							data.setInfo(Constant.AUTHTIMEOUT);
							data.setStatus(2);
						} else {
							data.setInfo(Constant.NOTAUTH);
							data.setUrl("/wechat/index/authenticate?openId=" + id);
						}
					} else if(Constant.NOT.equals(dataObj.getString("paypassword"))) {
						data.setInfo(Constant.NOTSETPAYPASSWORD);
						data.setUrl("/wechat/index/set_pay_pwd?openId=" + id);
					} else {
						data.setStatus(1);
					}
				}else if(Constant.NOTSUCSCRIBEELOANCN.equals(tip)) {
					data.setInfo(Constant.LOGGEDIN);
					data.setUrl("/wechat/index/bind?openId=" + id);
				}	else if(Constant.NOTLOGGEDIN.equals(tip)) {
					data.setInfo(Constant.LOGGEDIN);
					data.setUrl("/wechat/index/bind?openId=" + id);
				} else {
					data.setInfo(tip);
					data.setUrl("/wechat/index/bind?openId=" + id);
				}
			}
		}catch(Exception e){
			logger.error("---------/public/ajax/check-----" + e.getMessage() + "-----------");
		}
		return data;
	}
	
	@RequestMapping("myaccount")
	@ResponseBody
	public JSONData myAccount(@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform){
		JSONData data = new ObjectData();
		JSONObject info = new JSONObject();
		List<NameValuePair> params = getParams(id, platform);
		try{
			data.setStatus(1);
			String responseText = RequestUtil.sendGETRequest(getUrl(Constant.LOADSTATINFO), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				JSONObject jsonObj = json.getJSONObject("jsonData");
				info.put("accountInfo", jsonObj.getJSONObject("data"));
			}
			JSONObject linkInfo = new JSONObject();
			linkInfo.put("wmps", "/wechat/index/ycb_show?openId=" + id);
			linkInfo.put("sesame", "/wechat/html/sesame/my_list.html?id=" + id + "&platform=" + platform);
			linkInfo.put("sanbiao", "/wechat/index/toubiao?openId=" + id);
			linkInfo.put("deallog", "/wechat/index/deal_log?openId=" + id);
			linkInfo.put("myhd", "/wechat/index/my_hd?openId=" + id);
			linkInfo.put("usesafe", "/wechat/index/user_safe?openId=" + id);
			linkInfo.put("index", "/wechat/html/index.html?id=" + id + "&platform=" + platform);
			linkInfo.put("czhi", "/wechat/index/czhi?openId=" + id);
			linkInfo.put("tixian", "/wechat/index/tixian?openId=" + id);
			info.put("linkInfo", linkInfo);
			info.put("vipInfo", getVipInfo(id, platform));
		}catch(Exception e){
			data.setStatus(0);
			logger.error("------/public/ajax/myaccount-------" + id + "---------- " + e.getMessage());
		}
		data.setInfo(info);
		return data;
	}
}

