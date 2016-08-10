/**
 * Project Name:Wechat_160219
 * File Name:WechatTemplateMsg.java
 * Package Name:com.eloancn.wechat.entities
 * Date:2016年2月19日下午6:17:31
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
*/
/**
 * Project Name:Wechat_160219
 * File Name:WechatTemplateMsg.java
 * Package Name:com.eloancn.wechat.entities
 * Date:2016年2月19日下午6:17:31
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.entities;

import com.alibaba.fastjson.JSONObject;

/**
 * ClassName: WechatTemplateMsg <br/>
 * Function: 微信模板消息. <br/>
 * date: 2016年2月19日 下午6:17:31 <br/>
 *
 * @author liben
 * @version 
 */
public class WechatTemplateMsg {
	private String touser;
	private String template_id;
	private String url;
	private JSONObject data;
	/**
	 * touser.
	 * @return  the touser
	 */
	public String getTouser() {
		return touser;
	}
	/**
	 * touser.
	 * @param   touser    the touser to set
	 */
	public void setTouser(String touser) {
		this.touser = touser;
	}
	/**
	 * template_id.
	 * @return  the template_id
	 */
	public String getTemplate_id() {
		return template_id;
	}
	/**
	 * template_id.
	 * @param   template_id    the template_id to set
	 */
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	/**
	 * url.
	 * @return  the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * url.
	 * @param   url    the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * data.
	 * @return  the data
	 */
	public JSONObject getData() {
		return data;
	}
	/**
	 * data.
	 * @param   data    the data to set
	 */
	public void setData(JSONObject data) {
		this.data = data;
	}
	
	
}

