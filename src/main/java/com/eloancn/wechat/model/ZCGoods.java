package com.eloancn.wechat.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;


/**
 * 
 * @Project Name:Mobile_Spring
 * @File Name:ZCGoods.java
 * @Package Name:com.eloancn.dkweb.controller.mobile
 * @Date:2015-9-22 上午11:13:05 
 * @Copyright (c) 2015, eloancn All Rights Reserved.
 * @author zhen.liu
 * @version 1.0
 * @category(tell me)
 */
@Alias("zCGoods")
public class ZCGoods implements Serializable{

	
	private static final long serialVersionUID = -5902239741116432186L;

	private Integer id;   //产品主键id
	private String organizer;//发起人
	private Integer cate;//分类，1商品众筹，2收益权众筹，3公益众筹
	private String name;//商品名称
	private String pic;//标题图
	private String info;//简介
	private String detail;//详情
	private String target;//目标金额
	private Integer hbsj;//项目成功结束后几天内回报
	private Integer s_time;//开始时间
	private Integer e_time; //结束时间
	private Integer addtime;//添加时间
	private Integer edittime;//编辑时间
	private Integer state;//1,正常，0，删除
	private Integer success;//0失败，1成功
	private String url;//详情url
	
	private String openId;
	private Long currTime;//当前时间
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrganizer() {
		return organizer;
	}
	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
	public Integer getCate() {
		return cate;
	}
	public void setCate(Integer cate) {
		this.cate = cate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Integer getHbsj() {
		return hbsj;
	}
	public void setHbsj(Integer hbsj) {
		this.hbsj = hbsj;
	}
	public Integer getS_time() {
		return s_time;
	}
	public void setS_time(Integer s_time) {
		this.s_time = s_time;
	}
	public Integer getE_time() {
		return e_time;
	}
	public void setE_time(Integer e_time) {
		this.e_time = e_time;
	}
	public Integer getAddtime() {
		return addtime;
	}
	public void setAddtime(Integer addtime) {
		this.addtime = addtime;
	}
	public Integer getEdittime() {
		return edittime;
	}
	public void setEdittime(Integer edittime) {
		this.edittime = edittime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Long getCurrTime() {
		return currTime;
	}
	public void setCurrTime(Long currTime) {
		this.currTime = currTime;
	}
	
}

