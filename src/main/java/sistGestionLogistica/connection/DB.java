package sistGestionLogistica.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	private static final String url ="jdbc:mysql://db4free.net:3306/died2020?useSSL=false";
	private static final String user="recursante2021";
	private static final String pass="tpdied2020";
	
	private static Connection crearConexion(){
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn= DriverManager.getConnection(url,user,pass);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return conn;
	}
	
	public static Connection getConexion() {
		
		return crearConexion();
	}
}
