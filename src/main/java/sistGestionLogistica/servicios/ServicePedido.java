package sistGestionLogistica.servicios;

import java.sql.SQLException;

import sistGestionLogistica.dao.*;
import sistGestionLogistica.dominio.ItemDetalle;
import sistGestionLogistica.dominio.Pedido;

public class ServicePedido {
	private PedidoDao pedidoDao = new PedidoDaoMysql(); 
	
	public Boolean registrarPedido(Pedido p) throws SQLException {
		
		
		return this.pedidoDao.save(p);
		
	}

}
