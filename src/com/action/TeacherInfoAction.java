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
import com.util.MD5;

public class TeacherInfoAction extends ActionSupport{
	
	TeacherService teacherService = new TeacherServiceImpl();
    //旧密码，新密码，确认密码
    private String oldPsw;
    private String newPsw;
    private String confirmPsw;
    private String msg;
	private List<Teacher> allteaher;
    private int id;
    private int qx;
    
    public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getConfirmPsw() {
		return confirmPsw;
	}
	public void setConfirmPsw(String confirmPsw) {
		this.confirmPsw = MD5.MD5(confirmPsw);
	}
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
		this.oldPsw = MD5.MD5(oldPsw);
	}
	public String getNewPsw() {
		return newPsw;
	}
	public void setNewPsw(String newPsw) {
		this.newPsw = MD5.MD5(newPsw);
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
    public String preparModifyPsw() throws Exception {
        return "modify";
    }
    public String modifyPassword(){
        if (ActionContext.getContext().getSession().get("teacher") != null) {
            Teacher st = (Teacher) ActionContext.getContext().getSession().get("teacher");

            Teacher tea = teacherService.loadTeacher(st.getId());
            //验证 
            if (!getOldPsw().equals(tea.getPassword())) {
                this.setMsg("旧密码输入不正确。");
                return "modify";
            }
            if (!getNewPsw().equals(this.getConfirmPsw())) {
            	 this.setMsg("新密码和确认密码不一致");
                return "modify";
            }
            if ("".equals(getNewPsw()) || getNewPsw() == null) {
            	 this.setMsg("输入不能为空！");
                return "modify";
            }
            tea.setPassword(getNewPsw());
            if (teacherService.update(tea)) {
            	 this.setMsg("修改密码成功！");
                return "modify";
            }
        }
        return ERROR;
    	
    }
    
}
