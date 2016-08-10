package com.eloancn.wechat.common.utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eloancn.wechat.common.constant.Constant;
import com.eloancn.wechat.entities.InfoData;
import com.eloancn.wechat.entities.JSONData;
import com.eloancn.wechat.entities.ObjectData;

public class RequestUtil {
	
	public static String localPort = null;
	/*
	 * 获得JSON数据
	 * **/
	public static JSONData getJSONData(String requestUrl , String page , String openId) throws Exception {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("page", page));
		JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(requestUrl, params, openId);
		JSONData data = null;
		if(null != jsonObj && Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
			data = new ObjectData();
			data.setStatus(1);
			data.setInfo(jsonObj.getObject("data" , Object.class));
		} else if(null != jsonObj) {
			data = new InfoData();
			data.setInfo(jsonObj.getString("data"));
		} else {
			data = new InfoData();
			data.setInfo("获取数据异常，请稍后再试");
		}
		return data;
	}
	
	/*
	 * 获得JSON数据
	 * **/
	public static JSONData getJSONData(String requestUrl , List<NameValuePair> params , String openId) throws Exception{
		JSONObject jsonObj = RequestUtil.getJSONObjectByHttpClient(requestUrl, params, openId);
		JSONData data = null;
		if(null != jsonObj && Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
			data = new ObjectData();
			data.setStatus(1);
			data.setInfo(jsonObj.getObject("data", Object.class));
		} else if(null != jsonObj){
			data = new InfoData();
			data.setInfo(jsonObj.getString("data"));
		} else {
			data = new InfoData();
			data.setInfo("获取数据异常，请稍后再试");
		}
		return data;
	}
	
	public static JSONObject getJSONObjectByHttpClient(String url , List<NameValuePair> params , String openId) throws Exception{
		String responseText = getResponseText(url , params , openId);
		JSONObject json = JSONObject.parseObject(responseText);
		return (JSONObject) json.get("jsonData");
	}
	
	public static JSONObject getJSONDataByHttpClient(String url , List<NameValuePair> params , String openId) throws Exception{
		String responseText = getResponseText(url , params , openId);
		JSONObject json = JSONObject.parseObject(responseText);
		return json;
	}
	
	public static JSONArray getJSONArrayByHttpClient(String url , List<NameValuePair> params , String openId) throws Exception{
		String responseText = getResponseText(url , params , openId);
		JSONObject json = JSONObject.parseObject(responseText);
		return json.getJSONArray("data");
	}
	
	public static String getResponseText(String url , List<NameValuePair> params , String openId) throws Exception{
			//更新Redis保存时间
			RefreshAllExpireTime(openId, Constant.EXPIRETIME);
			JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
			String app_secret = null != userInfo.getString("app_secret") ? userInfo.getString("app_secret") : "";
			String user_id = null != userInfo.getString("out_uid") ? userInfo.getString("out_uid") : "";
			if(params == null){
				params = new ArrayList<NameValuePair>();
			}
			params.add(new BasicNameValuePair("openid", openId));
			params.add(new BasicNameValuePair("accountno", Constant.getAppId()));
			params.add(new BasicNameValuePair("app_secret", app_secret));
			params.add(new BasicNameValuePair("user_id", user_id));
			params.add(new BasicNameValuePair("platform", Constant.PLATFORM));
			return sendGETRequest(Constant.getRequestUrl(localPort) + url , "utf-8" , params);
	}
	
	public static String sendGETRequest(String url , String charset , List<NameValuePair> params) throws Exception {
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		HttpPost post = new HttpPost(url);
		HttpEntity entity = new UrlEncodedFormEntity(params , charset);
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		if(response != null) {
			return IOUtils.toString(response.getEntity().getContent(), charset);
		} 
		return "";
	}
	
	public static String sendGETRequest(String url , List<NameValuePair> params) throws Exception {
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		HttpPost post = new HttpPost(url);
		HttpEntity entity = new UrlEncodedFormEntity(params , "utf-8");
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		if(response != null) {
			return IOUtils.toString(response.getEntity().getContent(), "utf-8");
		} 
		return "";
	}
	
	public static String sendGETRequest(String url , String charset) throws Exception {
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		HttpGet get = new HttpGet(url);
		get.setURI(new URI(url));  
		HttpResponse response = client.execute(get);
		if(response != null) {
			return IOUtils.toString(response.getEntity().getContent(), charset);
		} 
		return "";
	}
	
	public static String sendGETRequest(String url) throws Exception {
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		HttpGet get = new HttpGet(url);
		get.setURI(new URI(url));  
		HttpResponse response = client.execute(get);
		if(response != null) {
			return IOUtils.toString(response.getEntity().getContent(), "utf-8");
			
		} 
		return "";
	}
	
	/**
	 * sendPOSTRequest:发送post请求. <br/>
	 * @author liben
	 * @param url
	 * @param charset
	 * @param params
	 * @param request_body
	 * @return
	 * @throws Exception
	 */
	public static String sendPOSTRequest(String url , String charset , String request_body) throws Exception{
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		HttpPost method = new HttpPost(url);
		StringEntity entity = new StringEntity(request_body, charset);
		entity.setContentEncoding(charset);
		entity.setContentType("application/json");
		method.setEntity(entity);
		HttpResponse response = client.execute(method);
		if(response != null) {
			return IOUtils.toString(response.getEntity().getContent(), charset);
		} 
		return "";
	}
	
	/*
	 * 刷新所有redis缓存
	 * */
	public static void RefreshAllExpireTime(String openId , int expireTime){
		Set<String> keys = Constant.rediskeys.keySet();
		for(String key : keys){
			JedisUtil.RefreshExpireTime(openId + key, expireTime);
		}
	}
}
