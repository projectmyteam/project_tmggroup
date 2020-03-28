package com.otc.landmark.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "otc_news")
public class News extends AbstractPosting implements Posting{
	
	@Column(name = "ENTRY_ID")
	private Long entryId;
	
	@Column(name = "CATEGORY_ID")
	private Long categoryId;
	
	@Column(name = "SUB_CATEGORY_ID")
	private Long subCategoryId;


	public Long getEntryId() {
		return entryId;
	}

	public void setEntryId(Long entryId) {
		this.entryId = entryId;
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
	
}
