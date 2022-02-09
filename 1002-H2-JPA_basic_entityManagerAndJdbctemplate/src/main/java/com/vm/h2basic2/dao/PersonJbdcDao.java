package com.vm.h2basic2.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.vm.h2basic2.entity.Person;

@Repository
public class PersonJbdcDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate; 

	// BeanPropertyRowMapper -- use if bean properties are matching with table
		// column names otherwise u have to create custom row mapper 

	public List<Person> findAll(){
		return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper(Person.class));
	}
	
	class PersonRowMapper implements RowMapper<Person>{

		/**
		 * in this method we are specifing how we map table column name to person properties
		 */
		@Override
		public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
			Person p = new Person();
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			p.setLocation(rs.getString("location"));
			
			// NOTE we are using timestrap here
			p.setBirthDate(rs.getTimestamp("birth_date"));
			
			return p;
		}
		
	}
	public Person findByIdUsingCustomRowMapper(int id){
		
		Object[] param = new Object[]{id};
		Person person=null;
		try {	
			person = jdbcTemplate.queryForObject("select * from person where id=?", param,
					new PersonRowMapper());
		}
		catch(DataAccessException em) {
			System.out.println("Got an exception ... " +em);
		}
		
		return person;
	}
	public Person findById(int id){
		
		Object[] param = new Object[]{id};
		Person person=null;
		try {	
			person =jdbcTemplate.queryForObject("select * from person where id=?", param, new BeanPropertyRowMapper<Person>(Person.class));
		}
		catch(DataAccessException em) {
			System.out.println("Got an exception ... " +em);
		}
		
		return person;
	}
	public int deleteById(int id){
		
		Object[] param = new Object[]{id};
		//param[0]=id;
		return jdbcTemplate.update("Delete from person where id=?", param);
	}
	
	public int insert(Person person){
		// Note: we have mentioned column name in query as birth_date not birthDate   
		Object[] param = new Object[]{person.getId(), person.getName(), new Timestamp(person.getBirthDate().getTime())};
		// Notice I am not passing location
		return jdbcTemplate.update("Insert into person(id,name,birth_date) values (?,?,?) ", 
				param);
	}
	public int update(Person person){
		// Note: we have mentioned column name in query as birth_date not birthDate   
		Object[] param = new Object[]{person.getName(), person.getLocation(), new Timestamp(person.getBirthDate().getTime()), person.getId()};
		// Notice I am not passing location
		return jdbcTemplate.update("Update person set name=?, location=?, birth_date=?  "
				+ " where id=?",				
				param);
	}
}
