/**
 * @Title: RespArticleModel.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */
 

package com.eloancn.code.core.model.resp;

/**
 * @ClassName: RespArticleModel
 * @Description: Description of this class
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 下午2:09:39
 */

public class RespArticleModel extends RespBaseModel {
    /**
     * 图文消息名称
     */
    private String title;
    /**
     * 图文消息描述
     */
    private String description;
    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
     */
    private String picUrl;
    /**
     * 点击图文消息跳转链接
     */
    private String url;
    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title 要设置的 title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description 要设置的 description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return picUrl
     */
    public String getPicUrl() {
        return picUrl;
    }
    /**
     * @param picUrl 要设置的 picUrl
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param url 要设置的 url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
