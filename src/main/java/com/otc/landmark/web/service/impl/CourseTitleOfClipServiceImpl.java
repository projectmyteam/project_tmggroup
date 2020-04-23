package com.otc.landmark.web.service.impl;

import com.otc.landmark.web.domain.Courses;
import com.otc.landmark.web.domain.CoursesTitleOfClip;
import com.otc.landmark.web.dto.CoursesTitleOfClipDto;
import com.otc.landmark.web.repository.CourseTitleOfClipDao;
import com.otc.landmark.web.repository.CoursesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;

@Service
@Transactional(rollbackOn = Exception.class)
public class CourseTitleOfClipServiceImpl {


    public CoursesTitleOfClipDto add(CoursesTitleOfClipDto coursesTitleOfClipDto) {
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
            } else {
                coursesTitleOfClipDTO.setResult("false");
            }
        }
        return coursesTitleOfClipDTO;
    }



    @Autowired
    private CourseTitleOfClipDao courseTitleOfClipDao;
    @Autowired
    private CoursesDao coursesDao;


}
