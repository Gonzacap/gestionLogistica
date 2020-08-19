package sistGestionLogistica.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.connection.DB;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.StockInsumo;



public class StockInsumoDaoMysql implements StockInsumoDao{
	private Connection conn ;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@Override
	public Boolean save(StockInsumo s) throws SQLException {
		String insertStock = "INSERT INTO stockinsumo (planta,insumo,cantidad,puntoReposicion) VALUES (?,?,?,?)";
		
		
		try {
		
				conn = DB.getConexion();
				System.out.println("EJECUTA INSERT stock");
				pstmt= conn.prepareStatement(insertStock);
				
				pstmt.setInt(1,s.getPlanta().getId());
				pstmt.setInt(2,s.getInsumo().getIdInsumo());
				pstmt.setInt(3,s.getCantidad());
				pstmt.setInt(4,s.getPuntoReposicion());
			
				
			
				pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("el problema 1");
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();	
				
			}catch(SQLException e) {
				System.out.println("el problema 2");
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public Boolean update(StockInsumo stock) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existeStock(Integer idPlanta, Integer idInsumo) throws SQLException {
		String buscar = "SELECT * FROM stockinsumo WHERE planta = ? AND insumo = ?";
		Boolean res= true;
		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscar);
			pstmt.setInt(1, idPlanta);
			pstmt.setInt(2, idInsumo);
			rs = pstmt.executeQuery();
			res = rs.next();
			
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

		return res;
	}

	@Override
	public Integer stockTotal(Integer idInsumo) {
		String buscar = "SELECT SUM(cantidad) FROM stockinsumo WHERE insumo=?";
		Integer resultado= 0;
		
		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscar);
			pstmt.setInt(1, idInsumo);
			rs = pstmt.executeQuery();
			while(rs.next()){
				resultado = rs.getInt(1);
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

		return resultado;
	}
	
	

}
