package com.vm.h2basic2;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vm.h2basic2.dao.PersonJbdcDao;
import com.vm.h2basic2.entity.Person;
import com.vm.h2basic2.repository.PersonRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonJbdcDao personDao;
	
	@Autowired
	PersonRepository personRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		findAll();		
		deleteById();
		findById(10001);
		insert();
		update();
		findAll();
	}

	private void update() {
		System.out.println("=============== In Method = update() ===========");
		Person p = new Person(1, "vidyasagar modified", "Pune modified", new Date());
		Optional person = Optional.of(personRepository.update(p));
		if (person.isPresent()) {
			System.out.println(" Updated Number --> "+ person.get());
		}
		else {
			System.out.println("No of record added :( ");
		}
	}
	private void insert() {
		System.out.println("=============== In Method = insert() =========== ");
		// withoud ID
		Person p = new Person( "vidyasagar", "Pune", new Date());
		Optional person = Optional.of(personRepository.insert(p));
		if (person.isPresent()) {
			System.out.println("Number of record added --> "+ person.get());
		}
		else {
			System.out.println("No of record added :( ");
		}
	}
	private void deleteById() {
		System.out.println("=============== In Method = deleteById() ===========");
		personRepository.deleteById(10002);
		System.out.println("Record has been deleted.");
	}
	private void findById(int id) {
		System.out.println("=============== In Method = findById() ===========");
		Optional<Person> person = Optional.ofNullable(personRepository.findById(id));
		if (person.isPresent()) {
			System.out.println("Search Details found --> "+ person.get());
		}
		else {
			System.out.println("Search Details Not found  :( ");
		}
	}
	private void findByIdUsingCustomRowMapper(int id) {
		System.out.println("=============== In Method = findByIdUsingCustomRowMapper() ===========");
		Optional<Person> person = Optional.ofNullable(personDao.findByIdUsingCustomRowMapper(id));
		if (person.isPresent()) {
			System.out.println("Search Details found --> "+ person.get());
		}
		else {
			System.out.println("Search Details Not found  :( ");
		}
	}

	private void findAll() {
		System.out.println("=============== In Method = findAll() ===========");
		Optional<List<Person>> list = Optional.of(personRepository.findAll());
		
		if (list.isPresent()) {
			list.get().stream().filter(Objects::nonNull).forEach(System.out::println);
		}else {
			System.out.println("Empty List");
		}
	}
	

}
