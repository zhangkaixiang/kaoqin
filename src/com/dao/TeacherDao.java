 
package com.dao;

import java.util.List;

import com.orm.Student;
import com.orm.Teacher;

 
public interface TeacherDao {
     //加载一个教师
    public Teacher load(int  id);
   
    //查询教师
    public Object queryUnique(String queryString);    
    //刷新教师
    public void flushTeacher();
    //添加教师
    public void save(Teacher tacher);
    //查询所有教师
    public List<Teacher> showAllTeacher(String queryString);
    //修改教师权限
    public boolean update(Teacher teacher);
    
}
