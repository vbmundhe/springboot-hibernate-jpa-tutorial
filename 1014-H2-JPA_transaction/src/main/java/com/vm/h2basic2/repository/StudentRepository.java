package com.vm.h2basic2.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.vm.h2basic2.entity.CourseEntity;
import com.vm.h2basic2.entity.Passport;
import com.vm.h2basic2.entity.Student;

@Repository
//@Transactional(isolation = Isolation.DEFAULT) // 0
//@Transactional(isolation = Isolation.READ_UNCOMMITTED) //1
@Transactional(isolation = Isolation.READ_COMMITTED)	// 2
//@Transactional(isolation = Isolation.REPEATABLE_READ) // 4
//@Transactional(isolation = Isolation.SERIALIZABLE)  // 8

//we set transaction isolation properties across application by setting below property
//spring.jpa.properties.hibernate.connection.isolation=2
public class StudentRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

}