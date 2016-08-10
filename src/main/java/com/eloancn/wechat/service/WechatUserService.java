package com.eloancn.wechat.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eloancn.wechat.dao.DaoSupport;
import com.eloancn.wechat.entities.ScanParam;
import com.eloancn.wechat.model.WechatUser;

/**   
* @ClassName:WechatUserService.java 
* @Function com.eloancn.wechat.service 
* @author: liben
* @date 2015年9月10日 下午4:45:37 
*/
@Service("wechatUserService")
public class WechatUserService {
	@SuppressWarnings("rawtypes")
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public WechatUser getWechatUserInfo(String openId) {
		try {
			return (WechatUser)dao.findForObject("WechatUserMapper.getWechatUserInfo", openId);
		} catch (Exception e) {
			logger.info("------getWechatUserInfo---------" + e.getMessage());
		}
		return null;
	}
	
	public Integer saveWechatUser(WechatUser wu) {
		try {
			return (Integer)dao.save("WechatUserMapper.saveWechatUser", wu);
		} catch (Exception e) {
			logger.info("------saveWechatUser---------" + e.getMessage());
		}	
		return null;
	}
	
	public Integer saveWechatUserByScan(WechatUser wu){
		try {
			return (Integer)dao.save("WechatUserMapper.saveWechatUserByScan", wu);
		} catch (Exception e) {
			logger.info("------saveWechatUserByScan---------" + e.getMessage());
		}	
		return null;
	}
	
	public WechatUser getWechatUserInfoByScan(ScanParam sp){
		try {
			return (WechatUser)dao.findForObject("WechatUserMapper.getWechatUserInfoByScan",sp);
		} catch (Exception e) {
			logger.info("------getWechatUserInfoByScan---------" + e.getMessage());
		}
		return null;
	}
	
	public void updateWechatUser(WechatUser wu){
		try {
			dao.update("WechatUserMapper.updateWechatUser", wu);
		} catch (Exception e) {
			logger.info("------updateWechatUser---------" + e.getMessage());
		}
	}
	
	public void setUnSubscribe(WechatUser wu){
		try {
			dao.update("WechatUserMapper.setUnSubscribe", wu);
		} catch (Exception e) {
			logger.info("------setUnSubscribe---------" + e.getMessage());
		}
	}
	
	public int setWechatUserLogin(WechatUser wu){
		try {
			return (Integer)dao.update("WechatUserMapper.setWechatUserLogin", wu);
		} catch (Exception e) {
			logger.info("------setWechatUserLogin---------" + e.getMessage());
		}
		return 0;
	}
	public WechatUser getWechatUserById(String id) {
		try {
			return (WechatUser)dao.findForObject("WechatUserMapper.getWechatUserById", id);
		} catch (Exception e) {
			logger.info("------getWechatUserByUid---------" + e.getMessage());
		}
		return null;
	}
}
