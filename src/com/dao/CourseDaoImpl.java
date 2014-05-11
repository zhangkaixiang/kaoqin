package com.dao;

import com.orm.Course;
import factory.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CourseDaoImpl implements CourseDao {

    HibernateUtil util = new HibernateUtil();

    @SuppressWarnings("finally")
	public Course load(int id) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        Course course = null;
        try {
            course = (Course) session.load(Course.class, new Integer(id));
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
            return course;
        }
    }

    public void save(Course course) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.save(course);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
    }

    @SuppressWarnings({ "finally", "unchecked" })
	public List<Course> queryList(String queryString) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        List<Course> list = null;
        try {
            Query q = session.createQuery(queryString);
            list = q.list();
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
            return list;
        }
    }

    public void update(Course course) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.update(course);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
    }

    public void delete(Course course) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.delete(course);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
    }
}
