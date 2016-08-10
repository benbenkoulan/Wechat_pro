/**
 * Project Name:wechat-20150909
 * File Name:JedisUtil.java
 * Package Name:com.eloancn.wechat.common.utils
 * Date:2015年11月9日下午6:15:11
 * Copyright (c) 2015, eloancn All Rights Reserved.
 *
*/
/**
 * Project Name:wechat-20150909
 * File Name:JedisUtil.java
 * Package Name:com.eloancn.wechat.common.utils
 * Date:2015年11月9日下午6:15:11
 * Copyright (c) 2015, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.common.utils;

import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.eloancn.jedis.JConnection;
import com.eloancn.wechat.common.constant.Constant;

/**
 * ClassName: JedisUtil <br/>
 * Function: 操作redis <br/>
 * date: 2015年11月9日 下午6:15:11 <br/>
 *
 * @author liben
 * @version 
 */
public class JedisUtil {
	protected static JConnection conn= new JConnection(Constant.REDISKEY);
	
	public static void saveObjectToRedis(String key , Object obj , int expireTime){
		conn.saveObject(key, obj, true);
		conn.expireTime(key, expireTime);
	}
	
	public static JSONObject getJSONObjectFromRedis(String key){
		Object obj = conn.getObject(key, JSONObject.class);
		JSONObject jsonObj = (null != obj ? (JSONObject)obj : new JSONObject());
		return jsonObj;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getObjectFromRedis(String key , Class clazz){
		Object obj = conn.getObject(key, clazz);
		try {
			obj = (null != obj ? obj : clazz.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public static void deleteObjectFromRedis(String key) {
		conn.del(key);
	}
	
	public static void deleteObjectsFromRedis(Map<String , String> keyMap){
		Set<String> keys = keyMap.keySet();
		for(String key : keys){
			conn.del(key);
		}
	}
	
	public static void RefreshExpireTime(String key , int expireTime) {
		conn.expireTime(key, expireTime);
	}	
}
