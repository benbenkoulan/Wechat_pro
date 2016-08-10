/**
 * @Title: RespMusicModel.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */
 

package com.eloancn.code.core.model.resp;

/**
 * @ClassName: RespMusicModel
 * @Description: Description of this class
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 下午2:10:32
 */

public class RespMusicModel extends RespBaseModel {
    /**
     * 音乐名称
     */
    private String Title;
    /**
     * 音乐描述
     */
    private String Description;
    /**
     * 音乐链接
     */
    private String MusicUrl;
    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private String HQMusicUrl;
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
     * @return musicUrl
     */
    public String getMusicUrl() {
        return MusicUrl;
    }
    /**
     * @param musicUrl 要设置的 musicUrl
     */
    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }
    /**
     * @return hQMusicUrl
     */
    public String getHQMusicUrl() {
        return HQMusicUrl;
    }
    /**
     * @param hQMusicUrl 要设置的 hQMusicUrl
     */
    public void setHQMusicUrl(String hQMusicUrl) {
        HQMusicUrl = hQMusicUrl;
    }
}
