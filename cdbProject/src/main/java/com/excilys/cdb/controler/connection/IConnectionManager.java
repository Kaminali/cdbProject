package com.excilys.cdb.controler.connection;

import java.sql.Connection;

public interface IConnectionManager {
	public Connection getConnection();
	public void closeAll() throws Exception;
}
