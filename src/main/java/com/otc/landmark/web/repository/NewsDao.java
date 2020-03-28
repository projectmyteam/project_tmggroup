package com.otc.landmark.web.repository;

import java.util.List;

import com.otc.landmark.web.domain.News;

public interface NewsDao {
	public List<News> findAll();
	public News findById(Long id);
	public void save(News news);
	public void delete(News news);
}
