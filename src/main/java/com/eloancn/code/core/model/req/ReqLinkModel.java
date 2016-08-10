/**
 * @Title: ReqLinkModel.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */

package com.eloancn.code.core.model.req;

/**
 * @ClassName: ReqLinkModel
 * @Description: Description of this class
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 下午2:03:46
 */

public class ReqLinkModel extends ReqBaseModel {
    /**
     * 消息标题
     */
    private String Title;
    /**
     * 消息描述
     */
    private String Description;
    /**
     * 消息链接
     */
    private String Url;
    /**
     * @return title
     */
    public String getTitle() {
        return Title;
    }
    /**
     * @param title 要设置的 title
     */
    public void setTitle(String title) {
        Title = title;
    }
    /**
     * @return description
     */
    public String getDescription() {
        return Description;
    }
    /**
     * @param description 要设置的 description
     */
    public void setDescription(String description) {
        Description = description;
    }
    /**
     * @return url
     */
    public String getUrl() {
        return Url;
    }
    /**
     * @param url 要设置的 url
     */
    public void setUrl(String url) {
        Url = url;
    }
}
