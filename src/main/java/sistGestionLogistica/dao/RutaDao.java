package sistGestionLogistica.dao;

import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.dominio.Ruta;

public interface RutaDao {
	public Boolean save(Ruta r) throws SQLException;
	public Boolean update(Ruta r) throws SQLException;
	public void borrar(Integer id) throws SQLException;
	public List<Ruta> buscarTodos() throws SQLException;
	public Ruta buscarPorId(Integer id)  throws SQLException;
}
