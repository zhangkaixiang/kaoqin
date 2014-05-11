package com.service;

import com.dao.ScoreDao;
import com.dao.ScoreDaoImpl;
import com.dao.SignRecordDao;
import com.dao.SignRecordDaoImpl;
import com.dao.SignSettingDao;
import com.dao.SignSettingDaoImpl;
import com.dao.StudentDao;
import com.dao.StudentDaoImpl;
import com.dao.TaskDao;
import com.dao.TaskDaoImpl;
import com.dao.TaskrecordDao;
import com.dao.TaskrecordDaoImpl;
import com.model.ScoreView;
import com.orm.Course;
import com.orm.Score;
import com.orm.Sign;
import com.orm.Signrecord;
import com.orm.Student;
import com.orm.Task;
import com.orm.Taskrecord;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ScoreServiceImpl implements ScoreService {

    ScoreDao scoreDao = new ScoreDaoImpl();
    SignSettingDao signSettingDao = new SignSettingDaoImpl();
    SignRecordDao signRecordDao = new SignRecordDaoImpl();
    StudentDao studentDao = new StudentDaoImpl();
    CourseService courseService = new CourseServiceImpl();
    TaskDao taskDao = new TaskDaoImpl();
    TaskrecordDao taskrecordDao = new TaskrecordDaoImpl();
    StudentService studentService = new StudentServiceImpl();

    
    public boolean staticAttendScore(int classid, int courseid) {

        Course course = courseService.load(courseid);
        int totalAttendNum = course.getAttendNum();
        List<Student> students = studentDao.queryList("from Student as s where s.TClass.id = '" + classid + "' ");

        //获取选取的某个班的某门课每个学生的成绩
        List<Sign> signs = signSettingDao.query("from Sign as s where s.course.id = '" + courseid + "' and s.TClass.id = '" + classid + "' ");
        if (!signs.isEmpty()) {
            //遍历所有学生，统计成绩 
            for (Student student : students) {
                int attendCount = 0;
                double totalscore = 0.00;
                //获取其下的所有签到规则，多每个签到规则的记录进行遍历，统计出出勤的次数。
                for (Sign sign : signs) {
                    //已经签到的记录
                    List<Signrecord> signedRecords = signRecordDao.queryList("from Signrecord as sr where sr.sign.id= '" + sign.getId() + "' and sr.lost = false and sr.student.id = '" + student.getId() + "'  ");
                    //未签到的记录
                    //   List<Signrecord> unsignedRecords = signRecordDao.queryList("from Signrecord as sr where sr.sign.id= '" + sign.getId() + "' and sr.lost = true and sr.student.id = '" + student.getId() + "'  ");
                    //如果有记录，则加上
                    if (signedRecords.size() > 0) {
                        attendCount = attendCount + signedRecords.size();
                    }
                }
                //计算学生的成绩，每次计算会更新，所以应该用saveorupdate
                totalscore = (95.0 / 2.0 / totalAttendNum) * attendCount;
                //保留两位
                DecimalFormat dcmFmt = new DecimalFormat("0.00");
                double db = totalscore;
                totalscore = Double.parseDouble(dcmFmt.format(db));


                //判断该学生的成绩是否已经存在，如果存在只需更新就行了
                if (isAttendScoreExist(student)) {
                    Score score = loadByStudent(student.getId());
                    score.setTscore(totalscore);
                    update(score);
                }
                //如果不存在，则直接更新。
                Score score = new Score();
                score.setStudent(student);
                score.setStype(0);
                score.setCourse(course);
                score.setTscore(totalscore);
                scoreDao.save(score);
            }
        }
        return true;

    }

    
    public boolean update(Score score) {
        scoreDao.update(score);
        return true;
    }

    
    public boolean isAttendScoreExist(Student student) {
        if (!scoreDao.queryList("from Score as sc  where sc.student.id = '" + student.getId() + "' and sc.stype = '0'  ").isEmpty()) {
            return true;
        }
        return false;
    }

    
    public Score loadByStudent(int stuid) {
        Score score = scoreDao.queryList("from Score as sc where sc.student.id = '" + stuid + "' and sc.stype = 0 ").get(0);
        return score;
    }

    
    public List<Score> allStudentsAttendScore(int classid, int courseid) {
        List<Student> students = studentDao.queryList("from Student as s where s.TClass.id = '" + classid + "'  ");
        List<Score> results = new ArrayList<Score>();
        for (Student student : students) {
            List<Score> scores = scoreDao.queryList("from Score as sc  where sc.student.id = '" + student.getId() + "' and sc.stype = 0  and sc.course.id = '" + courseid + "'  ");
            if (!scores.isEmpty()) {
                results.add(scores.get(0));
            }
        }
        return results;
    }
    //统计作业成绩

    
    public boolean staticTaskrecordScore(int classid, int courseid) {

        Course course = courseService.load(courseid);
        int totalTaskNum = course.getTaskNum();
        List<Student> students = studentDao.queryList("from Student as s where s.TClass.id = '" + classid + "' ");

        //获取选取的某个班的某门课每个学生的成绩
        List<Task> tasks = taskDao.queryList("from Task as s where s.course.id = '" + courseid + "' ");
        if (!tasks.isEmpty()) {
            //遍历所有学生，统计成绩 
            for (Student student : students) {
                int handInCount = 0;
                double totalscore = 0.00;
                //获取其下的所有签到规则，多每个签到规则的记录进行遍历，统计出出勤的次数。
                for (Task task : tasks) {
                    //已经批改作业到的记录
                    List<Taskrecord> taskRecords = taskrecordDao.queryList("from Taskrecord as tr where tr.task.id= '" + task.getId() + "' and tr.correct = true and tr.student.id = '" + student.getId() + "'  ");
                    //未签到的记录
                    //   List<Signrecord> unsignedRecords = signRecordDao.queryList("from Signrecord as sr where sr.sign.id= '" + sign.getId() + "' and sr.lost = true and sr.student.id = '" + student.getId() + "'  ");
                    //如果有记录，则加上
                    if (taskRecords.size() > 0) {
                        handInCount = handInCount + taskRecords.size();
                    }
                }
                //计算学生的成绩，每次计算会更新，所以应该用saveorupdate
                totalscore = (95.0 / 2.0 / totalTaskNum) * handInCount;
                //保留两位
                DecimalFormat dcmFmt = new DecimalFormat("0.00");
                double db = totalscore;
                totalscore = Double.parseDouble(dcmFmt.format(db));


                //判断该学生的成绩是否已经存在，如果存在只需更新就行了
                if (isTaskScoreExist(student)) {
                    Score score = loadByStudent(student.getId());
                    score.setTscore(totalscore);
                    update(score);
                    return true;
                }
                //如果不存在，则直接更新。
                Score score = new Score();
                score.setStudent(student);
                score.setStype(1);
                score.setCourse(course);
                score.setTscore(totalscore);
                scoreDao.save(score);
            }
        }
        return true;

    }

    
    public boolean isTaskScoreExist(Student student) {
        if (!scoreDao.queryList("from Score as sc  where sc.student.id = '" + student.getId() + "' and sc.stype = '1'  ").isEmpty()) {
            return true;
        }
        return false;
    }

    
    public List<Score> allStudentsTaskScore(int classid, int courseid) {
        List<Student> students = studentDao.queryList("from Student as s where s.TClass.id = '" + classid + "'  ");
        List<Score> results = new ArrayList<Score>();
        for (Student student : students) {
            List<Score> scores = scoreDao.queryList("from Score as sc  where sc.student.id = '" + student.getId() + "' and sc.stype = 1  and sc.course.id = '" + courseid + "'  ");
            if (!scores.isEmpty()) {
                results.add(scores.get(0));
            }
        }
        return results;
    }

    
    public boolean staticAllScores(int classid, int courseid) {
        if (staticAttendScore(classid, courseid) && staticTaskrecordScore(classid, courseid)) {
            return true;
        }
        return false;
    }

    
    @SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public List<ScoreView> allScoreViews(int courseid) {
        List list = new ArrayList();
        List viewList = new ArrayList();
        //如果一个集合正在iterator进行输出的时候对他进行操作，添加删除等，就会出现异常。
        System.out.print("select stuid,courseid, sum(tscore) from score  group by stuid where courseid ='" + courseid + "'");
        List viewLists = scoreDao.sqlquery("select stuid,courseid,SUM(tscore) from score  where courseid ='" + courseid + "' Group by stuid");
        Iterator it = viewLists.iterator();
        //循环迭代
        while (it.hasNext()) {
            Student student = null;
            Course course = null;
            double tscore = 0.0;
            //每一对象都是一个object数组 ，每个数组的索引为搜索的列的个数-1
            Object[] results = (Object[]) it.next();
            for (int i = 0; i < results.length; i++) {
                if (i == 0) {
                    student = studentService.loadStudent(Integer.parseInt(results[i].toString()));
                }
                if (i == 1) {
                    course = courseService.load(Integer.parseInt(results[i].toString()));
                }
                if (i == 2) {
                    tscore = Double.parseDouble(results[i].toString());
                }
            }
            ScoreView sv = new ScoreView();
            sv.setStudent(student);
            sv.setCourse(course);
            sv.setTtscore(tscore);
            list.add(sv);
        }
        return list;
    }
}
