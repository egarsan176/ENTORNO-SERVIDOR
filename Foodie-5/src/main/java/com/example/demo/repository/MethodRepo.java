package com.example.demo.repository;
/**
 * Encargada de la persistencia de datos de Method
 * @author estefgar
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Method;

public interface MethodRepo extends JpaRepository<Method, Integer> {

}
