package sistGestionLogistica.dao;

import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.dominio.Ruta;

public interface RutaDao {
	public Ruta saveOrUpdate(Ruta r) throws SQLException;
	public Ruta  buscarPorId(Integer id) throws SQLException;
	public void borrar(Integer id) throws SQLException;
	public List<Ruta> buscarTodos() throws SQLException;
}
