package com.excilys.cdb.model.dao;

import java.util.List;

import com.excilys.cdb.model.bean.Computer;

public interface IComputerDAO {

	public abstract List<Computer> getList();

	public abstract List<Computer> getList(Long begin, Long nb);

	public abstract Computer getById(Long id);

	public abstract void insert(Computer computer);

	public abstract void update(Computer computer);

	public abstract void delete(Computer computer);

	public abstract int getNb();

	public abstract List<Computer> getByName(String name, Long begin, Long nb);

	public abstract List<Computer> getByName(String name);

	public abstract int getNb(String name);

	public List<Computer> getIdsByCompany(Long id);
}