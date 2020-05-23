package app.ordenes.prueba.model;

public class solicitudServicio {

	private int ticket;
	private int idservicio;

	public solicitudServicio() {

	}

	public solicitudServicio(int ticket,int idservicio) {
		
		this.idservicio = idservicio;
		this.ticket = ticket;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	public int getIdservicio() {
		return idservicio;
	}

	public void setIdservicio(int idservicio) {
		this.idservicio = idservicio;
	}

}
