package com.dao;

import com.orm.Task;
import factory.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TaskDaoImpl implements TaskDao {

    HibernateUtil util = new HibernateUtil();

    @SuppressWarnings("finally")
	public Task load(int id) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        Task task = null;
        try {
            task = (Task) session.load(Task.class, new Integer(id));
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
            return task;
        }
    }

    public void save(Task task) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.save(task);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
    }

    @SuppressWarnings({ "unchecked", "finally" })
	public List<Task> queryList(String queryString) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        List<Task> list = null;
        try {
            System.out.print(queryString);
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

    public void update(Task task) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.saveOrUpdate(task);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
    }

   
}
