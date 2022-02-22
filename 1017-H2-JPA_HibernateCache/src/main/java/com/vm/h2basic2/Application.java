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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vm.h2basic2.entity.CourseEntity;
import com.vm.h2basic2.repository.CourseSpringDataRepository;
import com.vm.h2basic2.service.CourseServiceImpl;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseSpringDataRepository courseSpringDataRepository;
	
	@Autowired
	CourseServiceImpl courseServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		courseServiceImpl.playWithDifferentMethods();
//		After above line, u will see the below comment on console
//	    3599400 nanoseconds spent preparing 2 JDBC statements;
//	    4022100 nanoseconds spent executing 2 JDBC statements;
//	    0 nanoseconds spent executing 0 JDBC batches;
//	    3043100 nanoseconds spent performing 1 L2C puts;	// Storing in cache
//	    0 nanoseconds spent performing 0 L2C hits;
//	    1237400 nanoseconds spent performing 1 L2C misses;	// No data found in cache
		
		// Use hibernat's Secondary cache
		// As we have used catchable annotation on course entity, while fetching same course details 2nd time it will fetch details from hibernate 2nd level cache instead of hitting to DB
		// while fetching the review details query on review table will be fired as we hvn't added cachable on review table
		courseServiceImpl.playWithDifferentMethods2ndTime();
		
//		After above line, u will see the below comment on console
//	    104400 nanoseconds spent preparing 1 JDBC statements;
//	    215700 nanoseconds spent executing 1 JDBC statements;
//	    0 nanoseconds spent executing 0 JDBC batches;
//	    228300 nanoseconds spent performing 1 L2C hits;	--> we found data in cache
//	    0 nanoseconds spent performing 0 L2C puts;		// zero put in cache
//	    0 nanoseconds spent performing 0 L2C misses;  // zero miss while checking in cache


	}
}
