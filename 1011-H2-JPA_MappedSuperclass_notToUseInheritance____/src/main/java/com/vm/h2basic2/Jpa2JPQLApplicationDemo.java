package com.vm.h2basic2;

import java.math.BigDecimal;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vm.h2basic2.entity.Employee;
import com.vm.h2basic2.entity.FullTimeEmployee;
import com.vm.h2basic2.entity.PartTimeEmployee;
import com.vm.h2basic2.repository.EmployeeRepository;

@SpringBootApplication
public class Jpa2JPQLApplicationDemo implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(Jpa2JPQLApplicationDemo.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Here we are inserting the FullTimeEmployee, PartTimeEmployee employee in different 
		// table - refer note 1
		Employee fulEmp = new FullTimeEmployee("Vidyasagar", new BigDecimal("10000"));
		Employee partEmp = new PartTimeEmployee("Vidyasagar", new BigDecimal("50"));

		employeeRepository.insert(partEmp);
		employeeRepository.insert(fulEmp);

		log.info("================================= "
				+ "All employee = \n{}", employeeRepository.retrieveAllEmployee());
	}
	/*
	 ## Note 1]
	@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) - // Create new table for each subclass. 
	Each table has its own attributes (inherited as well as subclass specific)
    create table full_time_employee (
    	       id bigint not null,
    	        name varchar(255),
    	        salary decimal(19,2),
    	        primary key (id)
    	    )
    	Hibernate: 
    	    
    	    create table part_time_employee (
    	       id bigint not null,
    	        name varchar(255),
    	        hourly_wage decimal(19,2),
    	        primary key (id)
    	    )
    
	
	## NOTE 2: While retrieval of all employee data, union query is fired on db
		select
	     employee0_.id as id1_0_,
	     employee0_.name as name2_0_,
	     employee0_.salary as salary1_1_,
	     employee0_.hourly_wage as hourly_w1_2_,
	     employee0_.clazz_ as clazz_ 
		 from
		     ( select
		         id,
		         name,
		         salary,
		         null as hourly_wage,
		         1 as clazz_ 
		     from
		         full_time_employee 
		     union
		     all select
		         id,
		         name,
		         null as salary,
		         hourly_wage,
		         2 as clazz_ 
		     from
		         part_time_employee 
		 ) employee0	
		 
		
	## NOTE 3: Problem here, if we have 10 properties in Abstract Employee table then it will be replicated to all subclass tables
	 									_
	*/
}
