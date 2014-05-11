
package com.service;

import com.orm.Sign;
import com.orm.Signrecord;
import java.util.List;

 
public interface SignRecordService {
    
    
    //列出学生所有的考勤记录
    public List<Signrecord> allSignrecordsByStudent(int stuid);
     
   //列出签到的记录
    public  List<Signrecord> signedRecordByStudent(int stuid);
    
    //列出缺课的记录
       public  List<Signrecord> unsignedRecordByStudent(int stuid);
    
    //根据日期和学生获取今天缺课记录
    public List<Signrecord> listLostSignRecord(String todayDate,int sid);
    
     //根据日期和学生获取今日已签到记录，不算缺课的
    public List<Signrecord> listRecords(String todayDate,int sid);
    //根据id获取
    public Signrecord load(int id);
    
    //添加出勤记录
    public boolean save(Signrecord record);
    
    
    //根据签到规则的id判断 今天 的缺课记录是否已经存在，存在返回true,不存在返回false
    public  boolean isExist(int signdId , String todayDate ,  int sid);
    
    //统计某个学生今日签到次数
     public int countTodayLostsSignRecord(int stuid);
    //统计某个学生今日缺课次数
    public int countTodaySignedRecord(int stuid);
    
    //统计学生签到总次数
    public int countTotalLostsSignRecord(int stuid);
    //统计学生缺课总次数
    public int countTotalSignedRecord(int stuid);
    
    //获取某个班级全部学生的签到记录
    public List<Signrecord> allSignRecordsByStudent(int stuid,String todayDate,int signid);
    
    //统计全部当前已经签到上课的人数
    public int countTodaySignedStudent(Sign sign);
    
    //统计全部当前缺课的学生个数
    public int countTodayUnSignedStudent(Sign sign);
}
