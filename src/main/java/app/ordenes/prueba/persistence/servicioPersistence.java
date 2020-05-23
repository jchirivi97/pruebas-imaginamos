package app.ordenes.prueba.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.ordenes.prueba.model.servicio;

public class servicioPersistence {

	private static final String urlDB = "jdbc:postgresql://ec2-18-210-214-86.compute-1.amazonaws.com/d6a1k6u0bbnv2e?user=yneunnzvsxnpxf&password=9739613381bbbff3c7aa4046a999cb4a9cea8794d1eb873790288a87bd4173a6";
	private Connection c;
	private servicio serv;
	
	public void getConnection() {
		try {
			c = DriverManager.getConnection(urlDB);
		} catch (SQLException e) {
			
		}
	}
	
	public servicio getServicio (int idservicio) {
		PreparedStatement pstmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			getConnection();
			c.setAutoCommit(false);
			String sql = "select * from servicio where idservicio = ?";
			pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, idservicio);
			ResultSet rs = pstmt.executeQuery();
			c.close();
			if (rs.next()) {
				serv = new servicio(rs.getInt("idservicio"),rs.getString("nombre"),rs.getLong("valor"),rs.getInt("calificacion"));
			}
			pstmt.close();
			rs.close();
			return serv;
		} catch (Exception ex) {
			Logger.getLogger(servicioPersistence.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	public List<servicio> getAllServicio () {
		PreparedStatement pstmt = null;
		List<servicio> servicios = new ArrayList<servicio>();
		try {
			Class.forName("org.postgresql.Driver");
			getConnection();
			c.setAutoCommit(false);
			String sql = "select * from servicio";
			pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt.executeQuery();
			c.close();
			while (rs.next()) {
				serv = new servicio(rs.getInt("idservicio"),rs.getString("nombre"),rs.getLong("valor"),rs.getInt("calificacion"));
				servicios.add(serv);
			}
			pstmt.close();
			rs.close();
			return servicios;
		} catch (Exception ex) {
			Logger.getLogger(servicioPersistence.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	public List<servicio> getServicioSolicitud (int ticket) {
		PreparedStatement pstmt = null;
		List<servicio> servicios = new ArrayList<servicio>();
		try {
			Class.forName("org.postgresql.Driver");
			getConnection();
			c.setAutoCommit(false);
			String sql = "select servicio.idServicio,servicio.nombre,servicio.valor,servicio.calificacion from solicitud join solicitudservicio on solicitud.ticket = solicitudservicio.ticket join servicio on solicitudservicio.idservicio = servicio.idservicio where solicitud.ticket = ?";
			pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, ticket);
			ResultSet rs = pstmt.executeQuery();
			c.close();
			while (rs.next()) {
				serv = new servicio(rs.getInt("idservicio"),rs.getString("nombre"),rs.getLong("valor"),rs.getInt("calificacion"));
				servicios.add(serv);
			}
			pstmt.close();
			rs.close();
			return servicios;
		} catch (Exception ex) {
			Logger.getLogger(servicioPersistence.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	public void saveServicio(int idservicio,String nombre,long valor,int calificacion) {
		PreparedStatement pstmt = null;
    	try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "insert into servicio values(?,?,?,?)";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1, idservicio);
            pstmt.setString(2, nombre);
            pstmt.setLong(3, valor);
            pstmt.setInt(4, calificacion);
            ResultSet rs = pstmt.executeQuery();
            c.close();
            pstmt.close();
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(servicioPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
