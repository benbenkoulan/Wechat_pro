package com.eloancn.wechat.model;

import java.io.Serializable;


/**
 * ClassName: Banner
 * @Description: banner数据
 * @author zhangjun
 * @date 2015-08-17
 */
public class Banner implements Serializable{

	
	private static final long serialVersionUID = -5902239741116432186L;

	private Integer id;   //主键id
	private String picturePath;   //图片存放路径
	private String hrefUrl;   //图片链接到其他地方的url
	private Integer position;   //图片显示位置
	private Integer isenabled;   //开关（1 表示启用   2 表示不启用）
	private Integer type;   //终端类型(1表示PC  2表示移动端)
	private String description;   //描述信息
	private Integer cdate;   //操作时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public String getHrefUrl() {
		return hrefUrl;
	}
	public void setHrefUrl(String hrefUrl) {
		this.hrefUrl = hrefUrl;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Integer getIsenabled() {
		return isenabled;
	}
	public void setIsenabled(Integer isenabled) {
		this.isenabled = isenabled;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCdate() {
		return cdate;
	}
	public void setCdate(Integer cdate) {
		this.cdate = cdate;
	}
	
	@Override
	public String toString() {
		
		return "[Banner include:Id="+this.getId()+",picturePath="+this.getPicturePath()+
                ",hrefUrl="+this.getHrefUrl()+",position="+this.getPosition()+
                ",isenabled="+this.getIsenabled() +",type="+this.getType()+",description="+this.getDescription()+
                ",cdate="+this.getCdate()+"]";
	}
	
	 public Banner(Integer position,Integer isenabled){
	    	this.position = position;
	    	this.isenabled = isenabled;
	    }
	public Banner() {
		
	}
}

