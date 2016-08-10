package com.eloancn.wechat.entities.request;

import com.eloancn.wechat.entities.base.WechatBaseMsg;

/**   
* @ClassName:WechatCustomMsgRequest.java 
* @Function com.eloancn.wechat.entities.request 
* @author: liben
* @date 2015年9月9日 下午2:21:50 
*/
public abstract class WechatCustomMsgRequest extends WechatBaseMsg {
	private String MsgId;
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 */
	public WechatCustomMsgRequest(String toUserName, String fromUserName, String createTime, String msgType, String MsgId) {
		super(toUserName, fromUserName, createTime, msgType);
		this.MsgId = MsgId;
	}
	
	/**
	 * Date:2015年9月7日
	 * Time:下午4:11:12
	 */
	public String getMsgId() {
		return MsgId;
	}
	
	/**
	 * Date:2015年9月7日
	 * Time:下午4:11:12
	 */
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
}
