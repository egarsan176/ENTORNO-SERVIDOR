package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.LineaPedido;
import com.example.demo.repository.LineaPedidoRepository;

@Service
public class LineaService {
	
	@Autowired
	private LineaPedidoRepository lineaREPO;
	

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

	public void borrar(LineaPedido linea) {
		this.lineaREPO.delete(linea);
		
	}
	
	public LineaPedido crearLineaPedido() {
		return new LineaPedido();
	}

}
