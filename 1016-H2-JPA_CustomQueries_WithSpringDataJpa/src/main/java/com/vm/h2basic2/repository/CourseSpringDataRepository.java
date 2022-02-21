package com.vm.h2basic2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vm.h2basic2.entity.CourseEntity;

@Transactional
public interface CourseSpringDataRepository extends JpaRepository<CourseEntity, Long>{
	
	// Let's create the methods using the keywords
	// u can create method as findByName or queryByName
	List<CourseEntity> findByName(String name);
	List<CourseEntity> findByNameAndId(String name, Long id);
	int countByName(String name);
	List<CourseEntity> findByNameOrderByIdDesc(String name);
	List<CourseEntity> deleteByName(String name);
	
	
	// 3 ways to write the custom query 
	// custom queries
	@Transactional
	@Modifying	
	@Query( "DELETE from CourseEntity c where c.name like '%Spring Core%'")
	List<CourseEntity> deleteAllSpringCoreCourses();
	
	
}