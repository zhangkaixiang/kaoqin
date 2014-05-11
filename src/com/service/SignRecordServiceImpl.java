/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.SignRecordDao;
import com.dao.SignRecordDaoImpl;
import com.orm.Sign;
import com.orm.Signrecord;
import com.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SignRecordServiceImpl implements SignRecordService {

    SignRecordDao dao = new SignRecordDaoImpl();

    
    public Signrecord load(int id) {
        return dao.load(id);
    }

    
    public boolean save(Signrecord record) {
        try {
            dao.save(record);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public List<Signrecord> listRecords(String todayDate, int sid) {
        return dao.queryList("from Signrecord as sr where sr.signdate = '" + todayDate + "' and sr.student.id = '" + sid + "' and  sr.lost = false  order by sr.sign.id desc ");
    }

    
    public List<Signrecord> listLostSignRecord(String todayDate, int sid) {
        return dao.queryList("from Signrecord as sr where sr.signdate = '" + todayDate + "' and sr.student.id = '" + sid + "' and  sr.lost = true  order by sr.sign.id desc ");
    }

    
    public boolean isExist(int signdId, String todayDate, int sid) {
        try {
            //SQL查询 这里要判断2次
            //查询今天某个学生是否已有缺课记录。
            String queryString1 = "select * from signrecord   where signdate = '" + todayDate + "' and stuid = '" + sid + "' and signid = '" + signdId + "'  and  lost = '1'  ";
            List<Signrecord> recordLost = dao.sqlQueryList(queryString1);
            //查询今天某个学生是否已有签到记录
            String queryString2 = "select * from signrecord   where signdate = '" + todayDate + "' and stuid = '" + sid + "' and signid = '" + signdId + "' and  lost = '0'  ";
            List<Signrecord> recordSigned = dao.sqlQueryList(queryString2);
            if (recordLost.size() > 0 || recordSigned.size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    
    public int countTodayLostsSignRecord(int stuid) {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = sf.format(new Date());
        List<Signrecord> list = dao.queryList("from Signrecord as sr where sr.signdate='" + todayDate + "' and sr.lost = true and sr.student.id = '" + stuid + "'  ");
        return list.size();
    }

    
    public int countTodaySignedRecord(int stuid) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = sf.format(new Date());
        List<Signrecord> list = dao.queryList("from Signrecord as sr where sr.signdate='" + todayDate + "' and sr.lost = false and sr.student.id = '" + stuid + "'  ");
        return list.size();
    }

    
    public int countTotalLostsSignRecord(int stuid) {
        List<Signrecord> list = dao.sqlQueryList("select * from signrecord where stuid  = '" + stuid + "' and lost = 1   ");
        return list.size();
    }

    
    public int countTotalSignedRecord(int stuid) {
        List<Signrecord> list = dao.sqlQueryList("select * from signrecord where stuid  = '" + stuid + "' and lost = 0  ");
        return list.size();
    }

    
    public List<Signrecord> allSignRecordsByStudent(int stuid, String todayDate, int signid) {
        List<Signrecord> list = dao.sqlQueryList("select * from signrecord where stuid  = '" + stuid + "' and signDate = '" + todayDate + "'  and  signid='" + signid + "' order by signid asc  ");
        return list;
    }

    
    public int countTodaySignedStudent(Sign sign) {
        String today = DateUtil.getTodayYyyyMMdd();
        List<Signrecord> records = dao.queryList("from Signrecord as sr where sr.signdate = '" + today + "' and sr.lost = false  and sr.sign.id = '" + sign.getId() + "'  ");
        return records.size();
    }

    
    public int countTodayUnSignedStudent(Sign sign) {
        String today = DateUtil.getTodayYyyyMMdd();
        List<Signrecord> records = dao.queryList("from Signrecord as sr where sr.signdate = '" + today + "' and sr.lost = true  and sr.sign.id = '" + sign.getId() + "'  ");
        return records.size();
    }

    
    public List<Signrecord> allSignrecordsByStudent(int stuid) {
        List<Signrecord> records = dao.queryList("from Signrecord as sr where sr.student.id = '"+stuid+"'  ");
        return records;
    }

    
    public List<Signrecord> signedRecordByStudent(int stuid) {
         List<Signrecord> records = dao.queryList("from Signrecord as sr where sr.student.id = '"+stuid+"' and sr.lost = false  ");
        return records;
    }

    
    public List<Signrecord> unsignedRecordByStudent(int stuid) {
        List<Signrecord> records = dao.queryList("from Signrecord as sr where sr.student.id = '"+stuid+"' and sr.lost = true  ");
        return records;
    }
}
