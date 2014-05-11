package com.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.opensymphony.xwork2.ActionSupport;
import com.orm.Student;
import com.orm.TClass;
import com.orm.Teacher;
import com.service.SignRecordService;
import com.service.SignRecordServiceImpl;
import com.service.StudentService;
import com.service.StudentServiceImpl;
import com.service.TClassService;
import com.service.TClassServiceImpl;
import com.service.TeacherService;
import com.service.TeacherServiceImpl;
import com.util.MD5;

public class ImportTeacherAction extends ActionSupport{
	//服务

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TeacherService teacherService = new TeacherServiceImpl();
    //班级
	private Integer id;
    private String name;
    private String username;
    private String password;
    private Integer qx;
    private int tnum;
    //文件名
    private String fileName;
    //提示信息
    private String msg;
    @Override
    
    public String execute() throws Exception {
       return INPUT;
    }
    //准备导入

    public Integer getId() {
		return id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getTnum() {
		return tnum;
	}

	public void setTnum(int tnum) {
		this.tnum = tnum;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getQx() {
		return qx;
	}

	public void setQx(Integer qx) {
		this.qx = qx;
	}

    //导入教师信息

    public String importTeacherInfo() throws Exception {
        //将当前教师信息封装入此
        List<Teacher> teacher = new ArrayList<Teacher>();
        //根据路径获取excel
        Workbook workbook = Workbook.getWorkbook(new File("C:\\" + getFileName() + ".xls"));
        
        //获取第一个默认的Sheet
        Sheet sheet = workbook.getSheet(0);
        //若记录条数和学生数不等
        int rowNum = sheet.getRows();
        System.out.print(rowNum);
        if ((rowNum - 1) != tnum) {
            addFieldError("notMatch", "记录数不等于"+tnum+"，请核实！");
            return ERROR;
        }
        //这里要注意，如果excel里的记录数和班级人数不匹配，就会出现异常，索引越界了。
        //getCell(列,行)
        for (int i = 1; i <= getTnum(); i++) {
            Cell cellName = sheet.getCell(0, i);
            Cell cellUsername = sheet.getCell(1, i);
            Cell cellqx = sheet.getCell(2, i);
          //  Cell cellPhone = sheet.getCell(3, i);
            Teacher t = new Teacher();
            t.setName(cellName.getContents());
            t.setUsername(cellUsername.getContents());
            t.setPassword(MD5.MD5("111111"));
            if(cellqx.getContents().equals(""))
            t.setQx(0);
            else
            	t.setQx(1);
            teacher.add(t);
        }
        if (teacher != null) {
            if (teacher.size() != getTnum()) {
                addActionMessage("导入的记录数和输入人数不匹配，请核对后再添加。");
                return ERROR;
            }
            teacherService.importTeacherInfo(teacher);
            setMsg("done");
            return "importSuccess";
        }
		return ERROR;
    }

    //验证
    public void validateImportStuInfo() {
        if (getFileName() == null || getFileName().trim().length() < 1) {
            addFieldError("fileName", "文件名不能为空");
        }
    }
}
