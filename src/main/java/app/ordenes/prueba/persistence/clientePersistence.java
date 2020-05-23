package app.ordenes.prueba.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.ordenes.prueba.model.cliente;


public class clientePersistence {
	
	private static final String urlDB = "jdbc:postgresql://ec2-18-210-214-86.compute-1.amazonaws.com/d6a1k6u0bbnv2e?user=yneunnzvsxnpxf&password=9739613381bbbff3c7aa4046a999cb4a9cea8794d1eb873790288a87bd4173a6";
	private Connection c;
	private cliente client;
	
	public void getConnection() {
		try {
			c = DriverManager.getConnection(urlDB);
		} catch (SQLException e) {
			
		}
	}
	
	
	public cliente getCliente(int cedula, String tipocc) {
		PreparedStatement pstmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			getConnection();
			c.setAutoCommit(false);
			String sql = "select * from cliente where cedula = ? and tipocc = ? ";
			pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, cedula);
			pstmt.setString(2, tipocc);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("rssssssssssss: " + rs);
			c.close();
			if (rs.next()) {
				System.out.println("reproduccion: ");
				client = new cliente(rs.getInt("cedula"),rs.getString("tipocc"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("correo"),rs.getInt("celular"),rs.getString("direccion"),rs.getString("passw"));
			}
			pstmt.close();
			rs.close();
			return client;
		} catch (Exception ex) {
			Logger.getLogger(clientePersistence.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	public cliente getCliente(int cedula, String tipocc,String password) {
		PreparedStatement pstmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			getConnection();
			c.setAutoCommit(false);
			String sql = "select * from cliente where cedula = ? and tipocc = ? and passw = ? ";
			pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, cedula);
			pstmt.setString(2, tipocc);
			pstmt.setString(3, password);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("rssssssssssss: " + rs);
			c.close();
			if (rs.next()) {
				System.out.println("reproduccion: ");
				client = new cliente(rs.getInt("cedula"),rs.getString("tipocc"),rs.getString("nombre"),rs.getString("apellido"),rs.getString("correo"),rs.getInt("celular"),rs.getString("direccion"),rs.getString("passw"));
			}
			pstmt.close();
			rs.close();
			return client;
		} catch (Exception ex) {
			Logger.getLogger(clientePersistence.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}
	
	public void saveCliente(int cedula,String tipoCC,String nombre,String apellido,String correo,int celular,String direccion,String passw) {
		
		PreparedStatement pstmt = null;
    	try {
            Class.forName("org.postgresql.Driver");
            getConnection();
            c.setAutoCommit(false);
            String sql = "insert into cliente values(?,?,?,?,?,?,?,?)";
            pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1, cedula);
            pstmt.setString(2, tipoCC);
            pstmt.setString(3, nombre);
            pstmt.setString(4, apellido);
            pstmt.setString(5, correo);
            pstmt.setInt(6, celular );
            pstmt.setString(7, direccion);
            pstmt.setString(8, passw);            
            ResultSet rs = pstmt.executeQuery();
            c.close();
            pstmt.close();
            rs.close();
        } catch (Exception ex) {
            Logger.getLogger(clientePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
