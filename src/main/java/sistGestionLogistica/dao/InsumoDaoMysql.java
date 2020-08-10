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
import sistGestionLogistica.dominio.Insumo;
import sistGestionLogistica.dominio.InsumoGeneral;
import sistGestionLogistica.dominio.InsumoLiquido;

public class InsumoDaoMysql implements InsumoDao{
	
	private Connection conn ;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;	
	
	
	public Insumo save(Insumo i) throws SQLException {
		
		String insertInsumo = "INSERT INTO INSUMO (idInsumo,descripcion,unidadMedida,costo,precio) values(?,?,?,?)";
	

		try {
		        conn = DB.getConexion();
				System.out.println("EJECUTA INSERT");
				pstmt= conn.prepareStatement(insertInsumo);
				pstmt.setInt(1, i.getIdInsumo());
				pstmt.setString(2, i.getDescripcion());
				pstmt.setString(3, i.getUnidadMedida().toString());
				pstmt.setDouble(4, i.getCosto());
				pstmt.setDouble(5, i.getPrecio());
			    pstmt.executeUpdate();
			    if (i instanceof InsumoGeneral) {
			    	String peso = String.valueOf(((InsumoGeneral) i).getPeso());
			    	pstmt.execute("INSERT INTO insumoGeneral values("+i.getIdInsumo()+","+peso+");");
				}
			    else if(i instanceof InsumoLiquido) {
			    	String densidad =String.valueOf(((InsumoLiquido) i).getDensidad());
			    	pstmt.execute("INSERT INTO insumoLiqudido values("+i.getIdInsumo()+","+densidad+");");
			    	
			    }
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
		return i;
		
		
	}

	public Insumo update(Insumo i) throws SQLException {
		String updateInsumo =	" UPDATE INSUMO SET DESCRIPCION = ?, UNIDADMEDIDA =? ,COSTO= ? , PRECIO =?  WHERE idInsumo = ?";
		
		try {
			if(i.getIdInsumo()!=null && i.getIdInsumo()>0) {
				conn = DB.getConexion();
				System.out.println("EJECUTA UPDATE");
				pstmt= conn.prepareStatement(updateInsumo);
				pstmt.setString(1, i.getDescripcion());
				pstmt.setString(2, i.getUnidadMedida().toString());
				pstmt.setDouble(3, i.getCosto());
				pstmt.setDouble(4, i.getPrecio());
				pstmt.setInt(5, i.getIdInsumo());
			}
			pstmt.executeUpdate();
			
			if(i instanceof InsumoGeneral) {
				String peso = String.valueOf(((InsumoGeneral) i).getPeso());
		    	pstmt.execute("UPDATE insumoGeneral SET ID="+i.getIdInsumo()+", SET PESO = "+peso+" WHERE idInsumo = "+i.getIdInsumo()+");");
			}
			else if(i instanceof InsumoLiquido) {
				String densidad =String.valueOf(((InsumoLiquido) i).getDensidad());
		    	pstmt.execute("UPDATE  insumoLiqudido SET ID="+i.getIdInsumo()+", SET DENSIDAD="+densidad+");");
			}
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
		
	
		return i;
	
	}

	public Insumo buscarPorId(Integer id) throws SQLException {
	   
		return null;
	}

	public void borrar(Integer id) throws SQLException {
		String borrar = "DELETE FROM insumo i,insumoGeneral g,insumoLiquido l WHERE i.idInsumo = ? AND i.idInsumo=g.idInsumo OR i.idInsumo=l.idInsumo"; //FIJATE ESTO MILTON 
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

	public List<Insumo> buscarTodos() throws SQLException {
		
	  
		
		
		return null;
	}

}
