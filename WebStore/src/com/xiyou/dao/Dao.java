package com.xiyou.dao;

import java.util.List;

public interface Dao<T> {
	/**
	 * 插入一条记录，并将该条记录的主键值（id）返回，
	 * @param sql
	 * @param args
	 * @return
	 */
	long insert(String sql, Object ... args);

	/**
	 * 更新一条记录，update、delete
	 * @param sql
	 * @param args
	 */
	void update(String sql, Object ... args);

	/**
	 * 查询并返回查到的类的对象
	 * @param sql
	 * @param args
	 * @return
	 */
	T query(String sql, Object ... args);

	/**
	 * 批量查询
	 * @param sql
	 * @param args
	 * @return
	 */
	List<T> queryForList(String sql, Object ... args);

	/**
	 * 对一个字段进行查询，返回其值或统计值
	 * @param sql
	 * @param args
	 * @return
	 */
	<V> V getSingleVal(String sql, Object ... args);

	/**
	 * 批量进行update，delete
	 * @param sql
	 * @param params
	 */
	void batch(String sql, Object[]... params);
}