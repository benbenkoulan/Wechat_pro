package com.eloancn.wechat.entities.response;

import com.eloancn.wechat.entities.base.WechatBaseMsg;

/**   
* @ClassName:WechatImgMsgResponse.java 
* @Function com.eloancn.wechat.entities.response 
* @author: liben
* @date 2015年9月14日 下午5:25:54 
*/
public class WechatImgMsgResponse extends WechatBaseMsg {
	
	private Image img;
	
	public WechatImgMsgResponse()
	{
		
	}
	
	public WechatImgMsgResponse(String toUserName, String fromUserName, String createTime,
			String msgType, Image img) {
		super(toUserName, fromUserName, createTime, msgType);
		this.img = img;
	}

	/**
	 * Date:2015年9月14日
	 * Time:下午5:41:48
	 */
	public Image getImg() {
		return img;
	}

	/**
	 * Date:2015年9月14日
	 * Time:下午5:41:48
	 */
	public void setImg(Image img) {
		this.img = img;
	}
	
}
