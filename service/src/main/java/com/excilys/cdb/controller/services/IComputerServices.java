package com.excilys.cdb.controller.services;

import java.util.List;

import com.excilys.cdb.model.bean.Computer;

public interface IComputerServices {

	/**
	 * 
	 * @param begin
	 * @param nb
	 * @return a list of computer, with a size limited with the param nb, begin at the index declare with the param begin
	 * you can send -1 for begin and nb for a complete list
	 */
	public abstract List<Computer> getAllComputer(long begin, long nb);

	/**
	 * 
	 * @param id
	 * @return the computer with the param id, or null if it not exist.
	 */
	public abstract Computer getComputerById(long id);

	/**
	 * insert a computer
	 * @param computer
	 */
	public abstract void insertComputer(Computer computer);

	/**
	 * update a computer
	 * @param computer
	 */
	public abstract void updateComputer(Computer computer);

	/**
	 * delete a computer
	 * @param id : a computer id
	 */
	public abstract void deleteComputer(Long id) throws Exception;

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
	 * you can send -1 for begin and nb for a complete list with the restriction
	 */
	public abstract List<Computer> getByName(String name, long begin, long nb);

	/**
	 * 
	 * @param name
	 * @return the number of computer, with the name restriction
	 */
	public abstract int getNb(String name);

	/**
	 * delete a list of computer
	 * @param computersId : the list of the computersId
	 */
	public abstract void deleteComputer(List<Long> computersId);

}