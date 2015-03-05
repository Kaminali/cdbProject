/**
 * 
 */
package com.excilys.cdb.controler.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.controler.connection.ConnectionManager;
import com.excilys.cdb.model.bean.Computer;
import com.excilys.cdb.model.dao.ComputerDAO;

/**
 * @author excilys
 *
 */
public class ComputerServices implements IComputerServices {

	
	private ComputerDAO computerDAO;
	private Connection connection;

	private void openConnection() {
		connection = ConnectionManager.instance.getConnection();
		computerDAO = ComputerDAO.instance;
	}
	
	private void closeConnection() {
		try {
			connection.setAutoCommit(true);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Computer> getAllComputer(long begin, long nb) {
		List<Computer> computerList;
		openConnection();
		if (begin == -1 || nb == -1) {
			computerList = computerDAO.getList(connection);
		} else {
			computerList = computerDAO.getList(connection, begin, nb);
		}
		closeConnection();
		return computerList;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#getComputerById(long)
	 */
	@Override
	public Computer getComputerById(long id) {
		openConnection();
		Computer computer = computerDAO.getById(connection, id);
		closeConnection();
		return computer;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#insertComputer(com.excilys.cdb.model.bean.Computer)
	 */
	@Override
	public void insertComputer(Computer computer) throws Exception {
		openConnection();
		computerDAO.insert(connection, computer);
		closeConnection();

	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#updateComputer(com.excilys.cdb.model.bean.Computer)
	 */
	@Override
	public void updateComputer(Computer computer) throws Exception {
		openConnection();
		computerDAO.update(connection, computer);
		closeConnection();

	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#deleteComputer(java.lang.Long)
	 */
	@Override
	public void deleteComputer(Long id) throws Exception {
		openConnection();
		Computer computer = new Computer();
		computer.setId(id);
		computerDAO.delete(connection, computer);
		closeConnection();

	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#getNb()
	 */
	@Override
	public int getNb() {
		openConnection();
		int temp = computerDAO.getNb(connection);
		closeConnection();
		return temp;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#getByName(java.lang.String, long, long)
	 */
	@Override
	public List<Computer> getByName(String name, long begin, long nb) {
		List<Computer> computerList;
		openConnection();
		if (begin == -1 || nb == -1) {
			computerList = computerDAO.getByName(connection, name);
		} else {
			computerList = computerDAO.getByName(connection, name, begin, nb);
		}
		closeConnection();
		return computerList;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#getNb(java.lang.String)
	 */
	@Override
	public int getNb(String name) {
		openConnection();
		int temp = computerDAO.getNb(connection, name);
		closeConnection();
		return temp;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#deleteComputer(java.util.ArrayList)
	 */
	@Override
	public void deleteComputer(ArrayList<Long> computersId) {
		openConnection();
		try {
			connection.setAutoCommit(false);
			for(long id : computersId) {
				Computer computer = new Computer();
				computer.setId(id);
				computerDAO.delete(connection, computer);
			}
			connection.commit();
		} catch(Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new Error();
			}
		} finally {
			closeConnection();
		}
	}

}
