package com.eloancn.wechat.entities.response;

import com.eloancn.wechat.entities.XStreamCDATA;
import com.eloancn.wechat.entities.base.WechatBaseMsg;

/**   
* @ClassName:WechatTextMsgResponse.java 
* @Function com.eloancn.wechat.entities.response 
* @author: liben
* @date 2015年9月9日 下午2:33:00 
*/
public class WechatTextMsgResponse extends WechatBaseMsg {
	@XStreamCDATA
	private String Content;
	
	public WechatTextMsgResponse()
	{
		
	}
	
	/**
	 * content.
	 *
	 * @return  the content
	 */
	public String getContent() {
		return Content;
	}



	/**
	 * content.
	 *
	 * @param   content    the content to set
	
	 */
	public void setContent(String content) {
		Content = content;
	}



	public WechatTextMsgResponse(String toUserName, String fromUserName, String createTime,
			String msgType, String Content) {
		super(toUserName, fromUserName, createTime, msgType);
		this.Content = Content;
	}
}
