/**
 * 
 */
package com.excilys.cdb.model.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.bean.Computer;
import com.excilys.cdb.model.mapper.MapComputer;

@Repository
public class ComputerDAO extends JdbcDaoSupport implements IComputerDAO {

	@Autowired
	private DataSource dataSource;
 
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	@Override
	public List<Computer> getList() {
		List<Computer> listC = new ArrayList<Computer>();
		String sql = "SELECT computer.id, computer.name, introduced, discontinued, company_id AS cid, company.name AS cname FROM computer "
				+" LEFT JOIN company "
				+" ON computer.company_id = company.id;";
		 
		listC = getJdbcTemplate().query(sql, new MapComputer());
		return listC;
	}

	@Override
	public List<Computer> getList(Long begin, Long nb) {

		List<Computer> listC = new ArrayList<Computer>();
		String sql = "SELECT computer.id, computer.name, introduced, discontinued, company_id AS cid, company.name AS cname FROM computer "
				+" LEFT JOIN company "
				+" ON computer.company_id = company.id LIMIT ? OFFSET ?;";
		 
		listC = getJdbcTemplate().query(sql, new Object[] { nb, begin }, new MapComputer());
		return listC;
	}

	@Override
	public Computer getById(Long id) {
		String sql = "SELECT computer.id, computer.name, introduced, discontinued, company_id AS cid, company.name AS cname FROM computer "
							+" LEFT JOIN company "
							+" ON computer.company_id = company.id "
							+" WHERE computer.id = ?;";
		
		Computer computer = getJdbcTemplate().queryForObject(sql, new Object[] { id }, new MapComputer());

		return computer;
	}

	@Override
	public void insert(Computer computer){
		String sql = "INSERT into computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?);";
		
		Long idc;
		if (computer.getCompany() != null) {
			if (computer.getCompany().getId() > 0) {
				idc = computer.getCompany().getId();
			} else {
				idc = -1l;
			}
		} else {
			idc = -1l;
		}
		
		getJdbcTemplate().update(sql, new Object[] { computer.getName(), 
				(computer.getIntroduced() != null) ? 
						Timestamp.valueOf(LocalDateTime.of(computer.getIntroduced(), LocalTime.of(0, 0))) 
						: null , 
				(computer.getDiscontinued() != null) ? 
						Timestamp.valueOf(LocalDateTime.of(computer.getDiscontinued(), LocalTime.of(0, 0)))
						: null, 
				(idc != -1) ? idc : null,
				});
	}

	@Override
	public void update(Computer computer) {
		System.out.println(computer);
		String sql = "UPDATE computer SET name = ? , introduced = ? , discontinued = ? ,company_id = ? WHERE id = ? ;";
		
		Long idc;
		if (computer.getCompany() != null) {
			if (computer.getCompany().getId() > 0) {
				idc = computer.getCompany().getId();
			} else {
				idc = -1l;
			}
		} else {
			idc = -1l;
		}
		
		getJdbcTemplate().update(sql, new Object[] { computer.getName(), 
				(computer.getIntroduced() != null) ? 
						Timestamp.valueOf(LocalDateTime.of(computer.getIntroduced(), LocalTime.of(0, 0))) 
						: null , 
				(computer.getDiscontinued() != null) ? 
						Timestamp.valueOf(LocalDateTime.of(computer.getDiscontinued(), LocalTime.of(0, 0)))
						: null, 
				(idc != -1) ? idc : null,
				computer.getId()
				});
	}

	@Override
	public void delete(Computer computer) {
		String sql = "DELETE FROM computer WHERE id = ? ;";
		
		getJdbcTemplate().update(sql, new Object[] { computer.getId() }); 
	}

	@Override
	public List<Computer> getByName(String name, long begin, long nb) {

		List<Computer> listC = new ArrayList<Computer>();
		
		String sql = "SELECT computer.id, computer.name, introduced, discontinued, company_id AS cid, company.name AS cname FROM computer "
							+ " LEFT JOIN company "
							+ " ON computer.company_id = company.id "
							+ " WHERE computer.name LIKE ? OR company.name LIKE ? "
							+ " LIMIT ? OFFSET ?;";
		
		listC = getJdbcTemplate().query(sql, new Object[] { "%" + name + "%", "%" + name + "%", nb, begin }, new MapComputer());
		return listC;
		
	}

	@Override
	public List<Computer> getByName(String name) {

		List<Computer> listC = new ArrayList<Computer>();
		
		String sql = "SELECT computer.id, computer.name, introduced, discontinued, company_id AS cid, company.name AS cname FROM computer "
							+ " LEFT JOIN company "
							+ " ON computer.company_id = company.id "
							+ " WHERE computer.name LIKE ? OR company.name LIKE ? ;";
		
		listC = getJdbcTemplate().query(sql, new Object[] { "%" + name + "%", "%" + name + "%" }, new MapComputer());
		return listC;
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getNb(String name)  {
		String sql = "SELECT COUNT(computer.id) FROM computer "
				+" LEFT JOIN company "
				+" ON computer.company_id = company.id "
				+ " WHERE computer.name LIKE ? OR company.name LIKE ? ;";
	 
		int count = getJdbcTemplate().queryForInt(sql, new Object[] { name, name });
		return count;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Long> getIdsByCompany(Long id) {
		ArrayList<Long> ids = new ArrayList<Long>();
		String sql = "SELECT computer.id, computer.name, introduced, discontinued, company_id AS cid, company.name AS cname FROM computer "
							+" LEFT JOIN company "
							+" ON computer.company_id = company.id "
							+ "WHERE company.id = ?;";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, new Object[] { id });
		for (Map row : rows) {
			ids.add((Long)(row.get("computer.id")));
		}

		return ids;
	}

	@Override
	public String testSpring() {
		System.out.println("réussite");
		return " canard ";
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getNb() {
		String sql = "SELECT COUNT(id) FROM computer;";
	 
		int count = getJdbcTemplate().queryForInt(sql);
		return count;
	}
	
}
