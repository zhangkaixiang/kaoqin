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
import com.service.NoticeServiceImpl;
import com.service.StudentService;
import com.service.StudentServiceImpl;
import com.service.TaskService;
import com.service.TaskServiceImpl;
import com.service.TaskrecordService;
import com.service.TaskrecordServiceImpl;
import com.service.TeacherServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.struts2.ServletActionContext;

public class UploadAction extends ActionSupport {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//引入服务
    TaskService taskService = new TaskServiceImpl();
    StudentService studentService = new StudentServiceImpl();
    TaskrecordService taskrecordService = new TaskrecordServiceImpl();
    CourseService courseService = new CourseServiceImpl();
    // 封装上传文件域的属性
    private File doc;
    // 封装上传文件的类型
    private String docContentType;
    // 封装上传文件名
    private String docFileName;
    private String path;
    private String name;
    private String remark;
    private String msg;
    
    public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public File getDoc() {
        return doc;
    }
    
    public void setDoc(File doc) {
        this.doc = doc;
    }
    
    public String getDocContentType() {
        return docContentType;
    }
    
    public void setDocContentType(String docContentType) {
        this.docContentType = docContentType;
    }
    
    public String getDocFileName() {
        return docFileName;
    }
    
    public void setDocFileName(String docFileName) {
        this.docFileName = docFileName;
    }
    
    public void setPath(String value) {
        this.path = value;
    }
    
    public String getPath() throws Exception {
        return ServletActionContext.getServletContext().getRealPath(path);
    }
    
    @Override
    public String execute() throws Exception {
    	String prefix=docFileName.substring(docFileName.lastIndexOf(".")+1);
     if(prefix.equals("doc")||prefix.equals("docx")||prefix.equals("xls")||prefix.equals("ppt")||prefix.equals("xlsx")||prefix.equals("pptx")||prefix.equals("pdf")){ 
    	int tskid = 0;
        int courseid = 0;     
        docFileName = getFileName(docFileName);
        FileOutputStream fos = new FileOutputStream(getPath() + "\\" + docFileName);
        FileInputStream fis = new FileInputStream(doc);

        //将路径写入数据库
        //如果该学生上传的作业已经存在，这次将重新上传。
        if (ServletActionContext.getRequest().getParameter("taskid") != null) {
            tskid = Integer.parseInt(ServletActionContext.getRequest().getParameter("taskid"));
            TaskServiceImpl taskservice=new TaskServiceImpl();
            int teacherid=taskservice.getTeacherid(tskid);
            TeacherServiceImpl teacherservice=new TeacherServiceImpl();
            Teacher teacher=teacherservice.loadTeacher(teacherid);
            System.out.println(teacherid);
        	NoticeServiceImpl no=new NoticeServiceImpl();
        	int noticeid=no.loadid(teacher.getId());
        	no.saveMsg(noticeid, teacher);
        }
        if (ServletActionContext.getRequest().getParameter("courseid") != null) {
            courseid = Integer.parseInt(ServletActionContext.getRequest().getParameter("courseid"));
        }
              
        if (ActionContext.getContext().getSession().get("student") != null) {
            Student stu = (Student) ActionContext.getContext().getSession().get("student");
            Student s = studentService.loadStudent(stu.getId());
            Date dt = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ");
            String dtString = sf.format(dt);
            Course curCourse = courseService.load(courseid);
            Task curTask = taskService.load(tskid);
            Taskrecord tskrecord = new Taskrecord();
            tskrecord.setName(name);
            tskrecord.setRemark(remark);
            tskrecord.setPath(docFileName);
            tskrecord.setTask(curTask);
            tskrecord.setStudent(s);
            tskrecord.setUptime(dtString);
            tskrecord.setCourse(curCourse);
            tskrecord.setCorrect(false);
            
            if (!taskrecordService.isExist(tskrecord)) {
                taskrecordService.save(tskrecord);
                //上传文件
                byte[] b = new byte[1024];
                int length = 0;
                while ((length = fis.read(b)) > 0) {
                    fos.write(b, 0, length);
                }
                this.setMsg("上传成功！");
                return SUCCESS;
            } else {
                this.setMsg("上传成功！您之前上传的作业已经被覆盖！");
                return SUCCESS;
            }
        }
        }else{
        	this.setMsg("您上传的文件类型不符合要求！");
        	return INPUT;
        }
        return INPUT;
    }
    
    private String getFileName(String fileName) {
        int position = fileName.lastIndexOf(".");
        String extension = fileName.substring(position);
        return System.currentTimeMillis() + extension;
    }
}
