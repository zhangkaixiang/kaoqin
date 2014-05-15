package com.dao;

import com.orm.Task;
import java.util.List;

public interface TaskDao {
    
	//根据任务id查询教师id
	public int getTeacherid(String sql);
    //加载一个课程

    public Task load(int id);

    //保存一个课程
    public void save(Task task);

    //根据条件查询s
    public List<Task> queryList(String queryString);

    //更新一个课程
    public void update(Task task);
}
