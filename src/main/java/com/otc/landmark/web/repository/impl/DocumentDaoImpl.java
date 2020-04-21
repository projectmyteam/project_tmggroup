package com.otc.landmark.web.repository.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otc.landmark.web.domain.Document;
import com.otc.landmark.web.repository.DocumentDao;

@Service
@Transactional(rollbackOn = Exception.class)
public class DocumentDaoImpl implements DocumentDao{

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public List<Document> findAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Document", Document.class).getResultList();
	}

	@Override
	public Document findById(Long id) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "FROM Document WHERE id = :id";
		Document document = session.createQuery(queryString, Document.class).setParameter("id", id).setMaxResults(1).getSingleResult();
		return document;
	}

	@Override
	public void save(Document document) throws Exception {
		Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(document);
	}

	@Override
	public void delete(Document document) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		session.delete(document);
	}

}
