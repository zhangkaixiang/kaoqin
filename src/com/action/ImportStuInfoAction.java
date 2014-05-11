package com.action;

import com.opensymphony.xwork2.ActionSupport;
import com.orm.Student;
import com.orm.TClass;
import com.service.SignRecordService;
import com.service.SignRecordServiceImpl;
import com.service.TClassService;
import com.service.TClassServiceImpl;
import com.service.StudentService;
import com.service.StudentServiceImpl;
import com.util.MD5;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ImportStuInfoAction extends ActionSupport {
    //服务

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TClassService sClassService = new TClassServiceImpl();
    StudentService studentService = new StudentServiceImpl();
    SignRecordService signRecordService = new SignRecordServiceImpl();
    //班级
    private List<TClass> sclasses;
    //选中的班级
    private String selectedClass;
    //文件名
    private String fileName;
    //提示信息
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSelectedClass() {
        return selectedClass;
    }

    public void setSelectedClass(String selectedClass) {
        this.selectedClass = selectedClass;
    }

    public List<TClass> getSclasses() {
        return sclasses;
    }

    public void setSclasses(List<TClass> sclasses) {
        this.sclasses = sclasses;
    }

    @Override
    public String execute() throws Exception {
        return super.execute();
    }
    //准备导入

    public String prepareImport() throws Exception {
        setSclasses(sClassService.allSClass());
        return SUCCESS;
    }
    //导入学生信息

    public String importStuInfo() throws Exception {
        //将当前学生信息封装入此
        List<Student> students = new ArrayList<Student>();
        //获取当前的班级
        TClass currentClass = sClassService.findByName(getSelectedClass());
        //根据路径获取excel
        Workbook workbook = Workbook.getWorkbook(new File("C:\\" + getFileName() + ".xls"));
        
        //获取第一个默认的Sheet
        Sheet sheet = workbook.getSheet(0);
        //若记录条数和学生数不等
        int rowNum = sheet.getRows();
        if ((rowNum - 1) != currentClass.getStuNum()) {
            addFieldError("notMatch", "记录数与班级人数不匹配！");
            return ERROR;
        }
        //这里要注意，如果excel里的记录数和班级人数不匹配，就会出现异常，索引越界了。
        //getCell(列,行)
        for (int i = 1; i <= currentClass.getStuNum(); i++) {
            Cell cellSno = sheet.getCell(0, i);
            Cell cellName = sheet.getCell(1, i);
            Cell cellSex = sheet.getCell(2, i);
          //  Cell cellPhone = sheet.getCell(3, i);
            Student s = new Student();
            s.setSno(cellSno.getContents());
            s.setName(cellName.getContents());
            s.setPassword(MD5.MD5("111111"));
            s.setTClass(currentClass);
            s.setSex(cellSex.getContents());
            s.setPhone("暂无");
            students.add(s);
        }
        if (students != null) {
            if (students.size() != currentClass.getStuNum()) {
                addActionMessage("导入的记录数和班级人数不匹配，请核对后再添加。");
                return ERROR;
            }
            //导入数据库---先清空这个表，然后再插入数据
            //删除学生会必须先删除该学生的所有记录。
            Student nowStudent = null;
            if (!studentService.allStudentsByClass(currentClass.getId()).isEmpty()) {
                nowStudent = studentService.allStudentsByClass(currentClass.getId()).get(0);
                if (!nowStudent.getSignrecords().isEmpty()) {
                    addActionMessage("该班学生已导入并产生关联记录，不允许重复导入，请联系管理员。");
                    return ERROR;
                }
            } //未产生记录，直接删除学生，重新导入
            else {
                //要重新导入未产生记录的学生，需要删除原来的学生信息
                if (!studentService.allStudentsByClass(currentClass.getId()).isEmpty()) {
                    studentService.deleteStuInfoByClass(currentClass.getId());
                }
                studentService.importStudentsInfo(students);
                setMsg("done");
                return "importSuccess";
            }

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
