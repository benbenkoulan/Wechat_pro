/**
 * @Title: ReqImageModel.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */
 

package com.eloancn.code.core.model.req;

/**
 * @ClassName: ReqImageModel
 * @Description: Description of this class
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 下午2:02:33
 */

public class ReqImageModel extends ReqBaseModel {
    /**
     * 图片链接  
     */
    private String PicUrl;

    /**
     * @return picUrl
     */
    public String getPicUrl() {
        return PicUrl;
    }

    /**
     * @param picUrl 要设置的 picUrl
     */
    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

}
