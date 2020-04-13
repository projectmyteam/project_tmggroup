package com.otc.landmark.web.repository.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.otc.landmark.web.domain.User;
import com.otc.landmark.web.repository.UserDao;

@Repository
@Transactional(rollbackOn = Exception.class)
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public User findByUserName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "from User user left join fetch user.roles where user.userName = :userName";
		User user = session.createQuery(queryString, User.class).setString("userName", userName).uniqueResult();
		return user;
	}

	public User findById(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		String query = "from User us where us.id = :userId";
		User user = session.createQuery(query, User.class).setParameter("userId", userId).uniqueResult();
		return user;
	}



}
