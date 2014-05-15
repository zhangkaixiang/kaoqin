 
package com.service;

import java.util.List;

import com.orm.Student;
import com.orm.Teacher;

 
public interface TeacherService {
    
    //根据id获取教师
    public Teacher loadTeacher(int id);
    
    //根据用户名获取教师
    public Teacher loadByUserName(String username);
    
    //教师登陆
    public boolean teacherLogin(String username,String psw);
    //导入教师信息
    public boolean importTeacherInfo(List<Teacher> teacher);
    //获取所有的教师信息
    public List<Teacher> loadAllTeacher();
    //更新教师权限
    public boolean updateTeacherQx(Teacher teacher);
    //修改教师密码
    public boolean update(Teacher teacher) ;
}
