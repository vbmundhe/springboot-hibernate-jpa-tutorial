package com.vm.h2basic2.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.vm.h2basic2.entity.CourseEntity;
import com.vm.h2basic2.entity.Review;
import com.vm.h2basic2.entity.Student;

@Repository
//@Transactional(isolation = Isolation.DEFAULT) // 0
//@Transactional(isolation = Isolation.READ_UNCOMMITTED) //1
@Transactional(isolation = Isolation.READ_COMMITTED)	// 2
//@Transactional(isolation = Isolation.REPEATABLE_READ) // 4
//@Transactional(isolation = Isolation.SERIALIZABLE)  // 8

// we set transaction isolation properties across application by setting below property
// spring.jpa.properties.hibernate.connection.isolation=2
public class CourseRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;

	public void jqlJoinQuery() {
		logger.info(" ========================== method = jqlJoinQuery ==========================================");
		Query query = em.createQuery("SELECT c,s from CourseEntity c JOIN c.students s");
		List<Object[] > resultList = query.getResultList();
		
		for(Object[] courseAndStudentDetail:resultList){ 
			// each row contain 2  arrays, first array contain course detail and 2nd array contain student details
			CourseEntity course = (CourseEntity)courseAndStudentDetail[0];
			Student student = (Student)courseAndStudentDetail[1];
			logger.info(" course detail = {} students details={} ", course, student);
		}
		// Observation: Spring Core course *doesnt* have any students so it should not populate in result
	}

	/*
	#2: LEFT JOIN - SELECT c,s from CourseEntity c LEFT JOIN STUDENT s
		--> this will return all courses which has or has not students and vice-versa NOT applicable
	*/
	public void jqlLeftJoinQuery() {
		logger.info(" ========================== method = jqlLeftJoinQuery ==========================================");
		Query query = em.createQuery("SELECT c,s from CourseEntity c LEFT JOIN c.students s");
		List<Object[] > resultList = query.getResultList();
		
		for(Object[] courseAndStudentDetail:resultList){ 
			// each row contain 2  arrays, first array contain course detail and 2nd array contain student details
			CourseEntity course = (CourseEntity)courseAndStudentDetail[0];
			Student student = (Student)courseAndStudentDetail[1];
			logger.info(" course detail = {} students details={} ", course, student);
		}
		// Observation: As we are using LEFT join, course:Spring Core is populated in result without Students
		// [id=10002, name=Spring Core, createdDate=2022-02-15T00:00, lastUpdatedDate=2022-02-15T00:00] students details=null 
	}

	public void jqlCrossJoinQuery() {
		logger.info(" ========================== method = jqlCrossJoinQuery ==========================================");
		Query query = em.createQuery("SELECT c,s from CourseEntity c, Student s");  // Note: we are r giving entity name directly  [not using c.students <-- property]
		List<Object[] > resultList = query.getResultList();
		
		for(Object[] courseAndStudentDetail:resultList){ 
			// each row contain 2  arrays, first array contain course detail and 2nd array contain student details
			CourseEntity course = (CourseEntity)courseAndStudentDetail[0];
			Student student = (Student)courseAndStudentDetail[1];
			logger.info(" course detail = {} students details={} ", course, student);
		}
		// Observation: 
		/*
			 [id=10001, name=JAVA, createdDate=2022-02-15T00:00, lastUpdatedDate=2022-02-15T00:00] students details=Student [id=20001, name=VBM] 
			 [id=10001, name=JAVA, createdDate=2022-02-15T00:00, lastUpdatedDate=2022-02-15T00:00] students details=Student [id=20002, name=NIl] 
			 [id=10001, name=JAVA, createdDate=2022-02-15T00:00, lastUpdatedDate=2022-02-15T00:00] students details=Student [id=20003, name=Ram] 
			 
			 [id=10002, name=Spring Core, createdDate=2022-02-15T00:00, lastUpdatedDate=2022-02-15T00:00] students details=Student [id=20001, name=VBM] 
			 [id=10002, name=Spring Core, createdDate=2022-02-15T00:00, lastUpdatedDate=2022-02-15T00:00] students details=Student [id=20002, name=NIl] 
			 [id=10002, name=Spring Core, createdDate=2022-02-15T00:00, lastUpdatedDate=2022-02-15T00:00] students details=Student [id=20003, name=Ram]
			  
			 [id=10003, name=Spring Mvc, createdDate=2022-02-15T00:00, lastUpdatedDate=2022-02-15T00:00] students details=Student [id=20001, name=VBM] 
			 [id=10003, name=Spring Mvc, createdDate=2022-02-15T00:00, lastUpdatedDate=2022-02-15T00:00] students details=Student [id=20002, name=NIl] 
			 [id=10003, name=Spring Mvc, createdDate=2022-02-15T00:00, lastUpdatedDate=2022-02-15T00:00] students details=Student [id=20003, name=Ram] 
		 */
	}

}