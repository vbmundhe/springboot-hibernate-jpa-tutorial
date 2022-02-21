package com.vm.h2basic2.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vm.h2basic2.entity.CourseEntity;
import com.vm.h2basic2.entity.Passport;
import com.vm.h2basic2.entity.Student;

@Repository
@Transactional
public class StudentRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public Student save(Student course) {

		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}

		return course;
	}

	public void playWithStudentPassport() {
		// saveStudentWithPassport();
		retriveStudentAndPassportDetails();

	}

	public void retriveStudentAndPassportDetails() {
		System.out.println("\n\n ========= In method =retriveStudentAndPassportDetails(); ");
		Student student = em.find(Student.class, 20001L);
		System.out.println(" Student details => " + student);

		// NOTE: even I am not fetching the passport detail, passport details are
		// fetched because of eagar loading this will be avoidable by lazy fetch
		System.out.println(" Passport details => " + student.getPassport());
	}

	public void retrivePassportAndAssociatedStudentAndDetails() {
		System.out.println("\n\n ========= In method =retrivePassportAndAssociatedStudentAndDetails(); ");
		Passport passport = em.find(Passport.class, 40001L);

		System.out.println(" \n passport details = " + passport);

	}

	public void deleteById(Long id) {
		Student course = findById(id);
		em.remove(course);
	}

	public void playWithEntityManager() {
		Student course1 = new Student("Web Services in 100 Steps");
		em.persist(course1);

		Student course2 = findById(10001L);
		course2.setName("JPA in 50 Steps - Updated");

	}

	public void addStudentToCourse() {
		Student student = new Student("Vidyasagar");
		CourseEntity course = new CourseEntity("AWS DyanamoDb");
		
		em.persist(student);
		em.persist(course);
		
		course.addStudent(student);
		student.addCourse(course);
		
	}
	public void addStudentListToCourse() {
		Student student1 = new Student("Jacob");
		Student student2 = new Student("Karthik");
		CourseEntity course = new CourseEntity("AWS with BigData");
		
		em.persist(student1);
		em.persist(student2);
		em.persist(course);
		
		List<Student> studentList = new ArrayList<Student>();
		studentList.add(student1);
		studentList.add(student2);
		
		course.setStudents(studentList);
		student1.addCourse(course);
		student2.addCourse(course);
		
	}
}