package com.xiyou.dao.impl;

import com.xiyou.dao.AccountDAO;
import com.xiyou.domain.Account;


public class AccountDAOImpl extends BaseDAO<Account> implements AccountDAO {

	@Override
	public Account get(Integer accountId) {
		String sql = "select ACCOUNTID,BALANCE from account where ACCOUNTID = ?";
		return query(sql, accountId); 
	}

	@Override
	public void updateBalance(Integer accountId, float amount) {
		String sql = "UPDATE account SET BALANCE = BALANCE - ? where ACCOUNTID = ?";
		update(sql, amount, accountId); 
	}

}
