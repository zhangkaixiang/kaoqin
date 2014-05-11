package com.service;

import com.dao.SignSettingDao;
import com.dao.SignSettingDaoImpl;
import com.orm.Sign;
import java.util.List;

public class SignSettingServiceImpl implements SignSettingService {

    SignSettingDao dao = new SignSettingDaoImpl();

    
    public boolean save(Sign signSetting) {
        try {
            dao.save(signSetting);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("设置时间规则时发生错误！");
            return false;
        }
    }

    
    //若dayOfWeek的值为0，此处查处自定义日期
    public List<Sign> findSignSettingByWeekClass(int dayOfWeek , int classid) {
        return dao.query("from Sign as s where s.dayOfWeek = '" + dayOfWeek + "' and s.customDate is null  and s.valid = true  and s.TClass.id = '"+classid+"'    ");
    }
   
    //获取今天教师所有课程的签到列表。
    
    public List<Sign> findSignSettingByWeekCourse(int dayOfWeek, int courseid) {
         return dao.query("from Sign as s where s.dayOfWeek = '" + dayOfWeek + "' and s.customDate is null  and s.valid = true  and s.course.id = '"+courseid+"'    ");
    }
    
    
    public List<Sign> findSignSettingByDate(String todayDate) {
        return dao.query("from Sign as s where s.customDate = '" + todayDate + "' and s.valid = true ");
    }

    
    public Sign load(int id) {
        return dao.load(id);
    }

    
    public List<Sign> allSignsByCourseId(int courseid) {
        return dao.query("from Sign as s where s.course.id  = '" + courseid + "' and s.valid = true order by s.dayOfWeek asc ");
    }

 

    
    public List<Sign> findSignSettingByDate(String todayDate, int courseid) {
        return dao.query("from Sign as s where s.customDate = '" + todayDate + "' and s.course.id = '" + courseid + "' and s.valid = true ");
    }

    
    public List<Sign> allSignsOnlyByWeek(int courseid) {
        return dao.query("from Sign as s where s.customDate is null  and s.valid = true and s.course.id = '"+courseid+"' ");
    }

    
    public List<Sign> allSignsOnlyByDate(int courseid) {
        return dao.query("from Sign as s where s.dayOfWeek = 0  and s.valid = true and s.course.id = '"+courseid+"'  ");
    }

    
    public boolean weeklyIsExist(Sign sign) {
        try {
            List<Sign> signs = dao.query("from Sign as s where s.startTime  = '" + sign.getStartTime() + "' and s.endTime = '" + sign.getEndTime() + "' and s.dayOfWeek = '" + sign.getDayOfWeek() + "' and s.course.id = '" + sign.getCourse().getId() + "'  and s.valid = true    ");
            if (signs.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean customIsExist(Sign sign) {
        try {
            List<Sign> signs = dao.query("from Sign as s where s.startTime  = '" + sign.getStartTime() + "' and s.endTime = '" + sign.getEndTime() + "' and s.customDate = '" + sign.getCustomDate() + "' and s.course.id = '" + sign.getCourse().getId() + "'   and s.valid = true   ");
            if (signs.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public List<Sign> allSignsByClassDayOfWeek(int dayOfWeek, int classid) {
        return dao.query("from Sign as s where s.dayOfWeek = '" + dayOfWeek + "' and s.TClass.id = '" + classid + "' and s.valid = true ");
    }

    
    public List<Sign> allSignsByClassCustomDate(String customDate, int classid) {
        return dao.query("from Sign as s where s.customDate = '" + customDate + "' and s.TClass.id = '" + classid + "' and s.valid = true ");
    }

    
    
    public boolean updateSign(Sign sign) {
        try {
            dao.saveOrUpdate(sign);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public List<Sign> allCourseByWeeklyAndCourseId(int dayOfWeek, int courseid) {
         return dao.query("from Sign as s where s.dayOfWeek = '" + dayOfWeek + "' and s.course.id = '" + courseid + "' and s.valid = true ");
    }

    
    public List<Sign> allCourseByCustomDateAndCourseId(String cDate, int courseid) {
        return dao.query("from Sign as s where s.customDate = '" + cDate + "' and s.course.id = '" + courseid + "' and s.valid = true ");
    }

    
    public boolean DeleteSignSetting(Sign sign) {
        try {
            dao.delete(sign);
            return  true;
        } catch (Exception e) {
            return false;
        }
    }

    
    public boolean invalidSign(Sign sign) {
        try
        {
          sign.setValid(false);
          updateSign(sign);
          return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

  

   



    
}
