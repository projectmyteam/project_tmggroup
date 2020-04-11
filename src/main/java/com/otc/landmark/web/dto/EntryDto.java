package com.otc.landmark.web.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.otc.landmark.web.domain.UserProfile;

public class EntryDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String subject;
	private String body;
	private Long categoryId;
	private Long subCategoryId;
	private CategoryDto categoryDto;
	private NewsDto newsDto;
	private MultipartFile avatarFile;
	private Integer rating;
	private String avatarPath;
	
	private Date createdDate;
	private String day;
	private String month;
	private String year;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public MultipartFile getAvatarFile() {
		return avatarFile;
	}

	public void setAvatarFile(MultipartFile avatarFile) {
		this.avatarFile = avatarFile;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getAvatarPath() {
		return avatarPath;
	}

	public void setAvatarPath(String avatarPath) {
		this.avatarPath = avatarPath;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public CategoryDto getCategoryDto() {
		return categoryDto;
	}

	public void setCategoryDto(CategoryDto categoryDto) {
		this.categoryDto = categoryDto;
	}

	public NewsDto getNewsDto() {
		return newsDto;
	}

	public void setNewsDto(NewsDto newsDto) {
		this.newsDto = newsDto;
	}
	
}
