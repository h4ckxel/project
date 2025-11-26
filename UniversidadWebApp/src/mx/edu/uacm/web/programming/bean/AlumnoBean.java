package mx.edu.uacm.web.programming.bean;

public class AlumnoBean {

	private String matricula;
	private String nombre;
	private String ap1;
	private String ap2;
	private String semestre;
	private String carrera;
	
	public AlumnoBean ( String matricula, String nombre, String ap1, String ap2, String semestre, String carrera) {
		this.matricula = matricula;
		this.nombre =  nombre;
		this.ap1 = ap1;
		this.ap2 = ap2;
		this.semestre = semestre;
		this.carrera = carrera;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAp1() {
		return ap1;
	}
	public void setAp1(String ap1) {
		this.ap1 = ap1;
	}
	public String getAp2() {
		return ap2;
	}
	public void setAp2(String ap2) {
		this.ap2 = ap2;
	}
	public String getSemestre() {
		return semestre;
	}
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	
	
	
}
