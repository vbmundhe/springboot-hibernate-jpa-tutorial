package com.vm.h2basic2.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

//An entity represents a table stored in a database. Every instance of an entity represents a row in the table.
@Entity
// course_detail table will be created
@Table(name = "courseDetails")

@NamedQueries(value = { @NamedQuery(name = "get_all_courses", query = " Select c from CourseEntity c"),
						@NamedQuery(name = "find_by_like_on_name", query = " Select c from CourseEntity c where name like '%AV%'"),
						@NamedQuery(name = "getSpringMvcCourses", query = " SELECT c from CourseEntity c where c.name like '%Spring Mvc%'")
						}
				)
// Let say course will not change frequently.  Make sure you are using javax.persistence.Cacheable instead of spring
// Use hibernat's Secondary cache
@Cacheable 
public class CourseEntity {
	// this will indicate as primary key
	@Id
	@GeneratedValue // Let the JPA generate the id
	private Long id;

	@Column(name = "fullname", nullable = false)
	private String name;

	// Here review is maintaining the relationship && Mapping column will be created
	// in review table
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private List<Review> reviews = new ArrayList<>();

	// Here student is maintaining the relationship so STUDENT_COURSES table will be created. 
	// We are saying this column is mapped by the student's courses field
	
	@ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	// join column for student is STUDENT_ID and inverse join column is COURSE_ID
	@JsonIgnore
	private Set<Student> students = new HashSet<>();

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

	public List<Review> getReviews() {
		return reviews;
	}

	// I dont want someone take list of review and add to course
//	public void setReviews(List<Review> reviews) {
//		this.reviews = reviews;
//	}
	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public void removeReview(Review review) {
		this.reviews.remove(review);
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
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