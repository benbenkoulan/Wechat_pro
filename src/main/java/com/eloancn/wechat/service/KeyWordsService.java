/**
 * Project Name:wechat-20150909
 * File Name:KeyWordsService.java
 * Package Name:com.eloancn.wechat.service
 * Date:2015年10月22日上午10:21:51
 * Copyright (c) 2015, eloancn All Rights Reserved.
 *
*/
/**
 * Project Name:wechat-20150909
 * File Name:KeyWordsService.java
 * Package Name:com.eloancn.wechat.service
 * Date:2015年10月22日上午10:21:51
 * Copyright (c) 2015, eloancn All Rights Reserved.
 *
 */

package com.eloancn.wechat.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.eloancn.wechat.dao.DaoSupport;
import com.eloancn.wechat.model.KeyWords;

/**
 * ClassName: KeyWordsService <br/>
 * Function: KeyWords数据库操作. <br/>
 * date: 2015年10月22日 上午10:21:51 <br/>
 *
 * @author liben
 * @version 
 */
@Service("keyWordsService")
@SuppressWarnings({ "unchecked" , "rawtypes"})
public class KeyWordsService {
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List<KeyWords> getUnknownKeyWords() {
		try {
			return dao.findForList("KeyWordsMapper.getUnknownKeyWords", null);
		} catch (Exception e) {
			logger.error("-----getUnknownKeyWords---------" + e.getMessage());	
		}
		return null;
	}
	
	public List<KeyWords> getKeyWords(String keyword) {
		try {
			return dao.findForList("KeyWordsMapper.getKeyWords", keyword);
		} catch (Exception e) {
			logger.error("-----getKeyWords---------" + e.getMessage());	
		}
		return null;
	}
	
	public List<KeyWords> getSubscribeKeyWords() {
		try {
			return dao.findForList("KeyWordsMapper.getSubscribeKeyWords", null);
		} catch (Exception e) {
			logger.error("-----getSubscribeKeyWords---------" + e.getMessage());	
		}
		return null;
	}
}

