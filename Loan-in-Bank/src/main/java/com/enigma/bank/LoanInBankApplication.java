package com.enigma.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class LoanInBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanInBankApplication.class, args);
	}

}
