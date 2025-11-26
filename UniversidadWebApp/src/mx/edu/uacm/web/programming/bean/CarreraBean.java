package mx.edu.uacm.web.programming.bean;

public class CarreraBean {
	
	private String carrera;
	private String nombre;
	
	public CarreraBean(String carrera, String nombre) {
		this.carrera = carrera;
		this.nombre = nombre;
	}
	
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "CarreraBean [carrera=" + carrera + ", nombre=" + nombre + "]";
	}
	
    
}
