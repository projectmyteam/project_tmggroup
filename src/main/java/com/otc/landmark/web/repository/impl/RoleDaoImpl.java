package com.otc.landmark.web.repository.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.otc.landmark.web.domain.Role;
import com.otc.landmark.web.domain.User;
import com.otc.landmark.web.repository.RoleDao;

@Repository
@Transactional(rollbackOn = Exception.class)
public class RoleDaoImpl implements RoleDao{

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public Role findById(Long roleId) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "FROM Role WHERE roleId = :roleId";
		Role role = session.createQuery(queryString, Role.class).setParameter("roleId", roleId).setMaxResults(1).getSingleResult();
//    	Role role = null;
//    	role = (Role) session.createNativeQuery("SELECT * FROM otc_role WHERE ROLE_ID = ?").addEntity(Role.class).setParameter(1, roleId).getSingleResult();
    	
    	return role;
	}

}
