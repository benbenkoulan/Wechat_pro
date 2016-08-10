/**
 * Project Name:wechat-20150909
 * File Name:UserMsgService.java
 * Package Name:com.eloancn.wechat.service
 * Date:2015年10月22日下午3:36:12
 * Copyright (c) 2015, eloancn All Rights Reserved.
 *
*/
/**
 * Project Name:wechat-20150909
 * File Name:UserMsgService.java
 * Package Name:com.eloancn.wechat.service
 * Date:2015年10月22日下午3:36:12
 * Copyright (c) 2015, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eloancn.wechat.dao.DaoSupport;
import com.eloancn.wechat.model.UserMsg;

/**
 * ClassName: UserMsgService <br/>
 * date: 2015年10月22日 下午3:36:12 <br/>
 *
 * @author liben
 * @version 
 */
@Service("userMsgService")
public class UserMsgService {
	
	@SuppressWarnings("rawtypes")
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Integer saveUserMsg(UserMsg um) {
		try {
		return (Integer)dao.save("UserMsgMapper.saveUserMsg", um);
		} catch (Exception e) {
			logger.error("-----saveUserMsg---------" + e.getMessage());	
		}
		return null;
	}
	
	public int updateReplyMsg(UserMsg um) {
		try {
			return (Integer)dao.update("UserMsgMapper.updateReplyMsg", um);
		} catch (Exception e) {
			logger.error("-----updateReplyMsg---------" + e.getMessage());	
		}
		return 0;
	}
}

