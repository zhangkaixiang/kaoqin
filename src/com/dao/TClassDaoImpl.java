package com.dao;

import com.orm.Course;
import com.orm.TClass;
import factory.HibernateUtil;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TClassDaoImpl implements TClassDao {

    HibernateUtil util = new HibernateUtil();

    @SuppressWarnings({ "unchecked", "finally" })
	public List<TClass> allSClass() {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        List<TClass> list = null;
        try {
            Query query = session.createQuery("from TClass as sc");
            list = query.list();
            tr.commit();
        } catch (Exception e) {
            System.out.println("all SClass error!");
            e.printStackTrace();
        } finally {
            util.closeSession(session);
            return list;
        }
    }

    @SuppressWarnings("finally")
	public TClass queryUnique(String queryString) {
        Session session = util.getSession();
        TClass obj = null;
        @SuppressWarnings("rawtypes")
		List list = null;
        Transaction tr = session.beginTransaction();
        try {
            Query query = session.createQuery(queryString);
            list = query.list();
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (list.size() > 0) {
                obj = (TClass) list.get(0);
            }
            util.closeSession(session);
            return obj;

        }
    }

    @SuppressWarnings("finally")
	public TClass load(int id) {
        Session session = util.getSession();
        TClass obj = null;
        @SuppressWarnings("unused")
		Transaction tr = session.beginTransaction();
        try {
            obj = (TClass) session.load(TClass.class, new Integer(id));
            if (!Hibernate.isInitialized(obj.getStudents())) {
                Hibernate.initialize(obj.getStudents());
            }
            if (!Hibernate.isInitialized(obj.getCourses())) {
                Hibernate.initialize(obj.getCourses());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            util.closeSession(session);
            return obj;

        }
    }

    public void save(TClass tclass) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.save(tclass);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
    }

    public void update(TClass tclass) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.saveOrUpdate(tclass);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
    }
    
    public void delete(TClass tclass) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.delete(tclass);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
    }
}
