package com.otc.landmark.web.service.impl;

import com.otc.landmark.web.Utils.DTOConvert;
import com.otc.landmark.web.Utils.UtilsUploadFile;
import com.otc.landmark.web.constant.CommonConst;
import com.otc.landmark.web.domain.Courses;
import com.otc.landmark.web.dto.CoursesDto;
import com.otc.landmark.web.repository.CoursesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    public List<CoursesDto> findAll() {
        List<CoursesDto> coursesDtos = new ArrayList<>();
        List<Courses> coursesList = coursesDao.findAll();
        DTOConvert.convertListCourse2DTO(coursesList, coursesDtos);
        return coursesDtos;
    }

    public CoursesDto findById(Long courseId) {
        Courses courses = coursesDao.findById(courseId);
        CoursesDto coursesDto = new CoursesDto();
        DTOConvert.convertCourse2DTO(courses, coursesDto);
        return coursesDto;
    }


    @Autowired
    private CoursesDao coursesDao;
}
