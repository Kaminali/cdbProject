package com.excilys.cdb.model.dao;

import java.util.List;

import com.excilys.cdb.model.bean.Computer;

public interface IComputerDAO {

	/**
	 * 
	 * @return a list with all computer
	 */
	public abstract List<Computer> getList();

	/**
	 * 
	 * @param begin
	 * @param nb
	 * @return a list of computer, with a size limited with the param nb, begin at the index declare with the param begin
	 */
	public abstract List<Computer> getList(Long begin, Long nb);

	/**
	 * 
	 * @param id
	 * @return the computer with the param id, or null if it not exist.
	 */
	public abstract Computer getById(Long id);

	/**
	 * insert a computer
	 * @param computer
	 */
	public abstract void insert(Computer computer);

	/**
	 * update a computer
	 * @param computer
	 */
	public abstract void update(Computer computer);

	/**
	 * delete a computer
	 * @param computer
	 */
	public abstract void delete(Computer computer);

	/**
	 * 
	 * @return the number of computer
	 */
	public abstract int getNb();

	/**
	 * 
	 * @param name
	 * @param begin
	 * @param nb
	 * @return a list of computer, with the name restriction, a size limited with the param nb and begin at the index declare with the param begin
	 */
	public abstract List<Computer> getByName(String name, Long begin, Long nb);

	/**
	 * 
	 * @param name
	 * @return a list with all computer, with the name restriction
	 */
	public abstract List<Computer> getByName(String name);

	/**
	 * 
	 * @param name
	 * @return the number of computer, with the name restriction
	 */
	public abstract int getNb(String name);

	/**
	 * 
	 * @param id : a company id
	 * @return all computer linked to this company
	 */
	public List<Computer> getIdsByCompany(Long id);
}