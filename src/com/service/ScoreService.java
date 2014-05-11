package com.service;

import com.model.ScoreView;
import com.orm.Score;
import com.orm.Student;
import java.util.List;

public interface ScoreService {

    //显示所有成绩
    public List<ScoreView> allScoreViews(int courseid);
    
    //所有成绩
    public boolean staticAllScores(int classid, int courseid);

    //统计某门课所有学生的考勤成绩
    public boolean staticAttendScore(int classid, int courseid);

    //查看某门课所有学生的考勤成绩
    public List<Score> allStudentsAttendScore(int classid, int courseid);

    //查看某门课所有学生的作业成绩
    public List<Score> allStudentsTaskScore(int classid, int courseid);
    //更新学生的分数

    public boolean update(Score score);

    //判断学生考勤成绩记录是否存在
    public boolean isAttendScoreExist(Student student);

    //判断学生作业成绩记录是否存在
    public boolean isTaskScoreExist(Student student);

    //根据学生的id获取考勤分数
    public Score loadByStudent(int stuid);

    //统计学生成绩
    public boolean staticTaskrecordScore(int classid, int courseid);
}
