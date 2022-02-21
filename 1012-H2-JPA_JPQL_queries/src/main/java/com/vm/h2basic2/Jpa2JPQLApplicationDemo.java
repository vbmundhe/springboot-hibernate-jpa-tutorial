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
public class Jpa2JPQLApplicationDemo implements CommandLineRunner{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Jpa2JPQLApplicationDemo.class, args);
	}

	

	@Override
	public void run(String... args) throws Exception {
//		courseRepository.playWithManyToManyGetStudentsForCourse();
//		studentRepository.addStudentToCourse();
//		studentRepository.addStudentListToCourse();
		
		
		// AIM. find all courses without any student
		courseRepository.jqlCoursesWithoutStudent();
		
		// AIM. find all student which are enrolled to any course
		studentRepository.jqlStudentNoCourse();
		
		// AIM. find all courses which are enrolled by atleast 2 students
		courseRepository.jqlCoursesHavingAtleast2Students();
		
		// AIM. Order course by no of student enrollment count
		courseRepository.jqlCoursesOrdderByStudents();
		
		/*	AIM: Get All student whose passport number contain 'E' letter
		  Similar to this use case, we can create query for different clouses
		 - BETWEEN 1 AND 1000
		 - IS NULL, IS NOT NULL
		 - UPPER, LOWERM TRIM, LENGTH
		 */
		studentRepository.jqlStudentLikeOnPassportNo(); 
		
		
	}

	

}
