package com.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.orm.Notice;

import factory.HibernateUtil;

public class NoticeDaoImp implements NoticeDao{
	  HibernateUtil util = new HibernateUtil();
	  
	public int queryexist(String sql) {
		Session session = util.getSession();
		Query q = session.createSQLQuery(sql);	
		session.beginTransaction().commit();
		int count = ((Number)q.uniqueResult()).intValue();  
		return count;
	}

	public void save(Notice notice) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.save(notice);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
	}

	public void update(Notice notice) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try {
            session.saveOrUpdate(notice);
            tr.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            util.closeSession(session);
        }
	}
//返回查询int类型的查询结果
	public int getid(String sql) {
		int id=0;
    	Session session = util.getSession();
    	Query q = session.createSQLQuery(sql);	
    	session.beginTransaction().commit();
    	System.out.println(sql);
    	try{
    	 id= ((Number)q.uniqueResult()).intValue();  
    	 
    	}catch(Exception e){
    		return id;
    	}
    	return id;
	}


}
