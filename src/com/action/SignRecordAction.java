package com.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Signrecord;
import com.orm.Student;
import com.service.SignRecordService;
import com.service.SignRecordServiceImpl;
import java.util.List;
import org.apache.struts2.ServletActionContext;

public class SignRecordAction extends ActionSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//引入服务
    SignRecordService signRecordService = new SignRecordServiceImpl();
    //所有
    List<Signrecord> allR;

    @Override
    public String execute() throws Exception {
        Student student = null;
        if (ActionContext.getContext().getSession().get("student") != null) {
            student = (Student) ActionContext.getContext().getSession().get("student");
        }

        if (!signRecordService.allSignrecordsByStudent(student.getId()).isEmpty()) {
            setAllR(signRecordService.allSignrecordsByStudent(student.getId()));
        }
        return SUCCESS;
    }

    public String search() throws Exception {
        
        String status = ServletActionContext.getRequest().getParameter("status").toString();
        Student s = null;
        if (ActionContext.getContext().getSession().get("student") != null) {
            s = (Student) ActionContext.getContext().getSession().get("student");
        }

        if ("signed".equals(status)) {
            if (!signRecordService.signedRecordByStudent(s.getId()).isEmpty()) {
                setAllR(signRecordService.signedRecordByStudent(s.getId()));
            }
            else  addActionMessage("未搜索到记录！");
        }
        if ("unsign".equals(status)) {
            if (!signRecordService.unsignedRecordByStudent(s.getId()).isEmpty()) {
                setAllR(signRecordService.unsignedRecordByStudent(s.getId()));
            }
            else  addActionMessage("未搜索到记录！");
        }
        
        return SUCCESS;
    }

    public List<Signrecord> getAllR() {
        return allR;
    }

    public void setAllR(List<Signrecord> allR) {
        this.allR = allR;
    }
}
