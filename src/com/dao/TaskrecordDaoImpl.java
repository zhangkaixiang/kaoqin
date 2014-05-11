/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.orm.Taskrecord;
import factory.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
public class TaskrecordDaoImpl implements TaskrecordDao {

    HibernateUtil util = new HibernateUtil();

    @SuppressWarnings("finally")
	public Taskrecord load(int id) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        Taskrecord taskrecord = null;
        try {
            taskrecord = (Taskrecord) session.load(Taskrecord.class, new Integer(id));
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
            return taskrecord;
        }
    }

    public void save(Taskrecord taskrecord) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.save(taskrecord);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
    }

    @SuppressWarnings({ "finally", "unchecked" })
	public List<Taskrecord> queryList(String queryString) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        List<Taskrecord> list = null;
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

    public void update(Taskrecord taskrecord) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.saveOrUpdate(taskrecord);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
    }
}
