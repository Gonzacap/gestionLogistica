package sistGestionLogistica.dao;

import java.util.List;

import sistGestionLogistica.dominio.Planta;

public interface PlantaDao {
	public Boolean saveOrUpdate(Planta p);
	public Boolean buscarPorId(Integer id);
	public void borrar(Integer id);
	public List<Planta> buscarTodos();
}
