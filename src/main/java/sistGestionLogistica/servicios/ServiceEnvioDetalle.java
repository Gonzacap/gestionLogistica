package sistGestionLogistica.servicios;

import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.dao.EnvioDetalleDao;
import sistGestionLogistica.dao.EnvioDetalleDaoMysql;
import sistGestionLogistica.dominio.EnvioDetalle;


public class ServiceEnvioDetalle {
	
	private EnvioDetalleDao envioDao = new EnvioDetalleDaoMysql();
	
	public Boolean registrarItem(EnvioDetalle envio) throws SQLException {
		
		return this.envioDao.save(envio);
	}
	
	public Boolean actualizarItem(EnvioDetalle item) throws SQLException {
		
		return this.envioDao.update(item);
	}
		
	public EnvioDetalle buscarPorNumOrden(Integer numOrden) throws SQLException {
		return this.envioDao.buscarNumOrden(numOrden);
	}
	public List<EnvioDetalle> buscarTodos() throws SQLException{
		return this.envioDao.buscarTodos();
	}
}
