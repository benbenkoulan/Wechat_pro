package com.eloancn.wechat.entities.request;

/**   
* @ClassName:WechatSubscirbeMsgRequest.java 
* @Function com.eloancn.wechat.entities.request 
* @author: liben
* @date 2015年9月9日 下午2:25:27 
*/
public class WechatSubscribeMsgRequest extends WechatEventMsgRequest {
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param MsgId
	 * @param Event
	 */
	public WechatSubscribeMsgRequest(String toUserName, String fromUserName, String createTime, String msgType,
			String Event) {
		super(toUserName, fromUserName, createTime, msgType, Event);
	}
}
