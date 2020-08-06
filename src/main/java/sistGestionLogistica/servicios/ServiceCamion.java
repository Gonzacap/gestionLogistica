package sistGestionLogistica.servicios;
import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.dominio.*;
import sistGestionLogistica.dao.*;


public class ServiceCamion {

	private CamionDao camionDao = new CamionDaoMysql();
	
	
	public ServiceCamion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Camion crearCamion(Camion c) throws SQLException {
		// si hay alguna regla de negocio que indque que no se 
		// puede agregar un camion si no se cumplen determinadas
		// condiciones en otras entidades o reglas 
		// se valida aquí
			return this.camionDao.saveOrUpdate(c);
	}
	
	public Camion editarCamion(Camion c) throws SQLException {
		
		return this.camionDao.saveOrUpdate(c);
	}
	public Camion buscarPorPatente(String patente) throws SQLException {
		return camionDao.buscarPorPatente(patente);
		
	}
	public Camion buscarPorId(Integer id) throws SQLException {
		return camionDao.buscarPorId(id);
		
	}
	public void borrar(int id) throws SQLException {
		camionDao.borrar(id);
		
	}
	public List<Camion> buscarTodos() throws SQLException {
		return camionDao.buscarTodos();
	}
	
	

}
