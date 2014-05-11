package com.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Course;
import com.orm.Student;
import com.orm.Task;
import com.orm.Taskrecord;
import com.orm.Teacher;
import com.service.CourseService;
import com.service.CourseServiceImpl;
import com.service.TaskService;
import com.service.TaskServiceImpl;
import com.service.TaskrecordService;
import com.service.TaskrecordServiceImpl;
import com.service.TeacherService;
import com.service.TeacherServiceImpl;
import java.util.List;
import org.apache.struts2.ServletActionContext;

public class TaskAction extends ActionSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//引入服务
    TaskService taskService = new TaskServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    CourseService courseService = new CourseServiceImpl();
    TaskrecordService taskrecordService = new TaskrecordServiceImpl();
    //变量
    private Task task;
    private int courseid;
    private List<Task> allTask;
    private String content;
    private List<Course> courses;
    private List<Taskrecord> taskrecords;
    private String message="提示：请点击查看按钮查询作业信息！";
    public List<Taskrecord> getTaskrecords() {
        return taskrecords;
    }

    public void setTaskrecords(List<Taskrecord> taskrecords) {
        this.taskrecords = taskrecords;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Task> getAllTask() {
        return allTask;
    }

    public void setAllTask(List<Task> allTask) {
        this.allTask = allTask;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	//默认显示
    @Override
    public String execute() throws Exception {
        if (ActionContext.getContext().getSession().get("teacher") != null) {
            Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
            List<Task> tasks = taskService.allTasks(teacher.getId());
            if (tasks != null) {
                setAllTask(tasks);
            }
            return SUCCESS;
        }
        return ERROR;
    }

    //准备添加作业要求
    public String prepareAdd() throws Exception {
        return "prepareAdd";
    }

    public String addTask() throws Exception {

        if (ActionContext.getContext().getSession().get("teacher") != null) {
            Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
            Course course = courseService.load(getCourseid());
            Task tsk = getTask();
            tsk.setTeacher(teacher);
            tsk.setCourse(course);
            if (taskService.addTask(tsk)) {
                addActionMessage("添加作业任务成功！");
                return "prepareAdd";
            }
        }
        return ERROR;
    }
    //搜索作业的任务

    public String searchTask() throws Exception {
        //教师搜索
        if (ActionContext.getContext().getSession().get("teacher") != null) {
            Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
            List<Task> tasks = taskService.allTasksByCourse(getCourseid(), teacher.getId());
            if (tasks != null) {
            	if(!tasks.isEmpty()){
                setAllTask(tasks);
            	}else{
            		this.setMessage("提示：该课程暂时还没有发布作业！");
            	}
            }
            return "viewTaskrecord";
        }
        return ERROR;
    }

    public String search() throws Exception {

        //教师搜索
        if (ActionContext.getContext().getSession().get("teacher") != null) {
            Teacher teacher = (Teacher) ActionContext.getContext().getSession().get("teacher");
            List<Task> tasks = taskService.allTasksByCourse(getCourseid(), teacher.getId());
            if (tasks != null) {
            	if(!tasks.isEmpty()){
                setAllTask(tasks);
            	}else{
                	this.setMessage("提示：该课程暂时还没有发布作业！");
                }
            }
            return SUCCESS;
        }
        //学生搜索
        if (ActionContext.getContext().getSession().get("student") != null) {
            Course curCourse = courseService.load(courseid);
            List<Task> tasks = taskService.allTasksByCourse(courseid, curCourse.getTeacher().getId());
            if (tasks != null) {
            	if(!tasks.isEmpty()){
                setAllTask(tasks);
                ServletActionContext.getRequest().setAttribute("courseid", getCourseid());
            	}else{
            	this.setMessage("提示：该课程暂时还没有发布作业！");
            }
            		
            }
            return "stuAllTask";
        }


        return ERROR;
    }

    public String viewTask() throws Exception {
        int taskid = 0;
        if (ServletActionContext.getRequest().getParameter("taskid") != null) {
            taskid = Integer.parseInt(ServletActionContext.getRequest().getParameter("taskid"));
            Task tsk = taskService.load(taskid);
            if (tsk != null) {
                setTask(tsk);
                return "view";
            }
        }
        return ERROR;
    }

    public String stuAllTask() throws Exception {
        /*
         * 逻辑。此为初始化信息页面，列出该学生所属班级的所有课程。学生根据课程搜索到任务列表。
         */
        if (ActionContext.getContext().getSession().get("student") != null) {
            Student student = (Student) ActionContext.getContext().getSession().get("student");
            List<Course> cs = courseService.listCourseByClassId(student.getTClass().getId());
            if (cs.size() > 0) {
                setCourses(cs);
                ActionContext.getContext().getSession().put("courses", cs);
                return "stuAllTask";
            }
            addActionMessage("没有搜索到作业！");
            return "stuAllTask";
        }
        return ERROR;
    }

    public String prepareHandInTask() throws Exception {
        return "prepareHandIn";
    }

    public String prepareExame() throws Exception {

        return "viewTaskrecord";
    }

    public String searchTaskrecord() throws Exception {

        @SuppressWarnings("unused")
		int taskid = 0;
        if (ServletActionContext.getRequest().getParameter("taskid") != null) {
            taskid = Integer.parseInt(ServletActionContext.getRequest().getParameter("taskid"));
        }

        if (ActionContext.getContext().getSession().get("teacher") != null) {

            List<Taskrecord> results = taskrecordService.allTaskrecordsByTask(taskid);

            if (results != null) {
                setTaskrecords(results);
            }
            return "allTaskrecords";
        }
        return ERROR;
    }

    public void taskRecordFinished() throws Exception {
        int recordid = 0;
        if (ServletActionContext.getRequest().getParameter("recordid") != null) {
            recordid = Integer.parseInt(ServletActionContext.getRequest().getParameter("recordid"));
        }
        String queryString = ServletActionContext.getRequest().getQueryString();
        System.out.println(queryString);
        if (ActionContext.getContext().getSession().get("teacher") != null) {
            if (recordid != 0) {
                Taskrecord rcd = taskrecordService.load(recordid);
                rcd.setCorrect(true);
                if (taskrecordService.update(rcd)) {
                    ServletActionContext.getResponse().getWriter().println("success");
                } else {
                    addActionMessage("sorry,操作失败！");
                }
            }
        }

    }
}
