package com.otc.landmark.web.repository;

import com.otc.landmark.web.domain.Courses;

import java.util.List;

public interface CoursesDao {

    void addCourse(Courses courses);
    void editCourse(Courses courses);
    Courses findById(Long id);
    List<Courses> findAll();

}
