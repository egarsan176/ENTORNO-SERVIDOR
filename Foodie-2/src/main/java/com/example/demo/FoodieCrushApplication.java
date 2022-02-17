package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.Category;
import com.example.demo.model.Method;
import com.example.demo.model.Recipe;
import com.example.demo.model.User;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.RecipeRepo;
import com.example.demo.repository.UserRepo;

@SpringBootApplication
public class FoodieCrushApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodieCrushApplication.class, args);
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
					new Category("Pasta"),
					new Category("Arroz"),
					new Category("Carne"),
					new Category("Guisos"),
					new Category("Salsas"),
					new Category("Postres"),
					new Category("Otros")));
		};
	}
	

	

}
