/**
 * 
 */
package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * @author excilys
 *
 */
public abstract class BaseDAO {

	protected ConnectionManager connectionManager;
	protected ResultSet result;
	protected PreparedStatement statement;
	protected Connection connection;

	public BaseDAO(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	protected void initStatement() {

		result = null;
		statement = null;
		connection = connectionManager.openConnection();
	}

	protected void closeStatement() {

		try {
			if (result != null)

				result.close();

			if (statement != null)

				statement.close();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

		try {
			connectionManager.closeConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

	}
	
	public <T> ArrayList<T> getList(Long begin, Long nb) {
		throw new RuntimeException("not implemented yet");
	}
	
	/**
	* Can get too many data
	*/
	@Deprecated
	public <T> ArrayList<T> getList() {
		throw new RuntimeException("not implemented yet");
	}
	
	public <T> ArrayList<T> getListById(ArrayList<Long> listId) {
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
