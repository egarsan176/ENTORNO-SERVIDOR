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
	 * ENCUENTRA LA LISTA DE PRODUCTOS DEL REPOSITORIO
	 * @return lista de productos de la BBDD
	 */
	public List<Producto> findAllProducts(){
		return this.productoREPO.findAll();
	}
	
	/**
	 * AÑADIR UN NUEVO PRODUCTO AL REPOSITORIO
	 * @param producto
	 * @return producto añadido
	 */
	public Producto add(Producto producto) {
		return productoREPO.save(producto);
	}
	
	/**
	 * ENCUENTRA EL PRODUCTO QUE COINCIDE CON EL ID PASADO POR PARÁMETRO
	 * @param id
	 * @return producto que coincide con ese id o null si no lo encuentra
	 */
	public Producto findById(Integer id) {
		return productoREPO.findById(id).orElse(null);
	}
	

}
