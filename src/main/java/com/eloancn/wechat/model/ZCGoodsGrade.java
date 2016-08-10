package com.eloancn.wechat.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;


/**
 * 
 * ClassName: ZCGoodsGrade <br/>
 * Function: 众筹商品的档次 <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2015-10-9 上午10:07:57 <br/>
 *
 * @author liuzhen
 * @version
 */
@Alias("zCGoodsGrade")
public class ZCGoodsGrade implements Serializable{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer gid;//商品id
	private Integer unit_price;//单价
	private Integer number;//该档次份额
	private Integer yunfei;//运费
	private String miaosu;//描述
	private Integer state;//1正常，0删除
	private Integer already_number;//
	private Integer shengyu_number;//
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Integer getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(Integer unit_price) {
		this.unit_price = unit_price;
	}
	public Integer getYunfei() {
		return yunfei;
	}
	public void setYunfei(Integer yunfei) {
		this.yunfei = yunfei;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getMiaosu() {
		return miaosu;
	}
	public void setMiaosu(String miaosu) {
		this.miaosu = miaosu;
	}
	public Integer getAlready_number() {
		return already_number;
	}
	public void setAlready_number(Integer already_number) {
		this.already_number = already_number;
	}
	public Integer getShengyu_number() {
		return shengyu_number;
	}
	public void setShengyu_number(Integer shengyu_number) {
		this.shengyu_number = shengyu_number;
	}
	
}

