package com.dao;

import com.orm.Sign;
import factory.HibernateUtil;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SignSettingDaoImpl implements SignSettingDao {

    HibernateUtil util = new HibernateUtil();

    public void save(Sign signSetting) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.save(signSetting);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
    }

    @SuppressWarnings({ "finally", "unchecked" })
	public List<Sign> query(String queryString) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        List<Sign> list = null;
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

    @SuppressWarnings("finally")
	public Sign load(int id) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        Sign sign = null;
        try {
            sign = (Sign) session.load(Sign.class, new Integer(id));
            if (!Hibernate.isInitialized(sign.getSignrecords())) {
                Hibernate.initialize(sign.getSignrecords());
            }
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
            return sign;
        }
    }

    public void saveOrUpdate(Sign signSetting) {

        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.saveOrUpdate(signSetting);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }

    }

    public void delete(Sign sign) {

        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            if (sign.getSignrecords().size() > 0) {
                sign.getSignrecords().clear();
            }
            session.delete(sign);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
    }
}
