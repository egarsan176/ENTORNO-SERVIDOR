package com.example.demo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.UsuarioRepository;

@SpringBootApplication
public class NuevaVersionBdApplication {

	public static void main(String[] args) {
		SpringApplication.run(NuevaVersionBdApplication.class, args);
	}
	
	//hay que crear los bean para los usuarios y los productos predefinidos en la bbdd
	
	@Bean
	CommandLineRunner iniData (ProductoRepository productoREPO) {
		return (args) -> {
			productoREPO.saveAll(Arrays.asList(
					new Producto("Destornillador", 3.5, "Destornillador con punta imantada de estrella"),
					new Producto("Tornillos", 0.75, "Tornillos cabeza redonda de estrella"),
					new Producto("Taladro", 39.95, "Potente taladro percutor con batería de litio"),
					new Producto("Juego de llaves", 12.50, "Juego de llaves de distintos tamaños")));
		};
	}
	
	@Bean
	CommandLineRunner iniData2 (UsuarioRepository usuarioREPO) {
		return (args) -> {
			usuarioREPO.saveAll(Arrays.asList(
					new Usuario("Juan", "juan123","juan23@gmail.com", "666444888", "C/Caracola 6, 41109 Sevilla", "28632105D", "123456"),
					new Usuario("Olga", "olga12","olgaFdz1@gmail.com", "600333111", "C/Medusa 63, 41109 Sevilla", "41256301L", "111111"),
					new Usuario("Pedro", "pelopez","pelopez@gmail.com", "777444999", "C/Pulpo 4, 41109 Sevilla", "28510639M", "000000")));
		};
	}

}
