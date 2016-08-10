package com.eloancn.wechat.entities.request;

/**   
* @ClassName:WechatMenuMsgRequest.java 
* @Function com.eloancn.wechat.entities.request 
* @author: liben
* @date 2015年9月9日 下午2:27:11 
*/
public class WechatMenuMsgRequest extends WechatEventMsgRequest {
	private String EventKey;
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param MsgId
	 * @param Event
	 */
	public WechatMenuMsgRequest(String toUserName, String fromUserName, String createTime, String msgType,
			String Event, String EventKey) {
		super(toUserName, fromUserName, createTime, msgType, Event);
		this.EventKey = EventKey;
	}
	
	/**
	 * Date:2015年9月7日
	 * Time:下午4:31:32
	 */
	public String getEventKey() {
		return EventKey;
	}
	
	/**
	 * Date:2015年9月7日
	 * Time:下午4:31:32
	 */
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}
