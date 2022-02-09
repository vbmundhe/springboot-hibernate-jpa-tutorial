package com.vm.h2basic2.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


//An entity represents a table stored in a database. Every instance of an entity represents a row in the table.
@Entity
// course_detail table will be created
@Table(name = "courseDetails") 

@NamedQueries(value= {
		@NamedQuery(name="get_all_courses", query=" Select c from CourseEntity c"),
		@NamedQuery(name="find_by_like_on_name", query=" Select c from CourseEntity c where name like '%AV%'")
		
})

public class CourseEntity {
	// this will indicate as primary key
	@Id 
	@GeneratedValue  // Let the JPA generate the id
	private Long id;
	
	@Column(name="fullname", nullable = false)
	private String name;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	// Mandatory no=arg constructor
	public CourseEntity() {

	}
	public CourseEntity(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public CourseEntity(String name) {
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
	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	
	@Override
	public String toString() {
		return "\n [id=" + id + ", name=" + name + ", createdDate=" + createdDate + ", lastUpdatedDate="
				+ lastUpdatedDate + "]";
	}

	
	
}
