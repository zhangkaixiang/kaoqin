 package com.action;

import com.model.ReportView;
import com.model.StaticNum;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Course;
import com.orm.Sign;
import com.orm.Signrecord;
import com.orm.Student;
import com.orm.TClass;
import com.orm.Teacher;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class TeacherIndexAction extends ActionSupport {
    //引入服务
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CourseService courseService = new CourseServiceImpl();
    SignRecordService signRecordService = new SignRecordServiceImpl();
    TClassService tClassService = new TClassServiceImpl();
    StudentService studentService = new StudentServiceImpl();
    SignSettingService signSettingService = new SignSettingServiceImpl();
    //班级签到记录
    List<Signrecord> records;
    //签到规则列表
    List<Sign> signs;
    //当前签到规则
    Sign curSign;
    //当前统计
    StaticNum staticNum;
private String msg;
private int stunum;
int signStudent;
int unsignStudent;
private int getSignid;


	public int getGetSignid() {
	return getSignid;
}

public void setGetSignid(int getSignid) {
	this.getSignid = getSignid;
}

	public int getSignStudent() {
	return signStudent;
}

public void setSignStudent(int signStudent) {
	this.signStudent = signStudent;
}

public int getUnsignStudent() {
	return unsignStudent;
}

public void setUnsignStudent(int unsignStudent) {
	this.unsignStudent = unsignStudent;
}

	public int getStunum() {
	return stunum;
}

public void setStunum(int stunum) {
	this.stunum = stunum;
}

	public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

	public StaticNum getStaticNum() {
        return staticNum;
    }

    public void setStaticNum(StaticNum staticNum) {
        this.staticNum = staticNum;
    }

    public Sign getCurSign() {
        return curSign;
    }

    public void setCurSign(Sign curSign) {
        this.curSign = curSign;
    }

    public List<Sign> getSigns() {
        return signs;
    }

    public void setSigns(List<Sign> signs) {
        this.signs = signs;
    }

    public List<Signrecord> getRecords() {
        return records;
    }

    public void setRecords(List<Signrecord> records) {
        this.records = records;
    }

    //显示今天上课的班级，点击进去看截止目前为止，该班级的签到情况，学生签到的ip,签到时间。
    @Override
    public String execute() throws Exception {
        Teacher me = (Teacher) ActionContext.getContext().getSession().get("teacher");
        //获取今天该教师今天教授的课程(可能是好几个班)所有的签到规则
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = sf.format(new Date());
        //星期几，Calendar是西方默认以周日为星期1来计算的。星期六为最后一天
        //星球六是第七天，星期天是第一天。应该是通过枚举来实现的
        int dayOfWeek = DateUtil.getDayOfWeek();
        //初始化班级和课程
        Map<Integer, List<Course>> map = new HashMap<Integer, List<Course>>();
        @SuppressWarnings("unchecked")
		List<TClass> classList = (List<TClass>) ActionContext.getContext().getSession().get("currentClass");
        for (TClass tClass : classList) {
            map.put(tClass.getId(), courseService.listCourseByTeacherAndClass(me.getId(), tClass.getId()));
        }
        ActionContext.getContext().getSession().put("courseList", map);
        List<Sign> dayList = new ArrayList<Sign>();
        List<Sign> dateList = new ArrayList<Sign>();
        //遍历该教师所有课程，将今天的课程取出，每门课今天的签到列表，加入到dayList里
        @SuppressWarnings("rawtypes")
		Iterator it = me.getCourses().iterator();
        while (it.hasNext()) {
            Course course = (Course) it.next();
            List<Sign> weekResult = signSettingService.findSignSettingByWeekCourse(dayOfWeek, course.getId());
            List<Sign> dateResult = signSettingService.findSignSettingByDate(todayDate, course.getId());
            dayList.addAll(weekResult);
            dateList.addAll(dateResult);

        }
        List<Sign> allSigns = new ArrayList<Sign>();
        allSigns.addAll(dayList);
        allSigns.addAll(dateList);
        setSigns(allSigns);
        ActionContext.getContext().getSession().put("signs", allSigns);
        return SUCCESS;
    }

    public String viewSignInfo() throws Exception {

        int signid = 0;
        List<Signrecord> recordList = new ArrayList<Signrecord>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = sf.format(new Date());
        if (ServletActionContext.getRequest().getParameter("signid") != null) {
            signid = Integer.parseInt(ServletActionContext.getRequest().getParameter("signid"));
            setGetSignid(signid);
        }
        Sign s = signSettingService.load(signid);
        int classid = signSettingService.load(signid).getCourse().getTClass().getId();
        List<Student> students = studentService.allStudentsByClass(classid);
        for (Student student : students) {
            recordList.addAll(signRecordService.allSignRecordsByStudent(student.getId(), todayDate, signid));
        }
        //统计班级的签到人数
        int signedStudent = signRecordService.countTodaySignedStudent(s);
        int unsignedStudent = signRecordService.countTodayUnSignedStudent(s);
        //应先实例化
        StaticNum num = new StaticNum();
        num.setSignedStudentNum(signedStudent);
        num.setUnSignedStudentNum(unsignedStudent);
        setStaticNum(num);
        setRecords(recordList);
        setCurSign(s);
        Sign stusign=this.getCurSign();
        this.setStunum(stusign.getCourse().getTClass().getStuNum());
        return SUCCESS;
    }
    
    public String viewSignDraw() throws Exception {
        int signid = 0;
        List<Signrecord> recordList = new ArrayList<Signrecord>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = sf.format(new Date());
        if (ServletActionContext.getRequest().getParameter("signid") != null) {
            signid = Integer.parseInt(ServletActionContext.getRequest().getParameter("signid"));
        }
        Sign s = signSettingService.load(signid);
        int classid = signSettingService.load(signid).getCourse().getTClass().getId();
        List<Student> students = studentService.allStudentsByClass(classid);
        for (Student student : students) {
            recordList.addAll(signRecordService.allSignRecordsByStudent(student.getId(), todayDate, signid));
        }
        //统计班级的签到人数
         signStudent = signRecordService.countTodaySignedStudent(s);
         unsignStudent = signRecordService.countTodayUnSignedStudent(s);
        //应先实例化   
         HttpServletRequest request = ServletActionContext.getRequest();
         ServletContext servletContext = ServletActionContext.getServletContext();
         setCurSign(s);
         ReportView report=new ReportView();
         report.setSign(signStudent);
         report.setUnsign(unsignStudent);
         request.getSession().setAttribute("report", report);
         request.getSession().setAttribute("sign", recordList);
         Sign stusign=this.getCurSign();
         setStunum(stusign.getCourse().getTClass().getStuNum());
        return "draw";
    }
}
