package app.ordenes.prueba.model;

public class servicio {
	
	private int idservicio;
	private String nombre;
	private long valor;
	private int calificacion;
	
	

	public servicio() {
		
	}
	
	public servicio(int idservicio,String nombre,long valor,int calificacion) {
		this.idservicio = idservicio;
		this.nombre = nombre;
		this.valor = valor;
		this.calificacion = calificacion;
	}

	public int getIdservicio() {
		return idservicio;
	}


	public void setIdservicio(int idservicio) {
		this.idservicio = idservicio;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public long getValor() {
		return valor;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}
	
	
}
