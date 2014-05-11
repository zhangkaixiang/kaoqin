package com.dao;

import com.orm.Score;
import factory.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreDaoImpl implements ScoreDao {

    HibernateUtil util = new HibernateUtil();

    @SuppressWarnings("finally")
	public Score load(int id) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        Score course = null;
        try {
            course = (Score) session.load(Score.class, new Integer(id));
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
            return course;
        }
    }

    public void save(Score score) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.save(score);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
    }

    @SuppressWarnings({ "finally", "unchecked" })
	public List<Score> queryList(String queryString) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        List<Score> list = null;
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

    public void update(Score score) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.saveOrUpdate(score);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
    }

    @SuppressWarnings("rawtypes")
	public List sqlquery(String queryString) {
        Session session = util.getSession();
        @SuppressWarnings("unused")
		Transaction tr = session.beginTransaction();
//        List list = new ArrayList();
       Query q= session.createSQLQuery(queryString);
        return q.list();
    }
}
