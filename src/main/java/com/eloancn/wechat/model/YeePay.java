/**
 * Project Name:wechat-20150909
 * File Name:YeePay.java
 * Package Name:com.eloancn.wechat.model
 * Date:2016年1月8日下午1:27:46
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
*/
/**
 * Project Name:wechat-20150909
 * File Name:YeePay.java
 * Package Name:com.eloancn.wechat.model
 * Date:2016年1月8日下午1:27:46
 * Copyright (c) 2016, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.model;

import java.io.Serializable;

/**
 * ClassName: YeePay <br/>
 * Function: 易宝支付数据. <br/>
 * date: 2016年1月8日 下午1:27:46 <br/>
 *
 * @author liben
 * @version 
 */
public class YeePay implements Serializable{
	/**
	 * serialVersionUID:
	 */
	private static final long serialVersionUID = -870017506285849840L;
	private String card;
	private double amount;
	private String orderid;
	private int transtime;
	private int currency;
	private int productcatalog;
	private String productname;
	private String productdesc;
	private int identitytype;
	private int identityid;
	private int terminaltype;
	private String terminalid;
	private String userip;
	private String userua;
	private String paytypes;
	private String orderexpdate;
	private String idcardtype;
	private String idcard;
	private String owner;
	private String openId;
	/**
	 * card.
	 * @return  the card
	 */
	public String getCard() {
		return card;
	}
	/**
	 * card.
	 * @param   card    the card to set
	 */
	public void setCard(String card) {
		this.card = card;
	}
	/**
	 * amount.
	 * @return  the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * amount.
	 * @param   amount    the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * orderid.
	 * @return  the orderid
	 */
	public String getOrderid() {
		return orderid;
	}
	/**
	 * orderid.
	 * @param   orderid    the orderid to set
	 */
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	/**
	 * transtime.
	 * @return  the transtime
	 */
	public int getTranstime() {
		return transtime;
	}
	/**
	 * transtime.
	 * @param   transtime    the transtime to set
	 */
	public void setTranstime(int transtime) {
		this.transtime = transtime;
	}
	/**
	 * currency.
	 * @return  the currency
	 */
	public int getCurrency() {
		return currency;
	}
	/**
	 * currency.
	 * @param   currency    the currency to set
	 */
	public void setCurrency(int currency) {
		this.currency = currency;
	}
	/**
	 * productcatalog.
	 * @return  the productcatalog
	 */
	public int getProductcatalog() {
		return productcatalog;
	}
	/**
	 * productcatalog.
	 * @param   productcatalog    the productcatalog to set
	 */
	public void setProductcatalog(int productcatalog) {
		this.productcatalog = productcatalog;
	}
	/**
	 * productname.
	 * @return  the productname
	 */
	public String getProductname() {
		return productname;
	}
	/**
	 * productname.
	 * @param   productname    the productname to set
	 */
	public void setProductname(String productname) {
		this.productname = productname;
	}
	/**
	 * productdesc.
	 * @return  the productdesc
	 */
	public String getProductdesc() {
		return productdesc;
	}
	/**
	 * productdesc.
	 * @param   productdesc    the productdesc to set
	 */
	public void setProductdesc(String productdesc) {
		this.productdesc = productdesc;
	}
	/**
	 * identitytype.
	 * @return  the identitytype
	 */
	public int getIdentitytype() {
		return identitytype;
	}
	/**
	 * identitytype.
	 * @param   identitytype    the identitytype to set
	 */
	public void setIdentitytype(int identitytype) {
		this.identitytype = identitytype;
	}
	/**
	 * identityid.
	 * @return  the identityid
	 */
	public int getIdentityid() {
		return identityid;
	}
	/**
	 * identityid.
	 * @param   identityid    the identityid to set
	 */
	public void setIdentityid(int identityid) {
		this.identityid = identityid;
	}
	/**
	 * terminaltype.
	 * @return  the terminaltype
	 */
	public int getTerminaltype() {
		return terminaltype;
	}
	/**
	 * terminaltype.
	 * @param   terminaltype    the terminaltype to set
	 */
	public void setTerminaltype(int terminaltype) {
		this.terminaltype = terminaltype;
	}
	/**
	 * terminalid.
	 * @return  the terminalid
	 */
	public String getTerminalid() {
		return terminalid;
	}
	/**
	 * terminalid.
	 * @param   terminalid    the terminalid to set
	 */
	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
	}
	/**
	 * userip.
	 * @return  the userip
	 */
	public String getUserip() {
		return userip;
	}
	/**
	 * userip.
	 * @param   userip    the userip to set
	 */
	public void setUserip(String userip) {
		this.userip = userip;
	}
	/**
	 * userua.
	 * @return  the userua
	 */
	public String getUserua() {
		return userua;
	}
	/**
	 * userua.
	 * @param   userua    the userua to set
	 */
	public void setUserua(String userua) {
		this.userua = userua;
	}
	/**
	 * paytypes.
	 * @return  the paytypes
	 */
	public String getPaytypes() {
		return paytypes;
	}
	/**
	 * paytypes.
	 * @param   paytypes    the paytypes to set
	 */
	public void setPaytypes(String paytypes) {
		this.paytypes = paytypes;
	}
	/**
	 * orderexpdate.
	 * @return  the orderexpdate
	 */
	public String getOrderexpdate() {
		return orderexpdate;
	}
	/**
	 * orderexpdate.
	 * @param   orderexpdate    the orderexpdate to set
	 */
	public void setOrderexpdate(String orderexpdate) {
		this.orderexpdate = orderexpdate;
	}
	/**
	 * idcardtype.
	 * @return  the idcardtype
	 */
	public String getIdcardtype() {
		return idcardtype;
	}
	/**
	 * idcardtype.
	 * @param   idcardtype    the idcardtype to set
	 */
	public void setIdcardtype(String idcardtype) {
		this.idcardtype = idcardtype;
	}
	/**
	 * idcard.
	 * @return  the idcard
	 */
	public String getIdcard() {
		return idcard;
	}
	/**
	 * idcard.
	 * @param   idcard    the idcard to set
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	/**
	 * owner.
	 * @return  the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * owner.
	 * @param   owner    the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * openId.
	 * @return  the openId
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * openId.
	 * @param   openId    the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}

