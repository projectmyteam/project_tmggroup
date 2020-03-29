package com.otc.landmark.web.repository;

import com.otc.landmark.web.domain.Entry;

import java.util.List;

public interface EntryDao {
	
	List<Entry> findAll();
	
	Entry findById(Long id);
	
	Entry findEntryAndNewsById(Long id) throws Exception;
	
	List<Entry> findEntryBySubCateId(Long idsubcate) throws Exception;

	List<Entry> findEntryByParentId(Long id);

	List<Entry> findEntryLimit(Long subcateid, Long identry);

	Entry findNewestEntry(Long subCategory) throws Exception;
	
	Entry findNewestEntryByParentCategory(Long parentCategory) throws Exception;
	
	Entry findNewestEntryByCategoryList(Long[] categoryList) throws Exception;

    void save(Entry entry) throws Exception;
    
    void delete(Entry entry);
    
    int countBySubCategoryId(Long subCategoryId);
    
    List<Entry> findEntryBySubCateIdWithOffset(Long subCategoryId, int offset, int sizePage);

}
