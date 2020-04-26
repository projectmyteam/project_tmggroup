package com.otc.landmark.web.repository;

import com.otc.landmark.web.domain.CourseClip;

import java.util.List;

public interface CourseClipDao {

    void addCourseClip(CourseClip courseClip);
    void editCourseClip(CourseClip courseClip);
    List<CourseClip> findAllCourseClip();
    CourseClip findByIdCourseClip(Long id);


}
