package sistGestionLogistica.dao;

import java.sql.SQLException;
import java.util.List;
import sistGestionLogistica.dominio.Camion;


public interface CamionDao {

	public Boolean save(Camion c) throws SQLException;
	public Boolean update(Camion c) throws SQLException;
	public Camion buscarPorPatente(String patente) throws SQLException;
	public Camion buscarPorId(Integer id) throws SQLException;
	public void borrar(Integer id) throws SQLException;
	public List<Camion> buscarTodos() throws SQLException;

}
