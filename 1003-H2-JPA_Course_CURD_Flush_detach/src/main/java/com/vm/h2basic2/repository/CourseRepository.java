package com.vm.h2basic2.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vm.h2basic2.entity.Course;

@Repository
@Transactional
public class CourseRepository {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;
	
	public List<Course> findAll(){
		TypedQuery<Course> namedQuery = em.createNamedQuery("find_all_course", Course.class);
		return namedQuery.getResultList();
	}
	
	public Course findById(Long id) {
		return em.find(Course.class, id);
	}
	// for update/insert
	public Course save(Course course) {
		if (course.getId() == null) {
			// insert/persist
			 em.persist(course);
		}
		else {
			em.merge(course);
		}
		// Once course has been inserted in db, id will be populated in course instance
		return course;
	}
	public void deleteById(Long id) {
		em.remove(em.find(Course.class, id));
	}
	
	/**
	 * NOTE1: whenever you are working with transaction and you are managing something (insert,update, delete) with entity manager, 
	 * entity manager will keep track of the changes and update in DB.
	 * 
	 * Note2: As we hvnt use em.flush, changes will be reflected in db at the end of transaction i.e at the end of method
	 */
	public void playWithEntityManager() {
		System.out.println("=============== In Method = playWithEntityManager() ===========");
		// insert
		Course obj = new Course( "Play with AWS console ");
		em.persist(obj);
		
		Course obj2 = new Course( "Mulesoft");
		em.persist(obj2);
		
		// IMP: this changes automatically persisted to DB. [Changes on entity (obj) will be tracked by em and persisted in DB]
		obj.setName("Play with AWS console [Em update the obj automatically ]");
		obj2.setName("Mulesoft [Em update the obj automatically ]");
		
		System.out.println(obj);
	}

	/**
	 *	Note1: we haven't used persist(obj) > didnt called flush() method but in ahead code we called the detach/clear then data will not reflected in db
	 *because, before transaction complete - we have detached/cleared the entity 
	 */
	public void playWithEntityManagerFlushDetach() {
		System.out.println("=============== In Method = playWithEntityManagerFlushDetach() ===========");
		// insert
		Course obj = new Course( "AWS DyanomoDb");
		em.persist(obj);
		em.flush();
		
		Course obj2 = new Course( "Splunk");
		em.persist(obj2);
		em.flush();
		
		// IMP: this changes automatically persisted to DB (if no clear/detach has been happened ahead). 
		//[Changes on entity (obj) will be tracked by em and persisted in DB]
		obj.setName("AWS DyanomoDb [before 2nd flush]");
		obj2.setName("Splunk [before 2nd flush ]");
		em.flush();
		// Detach obj2-> means no longer need to track obj2 entity and update in db 
		// but you can see the changes in db until transaction complete
		//em.detach(obj2);
		
		// to detach all entities eg, obj1,obj2; i.e em is now not managing any entity 
		// If we dont execute clear/detach:all the changes on entity will be flushed/updated in eb at the end of transaction 
		em.clear();  
		obj.setName("AWS DyanomoDb [after detach-will  not found in db]");
		obj2.setName("Splunk [after detach/clear ]");
		 
		System.out.println(obj);
		System.out.println(obj2);
		
	}

	public void playWithEntityManagerFlushRefersh() {
		System.out.println("=============== In Method = playWithEntityManagerFlushRefersh() ===========");
		// insert
		Course obj1 = new Course( "Python");
		em.persist(obj1);
		Course obj2 = new Course( "UIpath");
		em.persist(obj2);
		em.flush();
		
		obj1.setName("Python [after 1st flush]" );
		obj2.setName("UIpath [after 1st flush]" );
		
		// here, we populate last updated data from db to obj2 entity[select statement will be fired on db]
		em.refresh(obj2);
		System.out.println(obj1);
		System.out.println(obj2); // you will see db details
	}
}
