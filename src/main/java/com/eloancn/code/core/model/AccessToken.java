/**
 * @Title: AccessToken.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月4日  吴青岭
 */
 

package com.eloancn.code.core.model;

import java.io.Serializable;

/**
 * @ClassName: AccessToken
 * @Description: 微信通用接口凭证
 * 微信服务器会返回json格式的数据：{"access_token":"ACCESS_TOKEN","expires_in":7200}，我们将其封装为一个AccessToken对象，对象有二个属性：token和expiresIn
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月4日 下午4:13:42
 */

public class AccessToken implements Serializable {

    /**
     * @Fields serialVersionUID : 序列化.
     */
    
    private static final long serialVersionUID = 7423995328790980746L;
    /**
     *  获取到的凭证.   
     */
    private String token;  
    /**
     *  凭证有效时间，单位：秒.     
     */
    private int expiresIn;
    /**
     * @return token
     */
    public String getToken() {
        return token;
    }
    /**
     * @param token 要设置的 token
     */
    public void setToken(String token) {
        this.token = token;
    }
    /**
     * @return expiresIn
     */
    public int getExpiresIn() {
        return expiresIn;
    }
    /**
     * @param expiresIn 要设置的 expiresIn
     */
    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

}
