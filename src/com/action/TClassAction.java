package com.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.orm.Course;
import com.orm.TClass;
import com.service.TClassService;
import com.service.TClassServiceImpl;
import java.util.List;

import org.apache.struts2.ServletActionContext;

public class TClassAction extends ActionSupport {
/*	private int classid;

	public int getClassid() {
		return classid;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}*/

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<TClass> allClasses;
    private TClass classes;
    TClassService classesService = new TClassServiceImpl();
    
    //getter setter ...

    public List<TClass> getAllClasses() {
        return allClasses;
    }

    public void setAllClasses(List<TClass> allClasses) {
        this.allClasses = allClasses;
    }

    public TClass getClasses() {
        return classes;
    }

    public void setClasses(TClass classes) {
        this.classes = classes;
    }
    //默认查看班级
    @Override
    public String execute() throws Exception {
        //如果不为空
        if (!classesService.allSClass().isEmpty()) {
            setAllClasses(classesService.allSClass());
        }
        return SUCCESS;

    }
    //准备添加

    public String prepareAdd() throws Exception {
        //初始化加载班级
        return "prepareAdd";
    }
    //添加班级

    public String add() throws Exception {
        try {
            TClass cls = new TClass();
            cls.setClassName(getClasses().getClassName());
            cls.setStuNum(getClasses().getStuNum());
            cls.setRxnf(getClasses().getRxnf());
            classesService.addClasses(cls);
            ActionContext.getContext().getSession().put("currentClass", classesService.allSClass());
            addActionMessage("班级添加成功！");
            return "addSuccess";
        } catch (Exception e) {
            return ERROR;
        }


    }
    //查看单个课程

    public String view() throws Exception {
        return "";
    }
    //准备修改

    public String prepareModify() throws Exception {
        int classid = -1;
        if (ServletActionContext.getRequest().getParameter("classid") != null) {
        	classid = Integer.parseInt(ServletActionContext.getRequest().getParameter("classid"));
        }
        if (classesService.load(classid) != null) {
        	setClasses(classesService.load(classid));
        }
        return "prepareModify";
    }
    //修改

    public String modify() throws Exception {
    	 int cid = -1;
         if (ServletActionContext.getRequest().getParameter("classid") != null) {
             cid = Integer.parseInt(ServletActionContext.getRequest().getParameter("classid"));
         }
         TClass old = classesService.load(cid);
         old.setClassName(getClasses().getClassName());
         old.setRxnf(getClasses().getRxnf());
         old.setStuNum(getClasses().getStuNum());
         if (classesService.updateClasses(old)) {
             addActionMessage("更新成功！");
             return "prepareModify";
         }
         addActionMessage("更新失败");
         return "prepareModify";
    }
    //删除

    public String delete() throws Exception {
    	   int csid = -1;
           if (ServletActionContext.getRequest().getParameter("classid") != null) {
               csid = Integer.parseInt(ServletActionContext.getRequest().getParameter("classid"));
           }
           TClass currentClass = classesService.load(csid);
           if (classesService.delete(currentClass)) {
               addActionMessage("删除成功！");
               return "delSuccess";
           }
           addActionMessage("删除失败");
           return "delSuccess";
    }
    

}
