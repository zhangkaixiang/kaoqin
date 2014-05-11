 
package com.service;

import com.orm.Sign;
import java.util.List;

 
public interface SignSettingService 
{
    //判断周期性的签到规则是否已经存在
    public boolean weeklyIsExist(Sign sign);
    
    //判断自定义日期的签到规则是否已经存在
    public boolean customIsExist(Sign sign);
    
    //添加时间规则
    public boolean save(Sign signSetting);
    
    //根据学生所属班，星期几加载待签到列表
    public List<Sign> findSignSettingByWeekClass(int dayOfWeek, int classid);
    
   
    
    //根据教师课程查找星期几的签到列表 
     public List<Sign> findSignSettingByWeekCourse(int dayOfWeek, int courseid);
    
    //根据自定义日期加载待签到列表
    public List<Sign> findSignSettingByDate(String todayDate);
    
    
    
    //重载。根据课程id和自定义日期加载待签到列表
    public List<Sign> findSignSettingByDate(String todayDate , int courseid );
    
    
    //根据id加载签到规则
    public Sign load(int id);
    
    //根据课程获取该课程的签到规则
    public List<Sign> allSignsByCourseId(int courseid);
    
    //仅列出全部周期签到规则
    public List<Sign>  allSignsOnlyByWeek(int courseid);
    
     //仅列出全部自定义签到规则
    public List<Sign>  allSignsOnlyByDate(int courseid);
    
    //查询某个班级，星期几的全部签到规则
    public List<Sign> allSignsByClassDayOfWeek(int dayOfWeek, int classid);
  
    //查询某个班级，当天日期的全部签到规则
    public List<Sign> allSignsByClassCustomDate(String customDate, int classid);
    
    
    
    //更新签到规则
    public boolean updateSign(Sign sign);
    
    //看今天星期几，查询今天所有课程的签到规则
    public List<Sign> allCourseByWeeklyAndCourseId(int dayOfWeek, int courseid);
    
    //看今天的日期是几月几号，查询今天的所有课程的签到规则
    public List<Sign> allCourseByCustomDateAndCourseId(String cDate, int courseid);
    
    //删除一个签到规则
    public boolean  DeleteSignSetting(Sign sign);
    
    //将此签到规则置为失效
    public boolean invalidSign(Sign sign);
    
    
    
}
