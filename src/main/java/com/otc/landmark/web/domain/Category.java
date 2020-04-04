package com.otc.landmark.web.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.otc.landmark.web.Utils.Utils;

@Entity
@Table(name = "otc_category")
public class Category implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID")
	private Long categoryId;
	
	@Column(name = "CATEGORY_CODE")
	private String categoryCode;
	
	@Column(name = "CATEGORY_NAME")
	private String categoryName;

	@Column(name = "PARENT_CATEGORY_ID")
	private Long parentCategoryId;

	@Column(name = "CATEGORY_ICON")
	private String categoryIcon;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<Entry> entries = new ArrayList<Entry>();

	@Transient
	private Category parentCategory;

	@Transient
	private List<Category> childsCategory;
	
	public Long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = Utils.upperCaseFirstChar(categoryName);
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public List<Category> getChildsCategory() {
		return childsCategory;
	}
	
	public void addChildCategory(Category childCategory) {
		if(childCategory == null) {
			throw new IllegalArgumentException("ChildCategory must be not null");
		}
		this.getChildsCategory().add(childCategory);
	}

	public void setChildsCategory(List<Category> childsCategory) {
		if(childsCategory != null && !childsCategory.isEmpty()) {
			for(Category category : childsCategory) {
				category.setCategoryName(Utils.upperCaseFirstChar(category.getCategoryName()));
			}
		}
		this.childsCategory = childsCategory;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public String getCategoryIcon() {
		return categoryIcon;
	}

	public void setCategoryIcon(String categoryIcon) {
		this.categoryIcon = categoryIcon;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

}
