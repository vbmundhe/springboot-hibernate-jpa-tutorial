package com.vm.h2basic2.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vm.h2basic2.entity.CourseEntity;
import com.vm.h2basic2.entity.Review;

@Repository
@Transactional
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;

	public CourseEntity findById(Long id) {
		return em.find(CourseEntity.class, id);
	}

	public CourseEntity save(CourseEntity course) {

		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}

		return course;
	}

	public void deleteById(Long id) {
		CourseEntity course = findById(id);
		em.remove(course);
	}

	public void playWithEntityManager() {
		CourseEntity course1 = new CourseEntity("Web Services in 100 Steps");
		em.persist(course1);
		
		CourseEntity course2 = findById(10001L);
		
		course2.setName("JPA in 50 Steps - Updated");
		
	}

	public void addReviewForCourse(long courseId, String rating, String reviewDescription) {
		CourseEntity course = findById(courseId);
		logger.info(" Existing review for course={}{}",courseId, course.getReviews());
		
		// create Review
		Review review = new Review(rating, reviewDescription) ;
		
		// Add relationship detaisl
		review.setCourse(course);
		course.addReview(review);
		
		
		// Persist the review 
		em.persist(review);
		logger.info(" After adding review to course={} {}",courseId, course.getReviews());
		
	}
	
	public void retriveReviewForCourse() {
		// one-many --> its LAZY loading
		
		logger.info(" ===================== in method = retriveReviewForCourse() ====================");
		/* one course -> many review relationship -- its LAZY (default) loading happened.
		 * If you check, 1st query on course will be fired on executing the findById(..) method 
		 * Hibernate: 
		    select
		        courseenti0_.id as id1_0_0_,
		        courseenti0_.created_date as created_2_0_0_,
		        courseenti0_.last_updated_date as last_upd3_0_0_,
		        courseenti0_.fullname as fullname4_0_0_ 
		    from
		        course_details courseenti0_ 
		    where
		        courseenti0_.id=?
		*/
		CourseEntity course = findById(10001L);
		
		
		// When we try to get the review for the current course, 2nd query will be fired
		/*Hibernate: 
		    select
		        reviews0_.course_id as course_i4_2_0_,
		        reviews0_.id as id1_2_0_,
		        reviews0_.id as id1_2_1_,
		        reviews0_.course_id as course_i4_2_1_,
		        reviews0_.description as descript2_2_1_,
		        reviews0_.rating as rating3_2_1_ 
		    from
		        review reviews0_ 
		    where
		        reviews0_.course_id=?
		        		*/
		logger.info("{}",course.getReviews());
		
	}
	
	public void retrieveCourseForReview() {
		// on review we have many-one relation annotation on course 
		logger.info(" ===================== in method = retrieveCourseForReview() ====================");
		
		// HERE (EAGER)default loading will be happened. left outer join will be fired on DB
		/* select
	        review0_.id as id1_2_0_,
	        review0_.course_id as course_i4_2_0_,
	        review0_.description as descript2_2_0_,
	        review0_.rating as rating3_2_0_,
	        courseenti1_.id as id1_0_1_,
	        courseenti1_.created_date as created_2_0_1_,
	        courseenti1_.last_updated_date as last_upd3_0_1_,
	        courseenti1_.fullname as fullname4_0_1_ 
	    from
	        review review0_ 
	    left outer join
	        course_details courseenti1_ 
	            on review0_.course_id=courseenti1_.id 
	    where
	        review0_.id=?*/
		Review review = em.find(Review.class, 50001L);
		
		// on below line, no query fired on DB as course details already present with entity 
		logger.info("{}",review.getCourse());
		
	}

	public void playWithManyToManyGetStudentsForCourse() {
		// As relationship is many-many,only course details are loaded. students are loaded lazily.
		//		select
		//	        courseenti0_.id as id1_0_0_,
		//	        courseenti0_.created_date as created_2_0_0_,
		//	        courseenti0_.last_updated_date as last_upd3_0_0_,
		//	        courseenti0_.fullname as fullname4_0_0_ 
		//	    from
		//	        course_details courseenti0_ 
		//	    where
		//	        courseenti0_.id=?
		CourseEntity course = findById(10001L);
		logger.info("================== course details ==>"+ course);

		
		// on below line, INNER JOIN query FIRED on db
		//	    select
		//	        students0_.course_id as course_i2_4_0_,
		//	        students0_.student_id as student_1_4_0_,
		//	        student1_.id as id1_3_1_,
		//	        student1_.name as name2_3_1_,
		//	        student1_.passport_id as passport3_3_1_ 
		//	    from
		//	        student_course students0_ 
		//	    inner join
		//	        student student1_ 
		//	            on students0_.student_id=student1_.id 
		//	    where
		//	        students0_.course_id=?
		logger.info("================== student details ==>"+ course.getStudents());
	}
	
}