
package com.service;

 
import com.orm.Student;
import java.util.List;


public interface  StudentService
{
    //获取所有学生
    public List<Student> allStudents();
    
    //根据id获取学生
    public Student loadStudent(int id);
    
    //根据学号获取学生
    public Student loadBySno(String sno);
    
    public List<Student> allStudentsByClass(int id);
    
    //学生登陆
    public boolean stuLogin(String sno,String psw);
    
    //添加一个学生
    public void  save(Student student);
    
    //批量导入学生信息
    public boolean importStudentsInfo(List<Student> students);
    
    //删除指定班级的学生信息 
    public boolean deleteStuInfoByClass(int id);
    
    //更新学生
    public boolean  update(Student student);
    
  }
