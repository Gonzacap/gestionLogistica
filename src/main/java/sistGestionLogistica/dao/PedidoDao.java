package sistGestionLogistica.dao;

import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.dominio.Pedido;
import sistGestionLogistica.enums.EstadoPedido;

public interface PedidoDao {
	
	public Boolean save(Pedido pe) throws SQLException;
	public Boolean update(Pedido pe) throws SQLException ;
	public Pedido buscarNumOrden(Integer numOrden) throws SQLException ;
	public List<Pedido> buscarTodos() throws SQLException ;
	public void cambiarEstado(Integer numOrden, EstadoPedido estado);
	
}
