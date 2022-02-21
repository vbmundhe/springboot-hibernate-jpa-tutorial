package com.vm.h2basic2;

import java.math.BigDecimal;

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

		// Here we are inserting the FullTimeEmployee, PartTimeEmployee employee in same
		// table which has inheritance relationship with Employee class
		Employee fulEmp = new FullTimeEmployee("Vidyasagar", new BigDecimal("10000"));
		Employee partEmp = new PartTimeEmployee("Vidyasagar", new BigDecimal("50"));

		employeeRepository.insert(partEmp);
		employeeRepository.insert(fulEmp);

		log.info("================================= "
				+ "All employee = \n{}", employeeRepository.retriveAllEmployee());
	}

}
