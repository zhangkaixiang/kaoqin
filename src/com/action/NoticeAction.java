package com.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Teacher;
import com.service.NoticeServiceImpl;

public class NoticeAction extends ActionSupport implements ServletResponseAware {
	private HttpServletResponse response;
	public void setServletResponse(HttpServletResponse res) {
		this.response=res;
	}
	NoticeServiceImpl no=new NoticeServiceImpl();

	Teacher me = (Teacher) ActionContext.getContext().getSession().get("teacher");
	public void waitRead(){
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
			if(no.waitRead(me))
				response.getWriter().print("wait");
			else
				response.getWriter().print("read");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void havaRead(){
		int id=no.loadid(me.getId());
		no.havaRead(id, me);	
	}


}
