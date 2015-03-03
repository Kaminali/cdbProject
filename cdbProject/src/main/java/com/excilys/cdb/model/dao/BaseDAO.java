/**
 * 
 */
package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.controler.connection.IConnectionManager;

/**
 * @author excilys
 *
 */
public abstract class BaseDAO {

	protected IConnectionManager connectionManager;
	protected ResultSet result;
	protected PreparedStatement statement;
	protected Connection connection;

	public BaseDAO(IConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	protected void initStatement() {

		result = null;
		statement = null;
		connection = connectionManager.getConnection();
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
			if (statement != null) {
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

	public boolean insert(Object e) {
		throw new RuntimeException("not implemented yet");
	}

	public boolean update(Object e) {
		throw new RuntimeException("not implemented yet");
	}

	public boolean delete(Object e) {
		throw new RuntimeException("not implemented yet");
	}

}
