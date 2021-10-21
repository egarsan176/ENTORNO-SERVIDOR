package bean;

import java.util.Date;

public class Empleado {

	/**
	 * Atributos de la clase, privados
	 */
	private String nombre;
	private String apellidos;
	private String dni;
	private String telefono;
	private int edad;
	private int sueldo;
	private int experiencia;
	private Date fechaIncorporacion;
	
	/**
	 * Constructor sin argumentos
	 */
	public Empleado() {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.edad = edad;
		this.sueldo = sueldo;
		this.experiencia = experiencia;
		this.fechaIncorporacion = fechaIncorporacion;
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
	 * método de Acceso al telefono
	 * @return String telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * Método de cambio de valor del telefono
	 * @param String DNI
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	public int getSueldo() {
		return sueldo;
	}

	/**
	 * Método de cambio de valor del sueldo
	 * @param float sueldo
	 */
	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}
	
	/**
	 * Método de acceso a los años de experiencias
	 * @return int años de experiencias
	 */
	public int getExperiencia() {
		return experiencia;
	}

	/**
	 * Método de cambio de valor del estado civil
	 * @param int años de experiencia
	 */
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}
	
	/**
	 * Método de acceso a la fecha de incorporacion
	 * @return fecha de incoroporacion
	 */
	public Date getFechaIncorporacion() {
		return fechaIncorporacion;
	}

	/**
	 * Método de cambio de valor de la fecha de incorporacion
	 * @param int años de experiencia
	 */
	public void setFechaIncorporacion(Date fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}
}
