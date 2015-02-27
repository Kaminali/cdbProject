/**
 * 
 */
package com.excilys.cdb.model.dao;

import java.util.ArrayList;

import com.excilys.cdb.model.bean.Computer;
import com.excilys.cdb.model.mapper.MapComputer;

/**
 * @author excilys
 *
 */
public class ComputerDAO extends BaseDAO {

	public ComputerDAO(ConnectionManager connectionManager) {
		super(connectionManager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Computer> getList() {

		ArrayList<Computer> listC = new ArrayList<Computer>();

		try {
			initStatement();
			statement = connection
					.prepareStatement("SELECT id, name, introduced, discontinued, company_id FROM computer;");
			result = statement.executeQuery();
			while (result.next()) {

				Computer computer = MapComputer.mapping(result);
				listC.add(computer);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			closeStatement();
		}

		return listC;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Computer> getList(Long begin, Long nb) {

		ArrayList<Computer> listC = new ArrayList<Computer>();

		try {
			initStatement();
			statement = connection
					.prepareStatement("SELECT id, name, introduced, discontinued, company_id FROM computer LIMIT ? OFFSET ?;");
			statement.setLong(1, nb);
			statement.setLong(2, begin);
			result = statement.executeQuery();
			while (result.next()) {

				Computer computer = MapComputer.mapping(result);
				listC.add(computer);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			closeStatement();
		}

		return listC;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Computer getById(Long id) {
		Computer computer = new Computer();
		computer.setId(-1l);
		try {
			initStatement();
			statement = connection
					.prepareStatement("SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?;");
			statement.setLong(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				computer = MapComputer.mapping(result);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			closeStatement();
		}

		return computer;
		// TODO Auto-generated method stub
	}

	@Override
	public boolean insert(Object computerO) {
		try {
			Computer computer = (Computer) computerO;
			initStatement();
			statement = connection
					.prepareStatement("INSERT into computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?);");

			statement.setString(1, computer.getName());
			statement.setTimestamp(2, computer.getIntroduced());
			statement.setTimestamp(3, computer.getDiscontinued());
			if (computer.getCompany() != null) {
				statement.setLong(4, computer.getCompany().getId());
			} else {
				statement.setNull(4, -1);
			}

			statement.executeUpdate();

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			closeStatement();
		}
		return true;
	}

	@Override
	public boolean update(Object computerO) {
		try {
			Computer computer = (Computer) computerO;
			initStatement();
			statement = connection
					.prepareStatement("UPDATE computer SET name = ? , introduced = ? , discontinued = ? ,company_id = ? WHERE id = ? ;");

			statement.setString(1, computer.getName());
			statement.setTimestamp(2, computer.getIntroduced());
			statement.setTimestamp(3, computer.getDiscontinued());
			if (computer.getCompany() != null) {
				statement.setLong(4, computer.getCompany().getId());
			} else {
				statement.setNull(4, -1);
			}
			statement.setLong(5, computer.getId());

			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeStatement();
		}
		return true;
	}

	@Override
	public boolean delete(Object computerO) {
		try {
			Computer computer = (Computer) computerO;
			initStatement();
			statement = connection
					.prepareStatement("DELETE FROM computer WHERE id = ? ;");

			statement.setLong(1, computer.getId());

			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			closeStatement();
		}
		return true;
	}

	public int getNb() {
		int test = 0;
		try {
			initStatement();
			statement = connection
					.prepareStatement("SELECT COUNT(id) FROM computer;");

			result = statement.executeQuery();
			while (result.next()) {
				test = result.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeStatement();
		}
		
		return test;
		
	}

}
