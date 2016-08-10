package com.eloancn.wechat.entities.base;

import java.util.List;

/**   
* @ClassName:LevelOneButton.java 
* @Function com.eloancn.wechat.entities.base 
* @author: liben
* @date 2015年9月14日 上午11:36:57 
*/
public class MenuWithSubMenu extends Menu {
	private List<SubMenu> sub_button;
	/**
	 * @param name
	 */
	public MenuWithSubMenu(String name) {
		super(name);
	}
	/**
	 * Date:2015年9月14日
	 * Time:下午1:42:40
	 */
	public List<SubMenu> getSub_button() {
		return sub_button;
	}
	/**
	 * Date:2015年9月14日
	 * Time:下午1:42:40
	 */
	public void setSub_button(List<SubMenu> sub_button) {
		this.sub_button = sub_button;
	}
	
	
}
