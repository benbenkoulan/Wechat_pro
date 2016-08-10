package com.eloancn.wechat.entities.base;

/**   
* @ClassName:ViewMenuWithoutSubMenu.java 
* @Function Beans 
* @author: liben
* @date 2015年9月14日 下午1:47:10 
*/
public class ViewMenuWithoutSubMenu extends MenuWithoutSubMenu {

	private String url;
	private final String type = "view";
	
	/**
	 * @param name
	 */
	public ViewMenuWithoutSubMenu(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Date:2015年9月14日
	 * Time:下午1:47:44
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Date:2015年9月14日
	 * Time:下午1:47:44
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Date:2015年9月14日
	 * Time:下午1:47:44
	 */
	public String getType() {
		return type;
	}

	
}
