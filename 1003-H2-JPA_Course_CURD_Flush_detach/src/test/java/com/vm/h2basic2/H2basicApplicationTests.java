package com.vm.h2basic2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.vm.h2basic2.repository.CourseRepository;

@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Jpa1CURDFlushDetachApplicationDemo.class)
public class H2basicApplicationTests {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;
	
	@Test
	void contextLoads() {
	}
	
	@Test 
	@DirtiesContext
	public void playWithEntityManagerTest() {
		log.info("============== inside playWithEntityManagerTest ");
		repository.playWithEntityManager();
	}

}
