package sistGestionLogistica.dao;

import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.dominio.EnvioDetalle;
import sistGestionLogistica.dominio.ItemDetalle;

public interface EnvioDetalleDao {
	
    public Boolean save(EnvioDetalle envio) throws SQLException;
    public Boolean update(EnvioDetalle envio) throws SQLException ;
	public EnvioDetalle buscarNumOrden(Integer numOrden) throws SQLException ;
	public List<EnvioDetalle> buscarTodos() throws SQLException ;
	
}
