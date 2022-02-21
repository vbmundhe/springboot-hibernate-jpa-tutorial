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

import com.vm.h2basic2.entity.ContractFullTimeEmployee;
import com.vm.h2basic2.entity.Employee;
import com.vm.h2basic2.entity.FullTimeEmployee;
import com.vm.h2basic2.entity.PartTimeEmployee;
import com.vm.h2basic2.repository.EmployeeRepository;

@SpringBootApplication
public class ApplicationDemo implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApplicationDemo.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee fulEmp = new FullTimeEmployee("Vidyasagar", new BigDecimal("10000"), new BigDecimal("800"));
		Employee partEmp = new PartTimeEmployee("Jhon", new BigDecimal("50"));
		ContractFullTimeEmployee cfullEmp = new ContractFullTimeEmployee("Tim", new BigDecimal("12000"),
				new BigDecimal("1000"), "ABC Consultancy Services", new BigDecimal("100"));

		employeeRepository.insert(fulEmp);
		employeeRepository.insert(partEmp);
		employeeRepository.insert(cfullEmp);

		log.info(
				"================================= retrieveFullTimeEmployees (including ContractFullTimeEmployees) = \n{}",
				employeeRepository.retrieveFullTimeEmployees());
		log.info("================================= retrievePartTimeEmployees = \n{}",
				employeeRepository.retrievePartTimeEmployees());
		//log.info("================================= retrieveContractFullTimeEmployees = \n{}",employeeRepository.retrieveContractFullTimeEmployees());
	}
	/*
	 ## Note 1]
	// In this subclass specific table will be created with subclass field (first level) 
	  FullTimeEmployee: Here singlePerClass inheritance strategy will be considered as default on FullTimeEmployee Class 
	  so separate table for ContractFullTimeEmployee is not created); 
	  If you want separate table then add @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) on FullTimeEmployee

	     create table full_time_employee (
	       dtype varchar(31) not null,
	        id bigint not null,
	        name varchar(255),
	        salary decimal(19,2),
	        variable_salary decimal(19,2),
	        contract_fee decimal(19,2),
	        contractor_company varchar(255),
	        primary key (id)
	    )
	Hibernate: 
	    
	    create table part_time_employee (
	       id bigint not null,
	        name varchar(255),
	        hourly_wage decimal(19,2),
	        primary key (id)
	    )
	    
	    ============================================================
	## NOTE 2: inserting into table
	EG.
	insert 
	    into
	        full_time_employee
	        (name, salary, variable_salary, dtype, id) 
	    values
	        (?, ?, ?, 'FullTimeEmployee', ?)
		 : binding parameter [1] as [VARCHAR] - [Vidyasagar]
		 : binding parameter [2] as [NUMERIC] - [10000]
		 : binding parameter [3] as [NUMERIC] - [800]
		 : binding parameter [4] as [BIGINT] - [1]
		 ------------------------------------------
	insert 
	    into
	        part_time_employee
	        (name, hourly_wage, id) 
	    values
	        (?, ?, ?)
		: binding parameter [1] as [VARCHAR] - [Jhon]
		: binding parameter [2] as [NUMERIC] - [50]
		: binding parameter [3] as [BIGINT] - [2]
		 ------------------------------------------
    insert 
	    into
	        full_time_employee
	        (name, salary, variable_salary, contract_fee, contractor_company, dtype, id) 
	    values
	        (?, ?, ?, ?, ?, 'ContractFullTimeEmployee', ?)
		: binding parameter [1] as [VARCHAR] - [Tim]
		: binding parameter [2] as [NUMERIC] - [12000]
		: binding parameter [3] as [NUMERIC] - [1000]
		: binding parameter [4] as [NUMERIC] - [100]
		: binding parameter [5] as [VARCHAR] - [ABC Consultancy Services]
		: binding parameter [6] as [BIGINT] - [3]
	  
	====================================================================
	
	## NOTE 3: While retrieval of all employee data, join query query will be executed
	 select
	        fulltimeem0_.id as id2_0_,
	        fulltimeem0_.name as name3_0_,
	        fulltimeem0_.salary as salary4_0_,
	        fulltimeem0_.variable_salary as variable5_0_,
	        fulltimeem0_.contract_fee as contract6_0_,
	        fulltimeem0_.contractor_company as contract7_0_,
	        fulltimeem0_.dtype as dtype1_0_ 
	    from
	        full_time_employee fulltimeem0_
	        
	        SELECT * FROM FULL_TIME_EMPLOYEE;
			DTYPE  	ID  	NAME  	SALARY  	VARIABLE_SALARY  	CONTRACT_FEE  	CONTRACTOR_COMPANY  
			FullTimeEmployee	1	Vidyasagar	10000.00	800.00	null	null
			ContractFullTimeEmployee	3	Tim	12000.00	1000.00	100.00	ABC Consultancy Services
        
        -------------------------------------
	
	select
	        parttimeem0_.id as id1_1_,
	        parttimeem0_.name as name2_1_,
	        parttimeem0_.hourly_wage as hourly_w3_1_ 
	    from
	        part_time_employee parttimeem0_
	        
	        SELECT * FROM PART_TIME_EMPLOYEE;
			ID  	NAME  	HOURLY_WAGE  
			2	Jhon	50.00
	 	
	 ===============================================================================
	 
	 NOTE 4: This is similar to TABLE_PER_CLASS - table is created for subclass. 
	 diff: 
	 - when our consideration is - no inheritance relationship between classes then use @mappbedBy . For each enitie's we hv to write separate operation queries
	 -for base class, we cannt perform select operation on base table 
	 
	 Q. Out of 4 (SINGLE_TABLE,TABLE_PER_CLASS, JOIN, MAPPED_BY)option which we can choose
	 --> its varies As per ur use case
	 	- data design (Data integrity )to top priority then use JOIN
	 	- if performance is top priority  - single table
	 									
	*/
}
