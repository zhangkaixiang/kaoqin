 
package com.service;

import java.util.List;

import com.dao.TeacherDao;
import com.dao.TeacherDaoImpl;
import com.orm.Student;
import com.orm.Teacher;

 
public class TeacherServiceImpl implements TeacherService {

    TeacherDao dao = new TeacherDaoImpl();
    
    
    public Teacher loadTeacher(int id) {
        return dao.load(id);
    }

    
    public Teacher loadByUserName(String username) {
       Teacher t = null;
        try 
        {
           t = (Teacher) dao.queryUnique("from Teacher as t where t.username = '"+username+"'  ");
        } catch (Exception e) {
            e.printStackTrace();
        }
         return t;
    }

    
    public boolean teacherLogin(String username, String psw) {
        Teacher t = (Teacher) dao.queryUnique("from Teacher as t where t.username = '"+username+"' and t.password = '"+psw+"'  ");
        if (t!=null) {
            return  true;
        }
         return false;
    }
    public boolean importTeacherInfo(List<Teacher> teacher) 
    {
        try 
        {
            for (int i = 0; i < teacher.size(); i++) 
            {
                //添加学生
                dao.save(teacher.get(i));
                //当累加器是20的倍数，将session数据刷新入数据库
                if (i % 20 == 0) 
                {
                    dao.flushTeacher();
                }
            }
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
       
    }
	
	public List<Teacher> loadAllTeacher(){
    	List<Teacher> list=null;
    	list=dao.showAllTeacher("from Teacher");
    	return list;
    }
	public boolean updateTeacherQx(Teacher teacher){
		boolean bool=false;
		if(dao.update(teacher))
			bool=true;
		return bool;
	}
    public boolean update(Teacher teacher) {
        try 
        {
            dao.update(teacher);
            return true;
        } catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
    }
}
