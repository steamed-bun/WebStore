package com.xiyou.dao;

import com.xiyou.domain.Account;

public interface AccountDAO {

	/**
	 * ���ݴ���accountId��ѯ����Ӧ��account���󣬲�����
	 * @param accountId
	 * @return
	 */
	public abstract Account get(Integer accountId);

	/**
	 * ���ݴ���Ĳ������������и���
	 * @param accountId
	 * @param amount
	 */
	public abstract void updateBalance(Integer accountId, float amount);

}