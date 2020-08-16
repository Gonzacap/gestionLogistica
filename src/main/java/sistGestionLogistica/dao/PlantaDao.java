package sistGestionLogistica.dao;

import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.dominio.Planta;

public interface PlantaDao {
	public Boolean save(Planta p);
	public Boolean update(Planta p);
	public void borrar(Integer id);
	public List<Planta> buscarTodos();
	public Planta buscarPorId(Integer id) throws SQLException; 
}
