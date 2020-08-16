package sistGestionLogistica.dao;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistGestionLogistica.connection.DB;
import sistGestionLogistica.dominio.Planta;

public class PlantaDaoMysql implements PlantaDao {
	private Connection conn ;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public Boolean save(Planta p) {
		
		String insert = "INSERT INTO planta (nombre) VALUES (?)";
//		String update =	" UPDATE planta SET nombre = ? WHERE id = ?";
		
		try {
			
				conn = DB.getConexion();
				System.out.println("EJECUTA INSERT");
				pstmt= conn.prepareStatement(insert);
				
				pstmt.setString(1, p.getNombre());
			
			
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

	//Faltaria agregar el StockInsumos
	public List<Planta> buscarTodos() {
		 String select_todos ="SELECT * FROM planta";
			List<Planta> lista = new ArrayList<Planta>();
			 conn = DB.getConexion();
			
			ResultSet rs = null;
			try {
				pstmt= conn.prepareStatement(select_todos);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Planta p = new Planta(rs.getInt("idPlanta"),rs.getString("nombre"));
					lista.add(p);
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

	public Boolean update(Planta p) {
		String update =	" UPDATE planta SET nombre = ? WHERE id = ?";
		try {
			if(p.getId()!=null && p.getId()>0) {
				conn = DB.getConexion();
				System.out.println("EJECUTA UPDATE");
				pstmt= conn.prepareStatement(update);
				pstmt.setString(1, p.getNombre());
				pstmt.setInt(2, p.getId());
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


	@Override
	public Planta buscarPorId(Integer id)  throws SQLException{ 
		
		String buscar = "SELECT * FROM planta WHERE ID = ?";
		Planta p = new Planta();
		p.setId(-1);
		try {
			conn = DB.getConexion();
			pstmt = conn.prepareStatement(buscar);
			pstmt.setInt(1,id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				p.setId(rs.getInt("ID"));
				p.setNombre(rs.getString("NOMBRE"));
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

		return p;
	}
	
	public void borrar(Integer id) {
		
		String borrar = "DELETE FROM planta WHERE ID=?";
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
		 

}
