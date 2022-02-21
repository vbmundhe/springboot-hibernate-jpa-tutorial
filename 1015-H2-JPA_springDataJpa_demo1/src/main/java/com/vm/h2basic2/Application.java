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

import com.vm.h2basic2.entity.CourseEntity;
import com.vm.h2basic2.repository.CourseSpringDataRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseSpringDataRepository courseSpringDataRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	

	// Getting familiar with default available on methods on spring data repository 
	@Override
	public void run(String... args) throws Exception {
		// FindAll
		displayAllCourses();
		
		// Find
		Optional<CourseEntity> course = courseSpringDataRepository.findById(10001L);
		displayCourse(course);

		// Add course 
		CourseEntity awsCourse = new CourseEntity("AWS associate course");
		
		courseSpringDataRepository.save(awsCourse);
		
		// Update aws course
		awsCourse.setName("AWS associate course -- updated");
		courseSpringDataRepository.save(awsCourse);

		//courseSpringDataRepository.deleteById(10001L);
		
		//Sort sort = new Sort(Sort.Direction.ASC, "name");
		CourseEntity awsCourse2 = new CourseEntity("AWS associate course");
		CourseEntity awsCourse3 = new CourseEntity("AWS associate course");
		courseSpringDataRepository.save(awsCourse2);
		courseSpringDataRepository.save(awsCourse3);

		String[] sortfields = {"name", "id"}; 
		Sort sort = Sort.by(Direction.DESC, "name");
		List<CourseEntity> sortedCourses = courseSpringDataRepository.findAll(sort);
		displayAllCourses("Sorted", sortedCourses);
		
		// pagination - I want 1st page (0'th index) and page size=2	
		PageRequest pageRequest = PageRequest.of(0, 2);
		Page<CourseEntity> firstPageResult = courseSpringDataRepository.findAll(pageRequest);
		displayAllCourses("FirstPage:", firstPageResult.toList());
		
		// Get next page detail
		Pageable secondPagable = firstPageResult.nextPageable();
		Page<CourseEntity> secondPageResult = courseSpringDataRepository.findAll(secondPagable);
		displayAllCourses("secondPageResult:", secondPageResult.toList());

	}
	private void displayAllCourses() {
		System.out.println("=========== All courses ===========");
		System.out.println(courseSpringDataRepository.findAll());
	}
	private void displayAllCourses(String OperationName, List<CourseEntity> courses) {
		System.out.println("=========== All courses("+OperationName+") ==========="+ courses);
	}

	private void displayCourse(Optional<CourseEntity> course) {
		if (course.isPresent()) {
			System.out.println(" details ="+ course);
		}
		else {
			System.err.println(" details not found");
		}
	}
}
