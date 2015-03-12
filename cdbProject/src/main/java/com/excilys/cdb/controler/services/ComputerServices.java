/**
 * 
 */
package com.excilys.cdb.controler.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.bean.Computer;
import com.excilys.cdb.model.dao.IComputerDAO;

/**
 * @author excilys
 *
 */
@Service
public class ComputerServices implements IComputerServices {

	@Autowired 
	private IComputerDAO computerDAO;

	@Override
	public String testSpring() {
		return String.valueOf(computerDAO.getNb());
	}

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

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#getComputerById(long)
	 */
	@Override
	public Computer getComputerById(long id) {
		Computer computer = computerDAO.getById(id);
		return computer;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#insertComputer(com.excilys.cdb.model.bean.Computer)
	 */
	@Override
	public void insertComputer(Computer computer) {
		System.out.println(computer);
		System.out.println(computerDAO);
		computerDAO.insert(computer);
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#updateComputer(com.excilys.cdb.model.bean.Computer)
	 */
	@Override
	public void updateComputer(Computer computer) {
		System.out.println(computer);
		System.out.println(computerDAO);
		computerDAO.update(computer);
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#deleteComputer(java.lang.Long)
	 */
	@Override
	public void deleteComputer(Long id) throws Exception {
		Computer computer = new Computer();
		computer.setId(id);
		computerDAO.delete(computer);

	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#getNb()
	 */
	@Override
	public int getNb() {
		int temp = computerDAO.getNb();
		return temp;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#getByName(java.lang.String, long, long)
	 */
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

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#getNb(java.lang.String)
	 */
	@Override
	public int getNb(String name) {
		int temp = computerDAO.getNb(name);
		return temp;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.controler.services.IComputerServices#deleteComputer(java.util.ArrayList)
	 */

	@Transactional
	@Override
	public void deleteComputer(ArrayList<Long> computersId) {
		for(long id : computersId) {
			Computer computer = new Computer();
			computer.setId(id);
			computerDAO.delete(computer);
		}
	}

}
