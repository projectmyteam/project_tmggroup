package com.otc.landmark.web.dto;


import java.io.Serializable;
import java.util.List;

public class CategoryDto implements Serializable {

    private Long categoryId;
    private String categoryCode;
    private String categoryName;
    private String categoryIcon;
    private Long parentCategoryId;
    private EntryDto newestEntry;
    private List<CategoryDto> childCategory;
    private List<EntryDto> entryList;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public EntryDto getNewestEntry() {
        return newestEntry;
    }

    public void setNewestEntry(EntryDto newestEntry) {
        this.newestEntry = newestEntry;
    }

    public List<CategoryDto> getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(List<CategoryDto> childCategory) {
        this.childCategory = childCategory;
    }

    public List<EntryDto> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<EntryDto> entryList) {
        this.entryList = entryList;
    }

	public Long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
    
}
