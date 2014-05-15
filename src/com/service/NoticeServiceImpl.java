package com.service;

import com.dao.NoticeDaoImp;
import com.orm.Notice;
import com.orm.Teacher;
/*说明：
 * 1.以教师的id为索引，如果教师存在，isExist返回一个true，否则返回false
 * 2.view字段表示教师是否有未阅读的消息，0表示没有未阅读消息，1表示有未阅读消息
 * */
public class NoticeServiceImpl implements NoticeService{
NoticeDaoImp dao=new NoticeDaoImp();
Notice notice =new Notice();
	public boolean isExist(Teacher teacher) {
		int teacherid=teacher.getId();
		if(dao.queryexist("select count(*) from Notice as notice where notice.teacherid ='"+teacherid+"'")>0){
			return true;
		}else{
			return false;
		}
	}

	public void havaRead(int id,Teacher teacher) {
		notice.setId(id);
		notice.setTeacher(teacher);
		notice.setView(0);
		dao.update(notice);
	}

	public boolean waitRead(Teacher teacher) {
		int teacherid=teacher.getId();
		if(dao.queryexist("select count(*) from Notice as notice where notice.teacherid ='"+teacherid+"' and notice.view=1")>0){
			return true;
		}else{
			return false;
		}
	}

	public void saveMsg(int id,Teacher teacher) {
		if(isExist(teacher)){
			notice.setId(id);
			notice.setTeacher(teacher);
			notice.setView(1);
			dao.update(notice);
		}else{
			notice.setTeacher(teacher);
			notice.setView(1);
			dao.save(notice);
		}
		
	}


public static void main(String args[]){
	NoticeServiceImpl no=new NoticeServiceImpl();
/*	TeacherServiceImpl teacherService=new TeacherServiceImpl();
	Teacher teacher=teacherService.loadTeacher(1);
	no.saveMsg(1, teacher);
	no.havaRead(1,teacher);
	if(no.waitRead(teacher)){
		System.out.print("no");
	}else
		System.out.print("ok");
	System.out.print(no.loadid(1));*/
}
//根据教师id加载notice的id
public int loadid(int teacherid) {
	return dao.getid("select notice.id from Notice as notice where teacherid='"+teacherid+"'");
	
}
}