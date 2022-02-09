package com.vm.h2basic2.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		//saveStudentWithPassport();
		getStudentWithPassport();
		
	}
	public Student saveStudentWithPassport() {
		System.out.println("\n\n ========= In method =saveStudentWithPassport(); ");
		// Note1: we need to persist passport first and then student other wise u will get error
		// ERROR: org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : com.vm.h2basic2.entity.Student.passport -> com.vm.h2basic2.entity.Passport; 
		Passport passport = new Passport("A1234");
		em.persist(passport);
		
		// Here student is owning the 1-1 relationship with passport
		Student student = new Student("Sagar");
		student.setPassport(passport);
		em.persist(student);
		em.flush();
		System.out.println("\n ----saved student detail = "+ student);
		return student;
		
	}
	/**
	 * 	Student student = em.find(Student.class, 20001L); // after this line, below query will be fired on db
	    /*select
	        student0_.id as id1_3_0_,
	        student0_.name as name2_3_0_,
	        student0_.passport_id as passport3_3_0_,
	        passport1_.id as id1_1_1_,
	        passport1_.number as number2_1_1_ 
	    from
	        student student0_ 
	    left outer join
	        passport passport1_ 
	            on student0_.passport_id=passport1_.id 
	    where
	        student0_.id=?
	 */
	public void getStudentWithPassport() {
		System.out.println("\n\n ========= In method =getStudentWithPassport(); ");
		Student student = em.find(Student.class, 20001L);
		System.out.println(" Student details => "+ student);
		
		// NOTE: even I am not fetching the passport detail, passport details are fetched because of eagar loading this will be avoidable by lazy fetch
		System.out.println(" Passport details => "+ student.getPassport());
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

	
}