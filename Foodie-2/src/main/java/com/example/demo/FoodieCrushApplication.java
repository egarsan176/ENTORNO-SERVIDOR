package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepo;

@SpringBootApplication
public class FoodieCrushApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodieCrushApplication.class, args);
	}
	
	//hay que crear los bean para los usuarios y los productos predefinidos en la bbdd
	
	@Autowired private PasswordEncoder passEncoder; //para que la contraseña se encripte al guardarla en la bbdd
	
	@Bean
	CommandLineRunner iniData (UserRepo userREPO) {
		return (args) -> {
			userREPO.save(new User("Estefanía García", "estefgar", "estefaniagarci@gmail.com", passEncoder.encode("123456")));
			userREPO.save(new User("Pepe López", "pepLo", "pelopez@gmail.com", passEncoder.encode("123456")));
		};
	}

}
