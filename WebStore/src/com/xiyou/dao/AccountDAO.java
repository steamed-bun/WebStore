package com.xiyou.dao;

import com.xiyou.domain.Account;

public interface AccountDAO {

	/**
	 * 根据传入accountId查询到对应的account对象，并返回
	 * @param accountId
	 * @return
	 */
	public abstract Account get(Integer accountId);

	/**
	 * 根据传入的参数，对余额进行更新
	 * @param accountId
	 * @param amount
	 */
	public abstract void updateBalance(Integer accountId, float amount);

}