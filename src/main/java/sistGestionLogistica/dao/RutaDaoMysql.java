package sistGestionLogistica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.connection.DB;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.Ruta;

public class RutaDaoMysql implements RutaDao{
	
	private Connection con ;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
			
			
	@Override
	public Ruta saveOrUpdate(Ruta r) {
		
		String updateRuta =" UPDATE ruta SET distancia = ? , duracionViaje =? , pesoMaximo =? , plantaOrigen=? , plantaDestino=?"
				+ " WHERE idRuta = ?";
		String insertRuta = "INSERT INTO ruta (distancia,duracionViaje,pesoMaximo,plantaOrigen,plantaDestino) VALUES (?,?,?,?,?)";
		
		 con = DB.getConexion();
		 ps = null;
		
		try {
			 if(r.getIdRuta() != null && r.getIdRuta() > 0) {
				 ps = con.prepareStatement(updateRuta);
				 ps.setDouble(1, r.getDistancia());
				 ps.setDouble(2, r.getDuracionViaje());
				 ps.setDouble(3, r.getPesoMaximo());
				 ps.setInt(4, r.getPlantaOrigen().getId());
				 ps.setInt(5, r.getPlantaDestino().getId());
				 ps.setInt(6, r.getIdRuta());
				 
			 }
			 else {
				 ps= con.prepareStatement(insertRuta);
				 ps.setDouble(1, r.getDistancia());
				 ps.setDouble(2, r.getDuracionViaje());
				 ps.setDouble(3, r.getPesoMaximo());
				 ps.setInt(4, r.getPlantaOrigen().getId());
				 ps.setInt(5, r.getPlantaDestino().getId());
				 
			 }
			 ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	@Override
	public Ruta buscarPorId(Integer id) {
		
		return null;
	}
	@Override
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
	@Override
	public List<Ruta> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
}
