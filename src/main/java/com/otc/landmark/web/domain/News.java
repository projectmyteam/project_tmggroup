package com.otc.landmark.web.domain;

import javax.persistence.*;

@Entity
@Table(name = "otc_news")
public class News extends AbstractPosting implements Posting {
	
//	@Column(name = "ENTRY_ID")
//	private Long entryId;
	
	@Column(name = "CATEGORY_ID")
	private Long categoryId;
	
	@Column(name = "SUB_CATEGORY_ID")
	private Long subCategoryId;
	
	@OneToOne(fetch = FetchType.EAGER
			/*, cascade=CascadeType.ALL*/)
	private Entry entry;
	
//	public Long getEntryId() {
//		return entryId;
//	}
//
//	public void setEntryId(Long entryId) {
//		this.entryId = entryId;
//	}

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

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}
	
}
