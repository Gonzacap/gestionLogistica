package sistGestionLogistica.dao;

import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.dominio.StockInsumo;

public interface StockInsumoDao {

	public Boolean save(StockInsumo s) throws SQLException;
	public Boolean update(StockInsumo stock) throws SQLException;
	public Boolean existeStock(Integer idPlanta, Integer idInsumo) throws SQLException;

}
