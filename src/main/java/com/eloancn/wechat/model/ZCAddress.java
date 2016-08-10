package com.eloancn.wechat.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

/**
 * 
 * ClassName: ZCAddress <br/>
 * Function: 众筹订单地址. <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2015-10-14 上午11:22:14 <br/>
 *
 * @author liuzhen
 * @version
 */
@Alias("zCAddress")
public class ZCAddress implements Serializable{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//主键id
	private String name;//姓名
	private String phone;//电话
	private String province;//省
	private String city;//市
	private String detail;//详细地址
	private String uid;//用户id
	private String out_uid;//翼龙贷用户id
	private Integer status;//0非默认，1默认
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOut_uid() {
		return out_uid;
	}
	public void setOut_uid(String out_uid) {
		this.out_uid = out_uid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}

