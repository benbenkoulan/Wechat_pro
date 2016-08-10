/**
 * Project Name:Wechat_160219
 * File Name:ActivityController.java
 * Package Name:com.eloancn.wechat.controller
 * Date:2016年4月24日下午4:43:01
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
*/
/**
 * Project Name:Wechat_160219
 * File Name:ActivityController.java
 * Package Name:com.eloancn.wechat.controller
 * Date:2016年4月24日下午4:43:01
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.controller;

import java.math.BigDecimal;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eloancn.wechat.common.constant.Constant;
import com.eloancn.wechat.common.utils.DateUtil;
import com.eloancn.wechat.common.utils.JedisUtil;
import com.eloancn.wechat.common.utils.RequestUtil;
import com.eloancn.wechat.common.utils.Tools;
import com.eloancn.wechat.common.utils.WechatUtil;
import com.eloancn.wechat.entities.InfoData;
import com.eloancn.wechat.entities.JSONData;

/**
 * ClassName: ActivityController <br/>
 * Function: 活动. <br/>
 * date: 2016年4月24日 下午4:43:01 <br/>
 *
 * @author liben
 * @version 
 */
@Controller
@RequestMapping("/activity/*")
public class ActivityController extends BaseController{
	
	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mv = this.getModelAndView();
		String platform = request.getParameter("platform");//平台：1 android,	2 IOS,	APP,3	WECHAT,4 WAP
		platform = Tools.isEmpty(platform) ? Constant.PLATFORM : platform;
		String userId = null;
		String isCheck = "1";
		String next_period = Tools.getProperties().getProperty("activity_next_period");//下一期数
		String prize_time_period = "prize_time_" + next_period;
		mv.addObject("prizeTime", Tools.getProperties().getProperty(prize_time_period));
		try{
			String responseText = RequestUtil.sendGETRequest(Constant.getBaseURL() + "/mobile" + Constant.LISTACTIVITYREDBAG51, "utf-8");
			JSONObject json = JSONObject.parseObject(responseText);
			JSONObject dataObj = (JSONObject) json.get("jsonData");
			if(Constant.REQUESTSUCCESS.equals(dataObj.getString(Constant.JSONDATATIP))){
				JSONArray dataArr = dataObj.getJSONArray("data");
				JSONArray prizeData1 = dataArr.getJSONArray(0);//top three
				JSONArray prizeData2 = dataArr.getJSONArray(1);//所有奖品
				Collections.shuffle(prizeData2);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("prize_data", prizeData2);
				jsonObj.put("top_three",prizeData1);
				mv.addObject("next_period", next_period);
				mv.addObject("jsonObj", jsonObj);
			}
		}catch(Exception e){
			logger.error("----activity/index--getdata--platform-" + platform + "-----userId----" + userId + "---" + e.getMessage());
		}
		try{
			if(Constant.PLATFORM.equals(platform)){//微信
				String openId = null;
				String code = request.getParameter("code");
				if(!Tools.isEmpty(code)){
					JSONObject obj = WechatUtil.getAccessTokenByCode(code);	
					openId = obj.getString("openid");
				} else {
					openId = request.getParameter("openId");
				}
				if(!Tools.isEmpty(openId)){
					JSONObject userInfo = JedisUtil.getJSONObjectFromRedis(openId);
					userId = userInfo.getString("out_uid");
				}
				mv.addObject("touziUrl", Constant.getOPENWEIXINURL("index/invested"));
			} else {
				userId = request.getParameter("out_uid");
				if("4".equals(platform)){//WAP
					mv.addObject("touziUrl", Constant.getBaseURL() + "/wap/wapwmps/index.jsp");
				}
			}
			if(Tools.notEmpty(userId)){//用户登录
				String period = Tools.getProperties().getProperty("activity_period");//当前期数
				String activityKey = "activity_51_record_" + userId + "_" + period;
				String resultResponseText = RequestUtil.sendGETRequest(Constant.getActivityRedisURL() + "/mobile" + Constant.ACTIVITYREDISKEY + activityKey, "utf-8");
				if(Tools.notEmpty(resultResponseText)){
					JSONObject resultJSON = JSONObject.parseObject(resultResponseText);
					mv.addObject("prizeNo", resultJSON.getString("awardCode"));
					isCheck = resultJSON.getString("isCheck");
				}
			}
		}catch(Exception e){
			logger.error("----activity/index----platform-" + platform + "-----userId----" + userId + "---" + e.getMessage());
		}
		mv.addObject("isCheck", isCheck);//0代表没看，1代表已看
		mv.addObject("userId", userId);
		mv.addObject("platform", platform);
		mv.addObject("currentTime", DateUtil.getCurrentTime());
		mv.setViewName("activity/index");
		return mv;
	}
	
	@RequestMapping("redBag")
	public ModelAndView redBag(@RequestParam(value="platform",required=false) String platform,@RequestParam(value="group",required=false) String group){
		ModelAndView mv = new ModelAndView();
		//A:1000,B:500,C:100,D:50,E:10
		try{
			StringBuffer sb = new StringBuffer();
			char[] chars = group.toCharArray();
			BigDecimal sum = new BigDecimal(0);
			for(char ch : chars){
				BigDecimal m = getMoney(ch);
				sum = sum.add(m);
				sb.append(m.intValue()).append("+");
			}
			String groupStr = sb.toString();
			mv.addObject("group", groupStr.substring(0, groupStr.length()-1));
			mv.addObject("number", group.length());
			mv.addObject("sum", sum.toPlainString());
			if(Constant.PLATFORM.equals(platform)){//微信
				mv.addObject("myAccountUrl", Constant.getOPENWEIXINURL("index/index.html"));
				mv.addObject("investUrl", Constant.getOPENWEIXINURL("index/invested"));
			} else if("4".equals(platform)){//WAP
				mv.addObject("investUrl", Constant.getBaseURL() + "/wap/wapwmps/index.jsp");
				mv.addObject("myAccountUrl", Constant.getBaseURL() + "/wap/wapwmps/loadStatInfo2_1.jsp");
			} else {
				mv.addObject("hiddenClose", true);
			}
		} catch (Exception e){
			logger.error("------activity/redBag------------" + e.getMessage() + "------group-------" + group + "----platform------" + platform);
		}
		mv.setViewName("activity/redbag");
		return mv;
	}
	
	@RequestMapping("prize_ajax")
	public @ResponseBody JSONData prizeAjax(@RequestParam(value="param1",required=false) String param1,@RequestParam(value="param2",required=false) String param2){
		//模糊化参数:param1	userid,	param2	prizeNo
		JSONData data = new InfoData();
		//中奖代码0,1,2,3,4,5,6,7,8,9,10,11,12,13
		//注意：7,12代表谢谢参与
		try{
			if(Tools.notEmpty(param1) && Tools.notEmpty(param2)){
				String period = Tools.getProperties().getProperty("activity_period");
				String activityKey = "activity_51_record_" + param1 + "_" + period;
				String resultResponseText = RequestUtil.sendGETRequest(Constant.getActivityRedisURL() + "/mobile" + Constant.ACTIVITYREDISKEY + activityKey, "utf-8");
				if(Tools.notEmpty(resultResponseText)){
					JSONObject resultJSON = JSONObject.parseObject(resultResponseText);
					String code = resultJSON.getString("awardCode");
					if(param2.equals(code)){
						data.setStatus(1);
						return data;
					}			
				}
			}
		} catch(Exception e){
			logger.error("-----prize_ajax------param1---" + param1 + "-----param2---" + param2);
		}
		return data;
	}
	
	@RequestMapping("update_ajax")
	public @ResponseBody JSONData updateAjax(@RequestParam(value="param1",required=false) String param1){
		//模糊化参数:param1	userid
		JSONData data = new InfoData();
		String activityKey = null;
		String resultResponseText = null;
		try {
			String period = Tools.getProperties().getProperty("activity_period");
			activityKey = "activity_51_record_" + param1 + "_" + period;
			resultResponseText = RequestUtil.sendGETRequest(Constant.getBaseURL() + "/mobile" + Constant.UPDATEREDISVALUE + activityKey, "utf-8");
			logger.info("activityKey--------" + activityKey + "---------resultResponseText--------" + resultResponseText);
			System.out.println("activityKey--------" + activityKey + "---------resultResponseText--------" + resultResponseText);
		} catch (Exception e) {
			logger.error("activityKey--------" + activityKey + "------resultResponseText-----" + resultResponseText + "-------" + e.getMessage());
		}
		data.setStatus(1);
		return data;
	}
	
	public BigDecimal getMoney(char ch){
		BigDecimal returnValue = null;
		switch (ch) {
			case 'A':returnValue = new BigDecimal(1000);break;
			case 'B':returnValue = new BigDecimal(500);break;
			case 'C':returnValue = new BigDecimal(100);break;
			case 'D':returnValue = new BigDecimal(50);break;
			case 'E':returnValue = new BigDecimal(10);break;
			default:returnValue = new BigDecimal(0);break;
		}
		return returnValue;
	}
}
