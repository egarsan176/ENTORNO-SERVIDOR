package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Producto;
import com.example.demo.repository.LineaPedidoRepository;

@Service
public class LineaService {
	
	@Autowired
	private LineaPedidoRepository lineaREPO;
	
	@Autowired
	private PedidoService servicioPedido;
	
	@Autowired
	private ProductoService servicioProducto;
	

	/**
	 * AÑADIR UNA NUEVA LÍNEA DE PEDIDO
	 * @param linea
	 * @return linea de pedido que se ha añadido
	 */
	public LineaPedido add(LineaPedido linea) {
		return lineaREPO.save(linea);
	}

	/**
	 * BUSCAR TODAS LAS LÍNEAS DE PEDIDO DEL REPOSITORIO
	 * @return lista con todas las líneas de pedido
	 */
	public List<LineaPedido> findAll() {
		return lineaREPO.findAll();
	}

	/**
	 * BUSCAR UNA LÍNEA DE PEDIDO POR SU ID
	 * @param id
	 * @return linea de pedido que coincide con ese id o null si no la encuentra
	 */
	public LineaPedido findById(Integer id) {
		return lineaREPO.findById(id).orElse(null);
	}

	/**
	 * EDITAR UNA LÍNEA DE PEDIDO
	 * @param LINEA DE PEDIDO
	 * @return linea de pedido editada
	 */
	public LineaPedido edit(LineaPedido linea) {
		return lineaREPO.save(linea);
	}

	/**
	 * BORRAR UNA LÍNEA DE PEDIDO
	 * @param id
	 */
	public void borrar(Integer id) {
		
		LineaPedido linea = this.lineaREPO.findById(id).orElse(null);
		linea.setCantidad(0);	//elimino la cantidad de la línea
		this.lineaREPO.save(linea); //la guardo en BBDD con cantidad=0
		
		Pedido pedido = this.servicioPedido.findPedido(linea.getPedido().getId()); //busco el pedido que contiene esa línea
		
		this.servicioPedido.eliminarLinea(pedido, linea);	//elimino la línea del pedido
		
		
	}
	
	
}
