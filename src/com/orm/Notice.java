package com.orm;

/**
 * Notice entity. @author MyEclipse Persistence Tools
 */

public class Notice implements java.io.Serializable {

	// Fields

	private Integer id;
	private Teacher teacher;
	private Integer view;

	// Constructors

	/** default constructor */
	public Notice() {
	}

	/** minimal constructor */
	public Notice(Teacher teacher) {
		this.teacher = teacher;
	}

	/** full constructor */
	public Notice(Teacher teacher, Integer view) {
		this.teacher = teacher;
		this.view = view;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Integer getView() {
		return this.view;
	}

	public void setView(Integer view) {
		this.view = view;
	}

}