package app.ordenes.prueba.model;

public class tecnicoSolicitud {

	private int token;
	private int ticket;
	private int tecnico;
	private String tipocc;

	public tecnicoSolicitud() {

	}

	public tecnicoSolicitud(int token,int ticket,int tecnico,String tipocc ) {
		this.tecnico = tecnico;
		this.ticket = ticket;
		this.token = token;
		this.tipocc = tipocc;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	public int getTenico() {
		return tecnico;
	}

	public void setTenico(int tecnico) {
		this.tecnico = tecnico;
	}

	public String getTipocc() {
		return tipocc;
	}

	public void setTipocc(String tipocc) {
		this.tipocc = tipocc;
	}

}
