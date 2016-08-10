package com.eloancn.wechat.model;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;


/**
 * 
 * ClassName: ZCOrder <br/>
 * Function: 众筹订单<br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2015-10-9 上午10:28:16 <br/>
 *
 * @author liuzhen
 * @version
 */
@Alias("zCOrder")
public class ZCOrder implements Serializable{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//订单id
	private Integer goods_id;//商品id
	private Integer dang_id;//档次id 一个商品有多个档次
	private Integer yunfei;//运费
	private Integer number;//数量
	private String goods_name;//商品名称
	private String goods_pic;//商品图片
	private Integer address_id;//地址id
	private String address;//地址
	private Integer price;//金额
	private Integer status;//状态:0已下单，1已支付，2已发货，3已完成，4已取消，5已退款
	private Integer addtime;//下单时间
	private Integer edittime;//修改时间
	private Integer uid;//微信用户id
	private Integer out_id;//翼龙贷用户id
	private String name;//收货人
	private String phone;//联系电话
	private String beizhu;//备注
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public Integer getDang_id() {
		return dang_id;
	}
	public void setDang_id(Integer dang_id) {
		this.dang_id = dang_id;
	}
	public Integer getYunfei() {
		return yunfei;
	}
	public void setYunfei(Integer yunfei) {
		this.yunfei = yunfei;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_pic() {
		return goods_pic;
	}
	public void setGoods_pic(String goods_pic) {
		this.goods_pic = goods_pic;
	}
	public Integer getAddress_id() {
		return address_id;
	}
	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getOut_id() {
		return out_id;
	}
	public void setOut_id(Integer out_id) {
		this.out_id = out_id;
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
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	


}

