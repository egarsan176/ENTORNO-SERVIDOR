package proyect_SERV;

import java.util.HashMap;
import java.util.Map;

public class Listado_Usuarios {
	
	private Map<String, String> listado;	//voy a usar un mapa para almacenar los usuarios porque así la clave será el nombre del usuario y el valor la constraseña
	

	public Listado_Usuarios() {
		this.listado = new HashMap<String, String>();
		
	}
	
	/**
	 * Este método carga los usuarios al mapa
	 */
	public void addDatos() {
		listado.put("pepe123", "54321");
		listado.put("estefgar", "12345");
		listado.put("juan12", "juan02");
		listado.put("carSanGan", "carla36");
		listado.put("luc18", "18ene90");
		listado.put("lala3", "1111");
	}
	
	/**
	 * Comprueba si el mapa tiene el usuario y la contraseña pasada por parámetro
	 * @param usuario
	 * @param password
	 * @return true si los contiene
	 */
	public boolean isContain(String usuario, String password) {
		boolean isValid = false;
		
		if(listado.containsKey(usuario) && listado.get(usuario).equals(password)) {
			isValid = true;
		}
		
		return isValid;
	}
	
	
}
