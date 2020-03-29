package com.otc.landmark.web.repository;

import com.otc.landmark.web.domain.News;

import java.util.List;

public interface NewsDao {
	public List<News> findAll();
	public News findById(Long id);
	public void save(News news);
	public void delete(News news) throws Exception;
}
