/**
 * Project Name:wechat-20160505
 * File Name:ScanParam.java
 * Package Name:com.eloancn.wechat.entities
 * Date:2016年6月14日上午10:51:22
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
*/
/**
 * Project Name:wechat-20160505
 * File Name:ScanParam.java
 * Package Name:com.eloancn.wechat.entities
 * Date:2016年6月14日上午10:51:22
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.entities;

import com.eloancn.wechat.entities.base.WechatParam;

/**
 * ClassName: ScanParam <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年6月14日 上午10:51:22 <br/>
 *
 * @author liben
 * @version 
 */
public class ScanParam extends WechatParam {
	private String TAG;
	
	public ScanParam(String OPENID,String TAG){
		super(OPENID);
		this.TAG = TAG;
	}

	/**
	 * tAG.
	 * @return  the tAG
	 */
	public String getTAG() {
		return TAG;
	}

	/**
	 * tAG.
	 * @param   tAG    the tAG to set
	 */
	public void setTAG(String tAG) {
		TAG = tAG;
	}
	
	
}

