/**
 * @Title: BaseDAO.java
 * @Copyright (C) 2015 翼龙贷
 * @Description:
 * @Revision History:
 * @Revision 1.0 2015年12月1日  吴青岭
 */
 

package com.eloancn.code.common.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * @ClassName: BaseDAO
 * @Description: 微信基础dao类
 * @author <a href="mailto:wqingling@eloancn.com">吴青岭</a> 于 2015年12月1日 上午9:57:12
 */

public class BaseDAO extends SqlSessionDaoSupport{
    /**
     * 注入sqlsession.
     */
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;
    /**
     * @Description:有条件批量查询数据
     * @param sqlid 查询sqlid
     * @param paramObj 查询条件
     * @return 查询结果（list对象）
     * @throws
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> selectList(String sqlid, Object paramObj) {
        return (List<T>) this.getSqlSession().selectList(sqlid, paramObj);
    }
    /**
     * @Description:无条件批量查询数据
     * @param sqlid 查询sqlid
     * @return 查询结果（list对象）
     * @throws
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> selectList(String sqlid) {
        return (List<T>) this.getSqlSession().selectList(sqlid);
    }
    /**
     * @Description:分页批量查询数据
     * @param sqlid 查询sqlid
     * @param paramObj 查询条件
     * @param arg3 分页信息
     * @return 查询结果（list对象）
     * @throws
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> selectList(String sqlid,Object paramObj,RowBounds arg3) {
        return (List<T>) this.getSqlSession().selectList(sqlid, paramObj, arg3);
    }
    /**
     * @Description:无条件查询一条数据.
     * @param sqlid 查询sqlid
     * @return 查询结果（list对象）
     * @throws
     */
    @SuppressWarnings("unchecked")
    public <T> T selectOne(String sqlid) {
        return (T) this.getSqlSession().selectOne(sqlid);
    }
    /**
     * @Description:有条件查询一条数据.
     * @param sqlid 查询sqlid
     * @param paramObj 查询条件
     * @return 查询结果（list对象）
     * @throws
     */
    @SuppressWarnings("unchecked")
    public <T> T selectOne(String sqlid, Object paramObj) {
        return (T) this.getSqlSession().selectOne(sqlid, paramObj);
    }
    /**
     * @Description:无条件删除数据
     * @param sqlid 删除数据sqlid
     * @return 
     * @throws
     */
    public int delete(String sqlid) {
        return this.getSqlSession().delete(sqlid);
    }
    /**
     * @Description:有条件删除数据
     * @param sqlid 删除数据sqlid
     * @param paramObj 删除数据条件
     * @return
     * @throws
     */
    public int delete(String sqlid,Object paramObj) {
        return this.getSqlSession().delete(sqlid, paramObj);
    }
    /**
     * @Description:有参数插入数据
     * @param sqlid 插入数据sqlid
     * @param paramObj 参数
     * @return
     * @throws
     */
    public int insert(String sqlid,Object paramObj) {
        return this.getSqlSession().insert(sqlid, paramObj);
    }
    /**
     * @Description:无参数插入数据
     * @param sqlid 插入数据sqlid
     * @return
     * @throws
     */
    public int insert(String sqlid) {
        return this.getSqlSession().insert(sqlid);
    }
    /**
     * @Description:有参数更新数据
     * @param sqlid 更新数据sqlid
     * @param paramObj 参数信息
     * @return
     * @throws
     */
    public int update(String sqlid,Object paramObj) {
        return this.getSqlSession().update(sqlid, paramObj);
    }
    /**
     * @Description:无参数更新数据
     * @param sqlid 更新数据sqlid
     * @return
     * @throws
     */
    public int update(String sqlid) {
        return this.getSqlSession().update(sqlid);
    }
}
