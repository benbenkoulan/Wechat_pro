/**
 * @Title: ReqVoiceModel.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */
 

package com.eloancn.code.core.model.req;

/**
 * @ClassName: ReqVoiceModel
 * @Description: Description of this class
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 下午2:07:55
 */

public class ReqVoiceModel extends ReqBaseModel {
    /**
     * 媒体ID
     */
    private String MediaId;
    /**
     * 语音格式
     */
    private String Format;
    /**
     * @return mediaId
     */
    public String getMediaId() {
        return MediaId;
    }
    /**
     * @param mediaId 要设置的 mediaId
     */
    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
    /**
     * @return format
     */
    public String getFormat() {
        return Format;
    }
    /**
     * @param format 要设置的 format
     */
    public void setFormat(String format) {
        Format = format;
    }
    
}
