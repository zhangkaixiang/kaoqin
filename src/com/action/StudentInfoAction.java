package com.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Student;
import com.orm.TClass;
import com.service.StudentService;
import com.service.StudentServiceImpl;
import com.service.TClassService;
import com.service.TClassServiceImpl;
import com.util.MD5;

import java.util.List;
import java.util.Set;
import org.apache.struts2.ServletActionContext;

public class StudentInfoAction extends ActionSupport {
    //服务

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TClassService classService = new TClassServiceImpl();
    StudentService studentService = new StudentServiceImpl();
    //班级
    private List<TClass> classes;
    //学生
    private Set<Student> students;
    //当前课程
    private TClass currentClass;
    //旧密码，新密码，确认密码
    private String oldPsw;
    private String newPsw;
    private String confirmPsw;
    private Student student;
    private String msg;
    public Student getStudent() {
        return student;
    }

    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setStudent(Student student) {
        this.student = student;
    }

    public String getConfirmPsw() {
        return confirmPsw;
    }

    public void setConfirmPsw(String confirmPsw) {
        this.confirmPsw = MD5.MD5(confirmPsw);
    }

    public String getNewPsw() {
        return newPsw;
    }

    public void setNewPsw(String newPsw) {
        this.newPsw = MD5.MD5(newPsw);
    }

    public String getOldPsw() {
        return oldPsw;
    }

    public void setOldPsw(String oldPsw) {
        this.oldPsw = MD5.MD5(oldPsw);
    }

    public TClass getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(TClass currentClass) {
        this.currentClass = currentClass;
    }

    public List<TClass> getClasses() {
        return classes;
    }

    public void setClasses(List<TClass> classes) {
        this.classes = classes;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String execute() throws Exception {
        setClasses(classService.allSClass());
        return SUCCESS;
    }

    public String showAllStudent() throws Exception {
        int cid = -1;
        if (ServletActionContext.getRequest().getParameter("cid") != null) {
            cid = Integer.parseInt(ServletActionContext.getRequest().getParameter("cid"));
            TClass tc = classService.load(cid);
            setCurrentClass(tc);
            return "allSuccess";
        }
        return ERROR;
    }

    public String preparModifyPsw() throws Exception {
        return "modify";
    }

    public String modifyPassword() throws Exception {

        if (ActionContext.getContext().getSession().get("student") != null) {
            Student st = (Student) ActionContext.getContext().getSession().get("student");

            Student stu = studentService.loadStudent(st.getId());
            //验证 
            if (!getOldPsw().equals(stu.getPassword())) {
                this.setMsg("旧密码输入不正确。");
                return "modify";
            }
            if (!getNewPsw().equals(getConfirmPsw())) {
            	this.setMsg("新密码和确认密码不一致");
                return "modify";
            }
            if ("".equals(getNewPsw()) || getNewPsw() == null) {
            	this.setMsg("输入不能为空！");
                return "modify";
            }
            stu.setPassword(getNewPsw());
            if (studentService.update(stu)) {
            	this.setMsg("修改密码成功！");
                return "modify";
            }
        }
        return ERROR;
    }

    public String viewInfo() throws Exception 
    {
        if (ActionContext.getContext().getSession().get("student")!=null) {
            Student s =  (Student)ActionContext.getContext().getSession().get("student");
            setStudent(studentService.loadStudent(s.getId()));
            return "sInfo";
        }
        return ERROR;
        
    }
}
