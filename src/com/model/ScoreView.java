package com.model;

import com.orm.Course;
import com.orm.Student;

public class ScoreView {
    
    //学生
    private Student student;
    //课程
    private Course course;
    //总分数
    private double ttscore;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getTtscore() {
        return ttscore;
    }

    public void setTtscore(double ttscore) {
        this.ttscore = ttscore;
    }
    
}
