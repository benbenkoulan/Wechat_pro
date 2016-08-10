package com.eloancn.wechat.entities;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**   
* @ClassName:XStreamCDATA.java 
* @Function com.eloancn.wechat.entities 
* @author: liben
* @date 2015年9月9日 上午9:52:51 
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface XStreamCDATA {
}
