package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.UserRepo;

@SpringBootApplication
public class Foodie4Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Foodie4Application.class, args);
	}
	
@Autowired private PasswordEncoder passEncoder; //para que la contraseña se encripte al guardarla en la bbdd
	
	
	@Bean
	CommandLineRunner iniData (UserRepo userREPO) {
		return (args) -> {
			userREPO.save(new User("Estefanía García", "estefgar", "estefaniagarci@gmail.com", passEncoder.encode("123456")));
			userREPO.save(new User("Pepe López", "pepLo", "pelopez@gmail.com", passEncoder.encode("123456")));
		};
	}
	
	@Bean
	CommandLineRunner iniDataCategories (CategoryRepo categoryREPO) {
		
		return (args) -> {
			categoryREPO.saveAll(Arrays.asList(
					new Category("Pasta", 1),
					new Category("Arroz",1),
					new Category("Carne",1),
					new Category("Pescados",1),
					new Category("Postres",1),
					new Category("Otros",1)));
		};
	}

}
