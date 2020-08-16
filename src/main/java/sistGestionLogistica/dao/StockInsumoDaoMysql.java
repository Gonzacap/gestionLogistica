package sistGestionLogistica.dao;

import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.dominio.StockInsumo;



public class StockInsumoDaoMysql implements StockInsumoDao{

	@Override
	public Boolean save(Integer id, StockInsumo s) throws SQLException {
		 String insertStock = "INSERT INTO stockInsumo()";
		return null;
	}

	@Override
	public Boolean update(StockInsumo stock) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
