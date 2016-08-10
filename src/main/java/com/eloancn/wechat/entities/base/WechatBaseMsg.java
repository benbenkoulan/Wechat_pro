package com.eloancn.wechat.entities.base;

import com.eloancn.wechat.entities.XStreamCDATA;

/**   
* @ClassName:WechatBaseMsg.java 
* @Function com.eloancn.wechat.entities.base 
* @author: liben
* @date 2015年9月9日 下午2:17:30 
*/
public abstract class WechatBaseMsg {
	@XStreamCDATA
	private String ToUserName;
	@XStreamCDATA
	private String FromUserName;
	private String CreateTime;
	@XStreamCDATA
	private String MsgType;
	
	public WechatBaseMsg()
	{
		
	}
	
	/**
	 * @param toUserName
	 * @param fromUserName
	 * @param createTime
	 * @param msgType
	 */
	public WechatBaseMsg(String toUserName, String fromUserName, String createTime, String msgType) {
		ToUserName =  toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
	}
	/**
	 * Date:2015年9月7日
	 * Time:下午4:01:45
	 */
	public String getToUserName() {
		return ToUserName;
	}
	/**
	 * Date:2015年9月7日
	 * Time:下午4:01:45
	 */
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	/**
	 * Date:2015年9月7日
	 * Time:下午4:01:45
	 */
	public String getFromUserName() {
		return FromUserName;
	}
	/**
	 * Date:2015年9月7日
	 * Time:下午4:01:45
	 */
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	/**
	 * Date:2015年9月7日
	 * Time:下午4:01:45
	 */
	public String getCreateTime() {
		return CreateTime;
	}
	/**
	 * Date:2015年9月7日
	 * Time:下午4:01:45
	 */
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	/**
	 * Date:2015年9月7日
	 * Time:下午4:01:45
	 */
	public String getMsgType() {
		return MsgType;
	}
	/**
	 * Date:2015年9月7日
	 * Time:下午4:01:45
	 */
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
}
