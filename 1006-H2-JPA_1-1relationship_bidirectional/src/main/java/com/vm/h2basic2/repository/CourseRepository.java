package com.vm.h2basic2.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vm.h2basic2.entity.CourseEntity;

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
}