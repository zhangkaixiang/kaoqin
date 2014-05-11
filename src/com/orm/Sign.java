package com.orm;
// Generated 2012-3-5 8:11:47 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Sign generated by hbm2java
 */
public class Sign  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
     private Course course;
     private TClass TClass;
     private String startTime;
     private String endTime;
     private Integer dayOfWeek;
     private String customDate;
     private Boolean valid;
     @SuppressWarnings("rawtypes")
	private Set signrecords = new HashSet(0);

    public Sign() {
    }

	
    public Sign(Course course, String startTime, String endTime) {
        this.course = course;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @SuppressWarnings("rawtypes")
	public Sign(Course course, TClass TClass, String startTime, String endTime, Integer dayOfWeek, String customDate, Boolean valid, Set signrecords) {
       this.course = course;
       this.TClass = TClass;
       this.startTime = startTime;
       this.endTime = endTime;
       this.dayOfWeek = dayOfWeek;
       this.customDate = customDate;
       this.valid = valid;
       this.signrecords = signrecords;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Course getCourse() {
        return this.course;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }
    public TClass getTClass() {
        return this.TClass;
    }
    
    public void setTClass(TClass TClass) {
        this.TClass = TClass;
    }
    public String getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public Integer getDayOfWeek() {
        return this.dayOfWeek;
    }
    
    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    public String getCustomDate() {
        return this.customDate;
    }
    
    public void setCustomDate(String customDate) {
        this.customDate = customDate;
    }
    public Boolean getValid() {
        return this.valid;
    }
    
    public void setValid(Boolean valid) {
        this.valid = valid;
    }
    @SuppressWarnings("rawtypes")
	public Set getSignrecords() {
        return this.signrecords;
    }
    
    @SuppressWarnings("rawtypes")
	public void setSignrecords(Set signrecords) {
        this.signrecords = signrecords;
    }




}

