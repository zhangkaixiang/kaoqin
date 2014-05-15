package com.action;

import com.model.MyDateTime;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Course;
import com.orm.TClass;
import com.orm.Sign;
import com.orm.Teacher;
import com.service.CourseService;
import com.service.CourseServiceImpl;
import com.service.TClassService;
import com.service.TClassServiceImpl;
import com.service.SignSettingService;
import com.service.SignSettingServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;

public class SignSettingeAction extends ActionSupport {
    //引入服务层服务
	private static final long serialVersionUID = 1L;
	SignSettingService signSettingService = new SignSettingServiceImpl();
    TClassService classService = new TClassServiceImpl();
    CourseService courseService = new CourseServiceImpl();
    private MyDateTime startTime;
    private MyDateTime endTime;
    private int classid;
    private int courseid;
    private String dayOfWeek;
    private String customDate;
    private int signid;
    private int type;
    //已设置的签到列表
    private List<Sign> signsByWeek;
    private List<Sign> signsByDate;
    //所有签到规则
    private List<Sign> allWeeklySigns;
    private List<Sign> allDateSigns;
    //修改的当前sign
    private Sign curSign;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSignid() {
        return signid;
    }

    public void setSignid(int signid) {
        this.signid = signid;
    }

    public Sign getCurSign() {
        return curSign;
    }

    public void setCurSign(Sign curSign) {
        this.curSign = curSign;
    }

    public List<Sign> getAllDateSigns() {
        return allDateSigns;
    }

    public void setAllDateSigns(List<Sign> allDateSigns) {
        this.allDateSigns = allDateSigns;
    }

    public List<Sign> getAllWeeklySigns() {
        return allWeeklySigns;
    }

    public void setAllWeeklySigns(List<Sign> allWeeklySigns) {
        this.allWeeklySigns = allWeeklySigns;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getCustomDate() {
        return customDate;
    }

    public List<Sign> getSignsByWeek() {
        return signsByWeek;
    }

    public void setSignsByWeek(List<Sign> signsByWeek) {
        this.signsByWeek = signsByWeek;
    }

    public List<Sign> getSignsByDate() {
        return signsByDate;
    }

    public void setSignsByDate(List<Sign> signsdByDate) {
        this.signsByDate = signsdByDate;
    }

    public void setCustomDate(String customDate) {
        this.customDate = customDate;
    }
    //二级联动
    private Map<Integer, List<Course>> courseList;

    public Map<Integer, List<Course>> getCourseList() {
        return courseList;
    }

    public void setCourseList(Map<Integer, List<Course>> courseList) {
        this.courseList = courseList;
    }

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public MyDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(MyDateTime endTime) {
        this.endTime = endTime;
    }

    public MyDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(MyDateTime startTime) {
        this.startTime = startTime;
    }

    //添加时间
    @Override
    public String execute() throws Exception {
        int dayofWeek = 0;
        //设置当前年月日
     /*   getStartTime().setYear();
        getStartTime().setMonth();
        getStartTime().setDay();
         *   SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date start = sf.parse(getStartTime().getFullDateTime());
        Date end = sf.parse(getEndTime().getFullDateTime());
         */
        //格式处理如果是个位数，则前面添加0
        if (startTime.getHour().length() < 2) {
            startTime.setHour("0" + startTime.getHour());
        }
        if (startTime.getMinute().length() < 2) {
            startTime.setMinute("0" + startTime.getMinute());
        }
        if (endTime.getHour().length() < 2) {
            endTime.setHour("0" + endTime.getHour());
        }
        if (endTime.getMinute().length() < 2) {
            endTime.setMinute("0" + endTime.getMinute());
        }
        int intStartHour = Integer.parseInt(startTime.getHour());
        int intEndHour = Integer.parseInt(endTime.getHour());
        if (intStartHour > intEndHour) {
            addActionMessage("开始时间不能大于结束时间！");
            return SUCCESS;
        }


        String start = startTime.getHour() + ":" + startTime.getMinute() + ":" + startTime.getSecond();
        String end = endTime.getHour() + ":" + endTime.getMinute() + ":" + endTime.getSecond();
        //获取周几
        if (ServletActionContext.getRequest().getParameter("dayofWeek") != null) {
            dayofWeek = Integer.parseInt(ServletActionContext.getRequest().getParameter("dayofWeek"));

        }
        //添加时间段
        //获取课程
        Course currentCourse = courseService.load(courseid);
        TClass tClass = currentCourse.getTClass();
        Sign signSetting = new Sign();
        signSetting.setStartTime(start);
        signSetting.setEndTime(end);
        signSetting.setCourse(currentCourse);
        signSetting.setTClass(tClass);
        //设置为有效
        signSetting.setValid(true);
        //若不是周期性，则星期几为0
        signSetting.setDayOfWeek(dayofWeek);
        if (!"".equals(getCustomDate()) && getCustomDate() != null) {
            signSetting.setCustomDate(customDate);
            signSetting.setDayOfWeek(0);
        }
        //若为周期性
        if (dayofWeek != 0) {
            //若不存在相同记录
            if (!signSettingService.weeklyIsExist(signSetting)) {
                if (signSettingService.save(signSetting)) {
                    addActionMessage("添加时间规则成功！");
                    return SUCCESS;
                }
            }
            addActionMessage("该规则已经存在，请不要重复添加！");
            return SUCCESS;
        }
        //若为自定义
        if (!getCustomDate().equals("")) {
            //若不存在相同记录
            if (!signSettingService.customIsExist(signSetting)) {
                if (signSettingService.save(signSetting)) {
                    addActionMessage("添加时间规则成功！");
                    return SUCCESS;
                }
            }
            addActionMessage("该规则已经存在，请不要重复添加！");
            return SUCCESS;
        }
        return ERROR;
    }

    //准备设置日期
    public String prepareSetting() throws Exception {
        Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        Map<Integer, List<Course>> map = new HashMap<Integer, List<Course>>();
        //初始化 前台需要一个课程集合 前台top可以传递一级联动的key
        //给定Map类型
        //利用循环给Map一个key为上级classid,对应的Value为List<Course>
        //获取所有的班级列表，前台可以根据classid来初始化课程列表，即List<Course> findCoursesById(int classid)
        @SuppressWarnings("unchecked")
		List<TClass> classList = (List<TClass>) ActionContext.getContext().getSession().get("currentClass");
        for (TClass tClass : classList) {
            map.put(tClass.getId(), courseService.listCourseByTeacherAndClass(teacher.getId(),tClass.getId()));
        }
        setCourseList(map);
        ActionContext.getContext().getSession().put("courseList", map);
        return SUCCESS;
    }

    //准备查看
    public String prepareView() throws Exception {
        Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        Map<Integer, List<Course>> map = new HashMap<Integer, List<Course>>();
        @SuppressWarnings("unchecked")
		List<TClass> classList = (List<TClass>) ActionContext.getContext().getSession().get("currentClass");
        for (TClass tClass : classList) {
        map.put(tClass.getId(), courseService.listCourseByTeacherAndClass(teacher.getId(),tClass.getId()));
        }
        //查看教师所教授的所有课程的所有签到规则
        Teacher me = (Teacher) ActionContext.getContext().getSession().get("teacher");
        List<Sign> weekSigns = new ArrayList<Sign>();
        List<Sign> customDateSigns = new ArrayList<Sign>();


        @SuppressWarnings("rawtypes")
		Iterator it = me.getCourses().iterator();
        while (it.hasNext()) {
        	Course course = (Course) it.next();  
            weekSigns.addAll(signSettingService.allSignsOnlyByWeek(course.getId()));
            customDateSigns.addAll(signSettingService.allSignsOnlyByDate(course.getId()));
  
        }


        //怎么判断是否是失效规则
        if (weekSigns != null) {
            setSignsByWeek(weekSigns);
        }
        if (customDateSigns != null) {
            setSignsByDate(customDateSigns);
        }
        ActionContext.getContext().getSession().put("courseList", map);
        return "prepareView";
    }
    //查看

    public String viewSignSetting() throws Exception {
        @SuppressWarnings("unused")
		int vcourseid = 0;
        if (ServletActionContext.getRequest().getParameter("courseid") != null) {
            vcourseid = Integer.parseInt(ServletActionContext.getRequest().getParameter("courseid"));
        }
        return "prepareView";
    }
    //搜索

    public String searchSignSetting() throws Exception {
        //classid=1&courseid=1&dayOfWeek=1
        Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");

        int classID = 0;
        int sdayOfWeek = 0;
        String custDate = "";


        if (ServletActionContext.getRequest().getParameter("classid") != null) {
            classID = Integer.parseInt(ServletActionContext.getRequest().getParameter("classid"));
        }

        if (ServletActionContext.getRequest().getParameter("dayOfWeek") != null) {
            sdayOfWeek = Integer.parseInt(ServletActionContext.getRequest().getParameter("dayOfWeek"));
        }
        if (ServletActionContext.getRequest().getParameter("customDate") != null) {
            custDate = ServletActionContext.getRequest().getParameter("customDate");
        }
        List<Sign> tempWSigns = new ArrayList<Sign>();
        List<Sign> tempDSigns = new ArrayList<Sign>();
        //获取指定星期几特定班级的所以签到列表，从中去除掉不是当前教师的记录
        List<Sign> wSigns = signSettingService.findSignSettingByWeekClass(sdayOfWeek, classID);
        List<Sign> dSigns = signSettingService.allSignsByClassCustomDate(customDate, classID);

        TClass tClass = classService.load(classID);

        for (Sign sign : wSigns) {
            //如果当前规则所属课程的所属教师的id相等，则即为搜索结果！
            if (sign.getCourse().getTeacher().getId().equals(teacher.getId())) {
                tempWSigns.add(sign);
            }
        }
        for (Sign sign : dSigns) {
            if (sign.getCourse().getTeacher().getId().equals(teacher.getId())) {
                tempDSigns.add(sign);
            }
        }



        if (wSigns.size() > 0) {
            addActionMessage("您所搜索的是：" + tClass.getClassName() + "班" + "   星期" + sdayOfWeek + "  的所有需要签到的课程");
            setAllWeeklySigns(tempWSigns);
        }
        if (dSigns.size() > 0) {
            addActionMessage("您所搜索的是：" + tClass.getClassName() + "班   " + custDate + "  的所有需要签到的课程");
            setAllDateSigns(tempDSigns);
        }
        if (wSigns.size() < 1 && dSigns.size() < 1) {
            if (sdayOfWeek == 0) {
                addActionMessage("您所搜索的是：" + tClass.getClassName() + "班   " + custDate + "  的所有需要签到的课程");
            }
            if (custDate == "") {
                addActionMessage("您所搜索的是：" + tClass.getClassName() + "班" + "   星期" + sdayOfWeek + "  的所有需要签到的课程");
            }
            addActionMessage(":(没有找到符合记录的条件。");
        }
        return "prepareView";
    }

    //准备修改
    public String prepareModify() throws Exception {
        int sid = 0;
        int tp = 1;
        if (ServletActionContext.getRequest().getParameter("signid") != null) {
            sid = Integer.parseInt(ServletActionContext.getRequest().getParameter("signid"));
        }
        if (ServletActionContext.getRequest().getParameter("type") != null) {
            tp = Integer.parseInt(ServletActionContext.getRequest().getParameter("type"));
        }
        Sign sign = signSettingService.load(sid);

        //检查时判断一下该签到规则是否已经存在记录了。如果存在，则不允许修改。
        if (sign.getSignrecords().size() > 0) {
            addActionMessage("该签到规则已经有记录产生，不允许修改。");
            return "prepareView";
        }
        setCurSign(sign);
        setSignid(sid);
        setType(tp);
        return "preparModify";
    }

    //修改签到规则
    public String modify() throws Exception {

        //获取id
        int sid = 0;

        if (ServletActionContext.getRequest().getParameter("signid") != null) {
            sid = Integer.parseInt(ServletActionContext.getRequest().getParameter("signid"));
        }
        //获取对象
        Sign currentSign = signSettingService.load(sid);
        if (startTime.getHour().length() < 2) {
            startTime.setHour("0" + startTime.getHour());
        }
        if (startTime.getMinute().length() < 2) {
            startTime.setMinute("0" + startTime.getMinute());
        }
        if (endTime.getHour().length() < 2) {
            endTime.setHour("0" + endTime.getHour());
        }
        if (endTime.getMinute().length() < 2) {
            endTime.setMinute("0" + endTime.getMinute());
        }
        int intStartHour = Integer.parseInt(startTime.getHour());
        int intEndHour = Integer.parseInt(endTime.getHour());
        if (intStartHour > intEndHour) {
            addActionMessage("开始时间不能大于结束时间！");
            return "preparModify";
        }
        String start = startTime.getHour() + ":" + startTime.getMinute() + ":" + startTime.getSecond();
        String end = endTime.getHour() + ":" + endTime.getMinute() + ":" + endTime.getSecond();
        //修改
        if (currentSign != null) {
            //修改开始，结束时间，判断是否是自定义或者周期，修改（所属课程 修改所属班级 会引起级联操作吗？不会）
            currentSign.setStartTime(start);
            currentSign.setEndTime(end);
            //设置周期，或者日期
            //修改周期星期几和自定义日期时请注意，如果已存在这些签到记录的话，会引起级联修改。
            //**问题描述，对于已有签到记录的签到规则来说，是否允许修改其时间呢**//
            /*最后想到，只有把这条已有记录的规则当做失效规则来对待，将信息封存至数据库。数据库需添加一个字段valid*/
            if (currentSign.getCustomDate() == null) {
                currentSign.setDayOfWeek(Integer.parseInt(getDayOfWeek()));
            }
            if (currentSign.getCustomDate() != null) {
                currentSign.setCustomDate(getCustomDate());
            }
            if (signSettingService.updateSign(currentSign)) {
                addActionMessage("修改成功");
                return "preparModify";
            }
        }
        return ERROR;
    }

    //准备删除
    public String prepareDelete() throws Exception {
        //查出规则，判断是不是有记录，如果存在记录，则不允许删除，提示是否使之失效
        int sid = 0;
        if (ServletActionContext.getRequest().getParameter("signid") != null) {
            sid = Integer.parseInt(ServletActionContext.getRequest().getParameter("signid"));
        }
        Sign s = signSettingService.load(sid);
        if (s.getSignrecords().size() > 0) {
            setSignid(sid);
            return "invalid";
        }
        setSignid(sid);
        return "delete";
    }

    //删除
    public String deleteSign() throws Exception {
        int sid = 0;
        if (ServletActionContext.getRequest().getParameter("signid") != null) {
            sid = Integer.parseInt(ServletActionContext.getRequest().getParameter("signid"));
        }
        Sign s = signSettingService.load(sid);
        if (s != null) {
            if (signSettingService.DeleteSignSetting(s)) {
                addActionMessage("操作成功，该规则删除成功！");
                return "prepareView";
            }
        }
        return ERROR;


    }

    //使签到规则失效
    public String invalidSign() throws Exception {
        int sid = 0;
        if (ServletActionContext.getRequest().getParameter("signid") != null) {
            sid = Integer.parseInt(ServletActionContext.getRequest().getParameter("signid"));
        }
        Sign s = signSettingService.load(sid);
        if (s != null) {
            signSettingService.invalidSign(s);
            addActionMessage("操作成功，该记录已失效！");
            return "prepareView";
        }
        return ERROR;
    }
}
