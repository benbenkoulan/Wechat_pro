package com.eloancn.wechat.common.encrypt;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * ClassName: EncryptPropertyPlaceholderConfigurer
 * 
 * @Description: 用于和spring 整合数据源登录用户名与密码解密
 * @author mingmeijun
 * @date 2015-6-4
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private String[] encryptPropNames = { "jdbc_username", "jdbc_password" };

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptProp(propertyName)) {
			return DESUtils.getDecryptString(propertyValue);
		} else {
			return propertyValue;
		}
	}

	/**
	 * 判断是否是加密的属性
	 * 
	 * @param propertyName
	 * @return
	 */
	private boolean isEncryptProp(String propertyName) {
		for (String encryptPropName : encryptPropNames) {
			if (encryptPropName.equals(propertyName)) {
				return true;
			}
		}
		return false;
	}
}
