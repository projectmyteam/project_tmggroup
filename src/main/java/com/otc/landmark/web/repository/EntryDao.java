package com.otc.landmark.web.repository;

import java.util.List;

import com.otc.landmark.web.domain.Entry;

public interface EntryDao {
	
	List<Entry> findAll();
	
	Entry findById(Long id);
	
	List<Entry> findEntryBySubCateId(Long idsubcate) throws Exception;

	List<Entry> findEntryByParentId(Long id);

	List<Entry> findEntryLimit(Long subcateid, Long identry);

	Entry findNewestEntry(Long subCategory) throws Exception;
	
	Entry findNewestEntryByParentCategory(Long parentCategory) throws Exception;
	
	Entry findNewestEntryByCategoryList(Long[] categoryList);

    void save(Entry entry) throws Exception;
    
    void delete(Entry entry);
    
    int countBySubCategoryId(Long subCategoryId);
    
    List<Entry> findEntryBySubCateIdWithOffset(Long subCategoryId, int offset, int sizePage);

}
