package com.otc.landmark.web.Utils;

import com.otc.landmark.web.domain.*;
import com.otc.landmark.web.dto.*;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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

	public static void convertUser2DTO(User user, UserDto userDto) {
		userDto.setUserId(user.getUserId());
		userDto.setAddress(user.getAddress());
		userDto.setEmail(user.getEmail());
		userDto.setTelephone(user.getTelephone());
		userDto.setUserName(user.getUserName());
	}

	public static void convertListEntry2DTO(List<Entry> entries, List<EntryDto> entryDtos) {
		for (Entry entry : entries) {
			EntryDto entryDto = new EntryDto();
			convertEntry2DTO(entry, entryDto);
			entryDtos.add(entryDto);
		}
	}

	public static void convertComment2DTO(Comment comment, CommentDto commentDto) {
		commentDto.setId(comment.getId());
		commentDto.setComment(comment.getBody());
		String format = "dd/MM/yyyy @ hh:mm";
		commentDto.setCreatedDate(dateString(comment.getCreatedDate(), format));
		User user = comment.getCreatedBy();
		UserDto userDto = new UserDto();
		DTOConvert.convertUser2DTO(user, userDto);
		commentDto.setUser(userDto);
	}

	public static void convertListComment2DTO(Collection<Comment> comments, Collection<CommentDto> commentDtos) {
		for (Comment comment : comments) {
			CommentDto commentDto = new CommentDto();
			DTOConvert.convertComment2DTO(comment, commentDto);
			commentDtos.add(commentDto);
		}
	}

	public static void convertCourse2DTO(Courses courses, CoursesDto coursesDto) {
		coursesDto.setId(courses.getId());
		coursesDto.setDescription(courses.getDescription());
		coursesDto.setTitle(courses.getTitle());
		coursesDto.setImgString(courses.getImage());
		coursesDto.setPrice(courses.getPrice());
		coursesDto.setInstructorName(courses.getInstructorName());
		String format = "dd/MM/yyyy";
		coursesDto.setCreatedDate(dateString(courses.getCreatedDate(), format));
	}

	public static void convertListCourse2DTO(Collection<Courses> courses, Collection<CoursesDto> courseDtos) {
		for (Courses course : courses) {
			CoursesDto coursesDto = new CoursesDto();
			convertCourse2DTO(course, coursesDto);
			courseDtos.add(coursesDto);
		}
	}


	public static String dateString(Date date, String typeFormat) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(typeFormat);
		String stringDate = simpleDateFormat.format(date);
		return stringDate;
	}


}
