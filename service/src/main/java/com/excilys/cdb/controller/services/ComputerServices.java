/**
 * 
 */
package com.excilys.cdb.controller.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.bean.Computer;
import com.excilys.cdb.model.dao.IComputerDAO;

@Service
@Transactional
public class ComputerServices implements IComputerServices {

	@Autowired 
	private IComputerDAO computerDAO;

	@Override
	public List<Computer> getAllComputer(long begin, long nb) {
		List<Computer> computerList;
		if (begin == -1 || nb == -1) {
			computerList = computerDAO.getList();
		} else {
			computerList = computerDAO.getList(begin, nb);
		}
		return computerList;
	}

	@Override
	public Computer getComputerById(long id) {
		Computer computer = computerDAO.getById(id);
		return computer;
	}

	@Override
	public void insertComputer(Computer computer) {
		computerDAO.insert(computer);
	}

	@Override
	public void updateComputer(Computer computer) {
		computerDAO.update(computer);
	}

	@Override
	public void deleteComputer(Long id) throws Exception {
		Computer computer = new Computer();
		computer.setId(id);
		computerDAO.delete(computer);

	}

	@Override
	public int getNb() {
		int temp = computerDAO.getNb();
		return temp;
	}

	@Override
	public List<Computer> getByName(String name, long begin, long nb) {
		List<Computer> computerList;
		if (begin == -1 || nb == -1) {
			computerList = computerDAO.getByName(name);
		} else {
			computerList = computerDAO.getByName(name, begin, nb);
		}
		return computerList;
	}

	@Override
	public int getNb(String name) {
		int temp = computerDAO.getNb(name);
		return temp;
	}

	@Override
	public void deleteComputer(List<Long> computersId) {
		for(long id : computersId) {
			Computer computer = new Computer();
			computer.setId(id);
			computerDAO.delete(computer);
		}
	}

}
