package com.otc.landmark.web.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "otc_entry")
public class Entry extends AbstractPosting {
	
	@Column(name = "RATING")
	private Integer rating;
	
	//non-owning side
	@OneToOne(fetch = FetchType.LAZY, 
//			cascade=CascadeType.ALL, 
	        mappedBy = "entry")
	private News news;
	
	@Column(name = "CATEGORY_ID")
	private Long categoryId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUB_CATEGORY_ID", referencedColumnName = "CATEGORY_ID")
	private Category category;
	
	@Transient
	private Category parentCategory;
	
	@Transient
	private Category subCategory;
	
	@Transient
	private UserProfile userProfile;
	
	@Transient
	private List<Attachment> entryAttachments = new ArrayList<Attachment>();

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

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
}
