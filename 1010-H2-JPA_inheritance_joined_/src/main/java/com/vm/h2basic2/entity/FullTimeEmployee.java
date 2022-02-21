package com.vm.h2basic2.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee {

	private BigDecimal salary;
	private BigDecimal variableSalary;

	public FullTimeEmployee() {
	}

	public FullTimeEmployee(String name, BigDecimal salary, BigDecimal variableSalary) {
		super(name);
		this.salary = salary;
		this.variableSalary = variableSalary;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getVariableSalary() {
		return variableSalary;
	}

	public void setVariableSalary(BigDecimal variableSalary) {
		this.variableSalary = variableSalary;
	}

	@Override
	public String toString() {
		return "FullTimeEmployee [salary=" + salary + ", variableSalary=" + variableSalary + "]";
	}

}
