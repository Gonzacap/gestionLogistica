package sistGestionLogistica.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.StockInsumo;

public interface StockInsumoDao {

	public Boolean save(StockInsumo s) throws SQLException;
	public Boolean update(StockInsumo stock) throws SQLException;
	public Boolean existeStock(Integer idPlanta, Integer idInsumo) throws SQLException;
	public Integer stockTotal(Integer idInsumo);
	public List<StockInsumo> faltantes();
	public List<StockInsumo> buscarStockPlanta(Integer id);
	public List<Planta> plantasConInsumo(Integer idInsumo, Integer cantidad);
}
