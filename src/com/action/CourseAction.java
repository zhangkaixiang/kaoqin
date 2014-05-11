package com.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Course;
import com.orm.TClass;
import com.orm.Teacher;
import com.service.CourseService;
import com.service.CourseServiceImpl;
import com.service.TClassService;
import com.service.TClassServiceImpl;
import com.service.TeacherService;
import com.service.TeacherServiceImpl;
import java.util.List;
import java.util.Set;
import org.apache.struts2.ServletActionContext;

public class CourseAction extends ActionSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//引入服务
    CourseService courseService = new CourseServiceImpl();
    TClassService classService = new TClassServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    //变量
    //所有课程
    private List<Course> myCourses;
    //所有班级
    private List<TClass> myClasses;
    //课程
    private Course course;
    //班级id
    private int classid;

    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<TClass> getMyClasses() {
        return myClasses;
    }

    public void setMyClasses(List<TClass> myClasses) {
        this.myClasses = myClasses;
    }

    public List<Course> getMyCourses() {
        return myCourses;
    }

    public void setMyCourses(List<Course> myCourses) {
        this.myCourses = myCourses;
    }

    //默认查看课程
    @Override
    public String execute() throws Exception {
        //列出该教师所教的所有课程
        Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        List<Course> allCourses = courseService.listCourseByTeacherId(teacher.getId());
        setMyCourses(allCourses);
        ActionContext.getContext().getSession().put("myClasses", myClasses);
        return SUCCESS;
    }

    //准备添加
    public String prepareAdd() throws Exception {
        //列出班级
        List<TClass> classes = classService.allSClass();
        setMyClasses(classes);
        return "prepareAdd";
    }
    //添加课程

    public String addCourse() throws Exception {
        //获取传来的班级id和教师id，查出班级，获取添加的课程名，和备注
        //添加。如果该教师已存在同名课程，提示已存在，不存在则添加。
        //获取教师和班级
    	
        TClass tClass = classService.load(getClassid());
        Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
        teacher = teacherService.loadTeacher(teacher.getId());
        @SuppressWarnings("unchecked")
		Set<Course> courseList = teacher.getCourses();
        for (Course cs : courseList) {
            //一个班级，一个老师教的课程名是唯一的。 但也有可能一个老师教多个班，课程名是一样的。所以只检查一个班级的某门课否有重名
            if (cs.getName().equals(getCourse().getName()) && cs.getTClass().getId().equals(tClass.getId())) {
                addActionMessage("已存在同名课程，添加失败！");
                return "prepareAdd";
            }
        }

        Course c = getCourse();
        System.out.println(c.getRemark()+"...."+c.getName()+"....fdsafdasfdsa");
        
        c.setTClass(tClass);
        c.setTeacher(teacher);
        c.setAttendNum(c.getAttendNum());
        c.setTaskNum(c.getTaskNum());
        if (courseService.save(c)) {
            ActionContext.getContext().getSession().put("currentClass", classService.allSClass());
            addActionMessage("添加课程成功！");
            prepareAdd();
            return "prepareAdd";
        }
        return ERROR;
    }
    //查看单个课程

    public String view() throws Exception {
        return "";
    }
    //准备修改

    public String prepareModify() throws Exception {
        int courseid = -1;
        if (ServletActionContext.getRequest().getParameter("courseid") != null) {
            courseid = Integer.parseInt(ServletActionContext.getRequest().getParameter("courseid"));
        }
        if (courseService.load(courseid) != null) {
            setCourse(courseService.load(courseid));
        }
        return "prepareModify";
    }
    //修改

    public String modify() throws Exception {
        int cid = -1;
        if (ServletActionContext.getRequest().getParameter("courseid") != null) {
            cid = Integer.parseInt(ServletActionContext.getRequest().getParameter("courseid"));
        }
        Course old = courseService.load(cid);
        old.setName(getCourse().getName());
        old.setAttendNum(getCourse().getAttendNum());
        old.setRemark(getCourse().getRemark());
        old.setTaskNum(getCourse().getTaskNum());
        if (courseService.update(old)) {
            addActionMessage("更新成功！");
            return "prepareModify";
        }
        addActionMessage("更新失败");
        return "prepareModify";
    }
    //删除

    public String delete() throws Exception {
        int csid = -1;
        if (ServletActionContext.getRequest().getParameter("courseid") != null) {
            csid = Integer.parseInt(ServletActionContext.getRequest().getParameter("courseid"));
        }
        Course currentCourse = courseService.load(csid);
        if (courseService.delete(currentCourse)) {
            addActionMessage("删除成功！");
            return "delSuccess";
        }
        addActionMessage("删除失败");
        return "delSuccess";
    }
}
