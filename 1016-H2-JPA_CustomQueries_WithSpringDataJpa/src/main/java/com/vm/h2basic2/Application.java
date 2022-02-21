package com.vm.h2basic2;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

import com.vm.h2basic2.entity.CourseEntity;
import com.vm.h2basic2.repository.CourseSpringDataRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseSpringDataRepository courseSpringDataRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		playWithDifferentMethods();


	}

	@Transactional
	private void playWithDifferentMethods() {
		// FindAll
		displayAllCourses();

		// Sort sort = new Sort(Sort.Direction.ASC, "name");
		CourseEntity awsCourse2 = new CourseEntity("AWS associate course");
		CourseEntity awsCourse3 = new CourseEntity("AWS associate course");
		courseSpringDataRepository.save(awsCourse2);
		courseSpringDataRepository.save(awsCourse3);


		System.out.println("\n\n ======================= Lets see custom queries using spring data repository ======================\n\n");
		displayAllCourses("Custom Find By name :", courseSpringDataRepository.findByName("AWS associate course"));

		System.out.println("\n\n count by name (AWS associate course) = "
				+ courseSpringDataRepository.countByName("AWS associate course"));

		displayAllCourses("\n\n=======================Custom Find By name & id :",
				courseSpringDataRepository.findByNameAndId("AWS associate course", awsCourse2.getId()));
		
		displayAllCourses("\n\n=======================Custom Find findByNameOrderByIdDesc :",
				courseSpringDataRepository.findByNameOrderByIdDesc("AWS associate course"));
		
		displayAllCourses("\n\n=======================Custom Find deleteByName = java :", courseSpringDataRepository.deleteByName("JAVA"));
		courseSpringDataRepository.flush();
		
		//displayAllCourses("Result of deleteAllSpringCoreCourses  :", courseSpringDataRepository.deleteAllSpringCoreCourses());
		courseSpringDataRepository.flush();
		 
		displayAllCourses();
	}

	private void displayAllCourses() {
		System.out.println("=========== All courses ===========");
		System.out.println(courseSpringDataRepository.findAll());
	}

	private void displayAllCourses(String OperationName, List<CourseEntity> courses) {
		System.out.println("=========== All courses(" + OperationName + ") ===========" + courses);
	}

	private void displayCourse(Optional<CourseEntity> course) {
		if (course.isPresent()) {
			System.out.println(" details =" + course);
		} else {
			System.err.println(" details not found");
		}
	}

}
