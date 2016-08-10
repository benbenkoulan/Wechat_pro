/**
 * @Title: WeixinUtil.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月4日  吴青岭
 */
 

package com.eloancn.code.core.util;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eloancn.code.common.utils.JedisUtil;
import com.eloancn.code.common.utils.PrefixConstants;
import com.eloancn.code.core.model.AccessToken;

/**
 * @ClassName: WeixinUtil
 * @Description: 微信接口核心工具类.
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月4日 下午4:16:34
 */

public class WeixinUtil {
    /**
     * 日志.
     */
    private static Logger logger = LoggerFactory.getLogger(WeixinUtil.class);  
    
    /**
     * @Description:获取access_token
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     * @throws
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {  
        AccessToken accessToken = null;
        String key = PrefixConstants.ACCESSTOKEN_PREFIX + appid;
        Object obj = JedisUtil.getObjectFromRedis(key,AccessToken.class);
        accessToken = (AccessToken) obj;
        if (accessToken!=null&&accessToken.getToken()!=null){
            logger.info("accessToken是从jedismanager中取得");
        } else {
            String requestUrl = InterfaceConstant.access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);  
            JSONObject jsonObject = HttpsUtil.httpRequest(requestUrl, "GET", null);  
            // 如果请求成功  
            if (null != jsonObject) {  
                try {  
                    accessToken = new AccessToken();  
                    accessToken.setToken(jsonObject.getString("access_token"));  
                    accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
                    //存入redis
                    JedisUtil.saveObjectToRedis(key, accessToken, WXConstant.TOKEN_OUT_TIME_REDIS);
                } catch (JSONException e) {  
                    accessToken = null;  
                    // 获取token失败  
                    logger.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
                }  
            }  
        }
        return accessToken;  
    }
    /**
     * @Description:获取jsapi_ticket
     * @param accessToken 介入凭证
     * @param appid 凭证
     * @return
     * @throws
     */
    public static String getJsapiTicket(String accessToken,String appid) {  
        String ticket = "";
        JSONObject jsonObject = null;
        String redisKey = PrefixConstants.TICKET_PREFIX+appid;
        jsonObject = JedisUtil.getJSONObjectFromRedis(redisKey);
        if(jsonObject!=null&&!jsonObject.isEmpty()) {
            logger.info("jsapi_ticket是从jedismanager中取得");
            ticket = jsonObject.getString("ticket");
        } else {
            String requestUrl = InterfaceConstant.get_jsapi_ticket.replace("ACCESS_TOKEN", accessToken);  
            jsonObject = HttpsUtil.httpRequest(requestUrl, "GET", null);
            if (null != jsonObject) {  
                try {  
                    ticket = jsonObject.getString("ticket");  
                } catch (JSONException e) {  
                    ticket = null;
                    logger.error("获取jsapi_ticket失败");  
                }
                JedisUtil.saveObjectToRedis(redisKey, ticket, WXConstant.TOKEN_OUT_TIME_REDIS);
            }  
        }
        return ticket;  
    } 

}
