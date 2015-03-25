package com.excilys.cdb.model.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.bean.User;
 
 
@Repository
public class UserDAO implements IUserDAO {
 
	@Autowired
	private SessionFactory sessionFactory;
 
	public User findByUserName(String username) {
 
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.like("username", username));
		
		return (criteria.list().size() > 0) ? (User) criteria.list().get(0) : new User(username, "", false);
 
	}
 
}