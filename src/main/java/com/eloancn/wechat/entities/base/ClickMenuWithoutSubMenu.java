package com.eloancn.wechat.entities.base;

/**   
* @ClassName:ClickMenuWithoutSubMenu.java 
* @Function Beans 
* @author: liben
* @date 2015年9月14日 下午1:46:10 
*/
public class ClickMenuWithoutSubMenu extends MenuWithoutSubMenu {

	private String key;
	private final String type = "click";
	/**
	 * @param name
	 */
	public ClickMenuWithoutSubMenu(String name , String key) {
		super(name);
		this.key = key;
	}
	/**
	 * Date:2015年9月14日
	 * Time:下午1:46:46
	 */
	public String getKey() {
		return key;
	}
	/**
	 * Date:2015年9月14日
	 * Time:下午1:46:46
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * Date:2015年9月14日
	 * Time:下午1:46:46
	 */
	public String getType() {
		return type;
	}
	
	
}
