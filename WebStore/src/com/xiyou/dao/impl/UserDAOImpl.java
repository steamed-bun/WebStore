package com.xiyou.dao.impl;

import com.xiyou.dao.UserDAO;
import com.xiyou.domain.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

	@Override
	public User getUser(String username) {
		String sql = "select USERID, USERNAME, ACCOUNTID from user_info where USERNAME = ?";
		return query(sql, username); 
	}

}
