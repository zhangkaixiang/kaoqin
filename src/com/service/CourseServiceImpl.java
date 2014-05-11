package com.service;

import com.dao.CourseDao;
import com.dao.CourseDaoImpl;
import com.orm.Course;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    CourseDao dao = new CourseDaoImpl();

    public Course load(int id) {
        return dao.load(id);
    }

    //根据教师获取课程
    
    public List<Course> listCourseByTeacherId(int teacherid) {
        return dao.queryList("from Course as c where c.teacher.id ='" + teacherid + "' ");
    }

    //根据班级获取课程
    
    public List<Course> listCourseByClassId(int classid) {
        return dao.queryList("from Course as c where c.TClass.id ='" + classid + "' ");
    }

    
    public boolean save(Course course) {
        try {
            dao.save(course);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
    public List<Course> listCourseByTeacherAndClass(int teacherid, int classid) {
        return dao.queryList("from Course as c where c.teacher.id ='" + teacherid + "' and c.TClass.id = '" + classid + "' ");
    }

    
    public boolean update(Course course) {
        try {
            dao.update(course);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
    public boolean delete(Course course) {
        try {
            dao.delete(course);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
