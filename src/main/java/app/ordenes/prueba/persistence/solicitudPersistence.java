package app.ordenes.prueba.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.ordenes.prueba.model.solicitud;



public class solicitudPersistence {

	private static final String urlDB = "jdbc:postgresql://ec2-18-210-214-86.compute-1.amazonaws.com/d6a1k6u0bbnv2e?user=yneunnzvsxnpxf&password=9739613381bbbff3c7aa4046a999cb4a9cea8794d1eb873790288a87bd4173a6";
	private Connection c;
	private solicitud solicit;
	
	public void getConnection() {
		try {
			c = DriverManager.getConnection(urlDB);
		} catch (SQLException e) {
			
		}
	}
	
	public solicitud getSolicitud (int ticket) {
		PreparedStatement pstmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			getConnection();
			c.setAutoCommit(false);
			String sql = "select * from solicitud where ticket = ?";
			pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, ticket);
			ResultSet rs = pstmt.executeQuery();
			c.close();
			if (rs.next()) {
				solicit = new solicitud(rs.getInt("ticket"),rs.getDate("fecha"),rs.getString("estado"),rs.getInt("cedula"),rs.getString("tipocc"));
			}
			pstmt.close();
			rs.close();
			return solicit;
		} catch (Exception ex) {
			Logger.getLogger(solicitudPersistence.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	public List<solicitud> getAllSolicitud () {
		PreparedStatement pstmt = null;
		List<solicitud> solicitudes = new ArrayList<solicitud>();
		try {
			Class.forName("org.postgresql.Driver");
			getConnection();
			c.setAutoCommit(false);
			String sql = "select * from solicitud ";
			pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt.executeQuery();
			c.close();
			while (rs.next()) {
				solicit = new solicitud(rs.getInt("ticket"),rs.getDate("fecha"),rs.getString("estado"),rs.getInt("cedula"),rs.getString("tipocc"));
				solicitudes.add(solicit);
			}
			pstmt.close();
			rs.close();
			return solicitudes;
		} catch (Exception ex) {
			Logger.getLogger(solicitudPersistence.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	
	public void saveSolicitud(int ticket,Date fecha,String estado,int cedula,String tipocc) {
		System.out.println("dddd"+ ticket+fecha+estado+cedula+tipocc);
		Statement stmt = null;
    	try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = 
            		
            "INSERT INTO solicitud (ticket,fecha,estado,cedula,tipocc) "
            + "VALUES ('" + ticket + "','" + fecha + "','" + estado
            + "','" + cedula + "','" + tipocc + "');";
            
            
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception ex) {
            Logger.getLogger(solicitudPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
}
