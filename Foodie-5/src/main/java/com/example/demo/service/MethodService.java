package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Method;
import com.example.demo.repository.MethodRepo;
/**
 * Servicio que se encarga de mediar entre el controller y el repositorio de Method
 * @author estefgar
 *
 */
@Service
public class MethodService {

	@Autowired private MethodRepo methodREPO;
	
	/**
	 * A este método se accede para eliminar una lista de métodos de la base de datos
	 * @param recipeMethod
	 */
	public void deleteMethod(List<Method> recipeMethod) {
		for (Method method : recipeMethod) {
			this.methodREPO.delete(method);
		}
		
	}
}
