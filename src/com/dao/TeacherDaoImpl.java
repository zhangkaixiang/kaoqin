package com.dao;

import java.util.List;

import com.orm.Teacher;
import factory.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

 
public class TeacherDaoImpl implements TeacherDao {

    HibernateUtil util = new HibernateUtil(); 
    
    
    @SuppressWarnings("finally")
	public Teacher load(int id) {
        
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        Teacher teacher = null;
        try 
        {
            teacher = (Teacher) session.load(Teacher.class, new Integer(id));
            if (!Hibernate.isInitialized(teacher.getCourses())) {
                Hibernate.initialize(teacher.getCourses());
            }
            tr.commit();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            util.closeSession(session);
            return teacher;
        }
    }

    @SuppressWarnings("finally")
	public Object queryUnique(String queryString) {
        Session session = util.getSession();
        Object obj=null;
        try
        {
            Transaction tr = session.beginTransaction();
            Query query = session.createQuery(queryString);
            tr.commit();
            if (query.list().size()>0) {
                obj = query.list().get(0);
            }
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            return obj;
        }
    }

	public void flushTeacher() {
        Session session  = util.getSession();
        session.flush();
        session.clear();
	}

	public void save(Teacher teacher) {
	    Session session = util.getSession();
	      Transaction tr = session.beginTransaction();
	        try 
	        {
	          session.save(teacher);
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

	@SuppressWarnings("unchecked")
	public List<Teacher> showAllTeacher(String queryString) {
		List<Teacher> list=null;
		Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try 
        {
            Query q = session.createQuery(queryString);
            list=q.list();
            tr.commit();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            util.closeSession(session);
        }
		return list ;
	}
    public boolean update(Teacher teacher){
	    Session session = util.getSession();
	      Transaction tr = session.beginTransaction();
	        try 
	        {
	          session.update(teacher);
	          tr.commit();
	          return true;
	        } catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        finally
	        {
	            util.closeSession(session);
	        }
	        return false;
    }

}
