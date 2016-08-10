package com.eloancn.wechat.entities.request;

import com.eloancn.wechat.entities.XStreamCDATA;

/**   
* @ClassName:WechatTextMsgRequest.java 
* @Function com.eloancn.wechat.entities.request 
* @author: liben
* @date 2015年9月9日 下午2:23:26 
*/
public class WechatTextMsgRequest extends WechatCustomMsgRequest {
	@XStreamCDATA
	private String Content;
	
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 * @param MsgId
	 */
	public WechatTextMsgRequest(String toUserName, String fromUserName, String createTime,
			String msgType, String MsgId, String Content) {
		super(toUserName, fromUserName, createTime, msgType, MsgId);
		this.Content = Content;
	}

	/**
	 * Date:2015年9月7日
	 * Time:下午4:13:12
	 */
	public String getContent() {
		return Content;
	}

	/**
	 * Date:2015年9月7日
	 * Time:下午4:13:12
	 */
	public void setContent(String content) {
		Content = content;
	}
}
