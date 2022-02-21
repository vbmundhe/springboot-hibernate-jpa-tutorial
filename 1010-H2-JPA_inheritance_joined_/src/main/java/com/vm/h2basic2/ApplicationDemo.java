package com.vm.h2basic2;

import java.math.BigDecimal;

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
		ContractFullTimeEmployee cfullEmp = new ContractFullTimeEmployee("Tim",  new BigDecimal("12000"), new BigDecimal("1000"), "ABC Consultancy Services", new BigDecimal("100"));

		
		employeeRepository.insert(fulEmp);
		employeeRepository.insert(partEmp);
		employeeRepository.insert(cfullEmp);

		log.info("================================= "
				+ "All employee = \n{}", employeeRepository.retrieveAllEmployee());
	}
	/*
	 ## Note 1]
	// In this subclass specific table will be created with subclass field and join operation will be used while fetching 
@Inheritance(strategy = InheritanceType.JOINED)
        
   create table contract_full_time_employee (
       contract_fee decimal(19,2),
        contractor_company varchar(255),
        id bigint not null,
        primary key (id)
    )
	Hibernate: 
	    
	    create table employee (
	       id bigint not null,
	        name varchar(255),
	        primary key (id)
	    )
	Hibernate: 
	    
	    create table full_time_employee (
	       salary decimal(19,2),
	        variable_salary decimal(19,2),
	        id bigint not null,
	        primary key (id)
	    )
	Hibernate: 
	    
	    create table part_time_employee (
	       hourly_wage decimal(19,2),
	        id bigint not null,
	        primary key (id)
	    )
	Hibernate: 
	    
	    alter table contract_full_time_employee 
	       add constraint FKm3pkiw0crd3n1vi8xbgwto4tp 
	       foreign key (id) 
	       references full_time_employee
	Hibernate: 
	    
	    alter table full_time_employee 
	       add constraint FKhkidbx7pliabdmr4wycrog3mg 
	       foreign key (id) 
	       references employee
	Hibernate: 
	    
	    alter table part_time_employee 
	       add constraint FKfan9lj9g0g880a30ca7bitghi 
	       foreign key (id) 
	       references employee
	       
	## NOTE 2: While inserting into the subclass, first parent data will be added first and then subclass table
	EG.
	Hibernate: 
    insert 
	    into
	        employee
	        (name, id) 
	    values
	        (?, ?)

    insert 
	    into
	        full_time_employee
	        (salary, variable_salary, id) 
	    values
	        (?, ?, ?)

    insert 
	    into
	        contract_full_time_employee
	        (contract_fee, contractor_company, id) 
	    values
	        (?, ?, ?)
	====================================================================
	
	## NOTE 3: While retrieval of all employee data, join query query will be executed
		 select
	        employee0_.id as id1_0_,
	        employee0_.name as name2_0_,
	        employee0_1_.salary as salary1_1_,
	        employee0_2_.hourly_wage as hourly_w1_2_,
	        case 
	            when employee0_1_.id is not null then 1 
	            when employee0_2_.id is not null then 2 
	            when employee0_.id is not null then 0 
	        end as clazz_	// class_ column used to identify the  
	        
	    from
	        employee employee0_ 
	    left outer join
	        full_time_employee employee0_1_ 
	            on employee0_.id=employee0_1_.id 
	    left outer join
	        part_time_employee employee0_2_ 
	            on employee0_.id=employee0_2_.id
		 
		ID1_1_  	NAME2_1_  	SALARY1_2_  	VARIABLE2_2_  	CONTRACT1_0_  	CONTRACT2_0_  	HOURLY_W1_3_  	CLAZZ_  
		1	Vidyasagar	10000.00	800.00	null	null	null	1
		2	Jhon	null	null	null	null	50.00	3
		3	Tim	12000.00	1000.00	100.00	ABC Consultancy Services	null	2
	 	
	 									
	Note 4: 
		From database design perspective, join is good thing but performance perspective (considering many subclass) might not be the best thing  
	*/
}
