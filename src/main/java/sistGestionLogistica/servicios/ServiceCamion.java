package sistGestionLogistica.servicios;

import com.mysql.cj.jdbc.Driver;



import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import sistGestionLogistica.dominio.*;
import sistGestionLogistica.comparator.CompareCamion;
import sistGestionLogistica.dao.*;


public class ServiceCamion {

	private CamionDao camionDao = new CamionDaoMysql();
	

	 
	
	public ServiceCamion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Boolean crearCamion(Camion c) throws SQLException {
		
		// si hay alguna regla de negocio que indque que no se 
		// puede agregar un camion si no se cumplen determinadas
		// condiciones en otras entidades o reglas 
		// se valida aquí
		
	

			return this.camionDao.save(c);
	}
	
	public Boolean editarCamion(Camion c) throws SQLException {
		
		
		return this.camionDao.update(c);
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
	
	public PriorityQueue<Camion> obtenerColaCamiones() throws SQLException{
		Comparator<Camion> comparator = new CompareCamion();
		PriorityQueue<Camion> cola= new PriorityQueue<Camion>(comparator);
		List<Camion> lista = this.buscarCamion(new Camion(-1,"","","",-1.0,-1.0,-1,LocalDate.MIN));
		
		for(Camion c : lista) {
			cola.add(c);
		}
		
		
		
		return cola;
	}
	public List<Camion> buscarCamion(Camion cam) throws SQLException {
		
		//inicializamos predicados
		Predicate<Camion> filtroId = (t) -> (true);
		Predicate<Camion> filtroPatente = (t) -> (true);
		Predicate<Camion> filtroMarca = (t) -> (true);
		Predicate<Camion> filtroModelo = (t) -> (true);
		Predicate<Camion> filtroCostoKM = (t) -> (true);
		Predicate<Camion> filtroCostoHs = (t) -> (true);
		Predicate<Camion> filtroKM = (t) -> (true);
		Predicate<Camion> filtroFecha = (t) -> (true);
		
		//Aplicamos Filtros solo si corresponde
		if(cam.getId()>=0) filtroId = (t) -> (cam.getId().equals(t.getId()));
		if(!cam.getPatente().equals("")) filtroPatente = (t) -> (cam.getPatente().equals(t.getPatente()));
		if(!cam.getMarca().equals("")) filtroMarca = (t) -> (cam.getMarca().equals(t.getMarca()));
		if(!cam.getModelo().equals("")) filtroModelo = (t) -> (cam.getModelo().equals(t.getModelo()));
		if(cam.getCostoKM() >=0) filtroCostoKM = (t) -> (cam.getCostoKM().equals(t.getCostoKM()));
		if(cam.getCostoHora() >= 0) filtroCostoHs = (t) -> (cam.getCostoHora().equals(t.getCostoHora()));
		if(cam.getKm() >= 0) filtroKM = (t) -> (cam.getKm().equals(t.getKm()));
		if(!cam.getFechaCompra().equals(LocalDate.MIN)) filtroFecha = (t) -> (cam.getFechaCompra().equals(t.getFechaCompra()));
		
		
	
		
		
		return camionDao.buscarTodos().stream().filter(filtroId)
				.filter(filtroPatente).filter(filtroMarca)
				.filter(filtroModelo).filter(filtroCostoKM)
				.filter(filtroCostoHs).filter(filtroKM)
				.filter(filtroFecha).collect(Collectors.toList());
		
		

	}
	
	

}
