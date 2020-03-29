package com.otc.landmark.web.repository.impl;

import com.otc.landmark.web.constant.CommonConst;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.domain.News;
import com.otc.landmark.web.repository.EntryDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class EntryDaoImpl implements EntryDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    public List<Entry> findAll(){
    	Session session = sessionFactory.getCurrentSession();
    	return session.createNativeQuery("SELECT * FROM otc_entry", Entry.class).list();
    }
    
    public Entry findById(Long id) {
    	Session session = sessionFactory.getCurrentSession();
    	Entry entry = null;
    	entry = (Entry) session.createNativeQuery("SELECT * FROM otc_entry WHERE ID = ?").addEntity(Entry.class).setParameter(1, id).getSingleResult();
    	
    	return entry;
    }

	@Override
	public List<Entry> findEntryBySubCateId(Long subCategoryId) throws Exception {
    	Session session = sessionFactory.getCurrentSession();
    	return session.createNativeQuery("SELECT * FROM otc_entry WHERE SUB_CATEGORY_ID = ?", Entry.class)
				.setParameter(1,subCategoryId).getResultList();
	}
	
	@Override
	public List<Entry> findEntryBySubCateIdWithOffset(Long subCategoryId, int offset, int sizePage) {
		Session session = sessionFactory.getCurrentSession();
		return session.createNativeQuery("SELECT * FROM otc_entry WHERE SUB_CATEGORY_ID = ? "
				+ " ORDER BY CREATED_DATE DESC "
				+ " LIMIT ? OFFSET ?", Entry.class).setParameter(1, subCategoryId).setParameter(2, sizePage).setParameter(3, offset).list();
	}

	@Override
	public List<Entry> findEntryLimit(Long subcateid, Long identry) {
		Session session = sessionFactory.getCurrentSession();
		return session.createNativeQuery("SELECT * FROM otc_entry WHERE SUB_CATEGORY_ID = ? AND ID <> ? LIMIT 4", Entry.class)
				.setParameter(1,subcateid).setParameter(2, identry).getResultList();
	}

	@Override
	public List<Entry> findEntryByParentId(Long id) {
    	Session session = sessionFactory.getCurrentSession();
		StringBuilder stringBuilder = new StringBuilder("SELECT * FROM otc_entry WHERE CATEGORY_ID = ?");
    	List<Entry> results = session.createNativeQuery(stringBuilder.toString(), Entry.class).setParameter(1, id).getResultList();
		return results;
	}


	@Override
	public Entry findNewestEntry(Long subcategory) throws Exception {
    	Session session = sessionFactory.getCurrentSession();
		Entry entry = null;
		try {
			entry = session.createNativeQuery("SELECT * FROM otc_entry WHERE SUB_CATEGORY_ID = ? ORDER BY CREATED_DATE DESC LIMIT 1",
					Entry.class).setParameter(1, subcategory).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return entry;
	}
	
	@Override
	public Entry findNewestEntryByParentCategory(Long parentCategory) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		Entry entry = null;
		try {
			entry = session.createNativeQuery("SELECT * FROM otc_entry WHERE CATEGORY_ID = ? ORDER BY CREATED_DATE DESC LIMIT 1",
					Entry.class).setParameter(1, parentCategory).getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return entry;
	}
	
	@Override
	public Entry findNewestEntryByCategoryList(Long[] categoryList) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		StringBuilder sql = new StringBuilder("SELECT * FROM otc_entry WHERE SUB_CATEGORY_ID IN ");
		for(int i = 0; i < categoryList.length; i++) {
			if(i == 0) {
				sql.append(CommonConst.OPEN_PARENTHESIS);
			}
			sql.append(categoryList[i]);
			if(i <= (categoryList.length - 2)) {
				sql.append(CommonConst.COMMA);
			}else {
				sql.append(CommonConst.CLOSE_PARENTHESIS);
			}
		}
		sql.append(" ORDER BY CREATED_DATE DESC LIMIT 1");
		Entry entry = null;
		try {
			entry = session.createNativeQuery(sql.toString(), Entry.class).getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return entry;
	}

	@Override
    public void save(Entry entry) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entry);
    }

	@Override
	public void delete(Entry entry) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(entry);
	}

	@Override
	public int countBySubCategoryId(Long subCategoryId) {
		Session session = sessionFactory.getCurrentSession();
		return ((BigInteger) session.createNativeQuery("SELECT COUNT(*) FROM otc_entry WHERE SUB_CATEGORY_ID = ?").setParameter(1, subCategoryId).getSingleResult()).intValue();
	}

	@Override
	public Entry findEntryAndNewsById(Long id) throws Exception {
		Entry entry = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Object[]> data = session.createNativeQuery("SELECT DISTINCT e.*, n.* FROM otc_entry e LEFT JOIN otc_news n ON e.ID = n.ENTRY_ID WHERE e.ID = ?")
					.addEntity("e", Entry.class)
					.addJoin("n", "e.news")
					.setParameter(1, id)
					.list();
			entry = (Entry) data.get(0)[0];
			
			News news = entry.getNews();
			entry.setNews(news);
			
		}catch (Exception e) {
			//write log
			throw e;
		}
		return entry;
		
	}

	
	
}
