package com.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.orm.Notice;

@SuppressWarnings("unused")
public interface NoticeDao {
	//查询记录是否存在
	public int queryexist(String sql);
//保存数据
	public void save(Notice notice);
//更新数据
	public void update(Notice notice);
//根据教师id加载noticeid
	public int getid(String sql);
}
