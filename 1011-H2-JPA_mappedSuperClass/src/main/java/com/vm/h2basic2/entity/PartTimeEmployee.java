package com.vm.h2basic2.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {

	private BigDecimal hourlyWage;
	
	public PartTimeEmployee() {
	}

	public PartTimeEmployee(String name, BigDecimal hourlyWage) {
		super(name);
		this.hourlyWage = hourlyWage;
	}

	public BigDecimal getHourlyWage() {
		return hourlyWage;
	}

	public void setHourlyWage(BigDecimal hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public PartTimeEmployee(BigDecimal hourlyWage) {
		super();
		this.hourlyWage = hourlyWage;
	}

	@Override
	public String toString() {
		return "\n" +super.toString() + "hourlyWage=" + hourlyWage ;
	}
	
	
}
