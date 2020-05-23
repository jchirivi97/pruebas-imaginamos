package app.ordenes.prueba.model;

import java.sql.Date;

public class solicitud {

	private int ticket;
	private Date fecha;
	private String estado;
	private int cedula;
	private String tipocc;

	public solicitud() {

	}

	public solicitud(int ticket,Date fecha,String estado,int cedula,String tipocc) {
		
		this.ticket = ticket;
		this.fecha = fecha;
		this.estado = estado;
		this.tipocc = tipocc;
		this.cedula = cedula;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getTipocc() {
		return tipocc;
	}

	public void setTipocc(String tipocc) {
		this.tipocc = tipocc;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
