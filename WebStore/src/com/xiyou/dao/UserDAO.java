package com.xiyou.dao;

import com.xiyou.domain.User;


public interface UserDAO {

	/**
	 * 根就username获取对应的user对象
	 * @param username
	 * @return
	 */
	public abstract User getUser(String username);

}

