package com.otc.landmark.web.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "otc_entry")
public class Entry extends AbstractPosting {
	
	@Column(name = "CATEGORY_ID")
	private Long categoryId;
	
	@Column(name = "SUB_CATEGORY_ID")
	private Long subCategoryId;
	
	@Column(name = "RATING")
	private Integer rating;

	@Transient
	private Category parentCategory;
	
	@Transient
	private Category subCategory;
	
	@Transient
	private UserProfile userProfile;
	
	@Transient
	private List<Attachment> entryAttachments = new ArrayList<Attachment>();

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

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Category getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Category subCategory) {
		this.subCategory = subCategory;
	}
}
