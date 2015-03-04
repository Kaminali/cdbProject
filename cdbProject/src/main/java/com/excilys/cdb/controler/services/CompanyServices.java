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

/**
 * @author Nicolas Guibert
 *
 */
public class CompanyServices {
	
	public List<Company> getAllCompany() {
		CompanyDAO companyDAO = new CompanyDAO(ConnectionManager.instance.getConnection(), true);
		List<Company> companyList = companyDAO.getList();
		return companyList;
	}

	public void deleteCompany(Long idCompany) {
		Connection connection = ConnectionManager.instance.getConnection();
		CompanyDAO companyDAO = new CompanyDAO(connection, false);
		ComputerDAO computerDAO = new ComputerDAO(connection, false);
		try {
			connection.setAutoCommit(false);
			List<Long> ids = computerDAO.getIdsByCompany(idCompany);
			for(long id : ids) {
				Computer computer = new Computer();
				computer.setId(id);
				computerDAO.delete(computer);
			}
			Company company = new Company();
			company.setId(idCompany);
			companyDAO.delete(company);
			connection.commit();
		} catch(Exception e) {
			try {
				System.out.println(e.getMessage());
				e.printStackTrace();
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
