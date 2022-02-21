package com.vm.h2basic2.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class ContractFullTimeEmployee extends FullTimeEmployee {

	private BigDecimal contractFee;
	private String contractorCompany;
	
	public ContractFullTimeEmployee() {
	}

	public ContractFullTimeEmployee(String name, BigDecimal salary, BigDecimal variableSalary, String contractorCompany, BigDecimal contractFee) {
		super(name, salary, variableSalary);
		this.contractorCompany = contractorCompany;
		this.contractFee = contractFee;
	}

	public BigDecimal getContractFee() {
		return contractFee;
	}

	public void setContractFee(BigDecimal contractFee) {
		this.contractFee = contractFee;
	}

	@Override
	public String toString() {
		return "\n ContractFullTimeEmployee ["+super.toString() +" contractFee=" + contractFee + ", contractorCompany=" + contractorCompany
				+ ", toString()=" +  "]";
	}

}
