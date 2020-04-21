package com.otc.landmark.web.repository;

import java.util.List;

import com.otc.landmark.web.domain.Document;

public interface DocumentDao {

	// Find all category 
	public List<Document> findAll();

	//Find category by categoryid
	public Document findById(Long id) throws Exception;

	// Insert or update a category     
	public void save(Document document)  throws Exception;
	
	public void delete(Document document)  throws Exception;
}
