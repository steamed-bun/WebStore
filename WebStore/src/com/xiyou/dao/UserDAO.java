package com.xiyou.dao;

import com.xiyou.domain.User;


public interface UserDAO {

	/**
	 * ����username��ȡ��Ӧ��user����
	 * @param username
	 * @return
	 */
	public abstract User getUser(String username);

}

