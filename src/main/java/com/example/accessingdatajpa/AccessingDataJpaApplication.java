package com.example.accessingdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class);
	}

	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("James", "Bond"));
			repository.save(new Customer("Ethan", "Hunt"));
			repository.save(new Customer("Sydney", "Bristow"));
			repository.save(new Customer("Jane", "Blonde"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(4L);
			log.info("Customer found with findById(4L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Blonde'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Blonde").forEach(blonde -> {
				log.info(blonde.toString());
			});
			// for (Customer blonde : repository.findByLastName("Blonde")) {
			// 	log.info(blonde.toString());
			// }
			log.info("");
		};
	}

}