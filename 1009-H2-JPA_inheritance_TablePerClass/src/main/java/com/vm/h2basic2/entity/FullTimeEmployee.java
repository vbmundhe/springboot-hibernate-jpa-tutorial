package com.vm.h2basic2.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee {

	private BigDecimal salary;
	
	public FullTimeEmployee() {
	}

	public FullTimeEmployee(String name, BigDecimal hourlyWage) {
		super(name);
		this.salary = hourlyWage;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "FullTimeEmployee [salary=" + salary + "]";
	}

}
