package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer >{
	
	public List<Pedido>findByUsuarioId(Long long1);

}
