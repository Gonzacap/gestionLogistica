package sistGestionLogistica.dao;

import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.dominio.ItemDetalle;

public interface ItemDetalleDao {
	
    public Boolean save(ItemDetalle item);
    public Boolean update(ItemDetalle item) throws SQLException ;
	public List<ItemDetalle> buscarNumOrden(Integer numOrden) throws SQLException ;
	public List<ItemDetalle> buscarTodos() throws SQLException ;
}
