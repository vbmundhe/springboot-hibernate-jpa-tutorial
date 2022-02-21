package com.vm.h2basic2.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity

// In this subclass specific table will be created with subclass field and join operation will be used while fetching 
@Inheritance(strategy = InheritanceType.JOINED)
// if we didnt provide the column name then DTYPE column will be created with value as subclass Name- like PartTimeEmployee/FullTimeEmployee
//@DiscriminatorColumn(name="EmployeeType") // it not needed for  TABLE_PER_CLASS
public abstract class Employee {
	// this will indicate as primary key
	@Id
	@GeneratedValue // Let the JPA generate the id
	private Long id;

	private String name;

	
	public Employee() {
	}
	
	public Employee(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee []";
	}
	
}
