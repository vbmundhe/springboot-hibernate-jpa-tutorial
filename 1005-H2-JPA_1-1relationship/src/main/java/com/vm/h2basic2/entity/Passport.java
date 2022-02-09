package com.vm.h2basic2.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


//An entity represents a table stored in a database. Every instance of an entity represents a row in the table.
@Entity
public class Passport {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String number;

	protected Passport() {
	}

	public Passport(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return " [id=" + id + ", number=" + number + "]";
	}

	
}
