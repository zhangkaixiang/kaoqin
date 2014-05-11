 
package com.model;

//日期模型,封装时间规则页面传来的参数

import java.util.Calendar;

public class MyDateTime 
{
    private String year;
    private String month;
    private String day;
    private String hour;
    private String minute;
    private String second = "00";
    //年月日时分秒加起来，如2011-12-12 19:05:04
    private String fullDateTime;

    public String getFullDateTime() {
        return fullDateTime;
    }

    public void setFullDateTime(String currentTimeString) {
        this.fullDateTime = this.year +"-"+ this.month +"-" + this.day+" " + currentTimeString;
    }
    
    public String getDay() {
        return day;
    }

    public void setDay() {
         this.day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"";
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth() {
         this.month = Calendar.getInstance().get(Calendar.MONTH)+"";
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getYear() {
        return year;
    }
   //设置当前年为服务器当前时间的年
    public void setYear() {
         //利用Calendar类获取年
         this.year = Calendar.getInstance().get(Calendar.YEAR)+"";
    }
    
}
