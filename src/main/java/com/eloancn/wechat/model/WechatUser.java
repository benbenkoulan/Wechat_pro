package com.eloancn.wechat.model;
/**   
* @ClassName:WechatUser.java 
* @Function com.eloancn.wechat.common.model 
* @author: liben
* @date 2015年9月10日 下午3:40:03 
*/
public class WechatUser { 
	private Integer ID;
	private String NICKNAME;
	private String OPENID;
	private int SEX;
	private String CITY;
	private String COUNTRY;
	private String PROVINCE;
	private String LANGUAGE;
	private String HEADIMGURL;
	private int SUBSCRIBE_TIME;
	private String UNIONID;
	private String REMARK;
	private String GROUPID;
	private int SUBSCRIBE;
	private int UNSUBSCRIBE_TIME;
	private String APP_SECRET;
	private String OUT_UID;
	private String IS_BIND;
	private String IS_ADD_WXUSER;
	private String PHONE;
	private String TAG;//添加Tag标示扫描二维码
	
	/**
	 * tAG.
	 * @return  the tAG
	 */
	public String getTAG() {
		return TAG;
	}
	/**
	 * tAG.
	 * @param   tAG    the tAG to set
	 */
	public void setTAG(String tAG) {
		TAG = tAG;
	}
	/**
	 * aPP_SECRET.
	 *
	 * @return  the aPP_SECRET
	 */
	public String getAPP_SECRET() {
		return APP_SECRET;
	}
	/**
	 * aPP_SECRET.
	 *
	 * @param   aPP_SECRET    the aPP_SECRET to set
	
	 */
	public void setAPP_SECRET(String aPP_SECRET) {
		APP_SECRET = aPP_SECRET;
	}
	/**
	 * pHONE.
	 *
	 * @return  the pHONE
	 */
	public String getPHONE() {
		return PHONE;
	}
	/**
	 * pHONE.
	 *
	 * @param   pHONE    the pHONE to set
	
	 */
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	/**
	 * oUT_UID.
	 *
	 * @return  the oUT_UID
	 */
	public String getOUT_UID() {
		return OUT_UID;
	}
	/**
	 * oUT_UID.
	 *
	 * @param   oUT_UID    the oUT_UID to set
	
	 */
	public void setOUT_UID(String oUT_UID) {
		OUT_UID = oUT_UID;
	}
	/**
	 * iS_BIND.
	 *
	 * @return  the iS_BIND
	 */
	public String getIS_BIND() {
		return IS_BIND;
	}
	/**
	 * iS_BIND.
	 *
	 * @param   iS_BIND    the iS_BIND to set
	
	 */
	public void setIS_BIND(String iS_BIND) {
		IS_BIND = iS_BIND;
	}
	/**
	 * iS_ADD_WXUSER.
	 *
	 * @return  the iS_ADD_WXUSER
	 */
	public String getIS_ADD_WXUSER() {
		return IS_ADD_WXUSER;
	}
	/**
	 * iS_ADD_WXUSER.
	 *
	 * @param   iS_ADD_WXUSER    the iS_ADD_WXUSER to set
	
	 */
	public void setIS_ADD_WXUSER(String iS_ADD_WXUSER) {
		IS_ADD_WXUSER = iS_ADD_WXUSER;
	}
	/**
	 * sUBSCRIBE.
	 *
	 * @return  the sUBSCRIBE
	 */
	public int getSUBSCRIBE() {
		return SUBSCRIBE;
	}
	/**
	 * sUBSCRIBE.
	 *
	 * @param   sUBSCRIBE    the sUBSCRIBE to set
	
	 */
	public void setSUBSCRIBE(int sUBSCRIBE) {
		SUBSCRIBE = sUBSCRIBE;
	}
	/**
	 * uNSUBSCRIBE_TIME.
	 *
	 * @return  the uNSUBSCRIBE_TIME
	 */
	public int getUNSUBSCRIBE_TIME() {
		return UNSUBSCRIBE_TIME;
	}
	/**
	 * uNSUBSCRIBE_TIME.
	 *
	 * @param   uNSUBSCRIBE_TIME    the uNSUBSCRIBE_TIME to set
	
	 */
	public void setUNSUBSCRIBE_TIME(int uNSUBSCRIBE_TIME) {
		UNSUBSCRIBE_TIME = uNSUBSCRIBE_TIME;
	}
	/**
	 * hEADIMGURL.
	 *
	 * @return  the hEADIMGURL
	 */
	public String getHEADIMGURL() {
		return HEADIMGURL;
	}
	/**
	 * hEADIMGURL.
	 *
	 * @param   hEADIMGURL    the hEADIMGURL to set
	
	 */
	public void setHEADIMGURL(String hEADIMGURL) {
		HEADIMGURL = hEADIMGURL;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午5:33:25
	 */
	public Integer getID() {
		return ID;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午5:33:25
	 */
	public void setID(Integer iD) {
		ID = iD;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public String getNICKNAME() {
		return NICKNAME;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public void setNICKNAME(String nICKNAME) {
		NICKNAME = nICKNAME;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public String getOPENID() {
		return OPENID;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public void setOPENID(String oPENID) {
		OPENID = oPENID;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public int getSEX() {
		return SEX;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public void setSEX(int sEX) {
		SEX = sEX;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public String getCITY() {
		return CITY;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public String getCOUNTRY() {
		return COUNTRY;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public void setCOUNTRY(String cOUNTRY) {
		COUNTRY = cOUNTRY;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public String getPROVINCE() {
		return PROVINCE;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public void setPROVINCE(String pROVINCE) {
		PROVINCE = pROVINCE;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public String getLANGUAGE() {
		return LANGUAGE;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public void setLANGUAGE(String lANGUAGE) {
		LANGUAGE = lANGUAGE;
	}
	
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public int getSUBSCRIBE_TIME() {
		return SUBSCRIBE_TIME;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public void setSUBSCRIBE_TIME(int sUBSCRIBE_TIME) {
		SUBSCRIBE_TIME = sUBSCRIBE_TIME;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public String getUNIONID() {
		return UNIONID;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public void setUNIONID(String uNIONID) {
		UNIONID = uNIONID;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public String getREMARK() {
		return REMARK;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public String getGROUPID() {
		return GROUPID;
	}
	/**
	 * Date:2015年9月10日
	 * Time:下午3:52:58
	 */
	public void setGROUPID(String gROUPID) {
		GROUPID = gROUPID;
	}
	
	
}
