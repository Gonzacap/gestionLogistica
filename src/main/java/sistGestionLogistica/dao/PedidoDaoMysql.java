package sistGestionLogistica.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sistGestionLogistica.connection.DB;
import sistGestionLogistica.dominio.ItemDetalle;
import sistGestionLogistica.dominio.Pedido;

public class PedidoDaoMysql implements PedidoDao{
	private Connection conn ;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	@Override
	public Boolean save(Pedido pe) throws SQLException {
		
		String insert = "INSERT INTO pedido (numOrden,plantaDestino,fechaSolicitud,fechaEntrega,camionAsignado,costoEnvio,estado) VALUES(?,?,?,?,?,?,?)";
		try {
			
			conn = DB.getConexion();
			System.out.println("EJECUTA INSERT");
			pstmt= conn.prepareStatement(insert);
			pstmt.setInt(1,pe.getNumOrden());
			pstmt.setInt(2, pe.getPlantaDestino().getId());
			pstmt.setDate(3, Date.valueOf(pe.getFechaSolicitud()));
			pstmt.setDate(4,Date.valueOf(pe.getFechaEntrega()));
			pstmt.setInt(5, pe.getCamionAsignado().getId());
			pstmt.setDouble(6, pe.getCostoEnvio());
			pstmt.setString(7, pe.getEstado().toString());
			
			ItemDetalleDao idd= new ItemDetalleDaoMysql(); 
			for(ItemDetalle item : pe.getItem()) {
				idd.save(item);
			}
			
			
		
			pstmt.executeUpdate();
			} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();	
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}
	}
	return true;
		
		
	}
	
	public Pedido buscarNumOrden(Integer numOrden) {
	
		
		
	 return null;
	 
	}

}
