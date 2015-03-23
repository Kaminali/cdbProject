/**
 * 
 */
package com.excilys.cdb.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.bean.Computer;

@Repository
public class ComputerDAO implements IComputerDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> getList() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> getList(Long begin, Long nb) {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
		criteria.setFirstResult(begin.intValue());
		criteria.setMaxResults(nb.intValue());
		return criteria.list();
	}

	@Override
	public Computer getById(Long id) {
		
		return (Computer) sessionFactory.getCurrentSession().get(Computer.class, id);
		
	}

	@Override
	public void insert(Computer computer){
		/*Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.save(computer);
		tx.commit();*/
		sessionFactory.getCurrentSession().save(computer);
	}

	@Override
	public void update(Computer computer) {
		sessionFactory.getCurrentSession().update(computer);
	}

	@Override
	public void delete(Computer computer) {
		sessionFactory.getCurrentSession().delete(computer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> getByName(String name, Long begin, Long nb) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
		criteria.add(Restrictions.like("name", "%"+name+"%"));
		criteria.setFirstResult(begin.intValue());
		criteria.setMaxResults(nb.intValue());
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> getByName(String name) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
		criteria.add(Restrictions.like("name", "%"+name+"%"));
		return criteria.list();
		
	}

	@Override
	public int getNb(String name)  {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
		criteria.add(Restrictions.like("name", "%"+name+"%"));
		return (int) ((long) criteria.setProjection(Projections.rowCount()).uniqueResult());
	}

	@Override
	public String testSpring() {
		System.out.println("réussite");
		return " canard ";
	}

	@Override
	public int getNb() {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
		return (int) ((long) criteria.setProjection(Projections.rowCount()).uniqueResult());
	}

}
