package com.otc.landmark.web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.otc.landmark.web.dto.DocumentDto;
import com.otc.landmark.web.exception.ConstraintException;

public interface DocumentService {

	// Find all category 
	public List<DocumentDto> findAll(int linkFile);

	//Find category by categoryid
	public DocumentDto findById(Long id, int linkFile) throws Exception;

	// Insert or update a category     
	public void saveDocument(HttpServletRequest req, DocumentDto documentDto)  throws Exception;
	
	public DocumentDto updateDocument(HttpServletRequest req, DocumentDto documentDto)  throws Exception;
	
    public void deleteDocument(HttpServletRequest req, Long id, boolean forceDel)  throws ConstraintException, Exception;
}
