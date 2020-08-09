package sistGestionLogistica.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistGestionLogistica.connection.*;
import sistGestionLogistica.dominio.Camion;
import com.mysql.cj.jdbc.Driver;


public class CamionDaoMysql implements CamionDao{
	
	private Connection conn ;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	

	public Camion save(Camion c) throws SQLException {
		
		String insertCamion = "INSERT INTO camion (PATENTE,MARCA,MODELO,KM,COSTO_KM,COSTO_HORA,FECHA_COMPRA) VALUES (?,?,?,?,?,?,?)";
		String updateCamion =	" UPDATE camion SET PATENTE = ?, MARCA =? ,MODELO = ? , KM =?,COSTO_KM=? ,COSTO_HORA=? ,FECHA_COMPRA=?  WHERE ID = ?";
		
		try {
		
				conn = DB.getConexion();
				System.out.println("EJECUTA INSERT");
				pstmt= conn.prepareStatement(insertCamion);
				
				pstmt.setString(1, c.getPatente());
				pstmt.setString(2, c.getMarca());
				pstmt.setString(3, c.getModelo());
				pstmt.setInt(4, c.getKm());
				pstmt.setDouble(5, c.getCostoKM());
				pstmt.setDouble(6,c.getCostoHora());
			
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
		return c;
	}



	public Camion buscarPorPatente(String patente)  throws SQLException{ 
		//String buscar = "SELECT * FROM camion WHERE PATENTE = ?" + patente;
		String buscar = "SELECT * FROM camion WHERE PATENTE = ?";
		Camion c = new Camion();
		c.setId(-1);
		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscar);
			pstmt.setString(1, patente);
			rs = pstmt.executeQuery();
			while(rs.next()){
//				c.setId(rs.getInt(1));
//				c.setPatente(rs.getString(2));
//				c.setMarca(rs.getString(3));
//				c.setModelo(rs.getString(4));
//				c.setCostoKM(rs.getDouble(5));
//				c.setCostoHora(rs.getDouble(6));
//				c.setFechaCompra(rs.getDate(7).toLocalDate());
				
				c.setId(rs.getInt("ID"));
				c.setMarca(rs.getString("MARCA"));
				c.setModelo(rs.getString("MODELO"));
				c.setPatente(rs.getString("PATENTE"));
				c.setKm(rs.getInt("KM"));
				c.setCostoKM(rs.getDouble("COSTO_KM"));
				c.setCostoHora(rs.getDouble("COSTO_HORA"));
				c.setFechaCompra(rs.getDate("FECHA_COMPRA").toLocalDate());
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

		return c;
	}
	
	public Camion buscarPorId(Integer id)  throws SQLException{ 
		//String buscar = "SELECT * FROM camion WHERE PATENTE = ?" + patente;
		String buscar = "SELECT * FROM camion WHERE ID = ?";
		Camion c = new Camion();
		c.setId(-1);
		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscar);
			pstmt.setInt(1,id);
			rs = pstmt.executeQuery();
			while(rs.next()){
//				c.setId(rs.getInt(1));
//				c.setPatente(rs.getString(2));
//				c.setMarca(rs.getString(3));
//				c.setModelo(rs.getString(4));
//				c.setCostoKM(rs.getDouble(5));
//				c.setCostoHora(rs.getDouble(6));
//				c.setFechaCompra(rs.getDate(7).toLocalDate());
				
				c.setId(rs.getInt("ID"));
				c.setMarca(rs.getString("MARCA"));
				c.setModelo(rs.getString("MODELO"));
				c.setPatente(rs.getString("PATENTE"));
				c.setKm(rs.getInt("KM"));
				c.setCostoKM(rs.getDouble("COSTO_KM"));
				c.setCostoHora(rs.getDouble("COSTO_HORA"));
				c.setFechaCompra(rs.getDate("FECHA_COMPRA").toLocalDate());
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

		return c;
	}


	public void borrar(Integer id) throws SQLException {
		//String borrar = "DELETE * FROM camion WHERE ID="+id;
		String borrar = "DELETE FROM camion WHERE ID=?";
		 try {
	            conn=DB.getConexion();
	            System.out.println("BORRADO?");
	            pstmt=conn.prepareStatement(borrar);
	            pstmt.setInt(1,id);
	            pstmt.executeUpdate();
	            System.out.println("BORRADO");
	        } catch (Exception e) {
	        }
		 finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn!=null) conn.close();				
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
		}
		
	}



	public List<Camion> buscarTodos() throws SQLException {
		 String select_todos ="SELECT * FROM camion";
		List<Camion> lista = new ArrayList<Camion>();
		 conn = DB.getConexion();
		
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(select_todos);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Camion c = new Camion();

				c.setId(rs.getInt("ID"));
				c.setMarca(rs.getString("MARCA"));
				c.setModelo(rs.getString("MODELO"));
				c.setPatente(rs.getString("PATENTE"));
				c.setKm(rs.getInt("KM"));
				c.setCostoKM(rs.getDouble("COSTO_KM"));
				c.setCostoHora(rs.getDouble("COSTO_HORA"));
				c.setFechaCompra(rs.getDate("FECHA_COMPRA").toLocalDate());
				lista.add(c);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
		System.out.println("Resultado "+lista);
		return lista;
	}


	@Override
	public Camion update(Camion c) throws SQLException {
		String updateCamion =	" UPDATE camion SET PATENTE = ?, MARCA =? ,MODELO = ? , KM =?,COSTO_KM=? ,COSTO_HORA=? ,FECHA_COMPRA=?  WHERE ID = ?";
		try {
			if(c.getId()!=null && c.getId()>0) {
				conn = DB.getConexion();
				System.out.println("EJECUTA UPDATE");
				pstmt= conn.prepareStatement(updateCamion);
				pstmt.setString(1, c.getPatente());
				pstmt.setString(2, c.getMarca());
				pstmt.setString(3, c.getModelo());
				pstmt.setInt(4, c.getKm());
				pstmt.setDouble(5, c.getCostoKM());
				pstmt.setDouble(6,c.getCostoHora());
				pstmt.setDate(7, Date.valueOf(c.getFechaCompra()));
				pstmt.setInt(8, c.getId());
			}
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
		
	
		return c;
	}
	

}
