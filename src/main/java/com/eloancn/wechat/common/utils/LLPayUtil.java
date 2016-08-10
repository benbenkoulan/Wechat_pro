/**
 * Project Name:wechat-20150909
 * File Name:LLPayUtil.java
 * Package Name:com.eloancn.wechat.common.utils
 * Date:2015年10月21日下午2:59:12
 * Copyright (c) 2015, eloancn All Rights Reserved.
 *
*/
/**
 * Project Name:wechat-20150909
 * File Name:LLPayUtil.java
 * Package Name:com.eloancn.wechat.common.utils
 * Date:2015年10月21日下午2:59:12
 * Copyright (c) 2015, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * ClassName: LLPayUtil <br/>
 * Function: lianlianpay工具类 <br/>
 * date: 2015年10月21日 下午2:59:12 <br/>
 *
 * @author liben
 * @version 
 */
public class LLPayUtil {
	/**
   * str空判断
   * @param str
   * @return
   * @author guoyx
   */
  public static boolean isnull(String str)
  {
      if (null == str || str.equalsIgnoreCase("null") || str.equals(""))
      {
          return true;
      } else
          return false;
  }

  /**
   * 获取当前时间str，格式yyyyMMddHHmmss
   * @return
   * @author guoyx
   */
  public static String getCurrentDateTimeStr()
  {
      SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
      Date date = new Date();
      String timeString = dataFormat.format(date);
      return timeString;
  }

  /**
   * 
   * 功能描述：获取真实的IP地址
   * @param request
   * @return
   * @author guoyx
   */
  public static String getIpAddr(HttpServletRequest request)
  {
      String ip = request.getHeader("x-forwarded-for");
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
      {
          ip = request.getHeader("Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
      {
          ip = request.getHeader("WL-Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
      {
          ip = request.getRemoteAddr();
      }
      if (!isnull(ip) && ip.contains(","))
      {
          String[] ips = ip.split(",");
          ip = ips[ips.length - 1];
      }
      //转换IP 格式
      if(!isnull(ip)){
          ip=ip.replace(".", "_");
      }
      return ip;
  }

  /**
   * 生成待签名串
   * @param paramMap
   * @return
   * @author guoyx
   */
  public static String genSignData(JSONObject jsonObject)
  {
      StringBuffer content = new StringBuffer();

      // 按照key做首字母升序排列
      List<String> keys = new ArrayList<String>(jsonObject.keySet());
      Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
      for (int i = 0; i < keys.size(); i++)
      {
          String key = (String) keys.get(i);
          if ("sign".equals(key))
          {
              continue;
          }
          String value = jsonObject.getString(key);
          // 空串不参与签名
          if (isnull(value))
          {
              continue;
          }
          content.append((i == 0 ? "" : "&") + key + "=" + value);

      }
      String signSrc = content.toString();
      if (signSrc.startsWith("&"))
      {
          signSrc = signSrc.replaceFirst("&", "");
      }
      return signSrc;
  }

  /**
   * 加签
   * 
   * @param reqObj
   * @param rsa_private
   * @param md5_key
   * @return
   * @author guoyx
   */
  public static String addSign(JSONObject reqObj, String rsa_private,
          String md5_key)
  {
      if (reqObj == null)
      {
          return "";
      }
      return addSignMD5(reqObj, md5_key);
  }
 
  /**
   * MD5加签名
   * 
   * @param reqObj
   * @param md5_key
   * @return
   * @author guoyx
   */
	private static String addSignMD5(JSONObject reqObj, String md5_key) {
      
      if(reqObj != null) {
      	// 生成待签名串
        String sign_src = genSignData(reqObj);
        sign_src += "&key=" + md5_key;
        try {
            return Md5Algorithm.getInstance().md5Digest(sign_src.getBytes("utf-8"));
        } catch (Exception e){
          return "";
        }
      } else {
      	return "";
      }
  }
  
  /**
   * 读取request流
   * @param req
   * @return
   * @author guoyx
   */
  public static String readReqStr(HttpServletRequest request)
  {
      BufferedReader reader = null;
      StringBuilder sb = new StringBuilder();
      try
      {
          reader = new BufferedReader(new InputStreamReader(request
                  .getInputStream(), "utf-8"));
          String line = null;

          while ((line = reader.readLine()) != null)
          {
              sb.append(line);
          }
      } catch (IOException e)
      {
          e.printStackTrace();
      } finally
      {
          try
          {
              if (null != reader)
              {
                  reader.close();
              }
          } catch (IOException e)
          {

          }
      }
      return sb.toString();
  }
}

