package sistGestionLogistica.servicios;

import java.sql.SQLException;


import sistGestionLogistica.dao.StockInsumoDao;
import sistGestionLogistica.dao.StockInsumoDaoMysql;
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

}
