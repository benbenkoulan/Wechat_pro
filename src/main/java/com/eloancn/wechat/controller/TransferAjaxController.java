package com.eloancn.wechat.controller;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.eloancn.wechat.common.constant.Constant;
import com.eloancn.wechat.common.utils.RequestUtil;
import com.eloancn.wechat.common.utils.Tools;
import com.eloancn.wechat.entities.JSONData;
import com.eloancn.wechat.entities.ObjectData;

@Controller
@RequestMapping("/transfer/ajax/")
public class TransferAjaxController extends BaseController {
	
	@RequestMapping("list")
	@ResponseBody
	public JSONData list(@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform,
			@RequestParam(value="timeType",required=false) String timeType,@RequestParam(value="amountType",required=false) String amountType,
			@RequestParam(value="page",required=false) String page){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData data = new ObjectData();
		List<NameValuePair> params = getParams(id, platform);
		params.add(new BasicNameValuePair("timeType", timeType));
		params.add(new BasicNameValuePair("amountType", amountType));
		params.add(new BasicNameValuePair("pageNo", page));
		try{
			String responseText = RequestUtil.sendGETRequest(getSesameUrl(Constant.GETTRANSFERLIST), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				if("0".equals(json.getString("code"))){
					JSONObject dataObj = json.getJSONObject("data");
					data.setInfo(dataObj.getJSONArray("result"));
					data.setStatus(1);
				} else {
					data.setInfo(json.getString("message"));
				}
			}
		}catch (Exception e) {
			data.setInfo(Constant.GETRANSFERLISTERROR);
			logger.error("------/transfer/ajax/list---id:---" + id + "-----timeType:---" + timeType + "------platform:--" + platform + "----------amountType:---------" + amountType + e.getMessage());
		}
		return data;
	}
	
	@RequestMapping("detail_init")
	@ResponseBody
	public JSONData detail(@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform,
			@RequestParam(value="accId",required=false) String accId,@RequestParam(value="userId",required=false) String userId,
			@RequestParam(value="productId",required=false) String productId,@RequestParam(value="recordId",required=false) String recordId){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData data = new ObjectData();
		List<NameValuePair> params = getParamsWithoutUserId(id, platform);
		params.add(new BasicNameValuePair("accId", accId));
		params.add(new BasicNameValuePair("userId", userId));
		params.add(new BasicNameValuePair("productId", productId));
		params.add(new BasicNameValuePair("recordId", recordId));
		try{
			String responseText = RequestUtil.sendGETRequest(getSesameUrl(Constant.TRANSFERDETAIL), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				if("0".equals(json.getString("code"))){
					data.setInfo(json.getJSONObject("data"));
					data.setStatus(1);
				} else {
					data.setInfo(json.getString("message"));
				}
			}
		}catch (Exception e) {
			data.setInfo(Constant.GETTRANSFERDETAILERROR);
			logger.error("------/transfer/ajax/detail_init---id:---" + id + "-----accId:---" + accId + "------platform:--" + platform + "----------userId:---------" + userId + "------productId:--------" + productId + "-------recordId:--------" + recordId + "-----------" + e.getMessage());
		}
		return data;
	}
	
	@RequestMapping("tenders")
	@ResponseBody
	public JSONData tenders(@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform,
			@RequestParam(value="accId",required=false) String accId,@RequestParam(value="userId",required=false) String userId,
			@RequestParam(value="productId",required=false) String productId,@RequestParam(value="page",required=false) String page){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData data = new ObjectData();
		List<NameValuePair> params = getParamsWithoutUserId(id, platform);
		params.add(new BasicNameValuePair("accId", accId));
		params.add(new BasicNameValuePair("userId", userId));
		params.add(new BasicNameValuePair("productId", productId));
		params.add(new BasicNameValuePair("pageNo", page));
		try{
			String responseText = RequestUtil.sendGETRequest(getSesameUrl(Constant.TRANSFERTENDERLIST), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				if("0".equals(json.getString("code"))){
					JSONObject dataObj = json.getJSONObject("data");
					data.setInfo(dataObj.getJSONArray("result"));
					data.setStatus(1);
				} else {
					data.setInfo(json.getString("message"));
				}
			}
		}catch (Exception e) {
			logger.error("------/transfer/ajax/tenders---id:---" + id + "-----accId:---" + accId + "------platform:--" + platform + "----------userId:---------" + userId + "------productId:--------" + productId + "-----------" + e.getMessage());
		}
		return data;
	}
	
	@RequestMapping("cancel_transfer")
	@ResponseBody
	public JSONData cancelTransfer(@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform,
			@RequestParam(value="accId",required=false) String accId,@RequestParam(value="productId",required=false) String productId){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData data = new ObjectData();
		List<NameValuePair> params = getParamsWithoutUserId(id, platform);
		params.add(new BasicNameValuePair("accId", accId));
		params.add(new BasicNameValuePair("productId", productId));
		try{
			String responseText = RequestUtil.sendGETRequest(getSesameUrl(Constant.CANCELTRANSFER), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				if("0".equals(json.getString("code"))){
					data.setInfo(Constant.CANCELTRANSFERTIP);
					data.setUrl("/wechat/html/account/myaccount.html?id=" + id + "&platform=" + platform);
					data.setStatus(1);
				} else {
					data.setInfo(json.getString("message"));
				}
			}
		}catch (Exception e) {
			data.setInfo(Constant.CANCELTRANSFERERROR);
			logger.error("------/transfer/ajax/cancel_transfer---id:---" + id + "-----accId:---" + accId + "------platform:--" + platform + "-----------" + e.getMessage());
		}
		return data;
	}
	
	@RequestMapping("transfer_init")
	@ResponseBody
	public JSONData transferInit(@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform,
			@RequestParam(value="accId",required=false) String accId,@RequestParam(value="productId",required=false) String productId){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData data = new ObjectData();
		List<NameValuePair> params = getParamsWithoutUserId(id, platform);
		params.add(new BasicNameValuePair("accId", accId));
		params.add(new BasicNameValuePair("productId", productId));
		try{
			String responseText = RequestUtil.sendGETRequest(getSesameUrl(Constant.TRANSFERINIT), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				if("0".equals(json.getString("code"))){
					data.setInfo(json.getJSONObject("data"));
					data.setStatus(1);
				} else {
					data.setInfo(json.getString("message"));
				}
			}
		}catch (Exception e) {
			logger.error("------/transfer/ajax/transfer_init---id:---" + id + "-----accId:---" + accId + "------platform:--" + platform + "-----------" + e.getMessage());
		}
		return data;
	}
	
	@RequestMapping("transfer")
	@ResponseBody
	public JSONData transfer(@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform,
			@RequestParam(value="accId",required=false) String accId,@RequestParam(value="productId",required=false) String productId,
			@RequestParam(value="pass",required=false) String pass){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData data = new ObjectData();
		List<NameValuePair> params = getParams(id, platform);
		params.add(new BasicNameValuePair("accId", accId));
		params.add(new BasicNameValuePair("productId", productId));
		params.add(new BasicNameValuePair("pass", pass));
		try{
			String responseText = RequestUtil.sendGETRequest(getSesameUrl(Constant.TRANSFERSESAME), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				if("0".equals(json.getString("code"))){
					data.setInfo(Constant.TRANSFERTIP);
					data.setUrl("/wechat/html/account/myaccount.html?id=" + id + "&platform=" + platform);
					data.setStatus(1);
				} else {
					data.setInfo(json.getString("message"));
				}
			}
		}catch (Exception e) {
			data.setInfo(Constant.TRANSFERERROR);
			logger.error("------/transfer/ajax/transfer---id:---" + id + "-----accId:---" + accId + "------pass:--" + pass + "-----------" + e.getMessage());
		}
		return data;
	}
	
	@RequestMapping("invest_init")
	@ResponseBody
	public JSONData investInit(@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform,
			@RequestParam(value="accId",required=false) String accId,@RequestParam(value="userId",required=false) String userId,
			@RequestParam(value="productId",required=false) String productId,@RequestParam(value="recordId",required=false) String recordId){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData data = new ObjectData();
		JSONObject infoObj = new JSONObject();
		List<NameValuePair> params = getParamsWithoutUserId(id, platform);
		params.add(new BasicNameValuePair("accId", accId));
		params.add(new BasicNameValuePair("userId", userId));
		params.add(new BasicNameValuePair("productId", productId));
		params.add(new BasicNameValuePair("recordId", recordId));
		try{
			String responseText = RequestUtil.sendGETRequest(getSesameUrl(Constant.INVESTTRANSFERINIT), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				if("0".equals(json.getString("code"))){
					infoObj = json.getJSONObject("data");
					data.setStatus(1);
				} else {
					data.setInfo(json.getString("message"));
				}
			}
		}catch (Exception e) {
			logger.error("------/transfer/ajax/invest_init--1-id:---" + id + "-----accId:---" + accId + "------recordId:--" + recordId + "------userId:-----" + userId + "--------------" + e.getMessage());
		}
		try{
			String responseText = RequestUtil.sendGETRequest(getUrl(Constant.LOADSTATINFO), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				JSONObject jsonObj = json.getJSONObject("jsonData");
				JSONObject dataObj = jsonObj.getJSONObject("data");
				infoObj.put("balance", dataObj.getString("balance"));
				data.setStatus(1);
			}
		}catch(Exception e){
			logger.error("------/transfer/ajax/invest_init--2-id:---" + id + "-----accId:---" + accId + "------recordId:--" + recordId + "-----------" + e.getMessage());
		}
		data.setInfo(infoObj);
		return data;
	}
	
	@RequestMapping("invest")
	@ResponseBody
	public JSONData invest(@RequestParam(value="id",required=false) String id,@RequestParam(value="platform",required=false) String platform,
			@RequestParam(value="accId",required=false) String accId,@RequestParam(value="amount",required=false) String amount,
			@RequestParam(value="productId",required=false) String productId,@RequestParam(value="recordId",required=false) String recordId){
		platform = Tools.notEmpty(platform) ? platform : Constant.PLATFORM;
		JSONData data = new ObjectData();
		List<NameValuePair> params = getParams(id, platform);
		params.add(new BasicNameValuePair("accId", accId));
		params.add(new BasicNameValuePair("productId", productId));
		params.add(new BasicNameValuePair("recordId", recordId));
		params.add(new BasicNameValuePair("amount", amount));
		try{
			String responseText = RequestUtil.sendGETRequest(getSesameUrl(Constant.INVESTTRANSFER), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				if("0".equals(json.getString("code"))){
					data.setInfo(Constant.INVESTTRANSFERTIP);
					data.setUrl("/wechat/html/account/myaccount.html?id=" + id + "&platform=" + platform);
					data.setStatus(1);
				} else {
					data.setInfo(json.getString("message"));
				}
			}
		}catch (Exception e) {
			data.setInfo(Constant.INVESTTRANSFERERROR);
			logger.error("------/transfer/ajax/invest----id:---" + id + "-----accId:---" + accId + "------recordId:--" + recordId + "------amount:-----" + amount + "----------" + e.getMessage());
		}
		return data;
	}
}
