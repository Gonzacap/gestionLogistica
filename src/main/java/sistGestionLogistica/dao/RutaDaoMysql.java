package sistGestionLogistica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistGestionLogistica.connection.DB;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.Ruta;
import sistGestionLogistica.servicios.ServicePlanta;

public class RutaDaoMysql implements RutaDao{
	
	private Connection con ;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
			
			
	public Boolean update(Ruta r) {
		
		String updateRuta =" UPDATE ruta SET distancia = ? , duracionViaje =? , pesoMaximo =? , plantaOrigen=? , plantaDestino=?"
				+ " WHERE idRuta = ?";
		
		 con = DB.getConexion();
		 ps = null;
		
		try {
			 
			ps = con.prepareStatement(updateRuta);
			ps.setDouble(1, r.getDistancia());
			ps.setDouble(2, r.getDuracionViaje());
			ps.setDouble(3, r.getPesoMaximo());
			ps.setInt(4, r.getPlantaOrigen().getId());
			ps.setInt(5, r.getPlantaDestino().getId());
			ps.setInt(6, r.getIdRuta());
				
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void borrar(Integer id) {
		String borrar = "DELETE * FROM ruta WHERE idRuta="+id;
		 try {
	            con=DB.getConexion();
	            ps=con.prepareStatement(borrar);
	            ps.executeUpdate();
	        } catch (Exception e) {
	        }
		 finally {
				try {
					if(ps!=null) ps.close();
					if(con!=null) con.close();				
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
		}
		
	}
	public List<Ruta> buscarTodos() {
		 String select_todos ="SELECT * FROM ruta";
		List<Ruta> lista = new ArrayList<Ruta>();
		 con = DB.getConexion();
		
		ResultSet rs = null;
		try {
			ps= con.prepareStatement(select_todos);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				Integer id= rs.getInt("idRuta");
				Double distancia = rs.getDouble("distancia");
				Double duracion = rs.getDouble("duracionViaje");
				Double pesoMax = rs.getDouble("pesoMaximo");
				Integer origen = rs.getInt("plantaOrigen");
				Integer destino = rs.getInt("plantaDestino");
				
				//buscamos las plantas
				Planta buscarPlantaOrigen = new Planta(origen,"");
				Planta buscarPlantaDestino = new Planta(destino,"");
				
				ServicePlanta sp = new ServicePlanta();
				
				Planta plantaOrigen = sp.buscarPlanta(buscarPlantaOrigen).get(0);
				Planta plantaDestino = sp.buscarPlanta(buscarPlantaDestino).get(0);
				
				//inicializamos la ruta
				Ruta c = new Ruta(id,distancia,duracion,pesoMax,plantaOrigen,plantaDestino);
				
				//agregamos la ruta a la lista
				lista.add(c);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(con!=null) con.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
		System.out.println("Resultado "+lista);
		return lista;
	}

	public Boolean save(Ruta r) throws SQLException {
		
		String insertRuta = "INSERT INTO ruta (distancia,duracionViaje,pesoMaximo,plantaOrigen,plantaDestino) VALUES (?,?,?,?,?)";
		
		 con = DB.getConexion();
		 ps = null;
		
		try {
			
			ps= con.prepareStatement(insertRuta);
			ps.setDouble(1, r.getDistancia());
		 	ps.setDouble(2, r.getDuracionViaje());
			ps.setDouble(3, r.getPesoMaximo());
			ps.setInt(4, r.getPlantaOrigen().getId());
			ps.setInt(5, r.getPlantaDestino().getId());	 
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
