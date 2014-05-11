package com.service;

import com.orm.Course;
import java.util.List;

public interface CourseService {
    //根据教师id获取课程列表

    public List<Course> listCourseByTeacherId(int teacherid);
    //根据id获取一个课程

    public Course load(int id);

    //获取某个班级所有的课程
    public List<Course> listCourseByClassId(int classid);

    //保存课程
    public boolean save(Course course);

    //根据教师id获取课程列表
    public List<Course> listCourseByTeacherAndClass(int teacherid, int classid);

    //更新课程
    public boolean update(Course course);

    public boolean delete(Course course);
}
