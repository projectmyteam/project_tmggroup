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
    public void removeCourseTitle(CoursesTitleOfClip coursesTitleOfClip) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(coursesTitleOfClip);
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

    @Override
    public List<CoursesTitleOfClip> findByCourseId(Long courseId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct a from CoursesTitleOfClip a left join fetch a.courses b " +
                "left join fetch a.courseClips c where b.id = :id order by a.id asc");
        query.setParameter("id", courseId);
        return query.list();
    }

    @Autowired
    private SessionFactory sessionFactory;

}
