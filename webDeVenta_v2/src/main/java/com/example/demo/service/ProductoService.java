package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;

//v2
@Service
public class ProductoService {
	
	private List<Producto> productos = new ArrayList<>();
	
	/**
	 * Este método devuelve la lista de productos del servicio
	 * @return lista de productos
	 */
	public List<Producto> findAll(){
		return productos;
	}
	
	/**
	 * Inicia el servicio con unos productos preestablecidos
	 */
	@PostConstruct
	public void init() {
		productos.addAll(Arrays.asList(
				new Producto(1,"Destornillador", 3.5, "Destornillador con punta imantada de estrella"),
				new Producto(2,"Tornillos", 0.75, "Tornillos cabeza redonda de estrella"),
				new Producto(3,"Taladro", 39.95, "Potente taladro percutor con batería de litio"),
				new Producto(4,"Juego de llaves", 12.50, "Juego de llaves de distintos tamaños")));
}

}
