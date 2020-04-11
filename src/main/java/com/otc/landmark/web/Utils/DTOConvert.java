package com.otc.landmark.web.Utils;

import com.otc.landmark.web.domain.Category;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.domain.News;
import com.otc.landmark.web.dto.CategoryDto;
import com.otc.landmark.web.dto.EntryDto;
import com.otc.landmark.web.dto.NewsDto;

import java.util.Collection;
import java.util.List;

public class DTOConvert {

	public static void convertCategory2DTO(Category category, CategoryDto categoryDto) {
		categoryDto.setCategoryId(category.getCategoryId());
		categoryDto.setCategoryName(category.getCategoryName());
		categoryDto.setCategoryCode(category.getCategoryCode());
		categoryDto.setCategoryIcon(category.getCategoryIcon());
		categoryDto.setParentCategoryId(category.getParentCategoryId());
	}
	
	public static void convertNews2DTO(News news, NewsDto newsDto) {
		newsDto.setId(news.getId());
		newsDto.setSubject(news.getSubject());
		newsDto.setEntryId(news.getEntry().getId());
		newsDto.setCategoryId(news.getCategoryId());
		newsDto.setSubCategoryId(news.getSubCategoryId());
		newsDto.setAvatarPath(news.getAvatar());
	}

	public static void convertListCategory2DTO(Collection<Category> categories, Collection<CategoryDto> categoryDtos) {
		for (Category category : categories) {
			CategoryDto categoryDto = new CategoryDto();
			convertCategory2DTO(category, categoryDto);
			categoryDtos.add(categoryDto);
		}
	}

	public static void convertEntry2DTO(Entry entry, EntryDto entryDto) {
         CategoryDto categoryDto = new CategoryDto();
         DTOConvert.convertCategory2DTO(entry.getCategory(), categoryDto);
         entryDto.setCategoryDto(categoryDto);        
         if(entry.getNews() != null) {
        	 NewsDto newsDto = new NewsDto();
             DTOConvert.convertNews2DTO(entry.getNews(), newsDto);
             entryDto.setNewsDto(newsDto);
         }
            
		 entryDto.setId(entry.getId());
         entryDto.setSubject(entry.getSubject());
         entryDto.setBody(entry.getBody());   
         entryDto.setSubCategoryId(entry.getCategory().getCategoryId());
         entryDto.setCategoryId(entry.getCategoryId());
         entryDto.setAvatarPath(entry.getAvatar());
         entryDto.setCreatedDate(entry.getCreatedDate());
         String createDateStr = DateUtil.getCreateDate(entry.getCreatedDate());
         entryDto.setYear(createDateStr.substring(0, 4));
         entryDto.setMonth(createDateStr.substring(4, 6));
         entryDto.setDay(createDateStr.substring(6, createDateStr.length()));
	}

	public static void convertListEntry2DTO(List<Entry> entries, List<EntryDto> entryDtos) {
		for (Entry entry : entries) {
			EntryDto entryDto = new EntryDto();		
			convertEntry2DTO(entry, entryDto);
			entryDtos.add(entryDto);
		}
	}

}
