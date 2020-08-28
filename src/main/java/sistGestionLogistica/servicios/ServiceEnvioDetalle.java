package sistGestionLogistica.servicios;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import sistGestionLogistica.comparator.CompareCamion;
import sistGestionLogistica.dao.EnvioDetalleDao;
import sistGestionLogistica.dao.EnvioDetalleDaoMysql;
import sistGestionLogistica.dominio.Camion;
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
	
    
  public Camion  asignarCamion() throws SQLException {
	  ServiceCamion sc = new ServiceCamion();
	  Comparator<Camion> comparator = new CompareCamion();
		PriorityQueue<Camion> cola= new PriorityQueue<Camion>(comparator);
		List<Camion> todosCamiones = sc.buscarCamion(new Camion(-1,"","","",-1.0,-1.0,-1,LocalDate.MIN));
		List<Camion> camionesAsignados = this.envioDao.camionesAsignados();
		
		for(Camion c: camionesAsignados) {
			todosCamiones.remove(c);
		}
			
		
		for(Camion c : todosCamiones) {
			cola.add(c);
		}
		
		return cola.poll();
		}
 
}
