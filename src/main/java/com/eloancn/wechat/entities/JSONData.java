package com.eloancn.wechat.entities;

import java.io.Serializable;

/**   
* @ClassName:JSONData.java 
* @Function com.eloancn.wechat.entities 
* @author: liben
* @date 2015年9月17日 上午11:01:51 
*/
@SuppressWarnings("serial")
public abstract class JSONData implements Serializable{
	private int status = 0;
	private String weizhi;
	private String url;
	
	public abstract void setInfo(Object info);
	
	public abstract Object getInfo();
	
	public JSONData(){}
	
	public JSONData(int status){
		this.status = status;
	}
	
	/**
	 * Date:2015年9月17日
	 * Time:上午11:03:38
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * Date:2015年9月17日
	 * Time:上午11:03:38
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * Date:2015年9月17日
	 * Time:上午11:03:38
	 */
	public String getWeizhi() {
		return weizhi;
	}
	/**
	 * Date:2015年9月17日
	 * Time:上午11:03:38
	 */
	public void setWeizhi(String weizhi) {
		this.weizhi = weizhi;
	}
	/**
	 * Date:2015年9月17日
	 * Time:上午11:03:38
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Date:2015年9月17日
	 * Time:上午11:03:38
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
