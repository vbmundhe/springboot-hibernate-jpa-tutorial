package com.vm.h2basic2.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.vm.h2basic2.entity.Person;

// U can implement the transaction on business layer (recommended) or repository level
@Repository
@Transactional
public class PersonRepository {

	// Provide way to connect to DB
	// EntityManager<I> - manages the entities which refer to persistence context
	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<Person> findAll(){
		TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_persons", Person.class);
		return namedQuery.getResultList();
	}
	
	public Person findById(int  id){
		return entityManager.find(Person.class, id);
	}
	
	public Person update(Person person){
		// This will update if id is present else insert
		return entityManager.merge(person);
	}
	public Person insert(Person person){
		// This will update if id is present else insert
		return entityManager.merge(person);
	}
	public void deleteById(int id){
		entityManager.remove(findById(id));
	}
}
