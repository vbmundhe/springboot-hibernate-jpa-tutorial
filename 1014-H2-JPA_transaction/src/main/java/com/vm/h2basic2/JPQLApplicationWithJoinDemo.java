package com.vm.h2basic2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vm.h2basic2.repository.CourseRepository;
import com.vm.h2basic2.repository.StudentRepository;

@SpringBootApplication
public class JPQLApplicationWithJoinDemo implements CommandLineRunner{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JPQLApplicationWithJoinDemo.class, args);
	}

	

	@Override
	public void run(String... args) throws Exception {
		/*
		#1: JOIN - SELECT c,s from CourseEntity c JOIN c.students s
			--> this will return all courses which has students and vice-versa also applicable
		*/
		courseRepository.jqlJoinQuery();
		/*
		#2: LEFT JOIN - SELECT c,s from CourseEntity c LEFT JOIN c.students s
			--> this will return all courses which has or has not students and vice-versa NOT applicable
		*/
		courseRepository.jqlLeftJoinQuery();
		/*
		#3: CROSS JOIN - SELECT c,s from CourseEntity c,c.students s
			--> it does simply Cartesian product (cross products) and return all details. If there are 3 courses and 3 student then 3*3 = 9 rows will be return 
		*/
		courseRepository.jqlCrossJoinQuery();
	}

	

}
