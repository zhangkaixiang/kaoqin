/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timer;

import com.orm.Course;
import com.orm.Sign;
import com.orm.Signrecord;
import com.orm.Student;
import com.service.CourseService;
import com.service.CourseServiceImpl;
import com.service.SignRecordService;
import com.service.SignRecordServiceImpl;
import com.service.SignSettingService;
import com.service.SignSettingServiceImpl;
import com.service.StudentService;
import com.service.StudentServiceImpl;
import com.service.TClassService;
import com.service.TClassServiceImpl;
import com.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import javax.servlet.ServletContext;

public class MyTask extends TimerTask {
    //引入服务

    SignRecordService signRecordService = new SignRecordServiceImpl();
    TClassService tClassService = new TClassServiceImpl();
    StudentService studentService = new StudentServiceImpl();
    CourseService courseService = new CourseServiceImpl();
    SignSettingService signSettingService = new SignSettingServiceImpl();
    //private static final int C_SCHEDULE_HOUR = 0;
    private static boolean isRunning = false;
    private ServletContext context = null;

    public MyTask(ServletContext context) {
        this.context = context;
    }

    //搜索，判断将数据库里的学生缺课信息都统计到数据库中
    //某个老师教的某个班所有学生，每个人缺了哪些课
    @Override
    public void run() {
        //如果当前线程不在运行，防止同一时刻执行同一个线程
        if (!isRunning) {
            //如果两个线程同时运行，则会出现异常。
            //表面当前线程正在运行
            isRunning = true;
            //将对数据库中每个学生的今天每门课的缺课记录进行记录。遍历获取所有课程
            List<Student> students = studentService.allStudents();
            for (Student student : students) {
                int studentid = student.getId();
                int classid = student.getTClass().getId();
                int dayofweek = DateUtil.getDayOfWeek();
                String todayDateString = DateUtil.getTodayYyyyMMdd();
                List<Course> courses = courseService.listCourseByClassId(classid);
                for (Course course : courses) {
                    //课程id 
                    int courseid = course.getId();
                    //根据学生id获取所有签到规则 和今天的星期几或者今天的日期
                    //根据今天是星期几查询今天所有课程的签到规则，参数pram1 课程id  param2 今天的星期几
                    List<Sign> signs = signSettingService.allCourseByWeeklyAndCourseId(dayofweek,courseid);
                    //根据今天的日期查询所有课程的签到规则 参数param1 今天日期， param2课程id
                    List<Sign> csigns = signSettingService.allCourseByCustomDateAndCourseId(todayDateString, courseid);
                    //《！--BUG修复，仅对截止到当前时间的签到的签到规则进行统计，而不是统计今天所有课程的所有时间的签到规则--》//
                    signs.addAll(csigns);
                  
                    //对该生所有的签到规则进行遍历，看是否有缺课
                    for (Sign sign : signs) {
                        //判断缺课逻辑.....
                        //获取现在时间
                        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                        String todayDate = sf.format(new Date());
                        int endhour = Integer.parseInt(sign.getEndTime().substring(0, 2));
                        int endminute = Integer.parseInt(sign.getEndTime().substring(3, 5));
                        Calendar now = Calendar.getInstance();
                        Calendar endCalendar = Calendar.getInstance();
                        endCalendar.set(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DATE), endhour, endminute, 0);
                        long nowMillis = now.getTimeInMillis();
                        long endMillis = endCalendar.getTimeInMillis();
                        //判断比现在时间前面的签到规则，即判断是否缺课

                        if (endMillis < nowMillis) {
                            //若为自定义日期规则，若今日的日期和规则日期不相等，跳出
                            if (sign.getCustomDate() != null && !sign.getCustomDate().equals(todayDate)) {
                                break;
                            }

                            //判断今天的所有人
                            if (!signRecordService.isExist(sign.getId(), todayDate, studentid)) {
                                Signrecord record = new Signrecord();
                                record.setSign(sign);
                                record.setStudent(student);
                                record.setSigndate(todayDate);
                                record.setLost(true);
                                signRecordService.save(record);
                                System.out.println(student.getTClass().getClassName() + "的 学生" + student.getName() + " 缺课：" + sign.getCourse().getName() + "上课时间：" + sign.getStartTime() + "至" + sign.getEndTime());
                            }
                        }
                    }
                }
            }
            //表明运行结束
           isRunning = false;
        } else {
            context.log("上一次任务执行还未结束");
        }
    }
}
