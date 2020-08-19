package sistGestionLogistica.servicios;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import sistGestionLogistica.dao.StockInsumoDao;
import sistGestionLogistica.dao.StockInsumoDaoMysql;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.StockInsumo;


public class ServiceStockInsumo {
	
	private StockInsumoDao stockDao = new StockInsumoDaoMysql();

	public ServiceStockInsumo() {
		// TODO Auto-generated constructor stub
	}
	public Boolean crearStock(StockInsumo stock) throws SQLException {
		
			return this.stockDao.save(stock);
	}
	public Boolean existeStock(Integer idPlanta, Integer idInsumo) throws SQLException {
		
		return this.stockDao.existeStock(idPlanta, idInsumo);
		
	}
	public Integer stockTotal(Integer idInsumo) {
		return stockDao.stockTotal(idInsumo);
	}
	public List<StockInsumo> faltantes(Integer idPlanta, Integer idInsumo ){
		//inicializamos predicados
		Predicate<StockInsumo> filtroIdPlanta = (t) -> (true);
		Predicate<StockInsumo> filtroIdInsumo = (t) -> (true);
		
		//Aplicamos Filtros solo si corresponde
		if(idPlanta>=0) filtroIdPlanta = (t) -> (idPlanta.equals(t.getPlanta().getId()));
		if(idInsumo >=0) filtroIdInsumo = (t) -> (idInsumo.equals(t.getInsumo().getIdInsumo()));
		
	
		return stockDao.faltantes().stream().filter(filtroIdPlanta).filter(filtroIdInsumo).collect(Collectors.toList());
	}

}
