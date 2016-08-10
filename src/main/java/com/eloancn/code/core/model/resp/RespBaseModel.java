/**
 * @Title: RespBaseModel.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */
 

package com.eloancn.code.core.model.resp;

/**
 * @ClassName: RespBaseModel
 * @Description: Description of this class
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 下午2:09:04
 */

public class RespBaseModel {
    /**
     * 接收方帐号（收到的OpenID）
     */
    private String toUserName;  
    /**
     * 开发者微信号   
     */
    private String fromUserName;  
    /**
     * 消息创建时间 （整型）  
     */
    private long createTime;  
    /**
     * 消息类型（text/music/news）
     */
    private String msgType;  
    
    /**
     * 位0x0001被标志时，星标刚收到的消息  
     */
    private int funcFlag;

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
     * @return funcFlag
     */
    public int getFuncFlag() {
        return funcFlag;
    }

    /**
     * @param funcFlag 要设置的 funcFlag
     */
    public void setFuncFlag(int funcFlag) {
        this.funcFlag = funcFlag;
    }
}
