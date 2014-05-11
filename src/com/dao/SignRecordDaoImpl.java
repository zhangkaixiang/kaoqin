/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.orm.Signrecord;
import factory.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

 
public class SignRecordDaoImpl implements SignRecordDao {
     HibernateUtil util = new HibernateUtil();
    @SuppressWarnings("finally")
	public Signrecord load(int id) {
       Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        Signrecord record = null;
        try 
        {
          record =  (Signrecord) session.load(Signrecord.class,new Integer(id));
            tr.commit();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            util.closeSession(session);
            return record;
        }
    }

    public void save(Signrecord record) {
       Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try 
        {
            session.save(record);
            tr.commit();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            util.closeSession(session);
        }
    }

    @SuppressWarnings({ "finally", "unchecked" })
	public List<Signrecord> queryList(String queryString) {
      Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        List<Signrecord> list = null;
        try 
        {
            Query q = session.createQuery(queryString);
            list = q.list();
            tr.commit();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            util.closeSession(session);
            return list;
        }
    }

    @SuppressWarnings({ "finally", "unchecked" })
	public List<Signrecord> sqlQueryList(String queryString) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        List<Signrecord> list = null;
        try 
        {
            list = session.createSQLQuery(queryString).addEntity(Signrecord.class).list();
            tr.commit();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            util.closeSession(session);
            return list;
        }
    }
}
