/**
 * @Title: ReqBaseModel.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */
package com.eloancn.code.core.model.req;

/**
 * @ClassName: ReqBaseModel
 * @Description: 请求信息基本model.
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 下午1:56:46
 */

public class ReqBaseModel {
    /**
     * 开发者微信号 .
     */
    private String toUserName;
    /**
     * 发送方帐号(一个OpenID).
     */
    private String fromUserName;
    /**
     * 消息创建时间 （整型） .
     */
    private long createTime;
    /**
     * 消息类型（text/image/location/link） .
     */
    private String msgType;
    /**
     * 消息id，64位整型 .
     */
    private long msgId;
    /**
     * @return toUserName
     */
    public String getToUserName() {
        return toUserName;
    }
    /**
     * @param toUserName 要设置的 toUserName
     */
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }
    /**
     * @return fromUserName
     */
    public String getFromUserName() {
        return fromUserName;
    }
    /**
     * @param fromUserName 要设置的 fromUserName
     */
    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }
    /**
     * @return createTime
     */
    public long getCreateTime() {
        return createTime;
    }
    /**
     * @param createTime 要设置的 createTime
     */
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    /**
     * @return msgType
     */
    public String getMsgType() {
        return msgType;
    }
    /**
     * @param msgType 要设置的 msgType
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    /**
     * @return msgId
     */
    public long getMsgId() {
        return msgId;
    }
    /**
     * @param msgId 要设置的 msgId
     */
    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }
}
