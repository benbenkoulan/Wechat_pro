package com.eloancn.wechat.entities.base;

/**   
* @ClassName:Button.java 
* @Function com.eloancn.wechat.entities.base 
* @author: liben
* @date 2015年9月14日 上午11:30:24 
*/
public abstract class Menu {
	private String name;
	
	/**
	 * @param name
	 */
	public Menu(String name) {
		this.name = name;
	}

	/**
	 * Date:2015年9月14日
	 * Time:上午11:31:51
	 */
	public String getName() {
		return name;
	}

	/**
	 * Date:2015年9月14日
	 * Time:上午11:31:51
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
}
