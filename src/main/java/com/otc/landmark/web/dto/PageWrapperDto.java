package com.otc.landmark.web.dto;

import java.util.List;

public class PageWrapperDto<T> {
	
	private int startPage;  //default
	private int pageSize;   //default
	private int totalPages;
	private List<T> data;
	private int countAll;
	
	public PageWrapperDto() {
	}
	
	public PageWrapperDto(int startPage, int pageSize) {
		this.startPage = startPage;
		this.pageSize = pageSize;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getData() {
		return data;
	}

	public void setDataAndCalculatePaging(List<T> data, int count) {
		this.data = data;
		this.countAll = count;
		int totalPages = count/pageSize;
		if((count % pageSize) > 0) {
			totalPages += 1;
		}
		this.totalPages = totalPages;
	}

	public int getCountAll() {
		return countAll;
	}

	public void setCountAll(int countAll) {
		this.countAll = countAll;
	}
	
}
