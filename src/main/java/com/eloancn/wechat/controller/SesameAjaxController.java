/**
 * Project Name:wechat-20160517
 * File Name:SesameAjaxController.java
 * Package Name:com.eloancn.wechat.controller
 * Date:2016年5月17日下午7:38:25
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.ws.RequestWrapper;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eloancn.wechat.common.constant.Constant;
import com.eloancn.wechat.common.utils.JedisUtil;
import com.eloancn.wechat.common.utils.RequestUtil;
import com.eloancn.wechat.common.utils.Tools;
import com.eloancn.wechat.entities.JSONData;
import com.eloancn.wechat.entities.ObjectData;

/**
 * ClassName: SesameAjaxController <br/>
 * Function: 芝麻开花请求. <br/>
 * date: 2016年5月17日 下午7:38:25 <br/>
 *
 * @author liben
 * @version 
 */
@Controller
@RequestMapping("/sesame/ajax/")
public class SesameAjaxController extends BaseController{

	@SuppressWarnings("rawtypes")
	@RequestMapping("list")
	@ResponseBody
	public JSONData list(@RequestParam(value="page",required=false) String page,@RequestParam(value="id",required=false) String id,
			@RequestParam(value="platform",required=false) String platform){
		id = Tools.notEmpty(id) ? id : "";
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		page = Tools.isEmpty(page) ? "1" : page;
		JSONData data = new ObjectData();
		JSONArray sesames = new JSONArray();
		List<NameValuePair> params = getParams(id, platform);
		params.add(new BasicNameValuePair("pageNo", page));
		try{
			String responseText = RequestUtil.sendGETRequest(getSesameUrl(Constant.SESAMELIST),params);
			if(Tools.notEmpty(responseText)){
				data.setStatus(1);
				JSONObject json = JSONObject.parseObject(responseText);
				JSONObject jsonObj = (JSONObject) json.get("data");
				JSONArray dataArr = jsonObj.getJSONArray("result");
				Iterator it = dataArr.iterator();
				BigDecimal times = new BigDecimal(10000);
				StringBuffer userInfoUrl = new StringBuffer("&id=");
				userInfoUrl.append(id);
				userInfoUrl.append("&platform=");
				userInfoUrl.append(platform);
				while(it.hasNext()){
					JSONObject sesame = (JSONObject)it.next();
					BigDecimal maxAmount = new BigDecimal(sesame.getDouble("max_amount")).divide(times);
					sesame.put("max_amount", maxAmount.setScale(0, BigDecimal.ROUND_DOWN));
					BigDecimal totalAmount = new BigDecimal(sesame.getDouble("total_amount")).divide(times);
					sesame.put("total_amount", totalAmount.setScale(2, BigDecimal.ROUND_DOWN));
					String pid = sesame.getString("id");
					StringBuffer detailUrl = new StringBuffer("/wechat/html/sesame/detail.html?pid=");
					detailUrl.append(pid);
					detailUrl.append(userInfoUrl.toString());
					StringBuffer investUrl = new StringBuffer("/wechat/html/sesame/invest.html?pid=");
					investUrl.append(pid);
					investUrl.append(userInfoUrl.toString());
					sesame.put("detail_url", detailUrl.toString());
					sesame.put("invest_url", investUrl.toString());
					sesames.add(sesame);
				}
			}
		}catch(Exception e){
			logger.error("---------/sesame/ajax/list-----" + e.getMessage() + "-------id----" + id);
		}
		data.setInfo(sesames);
		return data;
	}
	
	@RequestMapping("detail/{pid}")
	@ResponseBody
	public JSONData detail(@PathVariable("pid") String pid){
		JSONData data = new ObjectData();
		StringBuffer url = new StringBuffer(Constant.getSesameBaseURL());
		url.append(Constant.SESAMEDETAIL);
		url.append("?productId=");
		url.append(pid);
		JSONObject sesame = new JSONObject();
		sesame.put("tenderDetailUrl", getTenderBeforeUrl());
		try{
			String responseText = RequestUtil.sendGETRequest(url.toString());
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				JSONObject dataObj = json.getJSONObject("data");
				sesame.put("title", dataObj.getString("title"));
				sesame.put("closePeriod", dataObj.getString("closed_period"));
				BigDecimal mutiple = new BigDecimal(10000);
				BigDecimal mutiple2 = new BigDecimal(100);
				BigDecimal maxAmount = new BigDecimal(dataObj.getString("max_amount"));
				BigDecimal totalAmount = new BigDecimal(dataObj.getString("total_amount"));
				sesame.put("percentage", totalAmount.divide(maxAmount,2,BigDecimal.ROUND_CEILING).multiply(mutiple2));
				sesame.put("maxAmount", maxAmount.divide(mutiple).setScale(2, BigDecimal.ROUND_DOWN));
				sesame.put("totalAmount", totalAmount.divide(mutiple).setScale(2, BigDecimal.ROUND_DOWN));
				sesame.put("restAmount", maxAmount.subtract(totalAmount).setScale(0, BigDecimal.ROUND_DOWN));
				sesame.put("holdTime", dataObj.getString("investment_period"));
				sesame.put("number", dataObj.getString("total_user_number"));
				sesame.put("lasttime", dataObj.getString("surplusSeconds"));
				sesame.put("minRate", dataObj.getString("minRate"));
				BigDecimal maxRate = new BigDecimal(dataObj.getString("maxRate"));
				sesame.put("maxRate",maxRate.setScale(1, BigDecimal.ROUND_DOWN));
				sesame.put("incomeTable", dataObj.getJSONArray("incomeTable"));
				sesame.put("exitTimeStr", dataObj.getString("exitTimeStr"));
				sesame.put("productStatus", dataObj.getString("productStatus"));
				sesame.put("everyAmountStr", dataObj.getString("everyAmountStr"));
				sesame.put("transferLimit", dataObj.getString("transferLimit"));
				sesame.put("payInterstTimeStr", dataObj.getString("payInterstTimeStr"));
				sesame.put("interstTimeStr", dataObj.getString("interstTimeStr"));
				sesame.put("transferAmount", dataObj.getString("transferAmount"));
				sesame.put("transferRule", dataObj.getString("transferRule"));
				sesame.put("transferLimit", dataObj.getString("transferLimit"));
				sesame.put("endCloseDateStr", dataObj.getString("endCloseDateStr"));
			}
			data.setStatus(1);
		}catch(Exception e){
			System.out.println("----------" + e.getMessage());
		}
		data.setInfo(sesame);
		return data;
	}
	
	@RequestMapping("investors/{pid}")
	@ResponseBody
	public JSONData investors(@PathVariable("pid") String pid,@RequestParam(value="transactionType",required=false) String transactionType,
			@RequestParam(value="page",required=false) String pageNo){
		pageNo = Tools.notEmpty(pageNo) ? pageNo : "1";
		transactionType = Tools.notEmpty(transactionType) ? transactionType : "0";//0:购买,1:赎回
		JSONData data = new ObjectData();
		StringBuffer url = new StringBuffer(Constant.getSesameBaseURL());
		url.append(Constant.SESAMEINVESTORS);
		url.append("?productId=");
		url.append(pid);
		url.append("&transactionType=");
		url.append(transactionType);
		url.append("&pageNo=");
		url.append(pageNo);
		url.append("&pageSize=10");
		try{
			String responseText = RequestUtil.sendGETRequest(url.toString());
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				String code = json.getString("code");
				if("0".equals(code)){
					data.setStatus(1);
					JSONObject dataObj = json.getJSONObject("data");
					data.setInfo(dataObj.getJSONArray("result"));
				}
			}
		}catch(Exception e){
			
		}
		return data;
	}
	
	@RequestMapping("tenders/{pid}")
	@ResponseBody
	public JSONData tenders(@PathVariable("pid") String pid,@RequestParam(value="page",required=false) String page){
		page = Tools.notEmpty(page) ? page : "1";
		JSONData data = new ObjectData();
		StringBuffer url = new StringBuffer(Constant.getSesameBaseURL());
		url.append(Constant.SESAMETENDERLIST);
		url.append("?productId=");
		url.append(pid);
		url.append("&pageNo=");
		url.append(page);
		try{
			String responseText = RequestUtil.sendGETRequest(url.toString());
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				String code = json.getString("code");
				if("0".equals(code)){
					data.setStatus(1);
					JSONObject dataObj = json.getJSONObject("data");
					data.setInfo(dataObj.getJSONArray("result"));
				}
			}
		}catch(Exception e){
			
		}
		return data;
	}
	
	@RequestMapping("mytenders/{pid}")
	@ResponseBody
	public JSONData mytenders(@PathVariable("pid") String pid,@RequestParam(value="page",required=false) String page,
			@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform,
			@RequestParam(value="accId",required=false) String accId){
		page = Tools.notEmpty(page) ? page : "1";
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData data = new ObjectData();
		List<NameValuePair> params = getParams(id, platform);
		params.add(new BasicNameValuePair("productId", pid));
		params.add(new BasicNameValuePair("pageNo", page));
		params.add(new BasicNameValuePair("accId", accId));
		try{
			String responseText = RequestUtil.sendGETRequest(getSesameUrl(Constant.SESAMEMYTENDERLIST), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				String code = json.getString("code");
				if("0".equals(code)){
					data.setStatus(1);
					JSONObject dataObj = json.getJSONObject("data");
					data.setInfo(dataObj.getJSONArray("result"));
				}
			}
		}catch(Exception e){
			
		}
		return data;
	}
	
	@RequestMapping("invest/init/{pid}")
	@ResponseBody
	public JSONData investInit(@PathVariable("pid") String pid,
			@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData data = new ObjectData();
		try{
			data.setStatus(1);
			List<NameValuePair> params = getParams(id, platform);
			params.add(new BasicNameValuePair("productId", pid));
			String responseText = RequestUtil.sendGETRequest(getSesameUrl(Constant.SESAMEINVESTINIT), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				String code = json.getString("code");
				if("0".equals(code)){
					JSONObject info = json.getJSONObject("data");
					double currPeriodCanInvest = info.getDoubleValue("currPeriodCanInvest");
					BigDecimal totalAmount = new BigDecimal(info.getDoubleValue("total_amount"));
					BigDecimal maxAmount = new BigDecimal(info.getDoubleValue("max_amount"));
					BigDecimal mutiple = new BigDecimal(10000);
					double restAmount = maxAmount.subtract(totalAmount).doubleValue();
					double restAmountValue = currPeriodCanInvest < restAmount ? currPeriodCanInvest : restAmount;
					BigDecimal restDecimal = new BigDecimal(restAmountValue).divide(mutiple).setScale(2, BigDecimal.ROUND_DOWN);
					info.put("restAmount", restDecimal.toPlainString());
					String responseText2 = RequestUtil.sendGETRequest(getUrl(Constant.LOADSTATINFO), params);
		      if (Tools.notEmpty(responseText2)) {
		        JSONObject json2 = JSONObject.parseObject(responseText2);
		        JSONObject jsonObj2 = (JSONObject)json2.get("jsonData");
		        JSONObject dataObj2 = jsonObj2.getJSONObject("data");
		        info.put("balance", dataObj2.getString("balance"));
		      }
					data.setInfo(info);
				}
			}
		}catch(Exception e){
			data.setStatus(0);
		}
		return data;
	}
	
	@RequestMapping("invest")
	@ResponseBody
	public JSONData invest(@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform,
			@RequestParam(value="amount",required=false) String amount,@RequestParam(value="pid",required=false) String pid){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData data = new ObjectData();
		JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(id);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("app_secret", userInfo.getString("app_secret")));
		params.add(new BasicNameValuePair("platform", platform));
		params.add(new BasicNameValuePair("amount", amount));
		params.add(new BasicNameValuePair("productId", pid));
		if(Constant.PLATFORM.equals(platform)){
			params.add(new BasicNameValuePair("user_id", userInfo.getString("out_uid")));
			params.add(new BasicNameValuePair("userId", userInfo.getString("out_uid")));
			params.add(new BasicNameValuePair("openid", id));
			params.add(new BasicNameValuePair("account", Constant.getAppId()));
		} else {
			params.add(new BasicNameValuePair("user_id", id));
			params.add(new BasicNameValuePair("userId", id));
		}
		try{
			String responseText = RequestUtil.sendGETRequest(getSesameUrl(Constant.SESAMEINVEST), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = JSONObject.parseObject(responseText);
				String code = json.getString("code");
				if("0".equals(code)){
					data.setStatus(1);
					data.setUrl("/wechat/html/account/myaccount.html?id=" + id + "&platform=" + platform);
					data.setInfo(Constant.INVESTTIP);
				} else {
					data.setInfo(Constant.INVESTERROR);
				}
			}
		}catch(Exception e){
			logger.error("------sesame/ajax/invest---id:---" + id + "-----pid:---" + pid + "------platform:--" + platform + "------" + e.getMessage());
		}
		return data;
	}
	
	@RequestMapping("mylist")
	@ResponseBody
	public JSONData myList(@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform,
			@RequestParam(value="tranType",required=false) String tranType,@RequestParam(value="page",required=false) String page){
		page = Tools.notEmpty(page) ? page : "1";
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		tranType = Tools.notEmpty(tranType) ? tranType : "0";
		JSONData data = new ObjectData();
		JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(id);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("app_secret", userInfo.getString("app_secret")));
		params.add(new BasicNameValuePair("platform", platform));
		params.add(new BasicNameValuePair("tranType", tranType));
		params.add(new BasicNameValuePair("pageNo", page));
		if(Constant.PLATFORM.equals(platform)){
			params.add(new BasicNameValuePair("user_id", userInfo.getString("out_uid")));
			params.add(new BasicNameValuePair("userId", userInfo.getString("out_uid")));
			params.add(new BasicNameValuePair("openid", id));
		} else {
			params.add(new BasicNameValuePair("user_id", id));
			params.add(new BasicNameValuePair("userId", id));
		}
		try{
			String responseText = RequestUtil.sendGETRequest(getSesameUrl(Constant.MYSESAMELIST), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = JSONObject.parseObject(responseText);
				if("0".equals(json.getString("code"))){
					data.setStatus(1);
					JSONObject dataObj = json.getJSONObject("data");
					data.setInfo(dataObj.getJSONArray("result"));
				} else {
					data.setInfo(Constant.DATAERROR);
				}
			}
		}catch(Exception e){
			
		}
		return data;
	}
	
	@RequestMapping("mysesame/{pid}")
	@ResponseBody
	public JSONData mySesame(@PathVariable("pid") String pid,@RequestParam(value="id",required=false) String id,
			@RequestParam(value="platform",required=false) String platform,@RequestParam(value="tranType",required=false) String tranType,
			@RequestParam(value="accId",required=false) String accId){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		tranType = Tools.notEmpty(tranType) ? tranType : "0";
		JSONData data = new ObjectData();
		JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(id);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("app_secret", userInfo.getString("app_secret")));
		params.add(new BasicNameValuePair("platform", platform));
		params.add(new BasicNameValuePair("tranType", tranType));
		params.add(new BasicNameValuePair("productId", pid));
		params.add(new BasicNameValuePair("accId", accId));
		if(Constant.PLATFORM.equals(platform)){
			params.add(new BasicNameValuePair("user_id", userInfo.getString("out_uid")));
			params.add(new BasicNameValuePair("userId", userInfo.getString("out_uid")));
			params.add(new BasicNameValuePair("openid", id));
		} else {
			params.add(new BasicNameValuePair("user_id", id));
			params.add(new BasicNameValuePair("userId", id));
		}
		try{
			String responseText = RequestUtil.sendGETRequest(getSesameUrl(Constant.MYSESAMEDETAIL), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = JSONObject.parseObject(responseText);
				if("0".equals(json.getString("code"))){
					data.setStatus(1);
					JSONObject dataObj = json.getJSONObject("data");
					dataObj.put("tenderDetailUrl", getTenderUrl());
					data.setInfo(dataObj);
				}
			}
		}catch(Exception e){
			
		}
		return data;
	}
}

