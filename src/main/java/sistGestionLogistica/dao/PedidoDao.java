package sistGestionLogistica.dao;

import java.sql.SQLException;

import sistGestionLogistica.dominio.Pedido;

public interface PedidoDao {
	
	public Boolean save(Pedido pe) throws SQLException;
	public Pedido buscarNumOrden(Integer numOrden);

}
