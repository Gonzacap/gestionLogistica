package sistGestionLogistica.dao;

import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.dominio.Insumo;

public interface InsumoDao {
	
	public Insumo save(Insumo i) throws SQLException;
	public Insumo update(Insumo i) throws SQLException;
	public Insumo buscarPorId(Integer id) throws SQLException;
	public void borrar(Integer id) throws SQLException;
	public List<Insumo> buscarTodos() throws SQLException;

}
