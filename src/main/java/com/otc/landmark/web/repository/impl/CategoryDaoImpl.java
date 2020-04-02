package com.otc.landmark.web.repository.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otc.landmark.web.domain.Category;
import com.otc.landmark.web.repository.CategoryDao;

@Service
@Transactional(rollbackOn = Exception.class)
public class CategoryDaoImpl implements CategoryDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> findAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createNativeQuery("SELECT * FROM otc_category", Category.class).list();
	}

	@Override
	public Category findById(Long id) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		Category category = session.createNativeQuery("SELECT * FROM otc_category WHERE CATEGORY_ID = ?", Category.class)
				.setParameter(1, id).getSingleResult();
		return category;
	}

	@Override
	public List<Category> findLevel1Category() throws Exception{
		Session session = sessionFactory.getCurrentSession();
		return session.createNativeQuery("SELECT * FROM otc_category WHERE PARENT_CATEGORY_ID IS NULL", Category.class).list();
	}

	@Override
	public List<Category> findSubCategory(Long parentCategoryId) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		return session.createNativeQuery("SELECT * FROM otc_category WHERE PARENT_CATEGORY_ID = ?", Category.class)
					  .setParameter(1, parentCategoryId)
					  .list();
	}

	@Override
	public void save(Category category) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(category);
	}

	

}
