package com.vm.h2basic2.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vm.h2basic2.entity.Employee;

@Repository
@Transactional
public class EmployeeRepository {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public void insert(Employee e) {
		em.persist(e);;
	}
	
	public List<Employee> retriveAllEmployee(){
		return em.createQuery("Select e from Employee e", Employee.class).getResultList();
	}
	

}
