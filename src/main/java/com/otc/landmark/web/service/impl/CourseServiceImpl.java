package com.otc.landmark.web.service.impl;

import com.otc.landmark.web.Utils.DTOConvert;
import com.otc.landmark.web.Utils.UtilsUploadFile;
import com.otc.landmark.web.constant.CommonConst;
import com.otc.landmark.web.domain.CourseClip;
import com.otc.landmark.web.domain.Courses;
import com.otc.landmark.web.domain.CoursesTitleOfClip;
import com.otc.landmark.web.dto.CourseClipDto;
import com.otc.landmark.web.dto.CoursesDto;
import com.otc.landmark.web.dto.CoursesTitleOfClipDto;
import com.otc.landmark.web.repository.CourseClipDao;
import com.otc.landmark.web.repository.CourseTitleOfClipDao;
import com.otc.landmark.web.repository.CoursesDao;
import freemarker.template.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackOn = Exception.class)
public class CourseServiceImpl {

    public CoursesDto addCourse(CoursesDto coursesDto, HttpServletRequest req) throws Exception{
        String pathFile = UtilsUploadFile.uploadFile(req, coursesDto.getImage(), CommonConst.UPLOAD_FILE_COURSE);
        Courses courses = new Courses();
        courses.setTitle(coursesDto.getTitle());
        courses.setDescription(coursesDto.getDescription());
        courses.setPrice(coursesDto.getPrice());
        courses.setInstructorName(coursesDto.getInstructorName());
        courses.setImage(pathFile);
        courses.setCreatedDate(Calendar.getInstance().getTime());
        coursesDao.addCourse(courses);
        CoursesDto result = new CoursesDto();
        if(courses.getId()!=null) {
            result.setResult("true");
        } else {
            result.setResult("false");
        }
        return result;
    }

    public List<CoursesDto> findAllCourses() {
        List<CoursesDto> coursesDtos = new ArrayList<>();
        List<Courses> coursesList = coursesDao.findAll();
        DTOConvert.convertListCourse2DTO(coursesList, coursesDtos);
        return coursesDtos;
    }

    public CoursesDto findCourseById(Long courseId) {
        Courses courses = coursesDao.findById(courseId);
        CoursesDto coursesDto = new CoursesDto();
        DTOConvert.convertCourse2DTO(courses, coursesDto);
        return coursesDto;
    }

    public List<CoursesTitleOfClipDto> findByCourseId(Long courseId) {
        List<CoursesTitleOfClip> coursesTitleOfClips = courseTitleOfClipDao.findByCourseId(courseId);
        List<CoursesTitleOfClipDto> coursesTitleOfClipDtos = new ArrayList<>();
        DTOConvert.convertListCourseTitleClip2DTO(coursesTitleOfClips, coursesTitleOfClipDtos);
        return coursesTitleOfClipDtos;
    }

    public List<CoursesTitleOfClipDto> findAllCoursesTitleClip() {
        List<CoursesTitleOfClip> coursesTitleOfClips = courseTitleOfClipDao.findAll();
        List<CoursesTitleOfClipDto> coursesTitleOfClipDtos = new ArrayList<>();
        DTOConvert.convertListCourseTitleClip2DTO(coursesTitleOfClips, coursesTitleOfClipDtos);
        return coursesTitleOfClipDtos;
    }

    public CoursesTitleOfClipDto addCoursesTitleClip(CoursesTitleOfClipDto coursesTitleOfClipDto) {
        Courses courses = coursesDao.findById(coursesTitleOfClipDto.getCourseId());
        CoursesTitleOfClipDto coursesTitleOfClipDTO = new CoursesTitleOfClipDto();
        if(courses == null) {
            coursesTitleOfClipDTO.setResult("false");
        } else {
            CoursesTitleOfClip coursesTitleOfClip = new CoursesTitleOfClip();
            coursesTitleOfClip.setTitle(coursesTitleOfClipDto.getTitle());
            coursesTitleOfClip.setCourses(courses);
            coursesTitleOfClip.setCreatedDate(Calendar.getInstance().getTime());
            courseTitleOfClipDao.addCourseTitle(coursesTitleOfClip);
            if(coursesTitleOfClip.getId() != null) {
                coursesTitleOfClipDTO.setResult("true");
                coursesTitleOfClipDTO.setId(coursesTitleOfClip.getId());
            } else {
                coursesTitleOfClipDTO.setResult("false");
            }
        }
        return coursesTitleOfClipDTO;
    }

    public CoursesTitleOfClipDto editCoursesTitleOfClip(CoursesTitleOfClipDto coursesTitleOfClipDto) {
        CoursesTitleOfClip coursesTitleOfClip = courseTitleOfClipDao.findById(coursesTitleOfClipDto.getId());
        CoursesTitleOfClipDto dto = new CoursesTitleOfClipDto();
        if(coursesTitleOfClip != null) {
            coursesTitleOfClip.setTitle(coursesTitleOfClipDto.getTitle());
            coursesTitleOfClip.setUpdatedDate(Calendar.getInstance().getTime());
            courseTitleOfClipDao.updateCourseTitle(coursesTitleOfClip);
            dto.setResult("true");
        } else {
            dto.setResult("false");
        }
        return dto;
    }

    public CourseClipDto addCourseClip(CourseClipDto courseClipDto) {
        CourseClip courseClip = new CourseClip();
        CourseClipDto courseClipDTO = new CourseClipDto();
        if(courseClipDto.getCoursesTitleOfClipId() != null) {
            CoursesTitleOfClip coursesTitleOfClip = courseTitleOfClipDao.findById(courseClipDto.getCoursesTitleOfClipId());
            if(coursesTitleOfClip == null) {
                courseClipDTO.setResult("false");
            } else {
                courseClip.setCoursesTitleOfClip(coursesTitleOfClip);
                courseClip.setTitle(courseClipDto.getTitle());
                courseClip.setCreatedDate(Calendar.getInstance().getTime());
                courseClipDao.addCourseClip(courseClip);
                courseClipDTO.setResult("true");
                courseClipDTO.setId(courseClip.getId());
            }
        }
        return courseClipDTO;
    }

    public CourseClipDto editCourseClip(Long courseClipId, CourseClipDto courseClipDto) {
        CourseClip courseClip = courseClipDao.findByIdCourseClip(courseClipId);
        CourseClipDto courseClipDTO = new CourseClipDto();
        if(courseClip == null) {
            courseClipDTO.setResult("false");
            return courseClipDTO;
        }
        courseClip.setTitle(courseClipDto.getTitle());
        courseClip.setUpdatedDate(Calendar.getInstance().getTime());
        courseClipDTO.setResult("true");
        return courseClipDTO;
    }

    public CourseClipDto removeCourseClip(Long courseClipId) {
        CourseClip courseClip = courseClipDao.findByIdCourseClip(courseClipId);
        CourseClipDto courseClipDTO = new CourseClipDto();
        if(courseClip == null) {
            courseClipDTO.setResult("false");
            return courseClipDTO;
        }
        courseClipDao.removeCourseClip(courseClip);
        courseClipDTO.setResult("true");
        return courseClipDTO;
    }

    @Autowired
    private CoursesDao coursesDao;
    @Autowired
    private CourseTitleOfClipDao courseTitleOfClipDao;
    @Autowired
    private CourseClipDao courseClipDao;
}
