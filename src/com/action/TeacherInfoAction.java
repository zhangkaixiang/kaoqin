package com.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Student;
import com.orm.TClass;
import com.orm.Teacher;
import com.service.TeacherService;
import com.service.TeacherServiceImpl;

public class TeacherInfoAction extends ActionSupport{
	
	TeacherService teacherService = new TeacherServiceImpl();
    //旧密码，新密码，确认密码
    private String oldPsw;
    private String newPsw;
    private List<Teacher> allteaher;
    private int id;
    private int qx;
    

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQx() {
		return qx;
	}
	public void setQx(int qx) {
		this.qx = qx;
	}
	public List<Teacher> getAllteaher() {
		return allteaher;
	}
	public void setAllteaher(List<Teacher> allteaher) {
		this.allteaher = allteaher;
	}
	public String getOldPsw() {
		return oldPsw;
	}
	public void setOldPsw(String oldPsw) {
		this.oldPsw = oldPsw;
	}
	public String getNewPsw() {
		return newPsw;
	}
	public void setNewPsw(String newPsw) {
		this.newPsw = newPsw;
	}
    public String execute() throws Exception {
    List<Teacher> list=teacherService.loadAllTeacher();
    setAllteaher(list);   
    return SUCCESS;
    }
    public String updateQx(){
    	Teacher tea=teacherService.loadTeacher(id);
    	tea.setQx(qx);
    	if(teacherService.updateTeacherQx(tea))
    		return "tqx";
    	else
    		return ERROR;
    }
    
}
