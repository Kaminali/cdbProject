package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.cdb.model.bean.Computer;

public interface IComputerDAO {

	public abstract List<Computer> getList(Connection connection);

	public abstract List<Computer> getList(Connection connection, Long begin, Long nb);

	public abstract Computer getById(Connection connection, Long id);

	public abstract boolean insert(Connection connection, Computer computer) throws Exception;

	public abstract boolean update(Connection connection, Computer computer) throws Exception;

	public abstract boolean delete(Connection connection, Computer computer) throws Exception;

	public abstract int getNb(Connection connection);

	public abstract List<Computer> getByName(Connection connection, String name, long begin, long nb);

	public abstract List<Computer> getByName(Connection connection, String name);

	public abstract int getNb(Connection connection, String name);

	public abstract List<Long> getIdsByCompany(Connection connection, Long id);


}