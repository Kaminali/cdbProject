/**
 * 
 */
package com.excilys.cdb.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.bean.Computer;
import com.excilys.cdb.model.mapper.MapComputer;

/**
 * @author excilys
 *
 */
public enum ComputerDAO implements IComputerDAO {

	instance;
	
	@Override
	public List<Computer> getList(Connection connection) {

		List<Computer> listC = new ArrayList<Computer>();
		ResultSet result = null;
		PreparedStatement statement = null;

		try {
			statement = connection
					.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name AS cname FROM computer "
							+" LEFT JOIN company "
							+" ON computer.company_id = company.id ;");
			result = statement.executeQuery();
			while (result.next()) {

				Computer computer = MapComputer.mapping(result);
				listC.add(computer);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				result.close();
				statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		}

		return listC;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.model.dao.IComputerDAO#getList(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Computer> getList(Connection connection, Long begin, Long nb) {

		List<Computer> listC = new ArrayList<Computer>();
		ResultSet result = null;
		PreparedStatement statement = null;

		try {
			statement = connection
					.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id AS cid, company.name AS cname FROM computer "
							+" LEFT JOIN company "
							+" ON computer.company_id = company.id "
							+ " LIMIT ? OFFSET ?;");
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
			try {
				result.close();
				statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		}

		return listC;
	}
	
	/* (non-Javadoc)
	 * @see com.excilys.cdb.model.dao.IComputerDAO#getById(java.lang.Long)
	 */
	@Override
	public Computer getById(Connection connection, Long id) {
		Computer computer = new Computer();
		computer.setId(-1l);
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			statement = connection
					.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id AS cid, company.name AS cname FROM computer "
							+" LEFT JOIN company "
							+" ON computer.company_id = company.id "
							+ "WHERE computer.id = ?;");
			statement.setLong(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				computer = MapComputer.mapping(result);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				result.close();
				statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		}

		return computer;
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.model.dao.IComputerDAO#insert(com.excilys.cdb.model.bean.Computer)
	 */
	@Override
	public boolean insert(Connection connection, Computer computer) throws Exception {
		PreparedStatement statement = null;
		try {
			statement = connection
					.prepareStatement("INSERT into computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?);");

			statement.setString(1, computer.getName());
			statement.setTimestamp(2, (computer.getIntroduced() != null) ? 
					Timestamp.valueOf(LocalDateTime.of(computer.getIntroduced(), LocalTime.of(0, 0))) 
					: null);
			statement.setTimestamp(3, (computer.getDiscontinued() != null) ? 
					Timestamp.valueOf(LocalDateTime.of(computer.getDiscontinued(), LocalTime.of(0, 0)))
					: null);
			if (computer.getCompany() != null) {
				if (computer.getCompany().getId() > 0) {
					statement.setLong(4, computer.getCompany().getId());
				} else {
					statement.setNull(4, -1);
				}
			} else {
				statement.setNull(4, -1);
			}

			statement.executeUpdate();
		} catch (Exception e) {
				throw new Exception(e.getMessage());
		} finally {
			try {
				statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.model.dao.IComputerDAO#update(com.excilys.cdb.model.bean.Computer)
	 */
	@Override
	public boolean update(Connection connection, Computer computer) throws Exception {
		PreparedStatement statement = null;
		try {
			statement = connection
					.prepareStatement("UPDATE computer SET name = ? , introduced = ? , discontinued = ? ,company_id = ? WHERE id = ? ;");

			statement.setString(1, computer.getName());
			statement.setTimestamp(2,  (computer.getIntroduced() != null) ? 
					Timestamp.valueOf(LocalDateTime.of(computer.getIntroduced(), LocalTime.of(0, 0)))
					: null);
			statement.setTimestamp(3,   (computer.getDiscontinued() != null) ? 
					Timestamp.valueOf(LocalDateTime.of(computer.getDiscontinued(), LocalTime.of(0, 0)))
					: null);
			if (computer.getCompany() != null) {
				if (computer.getCompany().getId() > 0) {
					statement.setLong(4, computer.getCompany().getId());
				} else {
					statement.setNull(4, -1);
				}
			} else {
				statement.setNull(4, -1);
			}
			statement.setLong(5, computer.getId());

			statement.executeUpdate();

		} catch (Exception e) {
				throw new Exception(e.getMessage());
		} finally {
			try {
				statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.model.dao.IComputerDAO#delete(com.excilys.cdb.model.bean.Computer)
	 */
	@Override
	public boolean delete(Connection connection, Computer computer) throws Exception {
		PreparedStatement statement = null;
		try {
			statement = connection
					.prepareStatement("DELETE FROM computer WHERE id = ? ;");

			statement.setLong(1, computer.getId());

			statement.executeUpdate();

		} catch (Exception e) {
				throw new Exception(e.getMessage());
		} finally {
			try {
				statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.model.dao.IComputerDAO#getNb()
	 */
	@Override
	public int getNb(Connection connection) {
		ResultSet result = null;
		PreparedStatement statement = null;
		int test = 0;
		try {
			statement = connection
					.prepareStatement("SELECT COUNT(id) FROM computer;");

			result = statement.executeQuery();
			while (result.next()) {
				test = result.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		}

		return test;

	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.model.dao.IComputerDAO#getByName(java.lang.String, long, long)
	 */
	@Override
	public List<Computer> getByName(Connection connection, String name, long begin, long nb) {

		List<Computer> listC = new ArrayList<Computer>();
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			statement = connection
					.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id AS cid, company.name AS cname FROM computer "
							+ " LEFT JOIN company "
							+ " ON computer.company_id = company.id "
							+ " WHERE computer.name LIKE ? OR company.name LIKE ? "
							+ " LIMIT ? OFFSET ?;");
			statement.setLong(3, nb);
			statement.setLong(4, begin);
			statement.setString(1, "%" + name + "%");
			statement.setString(2, "%" + name + "%");
			result = statement.executeQuery();
			while (result.next()) {

				Computer computer = MapComputer.mapping(result);
				listC.add(computer);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				result.close();
				statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		}

		return listC;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.model.dao.IComputerDAO#getByName(java.lang.String)
	 */
	@Override
	public List<Computer> getByName(Connection connection, String name) {

		List<Computer> listC = new ArrayList<Computer>();
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			statement = connection
					.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id AS cid, company.name AS cname FROM computer "
							+" LEFT JOIN company "
							+" ON computer.company_id = company.id "
							+ " WHERE computer.name LIKE ? OR company.name LIKE ? ;");
			statement.setString(1, "%" + name + "%");
			statement.setString(2, "%" + name + "%");
			result = statement.executeQuery();
			while (result.next()) {

				Computer computer = MapComputer.mapping(result);
				listC.add(computer);
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				result.close();
				statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		}

		return listC;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.model.dao.IComputerDAO#getNb(java.lang.String)
	 */
	@Override
	public int getNb(Connection connection, String name)  {
		int test = 0;
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			statement = connection
					.prepareStatement("SELECT COUNT(computer.id) FROM computer "
							+" LEFT JOIN company "
							+" ON computer.company_id = company.id "
							+ " WHERE computer.name LIKE ? OR company.name LIKE ? ;");
			statement.setString(1, "%" + name + "%");
			statement.setString(2, "%" + name + "%");
			result = statement.executeQuery();
			while (result.next()) {
				test = result.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
				statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		}

		return test;

	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.model.dao.IComputerDAO#getIdsByCompany(java.lang.Long)
	 */
	@Override
	public List<Long> getIdsByCompany(Connection connection, Long id) {
		ArrayList<Long> ids = new ArrayList<Long>();
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			statement = connection
					.prepareStatement("SELECT computer.id, computer.name, introduced, discontinued, company_id AS cid, company.name AS cname FROM computer "
							+" LEFT JOIN company "
							+" ON computer.company_id = company.id "
							+ "WHERE company.id = ?;");
			statement.setLong(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				ids.add(result.getLong(1));
			}

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				result.close();
				statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		}

		return ids;
		// TODO Auto-generated method stub
	}
	
}
