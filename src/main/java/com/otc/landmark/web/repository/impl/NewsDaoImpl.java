package com.otc.landmark.web.repository.impl;

import com.otc.landmark.web.domain.News;
import com.otc.landmark.web.repository.NewsDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class NewsDaoImpl implements NewsDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<News> findAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createNativeQuery("SELECT * FROM otc_news", News.class).list();
	}
	
	@Override
	public News findById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (News) session.createNativeQuery("SELECT * FROM otc_news WHERE ID = ?").addEntity(News.class).setParameter(1, id).getSingleResult();
	}

	@Override
	public void save(News news) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(news);
	}

	@Override
	public void delete(News news) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		session.delete(news);
	}

}
