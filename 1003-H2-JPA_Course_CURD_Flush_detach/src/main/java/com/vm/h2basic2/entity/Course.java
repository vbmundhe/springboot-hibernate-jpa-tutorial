package com.vm.h2basic2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


//An entity represents a table stored in a database. Every instance of an entity represents a row in the table.
@Entity
@Table(name = "Course")
@NamedQuery(name="find_all_course", query=" Select c from Course c ")
public class Course {
	// this will indicate as primary key
	@Id 
	@GeneratedValue  // Let the JPA generate the id
	private Long id;
	private String name;

	// Mandatory no=arg constructor
	public Course() {

	}

	public Course(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + "]";
	}
	
}
