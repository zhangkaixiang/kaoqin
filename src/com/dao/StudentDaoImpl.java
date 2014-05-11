
package com.dao;

import com.orm.Student;
import factory.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class StudentDaoImpl implements StudentDao {
    
    HibernateUtil util = new HibernateUtil();
    
    @SuppressWarnings("finally")
	public Student load(int id) {
        Session session = util.getSession();
        Student stu = null;
        Transaction tr = session.beginTransaction();
        try
        {
            stu = (Student) session.load(Student.class, new Integer(id));
            tr.commit();
        }
        catch(Exception e)
        {
             e.printStackTrace();
        }
        finally
        {
            util.closeSession(session);
            return  stu;
        }
    }

    @SuppressWarnings("finally")
	public Student queryUnique(String queryString) {
        Session session = util.getSession();
        Student obj = null;
         @SuppressWarnings("rawtypes")
		List list =null;
        Transaction tr = session.beginTransaction();
        try 
        {
            Query query = session.createQuery(queryString);
            list = query.list();
            tr.commit();
        } catch (Exception e) {
        }
        finally
        {
            if (list.size()>0) {
                obj = (Student) list.get(0);
            }
            util.closeSession(session);
            return obj;
            
        }
    }

    public void save(Student student) 
    {
      Session session = util.getSession();
      Transaction tr = session.beginTransaction();
        try 
        {
          session.save(student);
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

    public void flushStudent() {
         Session session  = util.getSession();
         session.flush();
         session.clear();
    }

    public void executeQuery(String query) {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        try 
        {
            Query q = session.createQuery(query);
            q.executeUpdate();
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
	public List<Student> allStudents() {
        Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        List<Student> list = null;
        try 
        {
            Query q = session.createQuery("from Student as s order by s.id asc");
            list =  q.list();
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

    @SuppressWarnings({ "unchecked", "finally" })
	public List<Student> queryList(String queryString) {
          Session session = util.getSession();
        Transaction tr = session.beginTransaction();
        List<Student> list = null;
        try 
        {
            Query q = session.createQuery(queryString);
            list =  q.list();
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

    public void updateStudent(Student student) {
       Session session = util.getSession();
      Transaction tr = session.beginTransaction();
        try 
        {
          session.saveOrUpdate(student);
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
    
}
