package com.excilys.cdb.controller.services;

import java.util.List;

import com.excilys.cdb.model.bean.Computer;

public interface IComputerServices {

	public abstract List<Computer> getAllComputer(long begin, long nb);

	public abstract Computer getComputerById(long id);

	public abstract void insertComputer(Computer computer);

	public abstract void updateComputer(Computer computer);

	public abstract void deleteComputer(Long id) throws Exception;

	public abstract int getNb();

	public abstract List<Computer> getByName(String name, long begin, long nb);

	public abstract int getNb(String name);

	public abstract void deleteComputer(List<Long> computersId);

	public String testSpring();

}