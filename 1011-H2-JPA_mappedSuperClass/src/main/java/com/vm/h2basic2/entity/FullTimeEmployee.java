package com.vm.h2basic2.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

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

	public FullTimeEmployee(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "\n ["+super.toString() +"salary=" + salary + ", variableSalary=" + variableSalary	+  "]";
	}

}
