package sistGestionLogistica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistGestionLogistica.connection.DB;
import sistGestionLogistica.dominio.EnvioDetalle;
import sistGestionLogistica.servicios.ServiceCamion;


public class EnvioDetalleDaoMysql implements EnvioDetalleDao {
	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public Boolean save(EnvioDetalle envio) throws SQLException {
		String insert = "INSERT INTO enviodetalle(numOrden,camionAsignado,costoEnvio) VALUES(?,?,?) ";
		try {

			con = DB.getConexion();
			System.out.println("EJECUTA INSERT");
			ps = con.prepareStatement(insert);

			ps.setInt(1, envio.getNumOrden() );
			ps.setInt(2, envio.getCamionAsignado().getId() );
			ps.setDouble(3, envio.getCostoEnvio());
		
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
	public List<EnvioDetalle> buscarNumOrden(Integer numOrden) throws SQLException {
		String buscar = "SELECT * FROM enviodetalle WHERE numOrden = ?";
		List<EnvioDetalle> lista=new ArrayList<EnvioDetalle>();
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

}
