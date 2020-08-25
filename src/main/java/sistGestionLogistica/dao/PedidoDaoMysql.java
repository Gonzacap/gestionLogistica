package sistGestionLogistica.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistGestionLogistica.connection.DB;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.ItemDetalle;
import sistGestionLogistica.dominio.Pedido;

public class PedidoDaoMysql implements PedidoDao{
	private Connection conn ;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	@Override
	public Boolean save(Pedido pe) throws SQLException {
		
		String insert = "INSERT INTO pedido (numOrden,plantaDestino,fechaSolicitud,fechaEntrega,estado) VALUES(?,?,?,?,?)";
		try {
			
			conn = DB.getConexion();
			System.out.println("EJECUTA INSERT");
			pstmt= conn.prepareStatement(insert);
			pstmt.setInt(1,pe.getNumOrden());
			pstmt.setInt(2, pe.getPlantaDestino().getId());
			pstmt.setDate(3, Date.valueOf(pe.getFechaSolicitud()));
			pstmt.setDate(4,Date.valueOf(pe.getFechaEntrega()));
			pstmt.setString(7, pe.getEstado().toString());
			
			
			pstmt.executeUpdate();
			}
		 catch (SQLException e) {
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
	

	@Override
	public Boolean update(Pedido pe) throws SQLException {
		String update;
		return null;
	}


   @Override
	public Pedido buscarNumOrden(Integer numOrden) {
		String buscar = "SELECT * FROM pedido WHERE numOrden = ?";
		Pedido pe = new Pedido();
		pe.setNumOrden(-1);
		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscar);
			pstmt.setInt(1,numOrden);
			rs = pstmt.executeQuery();
			while(rs.next()){

				pstmt.setInt(1,pe.getNumOrden());
				pstmt.setInt(2, pe.getPlantaDestino().getId());
				pstmt.setDate(3, Date.valueOf(pe.getFechaSolicitud()));
				pstmt.setDate(4,Date.valueOf(pe.getFechaEntrega()));
				pstmt.setString(7, pe.getEstado().toString());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}

		 return pe;
		 
		}

	@Override
	public List<Pedido> buscarTodos() throws SQLException {
		String buscar = "SELECT * FROM pedido WHERE numOrden = ?";
	    List<Pedido> lista = new ArrayList<Pedido>();
	    
		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscar);
		
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Pedido pe = new Pedido();
				pstmt.setInt(1,pe.getNumOrden());
				pstmt.setInt(2, pe.getPlantaDestino().getId());
				pstmt.setDate(3, Date.valueOf(pe.getFechaSolicitud()));
				pstmt.setDate(4,Date.valueOf(pe.getFechaEntrega()));
				pstmt.setString(7, pe.getEstado().toString());
				
				lista.add(pe);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}

		 return lista;
	}

}
