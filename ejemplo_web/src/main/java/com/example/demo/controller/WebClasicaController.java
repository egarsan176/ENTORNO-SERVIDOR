package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebClasicaController {


	@GetMapping("/")
	public String portada(Model model) {
		model.addAttribute("nombreEmpresa", "CRISESWEB");
		model.addAttribute("nombrePropietaria", "Estefanía García, Cristina Blanco");
		return "portada";
	}
	
	@GetMapping("/que")
	public String queHacemos(Model model) {
		model.addAttribute("queHacemos", "Empresa dedicada al desarrollo de Aplicaciones Web");
		model.addAttribute("info", "Creamos la página web para tu negocio.");
		return "que";
	}
	
	@GetMapping("/contacto")
	public String contact(Model model) {
		model.addAttribute("mensaje", "Estamos en C/Secuoya, 26.");
		return "contacto";
	}
	

}
