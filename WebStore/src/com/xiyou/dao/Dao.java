package com.xiyou.dao;

import java.util.List;

public interface Dao<T> {
	/**
	 * ����һ����¼������������¼������ֵ��id�����أ�
	 * @param sql
	 * @param args
	 * @return
	 */
	long insert(String sql, Object ... args);

	/**
	 * ����һ����¼��update��delete
	 * @param sql
	 * @param args
	 */
	void update(String sql, Object ... args);

	/**
	 * ��ѯ�����ز鵽����Ķ���
	 * @param sql
	 * @param args
	 * @return
	 */
	T query(String sql, Object ... args);

	/**
	 * ������ѯ
	 * @param sql
	 * @param args
	 * @return
	 */
	List<T> queryForList(String sql, Object ... args);

	/**
	 * ��һ���ֶν��в�ѯ��������ֵ��ͳ��ֵ
	 * @param sql
	 * @param args
	 * @return
	 */
	<V> V getSingleVal(String sql, Object ... args);

	/**
	 * ��������update��delete
	 * @param sql
	 * @param params
	 */
	void batch(String sql, Object[]... params);
}