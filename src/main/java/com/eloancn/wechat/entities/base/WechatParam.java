/**
 * Project Name:wechat-20160505
 * File Name:WechatParam.java
 * Package Name:com.eloancn.wechat.entities
 * Date:2016年6月14日上午10:41:52
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.entities.base;
/**
 * ClassName: WechatParam <br/>
 * Function: 微信参数. <br/>
 * date: 2016年6月14日 上午10:41:52 <br/>
 *
 * @author liben
 * @version 
 */
public class WechatParam {
	private String OPENID;
	
	/**
	 * Creates a new instance of WechatParam.
	 *
	 * @param oPENID
	 */
	
	public WechatParam(String oPENID) {
		OPENID = oPENID;
	}

	/**
	 * oPENID.
	 * @return  the oPENID
	 */
	public String getOPENID() {
		return OPENID;
	}

	/**
	 * oPENID.
	 * @param   oPENID    the oPENID to set
	 */
	public void setOPENID(String oPENID) {
		OPENID = oPENID;
	}
	
	
}

