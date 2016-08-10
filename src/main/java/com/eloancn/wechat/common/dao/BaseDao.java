package com.eloancn.wechat.common.dao;

import java.util.List;

public interface BaseDao<M,VM>{
	public void create(M m);
	public void update(M m);
	public void delete(int id);
	public M getById(int id);
	public List<M> getByConditionPage(VM vm);
	public List<M> getQuery(VM vm);
}
