 
package com.service;

import com.dao.TaskDao;
import com.dao.TaskDaoImpl;
import com.orm.Task;
import java.util.List;

 
public class TaskServiceImpl implements TaskService {

    TaskDao dao = new TaskDaoImpl();
    
    
    public boolean addTask(Task task) 
    {
        try 
        {
            dao.save(task);
           return true;
        } catch (Exception e) 
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    
    public List<Task> allTasks(int teacherid) {
       return dao.queryList("from Task as t where t.teacher.id = '"+teacherid+"' ");
    }

    
    public List<Task> allTasksByCourse(int courseid, int teacherid) {
        return dao.queryList("from Task as t where t.teacher.id = '"+teacherid+"' and t.course.id= '"+courseid+"' ");
    }

    
    public Task load(int taskid) {
       return dao.load(taskid);
    }

    public int getTeacherid(int taskid){
    	String sql="select task.teacherid from Task as task where task.id='"+taskid+"'"; 
    	return dao.getTeacherid(sql);
    } 
    
    public static void main(String args[]){
    	TaskServiceImpl no=new TaskServiceImpl();
    /*	TeacherServiceImpl teacherService=new TeacherServiceImpl();
    	Teacher teacher=teacherService.loadTeacher(1);
    	no.saveMsg(1, teacher);
    	no.havaRead(1,teacher);
    	if(no.waitRead(teacher)){
    		System.out.print("no");
    	}else
    		System.out.print("ok");*/
   /*	System.out.print(no.getTeacherid(10));*/
    }
}
