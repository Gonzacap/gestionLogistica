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
import sistGestionLogistica.dominio.Planta;

public class PlantaDaoMysql implements PlantaDao {
	private Connection conn ;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@Override
	public Boolean saveOrUpdate(Planta p) {
		
		String insert = "INSERT INTO planta (nombre) VALUES (?)";
		String update =	" UPDATE planta SET nombre = ? WHERE id = ?";
		
		try {
			if(p.getId()!=null && p.getId()>0) {
				conn = DB.getConexion();
				System.out.println("EJECUTA UPDATE");
				pstmt= conn.prepareStatement(update);
				pstmt.setString(1, p.getNombre());
				pstmt.setInt(2, p.getId());
			}else {
				conn = DB.getConexion();
				System.out.println("EJECUTA INSERT");
				pstmt= conn.prepareStatement(insert);
				
				pstmt.setString(1, p.getNombre());
			
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
	public Boolean buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		
	}
	//Faltaria agregar el StockInsumos
	@Override
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

}
