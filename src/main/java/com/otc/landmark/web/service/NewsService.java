package com.otc.landmark.web.service;

import javax.servlet.http.HttpServletRequest;

import com.otc.landmark.web.dto.NewsDto;

public interface NewsService {
	public NewsDto getById(Long id) throws Exception;
	
	public void saveNews(HttpServletRequest req, NewsDto newsDto) throws Exception;
	
	public void updateNews(HttpServletRequest req, NewsDto newsDto) throws Exception;
	
	public void deleteNews(Long id) throws Exception;
}
