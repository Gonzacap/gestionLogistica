package sistGestionLogistica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistGestionLogistica.connection.DB;
import sistGestionLogistica.dominio.Insumo;
import sistGestionLogistica.dominio.InsumoGeneral;
import sistGestionLogistica.dominio.InsumoLiquido;
import sistGestionLogistica.enums.UnidadMedida;

public class InsumoDaoMysql implements InsumoDao{
	
	private Connection conn ;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;	
	
	
	public Insumo save(Insumo i) throws SQLException {
		
		String insertInsumo = "INSERT INTO insumo (idInsumo,descripcion,unidadMedida,costo,precio) values(?,?,?,?,?)";
	

		try {
		        conn = DB.getConexion();
				System.out.println("EJECUTA INSERT INSUMO");
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
			    	pstmt.execute("INSERT INTO insumoLiquido values("+i.getIdInsumo()+","+densidad+");");
			    	
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
		String updateInsumo =	" UPDATE insumo SET DESCRIPCION = ?, UNIDADMEDIDA =? ,COSTO= ? , PRECIO =?  WHERE idInsumo = ?";
		
		try {
			if(i.getIdInsumo()!=null && i.getIdInsumo()>0) {
				conn = DB.getConexion();
				System.out.println("EJECUTA UPDATE INSUMO");
				pstmt= conn.prepareStatement(updateInsumo);
				pstmt.setString(1, i.getDescripcion());
				pstmt.setString(2, i.getUnidadMedida().toString());
				pstmt.setDouble(3, i.getCosto());
				pstmt.setDouble(4, i.getPrecio());
				pstmt.setInt(5, i.getIdInsumo());
			}
			pstmt.executeUpdate();
			
			if(i instanceof InsumoGeneral) {
				
		    	pstmt.execute("UPDATE insumoGeneral SET idInsumo="+i.getIdInsumo()+", PESO = "+ ((InsumoGeneral) i).getPeso()+" WHERE idInsumo = "+i.getIdInsumo()+";");
			}
			else if(i instanceof InsumoLiquido) {
				
		    	pstmt.execute("UPDATE  insumoLiquido SET idInsumo="+i.getIdInsumo()+", densidad=" + ((InsumoLiquido) i).getDensidad() +" WHERE idInsumo= "+i.getIdInsumo()+";");
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

		String borrar = "DELETE FROM insumo WHERE idInsumo = ?";
		String borrarLiquido = "DELETE FROM insumoLiquido WHERE idInsumo= ? ";
		String borrarGeneral = "DELETE FROM insumoGeneral WHERE idInsumo= ? ";
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try {
	            conn=DB.getConexion();
	            System.out.println("INSUMO BORRADO?");
	           
	            //Borramos el insumo Liquido
	            ps1 = conn.prepareStatement(borrarLiquido);
	            ps1.setInt(1, id);
	            ps1.executeUpdate();
	            
	            //Borramos el insumo General
	            ps2 = conn.prepareStatement(borrarGeneral);
	            ps2.setInt(1, id);
	            ps2.executeUpdate();
	            
	            //borramos el insumo de la tabla insumo
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
		String select_todos ="SELECT * FROM( SELECT i.idInsumo, i.descripcion, i.unidadMedida, i.costo, i.precio, il.densidad, ig.peso FROM insumo i LEFT JOIN insumoGeneral ig ON ig.idInsumo = i.idInsumo LEFT JOIN insumoLiquido il ON  il.idInsumo=i.idInsumo) AS alias;";
		 
		List<Insumo> lista = new ArrayList<Insumo>();
		 conn = DB.getConexion();
		
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(select_todos);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Insumo i;
				
				if(rs.getDouble("peso") >0) {
					i = new InsumoGeneral();
					((InsumoGeneral)i).setPeso(rs.getDouble("peso"));
				}
				else{
					i = new InsumoLiquido();
					((InsumoLiquido) i).setDensidad(rs.getDouble("densidad"));
				}
				
				
				 i.setIdInsumo(rs.getInt("idInsumo"));
				 i.setDescripcion(rs.getString("descripcion"));
				 i.setUnidadMedida(UnidadMedida.valueof(rs.getString("unidadMedida"))); 
				 i.setCosto(rs.getDouble("costo")); 
			     i.setPrecio(rs.getDouble("precio"));	
			
				
				lista.add(i);
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
