/**
 * 
 */
package com.excilys.cdb.controler.services;

import java.util.List;

import com.excilys.cdb.controler.dto.ComputerDTO;
import com.excilys.cdb.controler.dtoMapper.MapComputerDTO;
import com.excilys.cdb.controler.validate.CheckValues;
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

	public List<Computer> getAllComputer(long begin, long nb) {
		List<Computer> computerList;
		if (begin == -1 || nb == -1) {
			computerList = computerDAO.getList();
		} else {
			computerList = computerDAO.getList(begin, nb);
		}

		return computerList;
	}

	public Computer getComputerById(long id) {
		Computer computer = computerDAO.getById(id);
		computer.setCompany(companyDAO.getById(id));
		return computer;
	}

	public void insertComputer(ComputerDTO computerDto) throws Exception {

		computerDAO.insert(MapComputerDTO.DtoToModel(computerDto));

	}

	public void updateComputer(ComputerDTO computerDto) throws Exception {

		Computer computer = MapComputerDTO.DtoToModel(computerDto);
		computerDAO.update(computer);

	}

	public void deleteComputer(Long id) throws Exception {

		CheckValues.checkComputer(id);
		Computer computer = new Computer();
		computer.setId(id);
		computerDAO.delete(computer);

	}

	public int getNb() {
		return computerDAO.getNb();
	}

}
