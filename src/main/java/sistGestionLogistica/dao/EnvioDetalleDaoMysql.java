package sistGestionLogistica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import sistGestionLogistica.connection.DB;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.EnvioDetalle;
import sistGestionLogistica.dominio.Ruta;
import sistGestionLogistica.servicios.ServiceCamion;
import sistGestionLogistica.servicios.ServiceRuta;


public class EnvioDetalleDaoMysql implements EnvioDetalleDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public Boolean save(EnvioDetalle envio) throws SQLException {
		String insert = "INSERT INTO enviodetalle(numOrden,camionAsignado,costoEnvio,plantaOrigen) VALUES(?,?,?,?) ";
		ServiceCamion sc = new ServiceCamion();
		ServiceRuta sr = new ServiceRuta();
		try {

			con = DB.getConexion();
			System.out.println("EJECUTA INSERT");
			ps = con.prepareStatement(insert);

			ps.setInt(1, envio.getNumOrden() );
			ps.setInt(2, envio.getCamionAsignado().getId() );
			ps.setDouble(3, envio.getCostoEnvio());
			ps.setInt(4, envio.getPlantaOrigen());
			
			
			if(envio.getRutaAsignada() !=  null) {
				for(int i=0; i< envio.getRutaAsignada().size(); i++) {
					//guardamos la ruta asignada ordenada
					this.agregarRutaPedido(envio.getRutaAsignada().get(i).getIdRuta(),envio.getNumOrden() , i);
				}
			}
			
			sc.sumarKM(envio.getCamionAsignado().getId(), sr.kilometrosRuta(envio.getRutaAsignada()).intValue());
		
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public Boolean update(EnvioDetalle envio) throws SQLException {
		String update = "UPDATE  enviodetalle SET camionAsignado=?,costoEnvio=? where numOrden=? ";
		try {

			con = DB.getConexion();
			System.out.println("EJECUTA INSERT");
			ps = con.prepareStatement(update);

		
			ps.setInt(1, envio.getCamionAsignado().getId() );
			ps.setDouble(2, envio.getCostoEnvio());
			ps.setInt(3, envio.getNumOrden() );
		
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public EnvioDetalle buscarNumOrden(Integer numOrden) throws SQLException {
		String buscar = "SELECT * FROM enviodetalle WHERE numOrden = ?";
		EnvioDetalle envio = new EnvioDetalle();
		ServiceCamion sc= new ServiceCamion();
		envio.setNumOrden(-1);
		try {
			con = DB.getConexion();
			ps = con.prepareStatement(buscar);
			ps.setInt(1,numOrden);
			rs = ps.executeQuery();
			while(rs.next()){
				
				envio.setNumOrden(rs.getInt("numOrden"));
				envio.setCamionAsignado(sc.buscarPorId(rs.getInt("camionAsignado")));;
				envio.setCostoEnvio(rs.getDouble("costoEnvio"));
				envio.setPlantaOrigen(rs.getInt("plantaOrigen"));
				envio.setRutaAsignada(this.obtenerListaRutaPedido(envio.getNumOrden()));
				
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		finally {
			try {
				if(ps!=null) ps.close();
				if(con!=null) con.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return envio;
	}

	@Override
	public List<EnvioDetalle> buscarTodos() throws SQLException {
		String buscar = "SELECT * FROM enviodetalle";
		List<EnvioDetalle> lista=new ArrayList<EnvioDetalle>();
		EnvioDetalle envio = new EnvioDetalle();
		ServiceCamion sc= new ServiceCamion();
		envio.setNumOrden(-1);
		try {
			con = DB.getConexion();
			ps = con.prepareStatement(buscar);
			rs = ps.executeQuery();
			while(rs.next()){
				
				envio.setNumOrden(rs.getInt("numOrden"));
				envio.setCamionAsignado(sc.buscarPorId(rs.getInt("camionAsignado")));;
				envio.setCostoEnvio(rs.getDouble("costoEnvio"));
				envio.setPlantaOrigen(rs.getInt("plantaOrigen"));
				envio.setRutaAsignada(this.obtenerListaRutaPedido(envio.getNumOrden()));
				lista.add(envio);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		finally {
			try {
				if(ps!=null) ps.close();
				if(con!=null) con.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return lista;
	}
	private void agregarRutaPedido(Integer idRuta, Integer numOrden, Integer orden) {
		Connection con2=null;
		PreparedStatement ps2 = null;
		String insert = "INSERT INTO pertenecea(idRuta,numOrden,orden) VALUES(?,?,?) ";
		try {
			con2 = DB.getConexion();
			System.out.println("EJECUTA INSERT");
			ps2 = con2.prepareStatement(insert);

			ps2.setInt(1, idRuta);
			ps2.setInt(2, numOrden );
			ps2.setDouble(3, orden);
		
			ps2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (ps2 != null)
					ps2.close();
				if (con2 != null)
					con2.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
	}
	public List<Ruta> obtenerListaRutaPedido(Integer numOrden){
		Connection con2=null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		List<Ruta> lista= new ArrayList<Ruta>();
		String buscar = "SELECT * FROM pertenecea WHERE numOrden = ? ORDER BY orden ASC";
		RutaDao daoRuta = new RutaDaoMysql();
		try {
			con2 = DB.getConexion();
			ps2 = con2.prepareStatement(buscar);
			ps2.setInt(1,numOrden);
			rs2 = ps2.executeQuery();
			while(rs2.next()){
				lista.add(daoRuta.buscarPorId(rs.getInt("idRuta")));
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		finally {
			try {
				if(ps2!=null) ps2.close();
				if(con2!=null) con2.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return lista;
	}
  
	public List<Camion> camionesAsignados(){
		String camiones = "SELECT camionAsignado FROM enviodetalle e , pedido p "
				+ "WHERE e.numOrden=p.numOrden "
				+ "AND  p.estado = 'PROCESADA' ";
		List<Camion> lista = new ArrayList<Camion>();
		ServiceCamion sc = new ServiceCamion();
		
		try {
			con = DB.getConexion();
			ps = con.prepareStatement(camiones);
			rs = ps.executeQuery();
			while(rs.next()){
				
			Camion c = new Camion();
			c= sc.buscarPorId(rs.getInt("camionAsignado"));
				lista.add(c);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		finally {
			try {
				if(ps!=null) ps.close();
				if(con!=null) con.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return lista;
		
		
	}
		
	
}
