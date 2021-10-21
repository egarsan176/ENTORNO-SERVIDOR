package bean;

public class Empleado {

	/**
	 * Atributos de la clase, privados
	 */
	private String nombre;
	private String apellidos;
	private String dni;
	private int edad;
	private float sueldo;
	private String estado;
	
	/**
	 * Constructor sin argumentos
	 */
	public Empleado() {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.edad = edad;
		this.sueldo = sueldo;
		this.estado = estado;
	}
	
	//Getters y Setters

	/**
	 * método de Acceso al nombre
	 * @return String nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método de cambio de valor del nombre
	 * @param String nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * método de Acceso a los apellidos
	 * @return String apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	
	/**
	 * Método de cambio de valor de los apellidos
	 * @param String apellidos
	 */
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	/**
	 * método de Acceso al DNI
	 * @return String DNI
	 */
	public String getDni() {
		return dni;
	}
	
	/**
	 * Método de cambio de valor del DNI
	 * @param String DNI
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * método de Acceso a la edad
	 * @return int edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Método de cambio de valor de la edad
	 * @param int edad
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	/**
	 * Método de Acceso al sueldo
	 * @return float sueldo
	 */
	public float getSueldo() {
		return sueldo;
	}

	/**
	 * Método de cambio de valor del sueldo
	 * @param float sueldo
	 */
	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}
	
	/**
	 * Método de acceso al estado civil
	 * @return String estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Método de cambio de valor del estado civil
	 * @param String estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
