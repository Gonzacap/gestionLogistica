package sistGestionLogistica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistGestionLogistica.connection.*;
import sistGestionLogistica.dominio.Camion;

public class CamionDaoMysql implements CamionDao{
	
	private Connection conn ;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	

	@Override
	public Camion saveOrUpdate(Camion c) throws SQLException {
	
		String insertCamion = "INSERT INTO CAMION (PATENTE,MARCA,MODELO,KM) VALUES (?,?,?,?)";
		String updateCamion =	" UPDATE CAMION SET PATENTE = ?, MARCA =? ,MODELO = ? , KM =?  WHERE ID = ?";
		
		try {
			if(c.getId()!=null && c.getId()>0) {
				System.out.println("EJECUTA UPDATE");
				pstmt= conn.prepareStatement(updateCamion);
				pstmt.setString(1, c.getPatente());
				pstmt.setString(2, c.getMarca());
				pstmt.setString(3, c.getModelo());
				pstmt.setInt(4, c.getKm());
				pstmt.setInt(5, c.getId());
			}else {
				System.out.println("EJECUTA INSERT");
				pstmt= conn.prepareStatement(insertCamion);
				pstmt.setString(1, c.getPatente());
				pstmt.setString(2, c.getMarca());
				pstmt.setString(3, c.getModelo());
				pstmt.setInt(4, c.getKm());
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
		return c;
	}



	@Override
	public Camion buscarPorPatente(String patente)  throws SQLException{ 
		String buscar = "SELECT * FROM camion WHERE patente =" + patente;
		Camion c = new Camion();
		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscar);
			rs = pstmt.executeQuery();
			while(rs.next()){
				c.setId(rs.getInt(1));
				c.setPatente(rs.getString(2));
				c.setMarca(rs.getString(3));
				c.setModelo(rs.getString(4));
				c.setCostoKM(rs.getDouble(5));
				c.setCostoHora(rs.getDouble(6));
				c.setFechaCompra(rs.getDate(7));
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

	@Override
	public void borrar(Integer id) throws SQLException {
		String borrar = "DELETE * FROM CAMION WHERE ID="+id;
		 try {
	            conn=DB.getConexion();
	            pstmt=conn.prepareStatement(borrar);
	            pstmt.executeUpdate();
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



	@Override
	public List<Camion> buscarTodos() throws SQLException {
		 String select_todos ="SELECT * FROM CAMION";
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



	

}
