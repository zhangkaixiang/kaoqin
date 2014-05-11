package com.service;

import com.orm.Taskrecord;
import java.util.List;


public interface TaskrecordService {
     //添加任务
    public boolean save(Taskrecord taskrecord);
    
   //加载某个任务
    public Taskrecord load(int taskid);
    
   //是否已存在
    public boolean  isExist(Taskrecord taskrecord);
    
    //更新任务记录
    public boolean update(Taskrecord taskrecord);
    
   
    
    //根据学生和任务来查看上传的作业
    public List<Taskrecord>  allTaskrecordsByTask(int taskid);
    
    
    //根据班级获取作业
    public List<Taskrecord> allTaskrecordsByCourseid(int cid);
}
