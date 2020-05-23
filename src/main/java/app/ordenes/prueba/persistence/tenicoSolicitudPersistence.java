package app.ordenes.prueba.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.ordenes.prueba.model.tecnicoSolicitud;



public class tenicoSolicitudPersistence {
	
	private static final String urlDB = "jdbc:postgresql://ec2-18-210-214-86.compute-1.amazonaws.com/d6a1k6u0bbnv2e?user=yneunnzvsxnpxf&password=9739613381bbbff3c7aa4046a999cb4a9cea8794d1eb873790288a87bd4173a6";
	private Connection c;
	private tecnicoSolicitud tecnicoSolicit;
	
	public void getConnection() {
		try {
			c = DriverManager.getConnection(urlDB);
		} catch (SQLException e) {
			
		}
	}
	
	
	public tecnicoSolicitud getTenicoSolicitud (int token) {
		PreparedStatement pstmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			getConnection();
			c.setAutoCommit(false);
			String sql = "select * from tecnicosolicitud where token = ?";
			pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, token);
			ResultSet rs = pstmt.executeQuery();
			c.close();
			if (rs.next()) {
				tecnicoSolicit = new tecnicoSolicitud(rs.getInt("token"),rs.getInt("ticket"),rs.getInt("tecnico"),rs.getString("tipocc"));
			}
			pstmt.close();
			rs.close();
			return tecnicoSolicit;
		} catch (Exception ex) {
			Logger.getLogger(tenicoSolicitudPersistence.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	public List<tecnicoSolicitud> getAllTenicoSolicitud () {
		PreparedStatement pstmt = null;
		List<tecnicoSolicitud> all = new ArrayList<tecnicoSolicitud>();
		try {
			Class.forName("org.postgresql.Driver");
			getConnection();
			c.setAutoCommit(false);
			String sql = "select * from tecnicosolicitud";
			pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt.executeQuery();
			c.close();
			while (rs.next()) {
				tecnicoSolicit = new tecnicoSolicitud(rs.getInt("token"),rs.getInt("ticket"),rs.getInt("tecnico"),rs.getString("tipocc"));
				all.add(tecnicoSolicit);
			}
			pstmt.close();
			rs.close();
			return all;
		} catch (Exception ex) {
			Logger.getLogger(tenicoSolicitudPersistence.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	public List<tecnicoSolicitud> getAllTenicoSolicitudes(int cedula,String tipocc) {
		PreparedStatement pstmt = null;
		List<tecnicoSolicitud> all = new ArrayList<tecnicoSolicitud>();
		try {
			Class.forName("org.postgresql.Driver");
			getConnection();
			c.setAutoCommit(false);
			String sql = "select * from tecnicosolicitud where tecnico = ? and tipocc = ?";
			pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, cedula);
			pstmt.setString(2, tipocc);
			ResultSet rs = pstmt.executeQuery();
			c.close();
			while (rs.next()) {
				tecnicoSolicit = new tecnicoSolicitud(rs.getInt("token"),rs.getInt("ticket"),rs.getInt("tecnico"),rs.getString("tipocc"));
				all.add(tecnicoSolicit);
			}
			pstmt.close();
			rs.close();
			return all;
		} catch (Exception ex) {
			Logger.getLogger(tenicoSolicitudPersistence.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	public void saveTenicoSolicitud(int token,int ticket,int tecnico,String tipocc) {
		Statement stmt = null;
    	try {
    		Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = 
            		
            "INSERT INTO tecnicosolicitud (token,ticket,tecnico,tipocc) "
            + "VALUES ('" + ticket + "','" + ticket + "','" + tecnico
            + "','" + tipocc + "');";
            
            
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception ex) {
            Logger.getLogger(tenicoSolicitudPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	

}
