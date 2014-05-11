
package com.dao;

import com.orm.Student;
import java.util.List;


public interface StudentDao 
{
    //查询学生集合
    public List<Student> queryList(String queryString);
    
    //获取所有学生
    public List<Student> allStudents();
    
    //执行查询
    public void executeQuery(String query);
    
    //加载一个学生
    public Student load(int  id);
   
    //查询学生
    public Student queryUnique(String queryString);
    
    //添加学生
    public void save(Student student);
    
    //刷新学生
    public void flushStudent();
    
    //更新学生
    public  void updateStudent(Student student);
    //删除学生
    
}
