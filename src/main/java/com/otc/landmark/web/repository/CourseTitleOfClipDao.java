package com.otc.landmark.web.repository;

import com.otc.landmark.web.domain.Courses;
import com.otc.landmark.web.domain.CoursesTitleOfClip;

import java.util.List;

public interface CourseTitleOfClipDao {

    void addCourseTitle(CoursesTitleOfClip coursesTitleOfClip);
    void updateCourseTitle(CoursesTitleOfClip coursesTitleOfClip);
    List<CoursesTitleOfClip> findAll();
    CoursesTitleOfClip findById(Long id);
    List<CoursesTitleOfClip> findByCourseId(Long courseId);
}
