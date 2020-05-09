package com.otc.landmark.web.repository.impl;

import com.otc.landmark.web.Utils.Utility;
import com.otc.landmark.web.constant.CommonConst;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.domain.News;
import com.otc.landmark.web.dto.EntryDto;
import com.otc.landmark.web.dto.EntrySearchDto;
import com.otc.landmark.web.repository.EntryDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Service
@Transactional(rollbackOn = Exception.class)
public class EntryDaoImpl implements EntryDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    public List<Entry> findAll(){
    	Session session = sessionFactory.getCurrentSession();
    	return session.createNativeQuery("SELECT * FROM otc_entry", Entry.class).list();
    }

    public List<Entry> findNewest() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from Entry A where A.category.categoryCode = 'trai-phieu' and A.createdDate in (select max(B.createdDate) from Entry B " +
				"where B.category.categoryCode = 'trai-phieu')";
		Query query = session.createQuery(sql, Entry.class);
		return query.list();
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
	public List<Entry> findEntryByEntrySearchWithOffset(EntrySearchDto searchDto, int offset, int sizePage) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder queryString = new StringBuilder("from Entry entry ");
		List<String> ruleFields = new ArrayList<String>();
        List<List<Object>> ruleParams = new ArrayList<List<Object>>();
        
        buildRuleForEntrySearchQuery(searchDto, ruleFields, ruleParams);
        for(String ruleField : ruleFields) {
        	queryString.append(ruleField);
        }  
        queryString.append(" order by entry.createdDate desc ");
        Query query = session.createQuery(queryString.toString(), Entry.class);
        for(List<Object> ruleParam : ruleParams) {
        	query.setParameter((String) ruleParam.get(0), ruleParam.get(1));
        } 
        query.setFirstResult(offset);
        query.setMaxResults(sizePage);
        
		return query.list();
	}

	@Override
	public List<Entry> findEntryByParentId(Long id) {
    	Session session = sessionFactory.getCurrentSession();
		StringBuilder stringBuilder = new StringBuilder("SELECT * FROM otc_entry WHERE CATEGORY_ID = ?");
    	List<Entry> results = session.createNativeQuery(stringBuilder.toString(), Entry.class).setParameter(1, id).getResultList();
		return results;
	}

	@Override
	public List<Entry> findNewestEntries(Long subcategory, int limit) throws Exception {
    	Session session = sessionFactory.getCurrentSession();
    	List<Entry> entries = null;
		try {
			entries = session.createNativeQuery("SELECT * FROM otc_entry WHERE SUB_CATEGORY_ID = ? ORDER BY CREATED_DATE DESC LIMIT ?",
					Entry.class).setParameter(1, subcategory).setParameter(2, limit).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return entries;
	}


	@Override
	public List<Entry> findRelativeEntries(Long entryId, Long subcategory, int limit) throws Exception {
    	Session session = sessionFactory.getCurrentSession();
    	List<Entry> entries = null;
		try {
			entries = session.createNativeQuery("SELECT * FROM otc_entry WHERE SUB_CATEGORY_ID = ? AND ID <> ? ORDER BY CREATED_DATE DESC LIMIT ?",
					Entry.class).setParameter(1, subcategory).setParameter(2, entryId).setParameter(3, limit).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return entries;
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
	public int countByEntrySearchDto(EntrySearchDto searchDto) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder queryString = new StringBuilder("select count(*) from Entry entry ");
		List<String> ruleFields = new ArrayList<String>();
        List<List<Object>> ruleParams = new ArrayList<List<Object>>();
        
        buildRuleForEntrySearchQuery(searchDto, ruleFields, ruleParams);    
        for(String ruleField : ruleFields) {
        	queryString.append(ruleField);
        }  
        Query query = session.createQuery(queryString.toString());
        for(List<Object> ruleParam : ruleParams) {
        	query.setParameter((String) ruleParam.get(0), ruleParam.get(1));
        } 
        
		return ((Long) query.uniqueResult()).intValue();	
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

	
	private void buildRuleForEntrySearchQuery(EntrySearchDto searchDto, List<String> ruleFields, List<List<Object>> ruleParams) {
		if(searchDto.getSubCategoryId() != null) {
			ruleFields.add("  where entry.category.categoryId = :subCategoryId ");
			List<Object> ruleParam = new ArrayList<Object>();
			ruleParam.add("subCategoryId");
			ruleParam.add(searchDto.getSubCategoryId());
			ruleParams.add(ruleParam);
		}
		if(Utility.checkString(searchDto.getSubject())) {
			ruleFields.add(" and entry.subject like :subject ");
			List<Object> ruleParam = new ArrayList<Object>();
			ruleParam.add("subject");
			ruleParam.add("%" + searchDto.getSubject() + "%");
			ruleParams.add(ruleParam);
		}
	}
}
