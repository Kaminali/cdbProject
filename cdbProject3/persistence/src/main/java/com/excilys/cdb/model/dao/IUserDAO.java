package com.excilys.cdb.model.dao;

import com.excilys.cdb.model.bean.User;

public interface IUserDAO {
		User findByUserName(String user);
}
