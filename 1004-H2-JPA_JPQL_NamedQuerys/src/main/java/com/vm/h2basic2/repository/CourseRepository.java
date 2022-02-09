package com.vm.h2basic2.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vm.h2basic2.entity.CourseEntity;

@Repository
@Transactional
public class CourseRepository {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	public void playWithJPQLOperation() {
		System.out.println("\n\n =============== In Method = playWithEntityManagerFlushRefersh() =========== \n\n");
		// add 
		em.persist(new CourseEntity("Android"));
		em.flush();

		try {
			Thread.currentThread().sleep(15);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Way 1:basic Query JPQL(Query on entity) query: we 
		Query query1 = em.createQuery("Select c from CourseEntity c"); // Course is the entity name
		List courseList = query1.getResultList();
		System.out.println(" courseList  = " + courseList);

		// add
		CourseEntity c1 = em.find(CourseEntity.class, 1L);
		c1.setName(" Android updated ");
		em.merge(c1);

		// Way 2:typed JPQL query
		TypedQuery<CourseEntity> query2 = em.createQuery("Select c from CourseEntity c", CourseEntity.class);
		List<CourseEntity> courseList2 = query2.getResultList();
		System.out.println(" courseList2  = " + courseList2);
		
		
		// where query
//		TypedQuery<CourseEntity> query3 = em.createQuery("Select c from CourseEntity c where id like '1000%'",
//				CourseEntity.class);
//		List<CourseEntity> courseList3 = query3.getResultList();
//		System.out.println(" where id like 1000% result => " + courseList3);
		
		// NamedQuery
		TypedQuery<CourseEntity> namedQuery1 = em.createNamedQuery("find_by_like_on_name", CourseEntity.class);
		List<CourseEntity> namedQueryRes = namedQuery1.getResultList();
		System.out.println(" \n ========= namedQueryRes (find_by_like_on_name)  => " + namedQueryRes);
		
		TypedQuery<CourseEntity> namedQuery2 = em.createNamedQuery("get_all_courses", CourseEntity.class);
		List<CourseEntity> namedQueryRes2 = namedQuery2.getResultList();
		System.out.println(" \n ========= namedQueryRes (get_all_courses)  => " + namedQueryRes2);
		
		

	}

	/**
	 *	Native Query: we generally use where JPQL can use easily. Eg Mass update, complicated join query 
	 */
	public void playWithNativeQuery() {
		System.out.println("\n\n =============== In Method = playWithNativeQuery() =========== \n\n");
		
		Query query2 = em.createNativeQuery("Update Course_details Set last_updated_date=sysdate()", CourseEntity.class);
		int count = query2.executeUpdate();
		System.out.println("\n\n --------------------------- Update Course_details Set last_updated_date=sysdate(). Affected row count  ==> "+ count);
		// Native query: here we pass actual SQL query
		Query query1 = em.createNativeQuery("Select * from Course_details where id=:id", CourseEntity.class);
		query1.setParameter("id", 10002);
		List<CourseEntity> list = query1.getResultList();
		System.out.println(" \n ---------------------------Native query result ==> "+ list);
		
	}

}
