/**
 * 
 */
package com.excilys.cdb.controler.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.controler.connection.ConnectionManager;
import com.excilys.cdb.controler.validate.CheckValues;
import com.excilys.cdb.model.bean.Computer;
import com.excilys.cdb.model.dao.ComputerDAO;

/**
 * @author excilys
 *
 */
public class ComputerServices {

	public List<Computer> getAllComputer(long begin, long nb) {
		List<Computer> computerList;
		ComputerDAO computerDAO = new ComputerDAO(ConnectionManager.instance.getConnection(), true);
		if (begin == -1 || nb == -1) {
			computerList = computerDAO.getList();
		} else {
			computerList = computerDAO.getList(begin, nb);
		}

		return computerList;
	}

	public Computer getComputerById(long id) {
		ComputerDAO computerDAO = new ComputerDAO(ConnectionManager.instance.getConnection(), true);
		Computer computer = computerDAO.getById(id);
		return computer;
	}

	public void insertComputer(Computer computer) throws Exception {
		ComputerDAO computerDAO = new ComputerDAO(ConnectionManager.instance.getConnection(), true);
		computerDAO.insert(computer);

	}

	public void updateComputer(Computer computer) throws Exception {
		ComputerDAO computerDAO = new ComputerDAO(ConnectionManager.instance.getConnection(), true);
		computerDAO.update(computer);

	}

	public void deleteComputer(Long id) throws Exception {
		ComputerDAO computerDAO = new ComputerDAO(ConnectionManager.instance.getConnection(), true);
		CheckValues.checkComputer(id);
		Computer computer = new Computer();
		computer.setId(id);
		computerDAO.delete(computer);

	}

	public int getNb() {
		ComputerDAO computerDAO = new ComputerDAO(ConnectionManager.instance.getConnection(), true);
		return computerDAO.getNb();
	}

	public List<Computer> getByName(String name, long begin, long nb) {
		List<Computer> computerList;
		ComputerDAO computerDAO = new ComputerDAO(ConnectionManager.instance.getConnection(), true);
		if (begin == -1 || nb == -1) {
			computerList = computerDAO.getByName(name);
		} else {
			computerList = computerDAO.getByName(name, begin, nb);
		}

		return computerList;
	}

	public int getNb(String name) {
		ComputerDAO computerDAO = new ComputerDAO(ConnectionManager.instance.getConnection(), true);
		return computerDAO.getNb(name);
	}

	public void deleteComputer(ArrayList<Long> computersId) {
		Connection connection = ConnectionManager.instance.getConnection();
		ComputerDAO computerDAO = new ComputerDAO(connection, false);
		try {
			connection.setAutoCommit(false);
			for(long id : computersId) {
				CheckValues.checkComputer(id);
				Computer computer = new Computer();
				computer.setId(id);
				computerDAO.delete(computer);
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
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Error();
			}
		}
	}

}
