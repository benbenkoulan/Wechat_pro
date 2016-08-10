package com.eloancn.wechat.entities;

/**   
* @ClassName:AccountData.java 
* @Function com.eloancn.wechat.entities 
* @author: liben
* @date 2015年9月24日 下午2:35:57 
*/
@SuppressWarnings("serial")
public class InfoData extends JSONData{
	private String info;

	public InfoData(){}
	
	public InfoData(int status){
		super(status);
	}
	
	/**
	 * Date:2015年9月24日
	 * Time:下午2:36:19
	 */
	public Object getInfo() {
		return info;
	}

	/**
	 * Date:2015年9月24日
	 * Time:下午2:36:19
	 */
	public void setInfo(Object info) {
		this.info = (String)info;
	}
}
