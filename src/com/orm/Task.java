package com.orm;

import java.util.HashSet;
import java.util.Set;

/**
 * Task entity. @author MyEclipse Persistence Tools
 */

public class Task implements java.io.Serializable {

	// Fields

	private Integer id;
	private Student student;
	private Teacher teacher;
	private Course course;
	private String name;
	private String content;
	private Set taskrecords = new HashSet(0);

	// Constructors

	/** default constructor */
	public Task() {
	}

	/** full constructor */
	public Task(Student student, Teacher teacher, Course course, String name,
			String content, Set taskrecords) {
		this.student = student;
		this.teacher = teacher;
		this.course = course;
		this.name = name;
		this.content = content;
		this.taskrecords = taskrecords;
	}

	// Property accessors

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

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set getTaskrecords() {
		return this.taskrecords;
	}

	public void setTaskrecords(Set taskrecords) {
		this.taskrecords = taskrecords;
	}

}