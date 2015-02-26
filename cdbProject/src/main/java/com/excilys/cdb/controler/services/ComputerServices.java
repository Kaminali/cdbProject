/**
 * 
 */
package com.excilys.cdb.controler.services;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.excilys.cdb.controler.validate.CheckValues;
import com.excilys.cdb.model.bean.Company;
import com.excilys.cdb.model.bean.Computer;
import com.excilys.cdb.model.dao.CompanyDAO;
import com.excilys.cdb.model.dao.ComputerDAO;
import com.excilys.cdb.model.dao.ConnectionManager;

/**
 * @author excilys
 *
 */
public class ComputerServices {

	private ComputerDAO computerDAO;
	private CompanyDAO companyDAO;

	public ComputerServices() {
		computerDAO = new ComputerDAO(ConnectionManager.getInstance());
		companyDAO = new CompanyDAO(ConnectionManager.getInstance());
	}

	public ArrayList<Computer> getAllComputeur(long begin, long nb) {
		ArrayList<Computer> computerList;
		if (begin == -1 || nb == -1) {
			computerList = computerDAO.getList();
		} else {
			computerList = computerDAO.getList(begin, nb);
		}

		for (Computer computer : computerList) {
			computer.setCompany(companyDAO.getById(computer.getCompany()
					.getId()));
		}
		return computerList;
	}

	public Computer getComputeurById(long id) {
		Computer computer = computerDAO.getById(id);
		computer.setCompany(companyDAO.getById(id));
		return computer;
	}

	public Computer formatComputer(String name, String introduced,
			String discontinued, Long idCompany) throws Exception {

		Computer computer = new Computer();

		CheckValues.checkName(name);
		computer.setName(name);

		Timestamp introducedTS;
		try {
			introducedTS = CheckValues.stringToTimestamp(introduced);
		} catch (Exception e) {
			introducedTS = null;
		}

		computer.setIntroduced(introducedTS);

		Timestamp discontinuedTS;
		try {
			discontinuedTS = CheckValues.stringToTimestamp(discontinued);
		} catch (Exception e) {
			discontinuedTS = null;
		}

		computer.setDiscontinued(discontinuedTS);
		Company company = null;
		try {
			company = companyDAO.getById(idCompany);
			CheckValues.checkCompany(company.getId());
		} catch (Exception e) {
			company = null;
		}

		computer.setCompany(company);

		return computer;
	}

	public void insertComputer(String name, String introduced,
			String discontinued, Long idCompany) throws Exception {

		Computer computer = formatComputer(name, introduced, discontinued,
				idCompany);
		computerDAO.insert(computer);

	}

	public void updateComputer(String name, String introduced,
			String discontinued, Long idCompany, Long id) throws Exception {

		CheckValues.checkComputer(id);
		Computer computer = formatComputer(name, introduced, discontinued,
				idCompany);
		computer.setId(id);
		computerDAO.update(computer);

	}

	public void deleteComputer(Long id) throws Exception {

		CheckValues.checkComputer(id);
		Computer computer = new Computer();
		computer.setId(id);
		computerDAO.delete(computer);

	}

}
