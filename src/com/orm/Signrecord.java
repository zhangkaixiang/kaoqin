package com.orm;
// Generated 2012-3-5 8:11:47 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Signrecord generated by hbm2java
 */
@SuppressWarnings("serial")
public class Signrecord  implements java.io.Serializable {


     private Integer id;
     private Student student;
     private Sign sign;
     private String ip;
     private String signdate;
     private Date signTime;
     private Boolean lost;

    public Signrecord() {
    }
    public Signrecord(Student student, Sign sign) {
        this.student = student;
        this.sign = sign;
    }
    public Signrecord(Student student, Sign sign, String ip, String signdate, Date signTime, Boolean lost) {
       this.student = student;
       this.sign = sign;
       this.ip = ip;
       this.signdate = signdate;
       this.signTime = signTime;
       this.lost = lost;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Student getStudent() {
        return this.student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    public Sign getSign() {
        return this.sign;
    }
    
    public void setSign(Sign sign) {
        this.sign = sign;
    }
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getSigndate() {
        return this.signdate;
    }
    
    public void setSigndate(String signdate) {
        this.signdate = signdate;
    }
    public Date getSignTime() {
        return this.signTime;
    }
    
    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }
    public Boolean getLost() {
        return this.lost;
    }
    
    public void setLost(Boolean lost) {
        this.lost = lost;
    }
}


