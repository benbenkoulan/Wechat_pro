package com.eloancn.wechat.entities.response;

import com.eloancn.wechat.entities.XStreamCDATA;

/**   
* @ClassName:Image.java 
* @Function com.eloancn.wechat.entities.response 
* @author: liben
* @date 2015年9月14日 下午5:36:33 
*/
public class Image {
	
	@XStreamCDATA
	private String MediaId;

	/**
	 * Date:2015年9月14日
	 * Time:下午5:41:20
	 */
	public String getMediaId() {
		return MediaId;
	}

	/**
	 * Date:2015年9月14日
	 * Time:下午5:41:20
	 */
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	
}
