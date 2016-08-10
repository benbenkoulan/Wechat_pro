/**
 * Project Name:wechat-20150909
 * File Name:UserMsg.java
 * Package Name:com.eloancn.wechat.model
 * Date:2015年10月22日下午3:12:47
 * Copyright (c) 2015, eloancn All Rights Reserved.
 *
*/
/**
 * Project Name:wechat-20150909
 * File Name:UserMsg.java
 * Package Name:com.eloancn.wechat.model
 * Date:2015年10月22日下午3:12:47
 * Copyright (c) 2015, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.model;
/**
 * ClassName: UserMsg <br/>
 * Function: 微信用户信息 <br/>
 * Reason: TODO ADD REASON(可选). <br/>
 * date: 2015年10月22日 下午3:12:47 <br/>
 *
 * @author liben
 * @version 
 */
public class UserMsg {
	private int MSGID;
	private String FROMUSERNAME;
	private String MSGTYPE;
	private String MEDIAID;
	private String CONTENT;
	private String TITLE;
	private String DESCRIPTION;
	private String MUISCURL;
	private String HQMUISCURL;
	private String THUMBMEDIAID;
	private String PICURL;
	private String URL;
	private int CREATETIME;
	private int ISREPLY;
	private int REPLYID;
	private String REPLYCONTENT;
	private int REPLYTIME;
	/**
	 * mSGID.
	 *
	 * @return  the mSGID
	 */
	public int getMSGID() {
		return MSGID;
	}
	/**
	 * mSGID.
	 *
	 * @param   mSGID    the mSGID to set
	
	 */
	public void setMSGID(int mSGID) {
		MSGID = mSGID;
	}
	/**
	 * fROMUSERNMAE.
	 *
	 * @return  the fROMUSERNMAE
	 */
	public String getFROMUSERNAME() {
		return FROMUSERNAME;
	}
	/**
	 * fROMUSERNMAE.
	 *
	 * @param   fROMUSERNMAE    the fROMUSERNMAE to set
	
	 */
	public void setFROMUSERNAME(String fROMUSERNAME) {
		FROMUSERNAME = fROMUSERNAME;
	}
	/**
	 * mSGTYPE.
	 *
	 * @return  the mSGTYPE
	 */
	public String getMSGTYPE() {
		return MSGTYPE;
	}
	/**
	 * mSGTYPE.
	 *
	 * @param   mSGTYPE    the mSGTYPE to set
	
	 */
	public void setMSGTYPE(String mSGTYPE) {
		MSGTYPE = mSGTYPE;
	}
	/**
	 * mEDIAID.
	 *
	 * @return  the mEDIAID
	 */
	public String getMEDIAID() {
		return MEDIAID;
	}
	/**
	 * mEDIAID.
	 *
	 * @param   mEDIAID    the mEDIAID to set
	
	 */
	public void setMEDIAID(String mEDIAID) {
		MEDIAID = mEDIAID;
	}
	/**
	 * cONTENT.
	 *
	 * @return  the cONTENT
	 */
	public String getCONTENT() {
		return CONTENT;
	}
	/**
	 * cONTENT.
	 *
	 * @param   cONTENT    the cONTENT to set
	
	 */
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	/**
	 * tITLE.
	 *
	 * @return  the tITLE
	 */
	public String getTITLE() {
		return TITLE;
	}
	/**
	 * tITLE.
	 *
	 * @param   tITLE    the tITLE to set
	
	 */
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	/**
	 * dESCRIPTION.
	 *
	 * @return  the dESCRIPTION
	 */
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	/**
	 * dESCRIPTION.
	 *
	 * @param   dESCRIPTION    the dESCRIPTION to set
	
	 */
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	/**
	 * mUISCURL.
	 *
	 * @return  the mUISCURL
	 */
	public String getMUISCURL() {
		return MUISCURL;
	}
	/**
	 * mUISCURL.
	 *
	 * @param   mUISCURL    the mUISCURL to set
	
	 */
	public void setMUISCURL(String mUISCURL) {
		MUISCURL = mUISCURL;
	}
	/**
	 * hQMUISCURL.
	 *
	 * @return  the hQMUISCURL
	 */
	public String getHQMUISCURL() {
		return HQMUISCURL;
	}
	/**
	 * hQMUISCURL.
	 *
	 * @param   hQMUISCURL    the hQMUISCURL to set
	
	 */
	public void setHQMUISCURL(String hQMUISCURL) {
		HQMUISCURL = hQMUISCURL;
	}
	/**
	 * tHUMBMEDIAID.
	 *
	 * @return  the tHUMBMEDIAID
	 */
	public String getTHUMBMEDIAID() {
		return THUMBMEDIAID;
	}
	/**
	 * tHUMBMEDIAID.
	 *
	 * @param   tHUMBMEDIAID    the tHUMBMEDIAID to set
	
	 */
	public void setTHUMBMEDIAID(String tHUMBMEDIAID) {
		THUMBMEDIAID = tHUMBMEDIAID;
	}
	/**
	 * pICURL.
	 *
	 * @return  the pICURL
	 */
	public String getPICURL() {
		return PICURL;
	}
	/**
	 * pICURL.
	 *
	 * @param   pICURL    the pICURL to set
	
	 */
	public void setPICURL(String pICURL) {
		PICURL = pICURL;
	}
	/**
	 * uRL.
	 *
	 * @return  the uRL
	 */
	public String getURL() {
		return URL;
	}
	/**
	 * uRL.
	 *
	 * @param   uRL    the uRL to set
	
	 */
	public void setURL(String uRL) {
		URL = uRL;
	}
	/**
	 * cREATETIME.
	 *
	 * @return  the cREATETIME
	 */
	public int getCREATETIME() {
		return CREATETIME;
	}
	/**
	 * cREATETIME.
	 *
	 * @param   cREATETIME    the cREATETIME to set
	
	 */
	public void setCREATETIME(int cREATETIME) {
		CREATETIME = cREATETIME;
	}
	/**
	 * iSREPLY.
	 *
	 * @return  the iSREPLY
	 */
	public int getISREPLY() {
		return ISREPLY;
	}
	/**
	 * iSREPLY.
	 *
	 * @param   iSREPLY    the iSREPLY to set
	
	 */
	public void setISREPLY(int iSREPLY) {
		ISREPLY = iSREPLY;
	}
	/**
	 * rEPLYID.
	 *
	 * @return  the rEPLYID
	 */
	public int getREPLYID() {
		return REPLYID;
	}
	/**
	 * rEPLYID.
	 *
	 * @param   rEPLYID    the rEPLYID to set
	
	 */
	public void setREPLYID(int rEPLYID) {
		REPLYID = rEPLYID;
	}
	/**
	 * rEPLYCONTENT.
	 *
	 * @return  the rEPLYCONTENT
	 */
	public String getREPLYCONTENT() {
		return REPLYCONTENT;
	}
	/**
	 * rEPLYCONTENT.
	 *
	 * @param   rEPLYCONTENT    the rEPLYCONTENT to set
	
	 */
	public void setREPLYCONTENT(String rEPLYCONTENT) {
		REPLYCONTENT = rEPLYCONTENT;
	}
	/**
	 * rEPLYTIME.
	 *
	 * @return  the rEPLYTIME
	 */
	public int getREPLYTIME() {
		return REPLYTIME;
	}
	/**
	 * rEPLYTIME.
	 *
	 * @param   rEPLYTIME    the rEPLYTIME to set
	
	 */
	public void setREPLYTIME(int rEPLYTIME) {
		REPLYTIME = rEPLYTIME;
	}
	
	
}

