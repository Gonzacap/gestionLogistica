package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import excepciones.DatosInvalidosException;
import excepciones.PedidoCanceladoException;
import sistGestionLogistica.controller.EnvioController;
import sistGestionLogistica.dominio.Pedido;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.Ruta;
import sistGestionLogistica.enums.EstadoPedido;
import sistGestionLogistica.servicios.ServicePedido;

public class EnvioControllerTest {
	EnvioController ec= new EnvioController();

	@Test(expected = PedidoCanceladoException.class)
	public void plantasConStockCancelaPedidoTest() throws SQLException, PedidoCanceladoException {
		ServicePedido sp = new ServicePedido();
		List<Pedido> lista = sp.buscarPorEstado(EstadoPedido.CREADA);
		//buscamos el PRIMER pedido de la lista por motivos practicos de los datos que tenemos en la BD
		Pedido pedido= lista.get(0);
		List<Planta> plantas=ec.plantasConStock(pedido.getNumOrden());
		for(Planta p : plantas) System.out.println(p);
	}
	@Test
	public void plantasConStockCorrectoTest() throws SQLException, PedidoCanceladoException {
		ServicePedido sp = new ServicePedido();
		List<Pedido> lista = sp.buscarPorEstado(EstadoPedido.CREADA);
		//buscamos el ULTIMO pedido de la lista por motivos practicos de la base de datos que disponemos
		Pedido pedido= lista.get(lista.size()-1);
		List<Planta> plantas=ec.plantasConStock(pedido.getNumOrden());
		for(Planta p : plantas) System.out.println(p);
	}
	
	@Test
	public void calcularCaminosPorKilometrosCorrectoTest() throws SQLException, PedidoCanceladoException {
		ServicePedido sp = new ServicePedido();
		List<Pedido> lista = sp.buscarPorEstado(EstadoPedido.CREADA);
		//buscamos el ultimo pedido de la lista por motivos practicos de la base de datos que disponemos
		Pedido pedido= lista.get(lista.size()-1);
		List<Planta> plantas=ec.plantasConStock(pedido.getNumOrden());
		
		ArrayList<ArrayList<Ruta>> rutas = ec.calcularCaminos(pedido.getNumOrden(), plantas.get(1).getId(), "KILOMETRO");
		System.out.println("Resultado por Kilometros:");
		for(ArrayList<Ruta> listaruta : rutas ) {
			System.out.println("Ruta: ");
			for(Ruta r : listaruta) System.out.println(r);
		}

		
	}
	@Test
	public void calcularCaminosPorTiempoCorrectoTest() throws SQLException, PedidoCanceladoException {
		ServicePedido sp = new ServicePedido();
		List<Pedido> lista = sp.buscarPorEstado(EstadoPedido.CREADA);
		//buscamos el ultimo pedido de la lista por motivos practicos de la base de datos que disponemos
		Pedido pedido= lista.get(lista.size()-1);
		List<Planta> plantas=ec.plantasConStock(pedido.getNumOrden());
		
		ArrayList<ArrayList<Ruta>> rutas = ec.calcularCaminos(pedido.getNumOrden(), plantas.get(1).getId(), "TIEMPO");
		System.out.println("Resultado por Tiempo:");
		for(ArrayList<Ruta> listaruta : rutas ) {
			System.out.println("Ruta: ");
			for(Ruta r : listaruta) System.out.println(r);
		}
	}

}
