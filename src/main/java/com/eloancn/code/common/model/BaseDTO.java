/**
 * @Title: BaseModel.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */
 

package com.eloancn.code.common.model;

/**
 * @ClassName: BaseModel
 * @Description: 微信基础model
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 上午10:07:29
 */

public class BaseDTO implements java.io.Serializable {
    /**
     * 序列化.
     */ 
    private static final long serialVersionUID = 1L;
    /**
     * 主键.
     */
    private long id;
    /**
     * 编码.
     */
    private String code;
    /**
     * 是否有效.
     */
    private int isValid;
    /**
     * 创建时间.
     */
    private String createDate;
    /**
     * 创建人.
     */
    private String createUser;
    /**
     * 最后操作时间.
     */
    private String latestOpDate;
    /**
     * 最后操作人.
     */
    private String latestOpUser;
    /**
     * @return id
     */
    public long getId() {
        return id;
    }
    /**
     * @param id 要设置的 id
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * @return code
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code 要设置的 code
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * @return isValid
     */
    public int getIsValid() {
        return isValid;
    }
    /**
     * @param isValid 要设置的 isValid
     */
    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }
    /**
     * @return createDate
     */
    public String getCreateDate() {
        return createDate;
    }
    /**
     * @param createDate 要设置的 createDate
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    /**
     * @return createUser
     */
    public String getCreateUser() {
        return createUser;
    }
    /**
     * @param createUser 要设置的 createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    /**
     * @return latestOpDate
     */
    public String getLatestOpDate() {
        return latestOpDate;
    }
    /**
     * @param latestOpDate 要设置的 latestOpDate
     */
    public void setLatestOpDate(String latestOpDate) {
        this.latestOpDate = latestOpDate;
    }
    /**
     * @return latestOpUser
     */
    public String getLatestOpUser() {
        return latestOpUser;
    }
    /**
     * @param latestOpUser 要设置的 latestOpUser
     */
    public void setLatestOpUser(String latestOpUser) {
        this.latestOpUser = latestOpUser;
    }

}
