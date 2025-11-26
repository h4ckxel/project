package mx.edu.uacm.web.programming.bean;

public class MateriaBean {
	
	String cve_materia;
	String nombre;
	String carrera;
	String semestre;
	
	public MateriaBean ( String cve_materia,  String semestre, String nombre, String carrera) {
		this.cve_materia = cve_materia;
		this.semestre = semestre;
		this.nombre =  nombre;
		this.carrera = carrera;
	}

	public String getCve_materia() {
		return cve_materia;
	}

	public void setCve_materia(String cve_materia) {
		this.cve_materia = cve_materia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	
	
}
