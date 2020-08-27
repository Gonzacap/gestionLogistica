package sistGestionLogistica.servicios;

import java.sql.SQLException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import sistGestionLogistica.dao.*;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.ItemDetalle;
import sistGestionLogistica.dominio.Pedido;
import sistGestionLogistica.enums.EstadoPedido;

public class ServicePedido {
	private PedidoDao pedidoDao = new PedidoDaoMysql(); 
	
	public Boolean registrarPedido(Pedido p) throws SQLException { 
		return this.pedidoDao.save(p);
		
	}
	public Pedido buscarPorNumOrden(Integer numOrden) throws SQLException {
		return pedidoDao.buscarNumOrden(numOrden);
	}

	public void cambiarEstado(Integer numOrden, EstadoPedido estado) {
		pedidoDao.cambiarEstado(numOrden, estado);
	}
	
	public List<Pedido> buscarPorEstado(EstadoPedido estado) throws SQLException{
		return pedidoDao.buscarTodos().stream().filter((t) -> t.getEstado().equals(estado)).collect(Collectors.toList());
		
	}
	public List<Pedido> buscarTodos() throws SQLException{
		return this.pedidoDao.buscarTodos();
	}

}
