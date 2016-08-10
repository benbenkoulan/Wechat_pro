/**
 * @Title: RespTextModel.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */
 

package com.eloancn.code.core.model.resp;

/**
 * @ClassName: RespTextModel
 * @Description: Description of this class
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 下午2:11:49
 */

public class RespTextModel extends RespBaseModel {
    /**
     * 回复的消息内容
     */
    private String Content;

    /**
     * @return content
     */
    public String getContent() {
        return Content;
    }

    /**
     * @param content 要设置的 content
     */
    public void setContent(String content) {
        Content = content;
    }
}
