package com.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Student;
import com.orm.TClass;
import com.orm.Teacher;
import com.service.StudentService;
import com.service.StudentServiceImpl;
import com.service.TClassService;
import com.service.TClassServiceImpl;
import com.service.TeacherService;
import com.service.TeacherServiceImpl;
import com.util.MD5;

import java.util.List;

public class LoginAction extends ActionSupport {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
    private String password;
    private List<TClass> tclasses;
    StudentService stuService = new StudentServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    TClassService classService = new TClassServiceImpl();
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<TClass> getTclasses() {
        return tclasses;
    }

    public void setTclasses(List<TClass> tclasses) {
        this.tclasses = tclasses;
    }
    
    
    
    //登陆逻辑，现在教师表中寻找是否教师登陆，
    //如果是，则教师登陆
    //如果不是，则在学生表中寻找
    @Override
    public String execute() throws Exception {
         //清空当前session
        ActionContext.getContext().getSession().clear();
        setPassword(MD5.MD5(password));
        System.out.println(getPassword());
        setTclasses(classService.allSClass());
        ActionContext.getContext().getSession().put("currentClass", getTclasses());
        //如果教师成功，则将教师信息存入session
        if (teacherService.teacherLogin(getUsername(), getPassword())) {
            Teacher teacher =  teacherService.loadByUserName(getUsername());
            ActionContext.getContext().getSession().put("teacher", teacher);
            return "tloginSuccess";
        }
        //如果教师登陆不成功，则说明账号为学生登陆，若学生登陆成功，放入session
        else if(stuService.stuLogin(getUsername(), getPassword()))
        {
            Student student = stuService.loadBySno(getUsername());
            ActionContext.getContext().getSession().put("student", student);
            return SUCCESS;
        }
        return INPUT;
    }
    
    
    public  String exit() throws Exception
    {
       ActionContext.getContext().getSession().clear();
       return INPUT;
    }
       
}
