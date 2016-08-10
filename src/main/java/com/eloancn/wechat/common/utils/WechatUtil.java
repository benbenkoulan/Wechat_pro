package com.eloancn.wechat.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eloancn.wechat.common.constant.Constant;
import com.eloancn.wechat.entities.WechatTemplateMsg;
import com.eloancn.wechat.entities.WechatTemplateParam;
import com.eloancn.wechat.model.WechatUser;

/**   
* @ClassName:WechatUtil.java 
* @Function com.eloancn.wechat.common.utils 
* @author: liben
* @date 2015年9月6日 下午1:59:33 
*/
public class WechatUtil {
	public static boolean CheckSignature(String signature , String timestamp , String nonce) {
		String[] arrs = new String[]{Constant.getWechatToken() , timestamp , nonce};
		Arrays.sort(arrs);  
		StringBuffer sb = new StringBuffer();
		for(int i = 0 , m = arrs.length;i < m;i++) {
			sb.append(arrs[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(sb.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return tmpStr != null ? tmpStr.equals(signature) : false;
	}
	
	 /** 
     * 将字节数组转换为十六进制字符串 
     * @param byteArray 
     * @return 
     */  
    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  
  
	
	 /** 
     * 将字节转换为十六进制字符串 
     * @param mByte 
     * @return 
     */  
    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  
  
        String s = new String(tempArr);  
        return s;  
    }  
    
    public static Date parseTimeStamp(String timeStamp){
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String d = dateFormat.format(timeStamp);
    	Date date = null;
    	try {
			date = dateFormat.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
    }

    public static JSONObject sendGetRequestAndParse(String url , List<NameValuePair> params) {
    	try {
    		String responseText = RequestUtil.sendGETRequest(url, "utf-8" , params);
				return JSONObject.parseObject(responseText);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	return new JSONObject();
    }
    
    public static JSONObject sendPOSTRequestAndParse(String url , Map<String,String> params , String request_body , int errorTime){
    	try {
    		if(null == params || params.isEmpty()){
    			params = new HashMap<String , String>();
    		}
  			params.put("access_token",getAccessTokenFromRedis());
  			url = String.format(url, params.values().toArray());
				String responseText = RequestUtil.sendPOSTRequest(url, "utf-8", request_body);
				JSONObject jsonObj = JSONObject.parseObject(responseText);
				//accesstoken过期或其他错误，重试
				String errmsg = jsonObj.getString("errmsg");
				if(Tools.notEmpty(errmsg) && !"ok".equals(errmsg) && errorTime < 3){
					errorTime++;
					RefreshAccessToken();
					params.remove("access_token");
					return sendPOSTRequestAndParse(url, params, request_body, errorTime);
				} 
				return jsonObj;
			} catch (Exception e) {
				e.printStackTrace();
			}
    	return new JSONObject();
    }
    
    //刷新Accesstoken
    public static JSONObject RefreshAccessToken(){
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("grant_type", "client_credential"));
    	params.add(new BasicNameValuePair("appid", Constant.getAppId()));
    	params.add(new BasicNameValuePair("secret", Constant.getAppSecret()));
    	JSONObject jsonObj = sendGetRequestAndParse(Constant.ACCESSTOKENURL , params);
    	JSONObject accessToken = new JSONObject();
    	accessToken.put("accessToken", jsonObj.getString("access_token"));
    	accessToken.put("createTime", new Date());
    	JedisUtil.saveObjectToRedis(Constant.getAppId(), accessToken, Constant.ACCESSTOKENTIME);
			return accessToken;
    }
    
    //刷新JSAPITicket
    public static String RefreshJSAPITicket(){
    	String accessToken = getAccessTokenFromRedis();
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("type", "jsapi"));
    	params.add(new BasicNameValuePair("access_token", accessToken));
    	JSONObject jsonObj = sendGetRequestAndParse(Constant.JSAPITICKETURL, params);
    	JSONObject jsapiTicket = new JSONObject();
    	jsapiTicket.put("ticket", jsonObj.getString("ticket"));
    	jsapiTicket.put("createTime", new Date());
    	JedisUtil.saveObjectToRedis(Constant.getAppId() + Constant.JSAPITICKET, jsapiTicket, Constant.ACCESSTOKENTIME);
    	return jsonObj.getString("ticket");
    }
    
    public static String getAccessTokenFromRedis() {
    	JSONObject accessToken = JedisUtil.getJSONObjectFromRedis(Constant.getAppId());
    	if(null == accessToken.getString("accessToken")) {
    		accessToken = RefreshAccessToken();
    	}
    	return accessToken.getString("accessToken");
    }
    
    public static JSONObject getAccessTokenByCode(String code) {
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("appid", Constant.getAppId()));
    	params.add(new BasicNameValuePair("secret", Constant.getAppSecret()));
    	params.add(new BasicNameValuePair("code", code));
    	params.add(new BasicNameValuePair("grant_type", "authorization_code"));
    	return sendGetRequestAndParse(Constant.CODEURL , params);
    }
    
    //根据openId获取微信用户信息
    public static JSONObject getWechatUserByOpenId(String openId) {
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
    	String accessToken = getAccessTokenFromRedis();
    	params.add(new BasicNameValuePair("openid", openId));
    	params.add(new BasicNameValuePair("access_token", accessToken));
    	return getWechatUserByOpenId(openId , Constant.WECHATUSERURL , params , 1);
    }
    
    public static JSONObject getWechatUserByOpenId(String openId , String url , List<NameValuePair> params , int errorTime) {
  		JSONObject wechatUser = sendGetRequestAndParse(url , params);
  		if(null != wechatUser && wechatUser.getString("errcode") != null && !"".equals(wechatUser.getString("errcode"))) {
  			if(errorTime <= 3){
					errorTime++;
					String accessToken = RefreshAccessToken().getString("accessToken");
					params.add(new BasicNameValuePair("access_token", accessToken));
					params.add(new BasicNameValuePair("openid", openId));
					return getWechatUserByOpenId(openId , Constant.WECHATUSERURL , params , errorTime);
  			} else {
  				return wechatUser;
  			}
  		}
  		return wechatUser;
    }
    
    public static WechatUser getWechatUser(String openId) {
    	return getWechatUser(openId , 1);
    }
    
    public static WechatUser getWechatUser(String openId , int errorTime) {
    	String accessToken = getAccessTokenFromRedis();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("access_token", accessToken));
    	params.add(new BasicNameValuePair("openid", openId));
    	JSONObject jsonObj = sendGetRequestAndParse(Constant.WECHATUSERURL, params);
			//accesstoken过期
			if(jsonObj.getString("errcode") != null && errorTime <= 3){
				errorTime++;
				RefreshAccessToken();
				return getWechatUser(openId , errorTime);
			} else {
				WechatUser wu = new WechatUser();
				wu.setOPENID(openId);
				wu.setSUBSCRIBE_TIME(DateUtil.getCurrentTime());
				wu.setCITY(jsonObj.getString("city"));
				wu.setNICKNAME(jsonObj.getString("nickname"));
				wu.setCOUNTRY(jsonObj.getString("country"));
				wu.setPROVINCE(jsonObj.getString("province"));
				wu.setHEADIMGURL(jsonObj.getString("headimgurl"));
				wu.setUNIONID(null != jsonObj.getString("unionid") ? jsonObj.getString("unionid") : "");
				wu.setREMARK(jsonObj.getString("remark"));
				wu.setSEX(null != jsonObj.get("sex") ? jsonObj.getInteger("sex") : 1);
				wu.setGROUPID(null != jsonObj.get("groupid") ? jsonObj.getString("groupid") : "0");
				wu.setLANGUAGE(jsonObj.getString("language"));
				wu.setTAG("");
				return wu;
			}
    }
    
    //获取jsticket
    public static String getJSAPITicket(boolean isExpired){
    	//如果jsticket过期，直接刷新获取
    	if(isExpired){
    		return RefreshJSAPITicket();
    	}
    	JSONObject jsapi = JedisUtil.getJSONObjectFromRedis(Constant.getAppId() + Constant.JSAPITICKET);
    	if(null == jsapi || Tools.isEmpty(jsapi.getString("ticket"))){
    		return RefreshJSAPITicket();
    	} else {
    		return jsapi.getString("ticket");
    	}
    }
    
    public static String getQRTicketTemp(String scene_id){
    	JSONObject qrcode = new JSONObject();
    	qrcode.put("action_name", "QR_SCENE");
    	qrcode.put("expire_seconds", 3600);
    	JSONObject action_info = new JSONObject();
    	JSONObject scene = new JSONObject();
    	scene.put("scene_id", scene_id);
    	action_info.put("scene", scene);
    	qrcode.put("action_info", action_info);
    	String responseBody = JSONObject.toJSONString(qrcode);
    	JSONObject jsonObj = sendPOSTRequestAndParse("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s", null, responseBody, 0);
    	return jsonObj.getString("ticket");
    }
    
    public static String getQRTicketLimit(String scene_str){
    	JSONObject qrcode = new JSONObject();
    	qrcode.put("action_name", "QR_LIMIT_STR_SCENE");
    	JSONObject action_info = new JSONObject();
    	JSONObject scene = new JSONObject();
    	scene.put("scene_str", scene_str);
    	action_info.put("scene", scene);
    	qrcode.put("action_info", action_info);
    	String responseBody = JSONObject.toJSONString(qrcode);
    	JSONObject jsonObj = sendPOSTRequestAndParse("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s", null, responseBody, 0);
    	return jsonObj.getString("ticket");
    }
    
    /**
     * sendTemplateMsg:发送模板消息. <br/>
     * @author liben
     * @param wtm
     * @return
     */
    public static String sendTemplateMsg(Map<String,WechatTemplateParam> params,String openId,String templateId){    
    	JSONObject jsonObj = sendPOSTRequestAndParse(Constant.TEMPLATEMSGURL, null, JSON.toJSONString(buildTemplateMsg(params,openId,templateId)) , 0);
    	return jsonObj.getString("msgid");
    }
    
    /**
     * buildTemplateMsg:构造模板消息. <br/>
     * @author liben
     * @param params
     * @param openId
     * @param templateId
     * @return
     */
    public static WechatTemplateMsg buildTemplateMsg(Map<String,WechatTemplateParam> params,String openId,String templateId){
    	WechatTemplateMsg wtm = new WechatTemplateMsg();
    	wtm.setTouser(openId);
    	wtm.setTemplate_id(templateId);
    	JSONObject data = new JSONObject();
    	Set<String> keys = params.keySet();
    	for(String key : keys){
    		data.put(key,(WechatTemplateParam)params.get(key));
    	}
    	wtm.setData(data);
    	return wtm;
    }
}
