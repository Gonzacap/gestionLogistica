package sistGestionLogistica.servicios;

import java.sql.SQLException;
import java.util.PriorityQueue;

import sistGestionLogistica.dao.*;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.ItemDetalle;
import sistGestionLogistica.dominio.Pedido;

public class ServicePedido {
	private PedidoDao pedidoDao = new PedidoDaoMysql(); 
	
	public Boolean registrarPedido(Pedido p) throws SQLException { 
		return this.pedidoDao.save(p);
		
	}
	public Pedido buscarPorNumOrden(Integer numOrden) throws SQLException {
		return pedidoDao.buscarNumOrden(numOrden);
	}

}
