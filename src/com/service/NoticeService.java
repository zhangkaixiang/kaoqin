package com.service;
import com.orm.Teacher;

public interface NoticeService {
//获取数据库中是否存在该作业的记录
	public boolean isExist(Teacher teacher);
	//查看后把消息重置
	public void havaRead(int id,Teacher teacher);
	//学生上传作业后把记录设置为未读
	public boolean waitRead(Teacher teacher);
	//如果记录不存在，则插入记录
	public void saveMsg(int id,Teacher teacher);
	//查询一个notice的id
	public int loadid(int teacherid);
}
