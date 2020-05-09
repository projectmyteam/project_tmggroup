package com.otc.landmark.web.Utils;

import com.otc.landmark.web.domain.*;
import com.otc.landmark.web.dto.*;
import com.otc.landmark.web.repository.CourseTitleOfClipDao;

import java.text.SimpleDateFormat;
import java.util.*;

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
		commentDto.setCreatedDate(dateString(comment.getCreatedDate(), DATE_FOR_COMMENT));
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
		if(courses.getDescription().length() > 32) {
			coursesDto.setDescription(Utils.limitStringLength(courses.getDescription(), 32));
		} else {
			coursesDto.setDescription(courses.getDescription());
		}
		coursesDto.setTitle(courses.getTitle());
		coursesDto.setImgString(courses.getImage());
		coursesDto.setPrice(courses.getPrice());
		coursesDto.setInstructorName(courses.getInstructorName());
		coursesDto.setCreatedDate(dateString(courses.getCreatedDate(), DATE_FOR_SHOW));
	}

	public static void convertListCourse2DTO(Collection<Courses> courses, Collection<CoursesDto> courseDtos) {
		for (Courses course : courses) {
			CoursesDto coursesDto = new CoursesDto();
			convertCourse2DTO(course, coursesDto);
			courseDtos.add(coursesDto);
		}
	}

	public static void convertCourseTitleClip2DTO(CoursesTitleOfClip coursesTitleOfClip,
												  CoursesTitleOfClipDto coursesTitleOfClipDto, Boolean getCourseClip) {
		coursesTitleOfClipDto.setId(coursesTitleOfClip.getId());
		coursesTitleOfClipDto.setSource(coursesTitleOfClip.getSource());
		coursesTitleOfClipDto.setTitle(coursesTitleOfClip.getTitle());
		coursesTitleOfClipDto.setCreatedDate(dateString(coursesTitleOfClip.getCreatedDate(), DATE_FOR_SHOW));
		if(getCourseClip) {
			Set<CourseClip> courseClips = coursesTitleOfClip.getCourseClips();
			Set<CourseClipDto> courseClipDtos = new HashSet<>();
			convertListCourseClip2DTO(courseClips, courseClipDtos);
			List<CourseClipDto> courseClipList = new ArrayList<>();
			for (CourseClipDto courseClipDto : courseClipDtos) {
				CourseClipDto courseClipDtoNew = new CourseClipDto();
				courseClipDtoNew.setId(courseClipDto.getId());
				courseClipDtoNew.setTitle(courseClipDto.getTitle());
				courseClipDtoNew.setSourceLink(courseClipDto.getSourceLink());
				courseClipDtoNew.setCreatedDate(courseClipDto.getCreatedDate());
				courseClipList.add(courseClipDtoNew);
			}
			Comparator<CourseClipDto> compareById = Comparator.comparing(CourseClipDto::getId);
			Collections.sort(courseClipList, compareById);
			coursesTitleOfClipDto.setCourseListClips(courseClipList);
		}
	}

	public static void convertListCourseTitleClip2DTO(Collection<CoursesTitleOfClip> coursesTitleOfClips,
													  Collection<CoursesTitleOfClipDto> coursesTitleOfClipDtos) {
		for (CoursesTitleOfClip coursesTitleOfClip : coursesTitleOfClips) {
			CoursesTitleOfClipDto coursesTitleOfClipDto = new CoursesTitleOfClipDto();
			convertCourseTitleClip2DTO(coursesTitleOfClip, coursesTitleOfClipDto, true);
			coursesTitleOfClipDtos.add(coursesTitleOfClipDto);
		}
	}

	public static void convertListCourseClip2DTO(Collection<CourseClip> courseClips,
												 Collection<CourseClipDto> courseClipDtos) {
		for (CourseClip courseClip : courseClips) {
			CourseClipDto courseClipDto = new CourseClipDto();
			convertCourseClip2DTO(courseClip, courseClipDto);
			courseClipDtos.add(courseClipDto);
		}
	}

	public static void convertCourseClip2DTO(CourseClip courseClip, CourseClipDto courseClipDto) {
		courseClipDto.setId(courseClip.getId());
		courseClipDto.setTitle(courseClip.getTitle());
		courseClipDto.setSourceLink(courseClip.getSourceLink());
		courseClipDto.setCreatedDate(dateString(courseClip.getCreatedDate(), DATE_FOR_SHOW));
	}

	public static String dateString(Date date, String typeFormat) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(typeFormat);
		String stringDate = simpleDateFormat.format(date);
		return stringDate;
	}

	private static final String DATE_FOR_SHOW = "dd/MM/yyyy";
	private static final String DATE_FOR_COMMENT = "dd/MM/yyyy @ hh:mm";
}
