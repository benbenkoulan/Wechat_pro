package com.eloancn.wechat.entities.base;

/**   
* @ClassName:ViewMenu.java 
* @Function com.eloancn.wechat.entities.base 
* @author: liben
* @date 2015年9月14日 上午11:41:49 
*/
public class ViewMenu extends SubMenu {

	private String url;
	private final String type = "view";
	/**
	 * @param name
	 */
	public ViewMenu(String name , String url) {
		super(name);
		this.url = url;
	}
	/**
	 * Date:2015年9月14日
	 * Time:下午1:47:28
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Date:2015年9月14日
	 * Time:下午1:47:28
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * Date:2015年9月14日
	 * Time:下午1:47:28
	 */
	public String getType() {
		return type;
	}
	
	
}
