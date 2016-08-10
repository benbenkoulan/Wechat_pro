/**
 * Project Name:wechat-20160517
 * File Name:WmpAjaxController.java
 * Package Name:com.eloancn.wechat.controller
 * Date:2016年5月22日下午3:21:45
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import com.eloancn.wechat.common.utils.DateUtil;
import com.eloancn.wechat.common.utils.RequestUtil;
import com.eloancn.wechat.common.utils.Tools;
import com.eloancn.wechat.entities.InfoData;
import com.eloancn.wechat.entities.JSONData;
import com.eloancn.wechat.entities.ObjectData;

/**
 * ClassName: WmpAjaxController <br/>
 * Function: 翼农计划异步请求. <br/>
 * date: 2016年5月22日 下午3:21:45 <br/>
 *
 * @author liben
 * @version 
 */
@Controller
@RequestMapping("/wmps/ajax/")
public class WmpAjaxController extends BaseController {
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("list")
	@ResponseBody
	public JSONData list(@RequestParam(value="page",required=false) String page,
			@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform){
		JSONData data = new ObjectData();
		page = Tools.isEmpty(page) ? "1" : page;
		id = Tools.notEmpty(id) ? id : "";
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		List<NameValuePair> params = getParams(id, platform);
		params.add(new BasicNameValuePair("page", page));
		StringBuffer url = new StringBuffer(Constant.getBaseURL());
		url.append("/mobile");
		url.append(Constant.LOADALLRECORD);
		JSONArray products = new JSONArray();
		try{
			JSONObject isVip = getVipInfo(id, platform);
			String responseText = RequestUtil.sendGETRequest(url.toString(), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = JSONObject.parseObject(responseText);
				JSONObject jsonObj = (JSONObject) json.get("jsonData");
				if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))){
					data.setStatus(1);
					JSONObject dataObj = jsonObj.getJSONObject("data");
					JSONArray dataArr = dataObj.getJSONArray("data");
					Iterator it = dataArr.iterator();
					StringBuffer userInfoUrl = new StringBuffer("&id=");
					userInfoUrl.append(id);
					userInfoUrl.append("&platform=");
					userInfoUrl.append(platform);
					while(it.hasNext()){
						JSONObject obj = (JSONObject)it.next();
						JSONObject product1 = new JSONObject();
						product1.put("icon", obj.getString("icon"));
						product1.put("period",obj.getString("title"));
						String[] temList = obj.getString("strInterestrate").split("\\+");
						if(temList.length > 1) {
							product1.put("interest", temList[0]);
							product1.put("ewai", temList[1]);
						} else {
							product1.put("interest", obj.getString("strInterestrate").split("\\%")[0]);
						}
						product1.put("term", obj.getString("strPhases"));
						String doubleValue = String.format("%.2f", obj.getDoubleValue("amount") / 10000);
						if("00".equals(doubleValue.split("\\.")[1])) {
							product1.put("raised", Double.parseDouble(doubleValue.split("\\.")[0]));
						} else {
							product1.put("raised", Double.parseDouble(doubleValue));
						}
						double maxAmount = obj.getDoubleValue("SLMaxAmount");
						BigDecimal sum= new BigDecimal(maxAmount);
						product1.put("sum", sum.setScale(2,BigDecimal.ROUND_DOWN));
						product1.put("pid", obj.getString("id"));
						product1.put("lasttime", obj.getLong("strDongTime"));
						product1.put("status", obj.getString("status"));
						String pid = obj.getString("id");
						StringBuffer detailUrl = new StringBuffer("/wechat/html/wmps/detail.html?pid=");
						detailUrl.append(pid);
						detailUrl.append(userInfoUrl.toString());
						StringBuffer investUrl = new StringBuffer("/wechat/html/wmps/invest.html?pid=");
						investUrl.append(pid);
						investUrl.append(userInfoUrl.toString());
						product1.put("detail_url", detailUrl.toString());
						product1.put("invest_url", investUrl.toString());
						product1.put("vip_rate", isVip.get("strRatio"));
						products.add(product1);
					}
				}
			}
		} catch(Exception e){
			logger.error("---------/wmps/ajax/list-----" + e.getMessage() + "-----------");
		}
		data.setInfo(products);
		return data;
	}
	
	@RequestMapping("detail/{pid}")
	@ResponseBody
	public JSONData detail(@PathVariable("pid") String pid,
			@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform){
		JSONData data = new ObjectData();
		JSONObject info = new JSONObject();
		StringBuffer tenderUrl = new StringBuffer(Constant.getBaseURL());
		tenderUrl.append("/tender/tenderDetailBefore.jsp?tenderId=");
		info.put("tenderDetailUrl", tenderUrl.toString());
		BigDecimal totalInterest = new BigDecimal("0");
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		List<NameValuePair> params = getParams(id, platform);
		params.add(new BasicNameValuePair("id", pid));
		try{
			data.setStatus(1);
			JSONObject vip = getVipInfo(id, platform);
			String strRatio = vip.getString("strRatio");
			strRatio = Tools.notEmpty(strRatio) ? strRatio : "0";
			totalInterest = totalInterest.add(new BigDecimal(strRatio));
			info.put("strRatio", strRatio);
			String responseText = RequestUtil.sendGETRequest(getUrl(Constant.LOADWMPSRECORDDETAIL), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = JSONObject.parseObject(responseText);
				JSONObject jsonObj = (JSONObject) json.get("jsonData");
				JSONObject dataObj = jsonObj.getJSONObject("data");
				info.put("period", dataObj.getString("title"));
				info.put("term", Tools.replaceChinese(dataObj.getString("strPhases")));
				String interDate = dataObj.getString("strInterdate");
				String strPhases = dataObj.getString("strPhases");
				info.put("strInterdate", interDate);
				if(!Tools.isEmpty(interDate) && !Tools.isEmpty(strPhases)){
					info.put("firstInterDate", DateUtil.getAfterDayDate(DateUtil.fomatDate(interDate), "1", "yyyy-MM-dd"));
					info.put("outDate", DateUtil.getAfterDayDate(DateUtil.fomatDate(interDate), Tools.replaceChinese(strPhases), "yyyy-MM-dd"));
				}
				info.put("lasttime", dataObj.getString("strDongTime"));
				String strInterestrate = dataObj.getString("strInterestrate");
				String[] interests = strInterestrate.replace("%", "").split("\\+");
				info.put("interest1", interests[0]);
				totalInterest = totalInterest.add(new BigDecimal(interests[0]));
				if(interests.length > 1){
					info.put("interest2", interests[1]);
					totalInterest = totalInterest.add(new BigDecimal(interests[1]));
				}
				BigDecimal multiple = new BigDecimal("100");
				BigDecimal multiple2 = new BigDecimal("10000");
				BigDecimal maxAmount = new BigDecimal(dataObj.getString("maxAmount")).divide(multiple2);
				BigDecimal totalAmount = new BigDecimal(dataObj.getString("amount")).divide(multiple2);
				BigDecimal restAmount = maxAmount.subtract(totalAmount);
				info.put("maxAmount", maxAmount.setScale(2, BigDecimal.ROUND_DOWN));
				info.put("totalAmount", totalAmount.setScale(2, BigDecimal.ROUND_DOWN));
				info.put("restAmount", restAmount.setScale(2, BigDecimal.ROUND_DOWN));
				info.put("totalInterest", totalInterest.divide(new BigDecimal(100)).toPlainString());
				int percentage = totalAmount.divide(maxAmount,2,BigDecimal.ROUND_HALF_EVEN).multiply(multiple).intValue();
				if(percentage == 0 && totalAmount.doubleValue() > 0){
					percentage = 1;
				} 
				info.put("percentage", percentage);
				JSONObject pageinfoJSON = dataObj.getJSONObject("pageinfoJSON");
				info.put("investors", pageinfoJSON.getJSONArray("data"));
				info.put("number", dataObj.getString("yuGouNum"));
				info.put("status", dataObj.getString("status"));
				info.put("icon", dataObj.getString("icon"));
			}
			String responseText2 = RequestUtil.sendGETRequest(getUrl(Constant.LOADSTATINFO), params);
			if(Tools.notEmpty(responseText2)){
				JSONObject json2 = JSONObject.parseObject(responseText2);
				JSONObject jsonObj2 = (JSONObject) json2.get("jsonData");
				JSONObject dataObj2 = jsonObj2.getJSONObject("data");
				info.put("balance", dataObj2.getString("balance"));
			}
		}catch(Exception e){
			e.printStackTrace();
			data.setStatus(0);
			logger.error("-------wmps/ajax/detail/" + pid + "-------" + e.getMessage());
		}
		data.setInfo(info);
		return data;
	}
	
	@RequestMapping("invest/list/{pid}")
	@ResponseBody
	public JSONData investList(@PathVariable("pid") String pid,@RequestParam(value="page",required=false) String page,
			@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData data = new ObjectData();
		List<NameValuePair> params = getParams(id, platform);
		params.add(new BasicNameValuePair("id", pid));
		params.add(new BasicNameValuePair("page", page));
		try{
			String responseText = RequestUtil.sendGETRequest(getUrl(Constant.LOADWMPSRECORDDETAIL), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = JSONObject.parseObject(responseText);
				JSONObject jsonObj = (JSONObject) json.get("jsonData");
				JSONObject dataObj = jsonObj.getJSONObject("data");
				JSONObject pageinfoJSON = dataObj.getJSONObject("pageinfoJSON");
				data.setInfo(pageinfoJSON.getJSONArray("data"));
				data.setStatus(1);
			}
		}catch(Exception e){
			logger.error("-------wmps/ajax/invest/list/" + pid + "-------" + e.getMessage());
		}
		return data;
	}
	
	@RequestMapping("invest/init/{pid}")
	@ResponseBody
	public JSONData ivestInit(@PathVariable("pid") String pid,@RequestParam(value="id",required=false) String id,
			@RequestParam(value="platform",required=false) String platform){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData data = new ObjectData();
		JSONObject info = new JSONObject();
		StringBuffer licaiUrl = new StringBuffer(Constant.getBaseURL());
		licaiUrl.append(Constant.LICAIXIEYI_NOUSER);
		licaiUrl.append("?wid=");
		licaiUrl.append(pid);
		info.put("licaiUrl", licaiUrl.toString());
		BigDecimal totalInterest = new BigDecimal("0");
		List<NameValuePair> params = getParams(id,platform);
		params.add(new BasicNameValuePair("id", pid));
		try{
			data.setStatus(1);
			JSONObject vip = getVipInfo(id, platform);
			String strRatio = vip.getString("strRatio");
			strRatio = Tools.notEmpty(strRatio) ? strRatio : "0";
			totalInterest = totalInterest.add(new BigDecimal(strRatio));
			info.put("strRatio", strRatio);
			String responseText = RequestUtil.sendGETRequest(getUrl(Constant.LOADWMPSRECORDDETAIL), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = JSONObject.parseObject(responseText);
				JSONObject jsonObj = (JSONObject) json.get("jsonData");
				JSONObject dataObj = jsonObj.getJSONObject("data");
				info.put("icon", dataObj.getString("icon"));
				info.put("period", dataObj.getString("title"));
				info.put("term", Tools.replaceChinese(dataObj.getString("strPhases")));
				info.put("lasttime", dataObj.getString("strDongTime"));
				String strInterestrate = dataObj.getString("strInterestrate");
				String[] interests = strInterestrate.replace("%", "").split("\\+");
				info.put("interest1", interests[0]);
				totalInterest = totalInterest.add(new BigDecimal(interests[0]));
				if(interests.length > 1){
					info.put("interest2", interests[1]);
					totalInterest = totalInterest.add(new BigDecimal(interests[1]));
				}
				BigDecimal maxAmount = new BigDecimal(dataObj.getString("maxAmount"));
				BigDecimal totalAmount = new BigDecimal(dataObj.getString("amount"));
				info.put("restAmount", maxAmount.subtract(totalAmount).setScale(0, BigDecimal.ROUND_DOWN));
				info.put("totalInterest", totalInterest.divide(new BigDecimal(100)).toPlainString());
			}
			String responseText2 = RequestUtil.sendGETRequest(getUrl(Constant.LOADSTATINFO), params);
			if(Tools.notEmpty(responseText2)){
				JSONObject json2 = JSONObject.parseObject(responseText2);
				JSONObject jsonObj2 = (JSONObject) json2.get("jsonData");
				JSONObject dataObj2 = jsonObj2.getJSONObject("data");
				info.put("balance", dataObj2.getString("balance"));
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("-------wmps/ajax/invest/init-------" + e.getMessage());
		}
		data.setInfo(info);
		return data;
	}
	
	@RequestMapping("invest")
	@ResponseBody
	public JSONData invest(@RequestParam(value="id",required=false) String id,@RequestParam(value="pid",required=false) String pid,
			@RequestParam(value="platform",required=false) String platform,@RequestParam(value="amount",required=false) String amount,
			@RequestParam(value="type",required=false) String type){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData info = new InfoData();
		info.setInfo(Constant.INVESTERROR);
		StringBuffer url1 = new StringBuffer(Constant.getBaseURL());
		url1.append("/mobile");
		url1.append(Constant.TOKEN);
		List<NameValuePair> params = getParams(id, platform);
		try{
			String responseText1 = RequestUtil.sendGETRequest(url1.toString(), params);
			if(Tools.notEmpty(responseText1)){
				JSONObject json1 = JSONObject.parseObject(responseText1);
				String token = json1.getString("data");
				params.add(new BasicNameValuePair("token", token));
				params.add(new BasicNameValuePair("wid", pid));
				params.add(new BasicNameValuePair("amount", amount));
				StringBuffer url2 = new StringBuffer(Constant.getBaseURL());
				url2.append("/mobile");
				if("1".equals(type)){
					url2.append(Constant.ADDWMPSBUYRECORD);
				} else {
					url2.append(Constant.ADDWMPSEXPRIENCEBUYRECORD);
				}
				String responseText2 = RequestUtil.sendGETRequest(url2.toString(), params);
				if(Tools.notEmpty(responseText2)){
					JSONObject json2 = JSONObject.parseObject(responseText2);
					JSONObject jsonObj2 = (JSONObject) json2.get("jsonData");
					String tip = jsonObj2.getString(Constant.JSONDATATIP);
					if(Constant.REQUESTSUCCESS.equals(tip)){
						info.setStatus(1);
						info.setInfo(Constant.INVESTTIP);
						if(Constant.PLATFORM.equals(platform)){
							info.setUrl("/wechat/html/account/myaccount.html?id=" + id + "&platform=" + platform);
						} else {//WAP地址
							
						}
					} else {
						info.setInfo(tip);
					}
				}
			}
		}catch(Exception e){
			logger.error("-------wmps/ajax/invest-------" + e.getMessage());
		}
		return info;
	}
	
	@RequestMapping("tenders/{pid}")
	@ResponseBody
	public JSONData tenders(@PathVariable("pid") String pid,@RequestParam(value="page",required=false) String page){
		page = Tools.notEmpty(page) ? page : "1";
		JSONData data = new ObjectData();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", pid));
		params.add(new BasicNameValuePair("page", page));
		try{
			String responseText = RequestUtil.sendGETRequest(getUrl(Constant.LOADTENDERLIST), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = JSONObject.parseObject(responseText);
				JSONObject jsonObj = (JSONObject) json.get("jsonData");
				if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))){
					data.setStatus(1);
					data.setInfo(getLenderDetails(jsonObj));
				}
			}
		}catch(Exception e){
			logger.error("-------wmps/ajax/tenders----" + pid + "---" + e.getMessage());
		}
		return data;
	}
	
	@RequestMapping("gettenders")
	@ResponseBody
	public JSONData getTenders(@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform,
			@RequestParam(value="pid",required=false) String pid,@RequestParam(value="type",required=false) String type,
			@RequestParam(value="amount",required=false) String amount){
		JSONData data = new ObjectData();
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		List<NameValuePair> params = getParams(id, platform);
		params.add(new BasicNameValuePair("proID", pid));
		params.add(new BasicNameValuePair("amount", amount));
		try{
			String responseText = RequestUtil.sendGETRequest(getUrl(Constant.MATCHACTUAL), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				String tip = json.getString("tip");
				if("success".equals(tip)){
					data.setStatus(1);
					data.setInfo(json.getJSONObject("data"));
				}
			}
		}catch(Exception e){
			logger.error("------/wmps/ajax/gettenders----id---" + id + "-----platform----- " + platform + "-----------" + e.getMessage());
			logger.error("------/wmps/ajax/gettenders----pid---" + pid + "-----amount----- " + amount);
		}
		return data;
	}
}

