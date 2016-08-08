package com.xiyou.service;

import com.xiyou.dao.AccountDAO;
import com.xiyou.dao.impl.AccountDAOImpl;
import com.xiyou.domain.Account;

public class AccountService {
	
	private AccountDAO accountDAO = new AccountDAOImpl();
	
	public Account getAccount(int accountId){
		return accountDAO.get(accountId);
	}
	
}
