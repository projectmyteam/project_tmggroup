package com.otc.landmark.web.repository.impl;

import com.otc.landmark.web.domain.Comment;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.dto.CommentDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class CommentDaoImpl {

    @Autowired
    SessionFactory sessionFactory;

    public void save(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.save(comment);
    }

    public List<Comment> findAllWithMaxResults(Long entryId, Integer first, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "from Comment c where c.entryId = :entryId order by c.createdDate desc";
        Query query = session.createQuery(sql, Comment.class).setParameter("entryId", entryId).setFirstResult(first).setMaxResults(limit);
        return query.getResultList();
    }

    public List<Comment> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String sql = "from Comment";
        Query query = session.createQuery(sql, Comment.class);
        return query.getResultList();
    }

    public int countComment(Long entryId) {
        Session session = sessionFactory.getCurrentSession();
        return ((BigInteger) session.createNativeQuery("SELECT COUNT(*) FROM otc_comment m where m.ENTRY_ID = :entryId")
                .setParameter("entryId", entryId).getSingleResult()).intValue();
    }

    public List<Entry> findEntry() {
        Session session = sessionFactory.getCurrentSession();
        String sql = "from Entry c order by c.createdDate desc";
        Query query = session.createQuery(sql, Entry.class).setFirstResult(0).setMaxResults(3);
        return query.getResultList();
    }

}
