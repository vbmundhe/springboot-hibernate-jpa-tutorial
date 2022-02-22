package com.vm.h2basic2.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

//An entity represents a table stored in a database. Every instance of an entity represents a row in the table.
@Entity
public class Student {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	// Here Student is maintaining the relationship so STUDENT_COURSES table will be
	// created
	// on other side of relationship, we will be using mappedBy="course" so that
	// only one table created (STUDENT_COURSES)
	// otherwise 2 table are created STUDENT_COURSES && COURSE_STUDENTS
	// Problem here is it create the TB STUDENT_COURSES(plural course) with column
	// courses_id (plural s) but
	// I want singular representation in join table like STUDENT_COURSE and column
	// like course_id to achieve this we have to use @JoinTable
	
	//	   create table student_course (
	//		       student_id bigint not null,
	//		        course_id bigint not null
	//		    )
	//	    alter table student_course 
	//	       add constraint course_tb_foreign_key 
	//	       foreign key (course_id) 
	//	       references course_details
	//	    
	//	    alter table student_course 
	//	       add constraint student_tb_foreign_key 
	//	       foreign key (student_id) 
	//	       references student

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "STUDENT_COURSE", 
		joinColumns = @JoinColumn(name = "STUDENT_ID",	foreignKey = @ForeignKey(name = "student_tb_foreign_key")), 
		inverseJoinColumns = @JoinColumn(name = "COURSE_ID", foreignKey = @ForeignKey(name = "course_tb_foreign_key")))
	private Set<CourseEntity> courses = new HashSet<>();

	// @OneToOne // this will EAGER [default]
	@OneToOne(fetch = FetchType.LAZY)
	private Passport passport;

	protected Student() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	public Set<CourseEntity> getCourse() {
		return courses;
	}

	public void setCourses(Set<CourseEntity> courses) {
		this.courses = courses;
	}

	public void addCourse(CourseEntity course) {
		this.courses.add(course);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}

}
