 
package com.service;

import com.dao.StudentDao;
import com.dao.StudentDaoImpl;
 
import com.orm.Student;
import java.util.List;

 
public class StudentServiceImpl implements StudentService {
    
    StudentDao dao = new StudentDaoImpl();
    
    
    public Student loadStudent(int id) 
    {
        return dao.load(id);
    }

    
    public Student loadBySno(String sno) {
       return (Student) dao.queryUnique("from Student as s where s.sno = '"+sno+"' ");
    }

    
    public boolean stuLogin(String sno, String psw) {
       Student s = (Student)dao.queryUnique("from Student as s where s.sno = '"+sno+"' and s.password = '"+psw+"' ");
        if (s!=null) {
            return  true;
        }
         return false;
    }

    
    public void  save(Student student) {
        try {
             dao.save(student);
        } catch (Exception e) {
            System.out.println("add student error");
        }
    }
    
    //传入一个学生集，将这个学生集导入数据库
    //批量插入，定期刷新session缓存，防止内存溢出，
    
    public boolean importStudentsInfo(List<Student> students) 
    {
        try 
        {
            for (int i = 0; i < students.size(); i++) 
            {
                //添加学生
                dao.save(students.get(i));
                //当累加器是20的倍数，将session数据刷新入数据库
                if (i % 20 == 0) 
                {
                    dao.flushStudent();
                }
            }
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
       
    }

      
    public boolean deleteStuInfoByClass(int id) {
        try {
            dao.executeQuery("delete Student as s where s.TClass.id='"+id+"' ");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Student> allStudentsByClass(int id) {
         List list = null;
        try {
          list = dao.queryList("from Student as s where s.TClass.id = '"+id+"' ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Student> allStudents() {
         List list = null;
        try {
          list = dao.queryList("from Student as s ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public boolean update(Student student) {
        try 
        {
            dao.updateStudent(student);
            return true;
        } catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }
   
     
    
}
