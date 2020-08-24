package sistGestionLogistica.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sistGestionLogistica.connection.DB;
import sistGestionLogistica.dominio.ItemDetalle;

public class ItemDetalleDaoMysql implements ItemDetalleDao {
	
	private Connection conn ;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@Override
	public Boolean save(ItemDetalle item) {
		String insert ="INSERT INTO itemDetalle(numOrden,insumo,cantidad,precioItem) VALUES(?,?,?,?)";
		try {
			
			conn = DB.getConexion();
			System.out.println("EJECUTA INSERT");
			pstmt= conn.prepareStatement(insert);
			
			pstmt.setInt(1, item.getNumOrden());
			pstmt.setInt(2, item.getInsumo().getIdInsumo());
			pstmt.setInt(3, item.getCantidad());
			pstmt.setDouble(4, item.getPrecio());	
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
   
	
	
}
