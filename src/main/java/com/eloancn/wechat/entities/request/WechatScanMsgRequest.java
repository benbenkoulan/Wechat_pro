/**
 * Project Name:wechat-20160505
 * File Name:WechatScanMsgRequest.java
 * Package Name:com.eloancn.wechat.entities.request
 * Date:2016年6月13日下午4:23:37
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
*/
/**
 * Project Name:wechat-20160505
 * File Name:WechatScanMsgRequest.java
 * Package Name:com.eloancn.wechat.entities.request
 * Date:2016年6月13日下午4:23:37
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.entities.request;
/**
 * ClassName: WechatScanMsgRequest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2016年6月13日 下午4:23:37 <br/>
 *
 * @author liben
 * @version 
 */
public class WechatScanMsgRequest extends WechatEventMsgRequest {

	/**
	 * Creates a new instance of WechatScanMsgRequest.
	 *
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param Event
	 */
	
	private String EventKey;
	private String Ticket;
	public WechatScanMsgRequest(String toUserName, String fromUserName, String createTime, String msgType,
		String Event,String EventKey,String Ticket) {
		super(toUserName, fromUserName, createTime, msgType, Event);
		this.EventKey = EventKey;
		this.Ticket = Ticket;
	}
	/**
	 * eventKey.
	 * @return  the eventKey
	 */
	public String getEventKey() {
		return EventKey;
	}
	/**
	 * eventKey.
	 * @param   eventKey    the eventKey to set
	 */
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	/**
	 * ticket.
	 * @return  the ticket
	 */
	public String getTicket() {
		return Ticket;
	}
	/**
	 * ticket.
	 * @param   ticket    the ticket to set
	 */
	public void setTicket(String ticket) {
		Ticket = ticket;
	}

	
}

