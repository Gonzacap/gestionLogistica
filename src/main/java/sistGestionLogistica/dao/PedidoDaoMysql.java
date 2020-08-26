package sistGestionLogistica.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import sistGestionLogistica.connection.DB;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.ItemDetalle;
import sistGestionLogistica.dominio.Pedido;
import sistGestionLogistica.enums.EstadoPedido;
import sistGestionLogistica.servicios.ServicePlanta;

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
		String update = "UPDATE pedido SET plantaDestino= ? , fechaSolicitud=?, fechaEntrega=? , estado=?  WHERE numOrden =?";
		try {
			
			if(pe.getNumOrden() != null && pe.getNumOrden()>0) {
				conn = DB.getConexion();
				System.out.println("EJECUTA update");
				pstmt= conn.prepareStatement(update);
				
				pstmt.setInt(1, pe.getPlantaDestino().getId());
				pstmt.setDate(2, Date.valueOf(pe.getFechaSolicitud()));
				pstmt.setDate(3,Date.valueOf(pe.getFechaEntrega()));
				pstmt.setString(4, pe.getEstado().toString());
				pstmt.setInt(5,pe.getNumOrden());
			}
			
			
			
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
	public Pedido buscarNumOrden(Integer numOrden) {
		String buscar = "SELECT * FROM pedido WHERE numOrden = ?";
		Pedido pe = new Pedido();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ServicePlanta sp = new ServicePlanta();
		pe.setNumOrden(-1);
		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscar);
			pstmt.setInt(1,numOrden);
			rs = pstmt.executeQuery();
			while(rs.next()){
              
				pe.setNumOrden(rs.getInt("numOrden"));
				pe.setPlantaDestino(sp.buscarPorId(rs.getInt("PlantaDestino")));
				pe.setFechaSolicitud(LocalDate.parse((CharSequence) rs.getString("fechaSolicitud"), formatter));
				pe.setFechaEntrega(LocalDate.parse((CharSequence) rs.getString("fechaEntrega"), formatter));
				pe.setEstado(EstadoPedido.valueOf(rs.getString("estado")));
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
		String buscar = "SELECT * FROM pedido ";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		ServicePlanta sp = new ServicePlanta();
	    List<Pedido> lista = new ArrayList<Pedido>();
	    ItemDetalleDao daoItem = new ItemDetalleDaoMysql();
	    
		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscar);
		
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Pedido pe = new Pedido();
				pe.setNumOrden(rs.getInt("numOrden"));
				pe.setPlantaDestino(sp.buscarPorId(rs.getInt("PlantaDestino")));
				pe.setFechaSolicitud(LocalDate.parse((CharSequence) rs.getString("fechaSolicitud"), formatter));
				pe.setFechaEntrega(LocalDate.parse((CharSequence) rs.getString("fechaEntrega"), formatter));
				pe.setEstado(EstadoPedido.valueOf(rs.getString("estado")));
				pe.setItem(daoItem.buscarNumOrden(pe.getNumOrden()));
				
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



	public void cambiarEstado(Integer numOrden, EstadoPedido estado) {
		String update =	" UPDATE pedido SET estado = ?  WHERE numOrden = ?";
		try {
			
				conn = DB.getConexion();
				System.out.println("EJECUTA UPDATE ESTADO");
				pstmt= conn.prepareStatement(update);
				pstmt.setString(1, estado.toString());
				pstmt.setInt(2, numOrden);
		
			pstmt.executeUpdate();
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();				
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		
			
	}

}
