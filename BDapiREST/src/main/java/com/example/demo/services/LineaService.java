package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LineaPedido;
import com.example.demo.model.Pedido;
import com.example.demo.repository.LineaPedidoRepository;

@Service
public class LineaService {
	
	@Autowired
	private LineaPedidoRepository lineaREPO;
	
	@Autowired
	private PedidoService servicioPedido;
	

	public LineaPedido add(LineaPedido linea) {
		return lineaREPO.save(linea);
	}

	public List<LineaPedido> findAll() {
		return lineaREPO.findAll();
	}

	public LineaPedido findById(Integer id) {
		return lineaREPO.findById(id).orElse(null);
	}

	public LineaPedido edit(LineaPedido linea) {
		return lineaREPO.save(linea);
	}

	/**
	 * Este método borra una línea de pedido
	 * @param id
	 */
	public void borrar(Integer id) {
		
		LineaPedido linea = this.lineaREPO.findById(id).orElse(null);
		linea.setCantidad(0);	//elimino la cantidad de la línea
		this.lineaREPO.save(linea); //la guardo en BBDD con cantidad=0
		
		Pedido pedido = this.servicioPedido.findPedido(linea.getPedido().getId()); //busco el pedido que contiene esa línea
		
		this.servicioPedido.eliminarLineaVacia(pedido);	//elimino la línea del pedido
		this.servicioPedido.addPedidoaLaBBDD(pedido);	//guardo el pedido sin la línea en la BBDD
		
		this.lineaREPO.delete(linea);
		
	}
	
	public LineaPedido crearLineaPedido() {
		return new LineaPedido();
	}
	


}
