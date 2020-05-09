package com.otc.landmark.web.repository.impl;

import com.otc.landmark.web.domain.CourseClip;
import com.otc.landmark.web.repository.CourseClipDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseClipDaoImpl implements CourseClipDao {
    @Override
    public void addCourseClip(CourseClip courseClip) {
        Session session = sessionFactory.getCurrentSession();
        session.save(courseClip);
    }

    @Override
    public void editCourseClip(CourseClip courseClip) {
        Session session = sessionFactory.getCurrentSession();
        session.update(courseClip);
    }

    @Override
    public List<CourseClip> findAllCourseClip() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CourseClip");
        return query.list();
    }

    @Override
    public CourseClip findByIdCourseClip(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from CourseClip colip where colip.id = :id");
        query.setParameter("id", id);
        return (CourseClip) query.uniqueResult();
    }



    @Override
    public void removeCourseClip(CourseClip courseClip) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(courseClip);
    }

    @Override
    public List<CourseClip> findCourseClipByIdTitleCourse(Long courseTitleId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select A.courseClips from CoursesTitleOfClip A where A.id = :courseTitleId");
        query.setParameter("courseTitleId", courseTitleId);
        return query.list();
    }


    @Autowired
    private SessionFactory sessionFactory;
}
