package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller		//le decimos a Spring que esta clase tiene que recibir las peticiones cuando se entre a la aplicación
public class MainController {

	//desde localhost:8080 aparece la página index.html
//	@GetMapping("/")
//	public String welcome() {
//		return "index";
//	}
	
	//desde localhost:8080/hola aparece la página index.html
//	@GetMapping("/hola")
//	public String welcome() {
//		return "index";
//	}
	
//	@GetMapping("/")
//	public String welcome(Model model) {
//		model.addAttribute("mensaje", "Hola desde Spring con thymeleaf");
//		return "index";
//	}
	
		//se le pasa a la url localhost:8080/greeting?name=Estefanía
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue = "World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	//se le pasa a la url localhost:8080/greeting/Estefanía
	@GetMapping("/greeting/{name}")
	public String greetingPath(@PathVariable(name="name", required=false) String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	

}
