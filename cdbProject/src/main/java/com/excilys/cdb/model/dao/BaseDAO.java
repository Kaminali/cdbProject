/**
 * 
 */
package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author excilys
 *
 */
public abstract class BaseDAO {

	protected ResultSet result;
	protected PreparedStatement statement;
	protected Connection connection;
	protected boolean autoClose;

	public BaseDAO(Connection connection, boolean autoClose) {
		this.connection = connection;
		this.autoClose = autoClose;
	}

	protected void initStatement() {

		result = null;
		statement = null;
	}

	protected void close() {

		try {
			if (result != null) {
				result.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		
		try {
			if (statement != null && autoClose == true) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public <T> List<T> getList(Long begin, Long nb) {
		throw new RuntimeException("not implemented yet");
	}

	/**
	 * Can get too many data
	 */
	@Deprecated
	public <T> List<T> getList() {
		throw new RuntimeException("not implemented yet");
	}

	public <T> List<T> getListById(List<Long> listId) {
		throw new RuntimeException("not implemented yet");
	}

	public <E> E getById(Long id) {
		throw new RuntimeException("not implemented yet");
	}

	public boolean insert(Object e) throws Exception {
		throw new RuntimeException("not implemented yet");
	}

	public boolean update(Object e) throws Exception {
		throw new RuntimeException("not implemented yet");
	}

	public boolean delete(Object e) throws Exception {
		throw new RuntimeException("not implemented yet");
	}

}
