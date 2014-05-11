package com.service;

import com.dao.TaskrecordDao;
import com.dao.TaskrecordDaoImpl;
import com.orm.Taskrecord;
import java.util.List;

public class TaskrecordServiceImpl implements TaskrecordService {

    TaskrecordDao dao = new TaskrecordDaoImpl();

    
    public boolean save(Taskrecord taskrecord) {
        try {
            dao.save(taskrecord);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    
    public Taskrecord load(int taskrecordId) {
        return dao.load(taskrecordId);
    }

    
    public boolean isExist(Taskrecord taskrecord) {
        List<Taskrecord> results = dao.queryList("from  Taskrecord as tsr where tsr.student.id = '" + taskrecord.getStudent().getId() + "' and tsr.task.id = '" + taskrecord.getTask().getId() + "'  ");
        if (results.size() > 0) {
            return true;
        }
        return false;
    }

    
    public List<Taskrecord> allTaskrecordsByTask(int taskid) {
        return dao.queryList("from Taskrecord as tsr where tsr.task.id = '" + taskid + "' ");
    }

    
    public List<Taskrecord> allTaskrecordsByCourseid(int cid) {
        return dao.queryList("from Taskrecord as tsr where tsr.course.id = '" + cid + "' ");
    }

    
    public boolean update(Taskrecord taskrecord) {
        try {
            dao.update(taskrecord);
            return true;
        } catch (Exception e) {
            System.out.println("update taskrecord failed");
            return false;
        }
    }
}
