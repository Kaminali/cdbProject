/**
 * 
 */
package com.excilys.cdb.controler.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.controler.connection.ConnectionManager;
import com.excilys.cdb.model.bean.Company;
import com.excilys.cdb.model.bean.Computer;
import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.dao.ICompanyDAO;
import com.excilys.cdb.model.dao.IComputerDAO;

/**
 * @author Nicolas Guibert
 *
 */
public class CompanyServices extends ManageTransaction implements ICompanyServices {
	
	Connection connection;
	ICompanyDAO companyDAO;
	
	private void openConnection() {
		connection = ConnectionManager.instance.getConnection();
		companyDAO = CompanyDAO.instance;
	}
	
	private void closeConnection() {
		try {
			connection.setAutoCommit(true);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.ICompanyServices#getAllCompany()
	 */
	@Override
	public List<Company> getAllCompany() {
		openConnection();
		List<Company> companyList = companyDAO.getList(connection);
		closeConnection();
		return companyList;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.ICompanyServices#deleteCompany(java.lang.Long)
	 */
	@Override
	public void deleteCompany(Long idCompany) {
		openConnection();
		IComputerDAO computerDAO = ComputerDAO.instance;
		try {
			connection.setAutoCommit(false);
			List<Long> ids = computerDAO.getIdsByCompany(connection, idCompany);
			for(long id : ids) {
				Computer computer = new Computer();
				computer.setId(id);
				computerDAO.delete(connection, computer);
			}
			Company company = new Company();
			company.setId(idCompany);
			companyDAO.delete(connection, company);
			connection.commit();
		} catch(Exception e) {
			try {
				e.printStackTrace();
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
