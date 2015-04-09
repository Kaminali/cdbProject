package com.excilys.cdb.model.dao;

import com.excilys.cdb.model.bean.User;

public interface IUserDAO {
	/**
	 * 
	 * @param user : a user name
	 * @return a User corresponding to the user name
	 */
	User findByUserName(String user);
}
