package com.eloancn.wechat.entities.base;

/**   
* @ClassName:ClickMenu.java 
* @Function com.eloancn.wechat.entities.base 
* @author: liben
* @date 2015年9月14日 上午11:39:32 
*/
public class ClickMenu extends SubMenu {

	private String key;
	private final String type = "click";
	/**
	 * @param name
	 */
	public ClickMenu(String name , String key) {
		super(name);
		this.key = key;
	}
	/**
	 * Date:2015年9月14日
	 * Time:上午11:41:06
	 */
	public String getKey() {
		return key;
	}
	/**
	 * Date:2015年9月14日
	 * Time:上午11:41:06
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * Date:2015年9月14日
	 * Time:上午11:41:06
	 */
	public String getType() {
		return type;
	}
	
	
}
