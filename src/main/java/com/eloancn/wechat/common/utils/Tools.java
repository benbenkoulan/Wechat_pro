package com.eloancn.wechat.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONObject;
import com.eloancn.wechat.common.constant.Constant;
/**
 * ClassName: Tools
 * @Description: 常用方法工具类
 * @author liben
 * @date 2015-6-4
 */
public class Tools {
	
	private final static Properties PROPERTIES;
	private final static String PROPERTIES_FILE = "mobile.properties";
	//读取配置文件
	static {
		PROPERTIES = new Properties();
		try {
			PROPERTIES.load(Tools.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 移动端配置文件读取
	 *
	 * @return
	 */
	public static Properties getProperties() {
		return PROPERTIES;
	}
	
	/**
	 * 随机生成六位数验证码 
	 * @return
	 */
	public static int getRandomNum(){
		 Random r = new Random();
		 return r.nextInt(900000)+100000;//(Math.random()*(999999-100000)+100000)
	}
	
	/**
	 * 检测字符串是否不为空(null,"","null")
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	/**
	 * 检测字符串是否为空(null,"","null")
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	/**
	 * 字符串转换为字符串数组
	 * @param str 字符串
	 * @param splitRegex 分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * @param str	字符串
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	/**
	 * 把时间根据时、分、秒转换为时间段
	 * @param StrDate
	 */
	public static String getTimes(String StrDate){
		String resultTimes = "";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    java.util.Date now;
	    
	    try {
	    	now = new Date();
	    	java.util.Date date=df.parse(StrDate);
	    	long times = now.getTime()-date.getTime();
	    	long day  =  times/(24*60*60*1000);
	    	long hour = (times/(60*60*1000)-day*24);
	    	long min  = ((times/(60*1000))-day*24*60-hour*60);
	    	long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);
	        
	    	StringBuffer sb = new StringBuffer();
	    	if(hour>0 ){
	    		sb.append(hour+"小时前");
	    	} else if(min>0){
	    		sb.append(min+"分钟前");
	    	} else{
	    		sb.append(sec+"秒前");
	    	}
	    		
	    	resultTimes = sb.toString();
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
	    return resultTimes;
	}
	
	/**
	 * 写txt里的单行内容
	 * @param filePath  文件路径
	 * @param content  写入的内容
	 */
	public static void writeFile(String fileP,String content){
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//项目路径
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if(filePath.indexOf(":") != 1){
			filePath = File.separator + filePath;
		}
		try {
	        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");      
	        BufferedWriter writer=new BufferedWriter(write);          
	        writer.write(content);      
	        writer.close(); 

	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	  * 验证邮箱
	  * @param email
	  * @return
	  */
	 public static boolean checkEmail(String email){
	  boolean flag = false;
	  try{
	    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher(email);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	
	 /**
	  * 验证手机号码
	  * @param mobiles
	  * @return
	  */
	 public static boolean checkMobileNumber(String mobileNumber){
	  boolean flag = false;
	  try{
	  	/*dudj 20160129 begin*/
//	    Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,4-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
	  	Pattern regex = Pattern.compile("^(1[0-9]{10})$");
	  	/*dudj 20160129 end*/
	  	Matcher matcher = regex.matcher(mobileNumber);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	 
	/**
	 * 检测KEY是否正确
	 * @param paraname  传入参数
	 * @param FKEY		接收的 KEY
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean checkKey(String paraname, String FKEY){
		paraname = (null == paraname)? "":paraname;
		return MD5.md5(paraname+DateUtil.getDays()+",fh,").equals(FKEY);
	}
	
	/**
	 * @Description: 唯一标示,uuid
	 * @param @return   
	 * @return String  
	 * @author mingmeijun
	 * @date 2015-6-4
	 */
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
	
	/*
	 * 取出字符串中的数字
	 * 
	 * **/
	public static int intval(String str) {
		String reg = "[^0-9]";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		while(m.find()){
			str = str.replaceAll(m.group(), "");
		}
		return Integer.parseInt(str);
	}
	
	/**
	 * checkIdCard:检查是否是身份证号. <br/>
	 * @author liben
	 * @param str
	 * @return
	 */
	public static boolean checkIdCard(String str) {
		if(str == null) {
			return false;
		} else if(str.length() != 18) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * checkBankNo:检查银行卡号. <br/>
	 * @author liben
	 * @param str
	 * @return
	 */
	public static boolean checkBankNo(String str) {
		if(str == null) {
			return false;
		} else {
			Pattern p = Pattern.compile("[0-9]{10,23}");
			Matcher m = p.matcher(str);
			return m.find();
		}
	}

	/**
	 * 新的md5签名，首尾放secret。
	 * 
	 * @param secret
	 *          分配给您的APP_SECRET
	 */
	public static String md5Signature(TreeMap<String, String> params, String secret) {
		String result = null;
		StringBuffer orgin = getBeforeSign(params, new StringBuffer(secret));
		if (orgin == null)
			return result;
		orgin.append(secret);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			System.out.println("加密orgin：" + orgin.toString());
			result = byte2hex(md.digest(orgin.toString().getBytes("utf-8")));
		} catch (Exception e) {
			throw new java.lang.RuntimeException("sign error !");
		}
		return result;
	}

	/**
	 * 
	 * 二行制转字符串
	 */

	private static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}
	
	/**
	 * 
	 * 添加参数的封装方法
	 */

	private static StringBuffer getBeforeSign(TreeMap<String, String> params, StringBuffer orgin) {
		if (params == null)
			return null;
		Map<String, String> treeMap = new TreeMap<String, String>();
		treeMap.putAll(params);
		Iterator<String> iter = treeMap.keySet().iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			orgin.append(name).append(params.get(name));
		}
		return orgin;
	}
	
	/**
	 * 过滤字符串中的中文
	 * 
	 * **/
	public static String replaceChinese(String str) {
		Pattern pat = Pattern.compile("[\u4e00-\u9fa5]+");
		Matcher mat = pat.matcher(str);
		return mat.replaceAll("");
	}
	
	/**
	 * 将set根据可以排序
	 * 
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<JSONObject> sortMapByValue(Map<String,String> map){
		if(null == map || map.isEmpty()){
			return null;
		}
		List arrayList = new ArrayList(map.entrySet());
		Collections.sort(arrayList,new Comparator() {
			Collator cmp = Collator.getInstance(java.util.Locale.CHINA);  
			@Override
			public int compare(Object o1, Object o2) {
				 Map.Entry obj1 = (Map.Entry) o1;  
         Map.Entry obj2 = (Map.Entry) o2;
         String v1 = (String)obj1.getValue();
         String v2 = (String)obj2.getValue();
         int r = cmp.compare(v1, v2);
         return r;
			}
		});
		List<JSONObject> jsonObjectList = new ArrayList<JSONObject>();
		for(int i=0,max=arrayList.size();i<max;i++){
			String v = arrayList.get(i).toString();
			String[] vList = v.split("=");
			JSONObject obj = new JSONObject();
			obj.put("key", vList[0]);
			obj.put("value", vList[1]);
			jsonObjectList.add(obj);
		}
		return jsonObjectList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<String> sortList(List<String> list){
		Collections.sort(list,new Comparator() {
			Collator cmp = Collator.getInstance(java.util.Locale.CHINA);  
			@Override
			public int compare(Object o1, Object o2) {
				 String obj1 = (String) o1;  
				 String obj2 = (String) o2;
         int r = cmp.compare(obj1, obj2);
         return r;
			}
		});
		return list;
	}
	
	public static <T> boolean isNull(List<T> list){
		if(null == list || list.isEmpty()){
			return true;
		} else {
			return false;
		}
	}
	
	public static String getRequestStr(HttpServletRequest request) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuffer buffer = new StringBuffer();
		String str = null;
		while((str = reader.readLine()) != null) {
			buffer.append(str + "\n");
		}
		reader.close();
		return buffer.toString();
	}
	
	public static boolean checkBind(String id,String platform){
		JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(id);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("app_secret", userInfo.getString("app_secret")));
		params.add(new BasicNameValuePair("platform", platform));
		if(Constant.PLATFORM.equals(platform)){
			params.add(new BasicNameValuePair("user_id", userInfo.getString("out_uid")));
			params.add(new BasicNameValuePair("openid", id));
			params.add(new BasicNameValuePair("accountno", Constant.getAppId()));
		} else {
			params.add(new BasicNameValuePair("user_id", id));
		}
		StringBuffer url = new StringBuffer(Constant.getBaseURL());
		url.append("/mobile");
		url.append(Constant.FINDUSERVERIFYINFO);
		try{
			String responseText = RequestUtil.sendGETRequest(url.toString(), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				JSONObject jsonObj = json.getJSONObject("jsonData");
				if(Constant.REQUESTSUCCESS.equals(jsonObj.getString(Constant.JSONDATATIP))) {
					return true;
				}
			}
		}catch(Exception e){
		}
		return false;
	}
	
	public static int checkIdentity(String id,String platform){
		JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(id);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("app_secret", userInfo.getString("app_secret")));
		params.add(new BasicNameValuePair("platform", platform));
		if(Constant.PLATFORM.equals(platform)){
			params.add(new BasicNameValuePair("user_id", userInfo.getString("out_uid")));
			params.add(new BasicNameValuePair("openid", id));
			params.add(new BasicNameValuePair("accountno", Constant.getAppId()));
		} else {
			params.add(new BasicNameValuePair("user_id", id));
		}
		StringBuffer url = new StringBuffer(Constant.getBaseURL());
		url.append("/mobile");
		url.append(Constant.NETWORKAUTHRECORD);
		try{
			String responseText = RequestUtil.sendGETRequest(url.toString(), params);
			if(Tools.notEmpty(responseText)){
				JSONObject json = (JSONObject)JSONObject.parse(responseText);
				JSONObject jsonObj = json.getJSONObject("jsonData");
				String tip = jsonObj.getString(Constant.JSONDATATIP);
				if(Constant.REQUESTSUCCESS.equals(tip)) {
					JSONObject dataObj = jsonObj.getJSONObject("data");
					if(Constant.NOT.equals(dataObj.getString("idcard"))) {
						return 1;//没有身份证认证
					} else if(Constant.NOT.equals(dataObj.getString("paypassword"))) {
						return 2;//没有设置支付密码
					} else {
						return 0;
					}
				} else if(Constant.NOTSUCSCRIBEELOANCN.equals(tip)) {
					return 3;//没有关注
				}	else if(Constant.NOTLOGGEDIN.equals(tip)) {
					return 4;//没有登录
				} else {
					return 4;
				}
			}
		}catch(Exception e){
			return 4;
		}
		return 0;
	}
}