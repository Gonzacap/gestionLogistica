package sistGestionLogistica.servicios;


import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import sistGestionLogistica.dao.RutaDao;
import sistGestionLogistica.dao.RutaDaoMysql;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.Ruta;

public class ServiceRuta {
	private RutaDao rutaDao = new RutaDaoMysql();

	public ServiceRuta() {
		// TODO Auto-generated constructor stub
	}
	
	public Boolean registrarRuta(Ruta r) throws SQLException {
		return this.rutaDao.save(r);		
	}
	
public List<Ruta> buscarRuta(Ruta r) throws SQLException{
		
		//inicializamos predicados
		Predicate<Ruta> filtroId = (t) -> (true);
		Predicate<Ruta> filtroDistancia = (t) -> (true);
		Predicate<Ruta> filtroDuracionViaje = (t) -> (true);
		Predicate<Ruta> filtroPesoMaximo = (t) -> (true);
		Predicate<Ruta> filtroPlantaOrigen = (t) -> (true);
		Predicate<Ruta> filtroPlantaDestino = (t) -> (true);
		
		//Aplicamos Filtros solo si corresponde
		if(r.getIdRuta()>=0) filtroId = (t) -> (r.getIdRuta().equals(t.getIdRuta()));
		if(r.getDistancia()>=0) filtroDistancia = (t) -> (r.getDistancia().equals(t.getDistancia()));
		if(r.getDuracionViaje()>=0) filtroDuracionViaje = (t) -> (r.getDuracionViaje().equals(t.getDuracionViaje()));
		if(r.getPesoMaximo()>=0) filtroPesoMaximo = (t) -> (r.getPesoMaximo().equals(t.getPesoMaximo()));
		if(r.getPlantaOrigen().getId()>=0) filtroPlantaOrigen = (t) -> (r.getPlantaOrigen().equals(t.getPlantaOrigen()));
		if(r.getPlantaDestino().getId()>=0) filtroPlantaDestino = (t) -> (r.getPlantaDestino().equals(t.getPlantaDestino()));
		
		
		return rutaDao.buscarTodos().stream()
				.filter(filtroId).filter(filtroDistancia)
				.filter(filtroDuracionViaje).filter(filtroPesoMaximo)
				.filter(filtroPlantaOrigen).filter(filtroPlantaDestino)
				.collect(Collectors.toList());
	}
	
public Ruta buscarPorId(Integer id) throws SQLException {
	return this.rutaDao.buscarPorId(id);
}

}
