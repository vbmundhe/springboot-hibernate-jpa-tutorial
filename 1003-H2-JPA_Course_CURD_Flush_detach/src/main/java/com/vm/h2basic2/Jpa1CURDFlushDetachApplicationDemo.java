package com.vm.h2basic2;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vm.h2basic2.entity.Course;
import com.vm.h2basic2.repository.CourseRepository;

@SpringBootApplication
public class Jpa1CURDFlushDetachApplicationDemo implements CommandLineRunner{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CourseRepository courseRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Jpa1CURDFlushDetachApplicationDemo.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		findAll();		
		deleteById();
		findById(10001L);
		insert();
		insert();
		update();
		
		//courseRepository.playWithEntityManager();
		//courseRepository.playWithEntityManagerFlushDetach();
		courseRepository.playWithEntityManagerFlushRefersh();
		findAll();
	}

	private Course findById(Long id) {
		System.out.println("=============== In Method = findById() ===========");
		Optional<Course> result = Optional.ofNullable(courseRepository.findById(id));
		if (result.isPresent()) {
			System.out.println("Search Details found --> "+ result.get());
			return result.get();
		}
		else {
			System.out.println("Search Details Not found  :( ");
			return null;
		}
	}
	
	private void insert() {
		System.out.println("=============== In Method = insert() =========== ");
		// withoud ID
		Course obj = new Course( "Docker new");
		Optional<Course> result = Optional.of(courseRepository.save(obj));
		if (result.isPresent()) {
			System.out.println("Number of record added --> "+ result.get());
		}
		else {
			System.out.println("No of record added :( ");
		}
	}
	
	private void update() {
		System.out.println("=============== In Method = update() =========== ");
		// withoud ID
		Course obj = findById(10003L);
			obj.setName(" Spring Mvc [Updated name] ");
		Optional<Course> result = Optional.of(courseRepository.save(obj));
		if (result.isPresent()) {
			System.out.println("Number of record added --> "+ result.get());
		}
		else {
			System.out.println("No of record added :( ");
		}
	}
	
	private void deleteById() {
		System.out.println("=============== In Method = deleteById() ===========");
		courseRepository.deleteById(10002L);
		System.out.println("Record has been deleted.");
	}
	private void findAll() {
		System.out.println("=============== In Method = findAll() ===========");
		Optional<List<Course>> list = Optional.of(courseRepository.findAll());
		//personDao.findAll().stream().forEach(System.out::println);
		
		if (list.isPresent()) {
			list.get().stream().filter(Objects::nonNull).forEach(System.out::println);
		}else {
			System.out.println("Empty List");
		}
	}
	
}
