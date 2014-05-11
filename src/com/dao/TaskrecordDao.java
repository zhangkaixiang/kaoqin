package com.dao;

import com.orm.Taskrecord;
import java.util.List;

public interface TaskrecordDao {

    //加载一个任务记录
    public Taskrecord load(int id);

    //保存一个任务记录
    public void save(Taskrecord course);

    //根据条件查询s
    public List<Taskrecord> queryList(String queryString);

    public void update(Taskrecord taskrecord);
}
