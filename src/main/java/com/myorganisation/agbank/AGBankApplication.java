package com.myorganisation.agbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class AGBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(AGBankApplication.class, args);
	}

}
