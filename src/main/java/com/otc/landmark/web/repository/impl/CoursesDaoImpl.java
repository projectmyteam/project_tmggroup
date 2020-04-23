package com.otc.landmark.web.repository.impl;

import com.otc.landmark.web.domain.Courses;
import com.otc.landmark.web.repository.CoursesDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoursesDaoImpl implements CoursesDao {

    @Override
    public void addCourse(Courses courses) {
        Session session = sessionFactory.getCurrentSession();
        session.save(courses);
    }

    @Override
    public void editCourse(Courses courses) {

    }

    @Override
    public Courses findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Courses where id=:id", Courses.class)
                .setParameter("id", id);
        return (Courses) query.uniqueResult();
    }

    @Override
    public List<Courses> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Courses", Courses.class);
        return query.getResultList();
    }

    @Autowired
    private SessionFactory sessionFactory;
}
