package com.example.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Student;

@Service
public class StudentService {
	
	private List<Student> repositorio = new ArrayList<>(); 		//va a simular la base de datos

	public List<Student> findAll() {
		return repositorio;
	}
	
	@PostConstruct
	public void init() {
		repositorio.addAll(
				Arrays.asList(new Student(1, "Luis García"), 
								new Student(2, "Laura Sánchez"),
								new Student(3, "Pedro Romero")));
	}

}
