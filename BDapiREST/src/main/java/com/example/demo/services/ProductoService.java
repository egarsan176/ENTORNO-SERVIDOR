package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository productoREPO;
	
	/**
	 * Este método busca la lista de productos de la BBDD
	 * @return lista de productos de la BBDD
	 */
	public List<Producto> findAllProducts(){
		return this.productoREPO.findAll();
	}
	
	/**
	 * Para añadir un producto nuevo a la bbdd
	 * @param producto
	 * @return producto añadido
	 */
	public Producto add(Producto producto) {
		return productoREPO.save(producto);
	}
	
	/**
	 * Busca en la BBDD el producto que coincide con ese id
	 * @param id
	 * @return producto que coincide con el IS pasado por parámetro
	 */
	public Producto findById(Integer id) {
		return productoREPO.findById(id).orElse(null);
	}
	

}
