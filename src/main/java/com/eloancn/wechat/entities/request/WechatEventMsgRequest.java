package com.eloancn.wechat.entities.request;

import com.eloancn.wechat.entities.base.WechatBaseMsg;

/**   
* @ClassName:WechatEventMsgRequest.java 
* @Function com.eloancn.wechat.entities.request 
* @author: liben
* @date 2015年9月9日 下午2:24:27 
*/
public abstract class WechatEventMsgRequest extends WechatBaseMsg {
	private String Event;
	
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param MsgId
	 */
	public WechatEventMsgRequest(String toUserName, String fromUserName, String createTime, 
			String msgType, String Event) {
		super(toUserName, fromUserName, createTime, msgType);
		this.Event = Event;
	}

	/**
	 * Date:2015年9月7日
	 * Time:下午4:22:48
	 */
	public String getEvent() {
		return Event;
	}

	/**
	 * Date:2015年9月7日
	 * Time:下午4:22:48
	 */
	public void setEvent(String event) {
		Event = event;
	}
}
