package com.dao;

import com.orm.Course;
import java.util.List;

public interface CourseDao {
    //加载一个课程

    public Course load(int id);

    //保存一个课程
    public void save(Course course);

    //根据条件查询
    public List<Course> queryList(String queryString);

    public void update(Course course);

    public void delete(Course course);
}
