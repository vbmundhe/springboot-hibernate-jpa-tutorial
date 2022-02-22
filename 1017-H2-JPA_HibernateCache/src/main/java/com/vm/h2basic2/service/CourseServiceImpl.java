package com.vm.h2basic2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vm.h2basic2.entity.CourseEntity;
import com.vm.h2basic2.repository.CourseSpringDataRepository;

@Service
public class CourseServiceImpl {
	
	@Autowired
	CourseSpringDataRepository courseSpringDataRepository;
	
	@Transactional
	public void playWithDifferentMethods() {
		// FindAll
		//displayAllCourses();
		
		Optional<CourseEntity> course10001 = courseSpringDataRepository.findById(10001L);
		System.out.println("\n\n======================= 1st time findById on course table \n"+
				course10001.get());
		System.out.println("\n======================= fetching review for first fatched course. (Query on review table will be fired) \n"+
				course10001.get().getReviews());

	}	
	@Transactional
	public void playWithDifferentMethods2ndTime() {
		
		Optional<CourseEntity> course10001FetchedAgain = courseSpringDataRepository.findById(10001L);
		System.out.println("\n\n======================= findById course10001FetchedAgain (Loading from cache. DB query for course details didnt fired again. Please check console. YOu should not see the select query on course) \n"+
				course10001FetchedAgain.get());
		System.out.println("\n======================= fetching review for SECOND CACHE COURSE - fatched course. (Query on review table will be fired) \n"+
				course10001FetchedAgain.get().getReviews());
		
		
	}	
}