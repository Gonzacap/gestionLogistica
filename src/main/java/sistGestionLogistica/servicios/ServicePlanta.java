package sistGestionLogistica.servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import sistGestionLogistica.dao.CamionDao;
import sistGestionLogistica.dao.CamionDaoMysql;
import sistGestionLogistica.dao.PlantaDao;
import sistGestionLogistica.dao.PlantaDaoMysql;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.Planta;

public class ServicePlanta {
	private PlantaDao plantaDao = new PlantaDaoMysql();

	public ServicePlanta() {
		// TODO Auto-generated constructor stub
	}
	
	public Boolean registrarPlanta(Planta p) {
		return this.plantaDao.save(p);		
	}
	
	public List<Planta> buscarPlanta(Planta p){
		
		//inicializamos predicados
		Predicate<Planta> filtroId = (t) -> (true);
		Predicate<Planta> filtroNombre = (t) -> (true);
		
		//Aplicamos Filtros solo si corresponde
		if(p.getId()>=0) filtroId = (t) -> (p.getId().equals(t.getId()));
		if(!p.getNombre().isBlank()) filtroNombre = (t) -> (p.getNombre().equals(t.getNombre()));
		
		return plantaDao.buscarTodos().stream()
				.filter(filtroId).filter(filtroNombre).collect(Collectors.toList());
	}
	public Planta buscarPorId(Integer id) throws SQLException {
		return plantaDao.buscarPorId(id);
		
	}
	public void borrar(int id) throws SQLException {
		
		plantaDao.borrar(id);
		
	}

}
