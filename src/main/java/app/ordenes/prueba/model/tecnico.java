package app.ordenes.prueba.model;

public class tecnico {

	private int cedula;
	private String tipoCC;
	private String nombre;
	private String apellido;
	private String correo;
	private int celular;
	private String passw;
	
	public tecnico(){
		
	}
	
	public String getPassw() {
		return passw;
	}

	public void setPassw(String passw) {
		this.passw = passw;
	}

	public tecnico(int cedula,String tipoCC,String nombre,String apellido,String correo,int celular,String passw) {
		this.cedula = cedula;
		this.tipoCC = tipoCC;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.celular = celular;
		this.passw = passw;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getTipoCC() {
		return tipoCC;
	}

	public void setTipoCC(String tipoCC) {
		this.tipoCC = tipoCC;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getCelular() {
		return celular;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}		
}
