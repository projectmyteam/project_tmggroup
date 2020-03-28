package com.otc.landmark.web.repository;

import java.util.List;

import com.otc.landmark.web.domain.Category;


public interface CategoryDao {
	// Find all category 
	public List<Category> findAll();

	//Find category by categoryid
	public Category findById(Long id) throws Exception;

	//Find parent category
	public List<Category> findLevel1Category()  throws Exception;

	//Find parent lv 2
	public List<Category> findLevel2Category(Long parentCategoryId)  throws Exception;

	//Find sub category
	public List<Category> findSubCategory(Long parentCategoryId)  throws Exception;
	
	// Insert or update a category     
	public void save(Category category)  throws Exception;

}
