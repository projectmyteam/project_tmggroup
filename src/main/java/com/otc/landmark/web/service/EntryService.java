package com.otc.landmark.web.service;

import com.otc.landmark.web.dto.EntryDto;
import com.otc.landmark.web.dto.PageWrapperDto;
import com.otc.landmark.web.exception.ConstraintException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EntryService {
	
	public void saveEntry(HttpServletRequest req, EntryDto entryDto) throws Exception;
	
	public EntryDto getById(Long id) throws Exception;
	
	public void deleteEntry(Long id, boolean forceDel)  throws ConstraintException, Exception;

	public void updateEntry(HttpServletRequest req, EntryDto entryDto) throws Exception;
	
	public List<EntryDto> getEntryBySubCategoryId(Long subcategoryId) throws Exception;
	
	public List<EntryDto> getEntryBySubCategoryIdWithOffSet(Long subcategoryId, int offset, int sizePage) throws Exception;
	
	public PageWrapperDto<EntryDto> search(int page, int pageSize, EntryDto entryDto) throws Exception;
	
}
