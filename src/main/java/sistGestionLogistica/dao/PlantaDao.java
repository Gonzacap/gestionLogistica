package sistGestionLogistica.dao;

import java.util.List;

import sistGestionLogistica.dominio.Planta;

public interface PlantaDao {
	public Boolean save(Planta p);
	public Boolean update(Planta p);
	public Boolean buscarPorId(Integer id);
	public void borrar(Integer id);
	public List<Planta> buscarTodos();
}
