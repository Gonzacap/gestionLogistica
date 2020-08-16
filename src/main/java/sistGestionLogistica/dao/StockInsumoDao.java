package sistGestionLogistica.dao;

import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.dominio.StockInsumo;

public interface StockInsumoDao {

	public Boolean save(Integer id, StockInsumo s) throws SQLException;
	public Boolean update(StockInsumo stock) throws SQLException;

}
