package com.otc.landmark.web.repository.impl;

import com.otc.landmark.web.domain.CoursesTitleOfClip;
import com.otc.landmark.web.repository.CourseTitleOfClipDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseTitleOfClipDaoImpl implements CourseTitleOfClipDao {


    @Override
    public void addCourseTitle(CoursesTitleOfClip coursesTitleOfClip) {
        Session session = sessionFactory.getCurrentSession();
        session.save(coursesTitleOfClip);
    }

    @Override
    public void updateCourseTitle(CoursesTitleOfClip coursesTitleOfClip) {
        Session session = sessionFactory.getCurrentSession();
        session.update(coursesTitleOfClip);
    }

    @Override
    public List<CoursesTitleOfClip> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CoursesTitleOfClip");
        return query.list();
    }

    @Override
    public CoursesTitleOfClip findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CoursesTitleOfClip ctl where ctl.id = :id");
        query.setParameter("id", id);
        return (CoursesTitleOfClip) query.uniqueResult();
    }


    @Autowired
    private SessionFactory sessionFactory;

}
