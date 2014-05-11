package com.service;

import com.orm.Task;
import java.util.List;

public interface TaskService 
{
    //添加任务
    public boolean addTask(Task task);
    
    //查看所有任务
    public  List<Task> allTasks(int teacherid);
    
    //查找某门课的作业安排
    public  List<Task> allTasksByCourse(int courseid , int teacherid);
    
   //加载某个任务
    public Task load(int taskid);
}
