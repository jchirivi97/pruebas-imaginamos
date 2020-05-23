package app.ordenes.prueba.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import app.ordenes.prueba.model.solicitudServicio;

public class solicitudServicioPersistence {

	private static final String urlDB = "jdbc:postgresql://ec2-18-210-214-86.compute-1.amazonaws.com/d6a1k6u0bbnv2e?user=yneunnzvsxnpxf&password=9739613381bbbff3c7aa4046a999cb4a9cea8794d1eb873790288a87bd4173a6";
	private Connection c;
	private solicitudServicio solicit;

	public void getConnection() {
		try {
			c = DriverManager.getConnection(urlDB);
		} catch (SQLException e) {

		}
	}

	public solicitudServicio getSolicitudServ(int ticket, int idservicio) {
		PreparedStatement pstmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			getConnection();
			c.setAutoCommit(false);
			String sql = "select * from solicitudservicio where ticket = ? and idservicio = ?";
			pstmt = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, ticket);
			pstmt.setInt(2, idservicio);
			ResultSet rs = pstmt.executeQuery();
			c.close();
			if (rs.next()) {
				solicit = new solicitudServicio(rs.getInt("ticket"), rs.getInt("idservicio"));
			}
			pstmt.close();
			rs.close();
			return solicit;
		} catch (Exception ex) {
			Logger.getLogger(solicitudServicioPersistence.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public void saveSolicitud(int ticket, int idservicio) {
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			getConnection();
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql ="INSERT INTO solicitudservicio (ticket,idservicio) " + "VALUES ('" + ticket + "','" + idservicio +"');";

			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception ex) {
			Logger.getLogger(solicitudServicioPersistence.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
