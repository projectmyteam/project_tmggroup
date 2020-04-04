package com.otc.landmark.web.Utils;

import com.otc.landmark.web.domain.Category;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.dto.CategoryDto;
import com.otc.landmark.web.dto.EntryDto;

import java.util.Collection;

public class DTOConvert {

    public static void convertCategory2DTO(Category category, CategoryDto categoryDto) {
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setCategoryCode(category.getCategoryCode());
        categoryDto.setCategoryIcon(category.getCategoryIcon());
    }

    public static void convertListCategory2DTO(Collection<Category> categories, Collection<CategoryDto> categoryDtos) {
        for (Category category:categories) {
            CategoryDto categoryDto = new CategoryDto();
            convertCategory2DTO(category, categoryDto);
            categoryDtos.add(categoryDto);
        }
    }

    public static void convertEntry2DTO(Entry entry, EntryDto entryDto) {
        entryDto.setId(entry.getId());
        entryDto.setSubject(entry.getSubject());
        entryDto.setBody(entry.getBody());
        entryDto.setRating(entry.getRating());
    }



}
