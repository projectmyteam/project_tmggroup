package com.otc.landmark.web.repository.impl;

import com.otc.landmark.web.domain.CoursesTitleOfClip;
import com.otc.landmark.web.repository.CourseTitleOfClipDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseTitleOfClipDaoImpl implements CourseTitleOfClipDao {


    @Override
    public void addCourseTitle(CoursesTitleOfClip coursesTitleOfClip) {
        Session session = sessionFactory.getCurrentSession();
        session.save(coursesTitleOfClip);
    }


    @Autowired
    private SessionFactory sessionFactory;

}
