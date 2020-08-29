package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.PedidoController;
import sistGestionLogistica.dominio.ItemDetalle;
import sistGestionLogistica.dominio.Pedido;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.enums.EstadoPedido;
import sistGestionLogistica.servicios.ServiceInsumo;
import sistGestionLogistica.servicios.ServicePedido;
import sistGestionLogistica.servicios.ServicePlanta;

public class PedidoControllerTest {
	PedidoController pc= new PedidoController();
	@Before
	public void init() {
		
	}

	@Test
	//insertar un numOrden que no este en la DB
	public void crearPedidoCorrectoTest() throws SQLException, DatosInvalidosException {
		Integer numOrden= 666;
		
		List<ItemDetalle> items = new ArrayList<ItemDetalle>();
		ServiceInsumo si= new ServiceInsumo();
		items.add(new ItemDetalle(numOrden,si.buscarPorId(1),20));
		items.add(new ItemDetalle(numOrden,si.buscarPorId(3),2));
		
		pc.crearPedido(numOrden.toString(),"2", "12/07/2020", items);
		
	}
	@Test(expected = DatosInvalidosException.class)
	public void crearPedidoNroOrdenRepetidoTest() throws SQLException, DatosInvalidosException {
		List<ItemDetalle> items = new ArrayList<ItemDetalle>();
		ServiceInsumo si= new ServiceInsumo();
		//numOrden ya en base de datos
		Integer numOrden= 1111;
		items.add(new ItemDetalle(numOrden,si.buscarPorId(1),20));
		items.add(new ItemDetalle(numOrden,si.buscarPorId(3),2));
		
		pc.crearPedido(numOrden.toString(),"2", "12/07/2020", items);
		
	}
	@Test
	public void buscarPedidoCorrectoTest() throws SQLException {
		System.out.println("CREADA");
		String[][] matriz = pc.buscarPedido("CREADA");
		for(int i=0; i<matriz.length;i++) {
			System.out.println(matriz[i][0]+ " "+ matriz[i][1]+ " "+ matriz[i][2]+ " "+ matriz[i][3]+ " "+ matriz[i][4]);
		}
		System.out.println("PROCESADA");
		matriz = pc.buscarPedido("PROCESADA");
		for(int i=0; i<matriz.length;i++) {
			System.out.println(matriz[i][0]+ " "+ matriz[i][1]+ " "+ matriz[i][2]+ " "+ matriz[i][3]+ " "+ matriz[i][4]);
		}
		System.out.println("CANCELADA");
		matriz = pc.buscarPedido("CANCELADA");
		for(int i=0; i<matriz.length;i++) {
			System.out.println(matriz[i][0]+ " "+ matriz[i][1]+ " "+ matriz[i][2]+ " "+ matriz[i][3]+ " "+ matriz[i][4]);
		}
		System.out.println("ENTREGADA");
		matriz = pc.buscarPedido("ENTREGADA");
		for(int i=0; i<matriz.length;i++) {
			System.out.println(matriz[i][0]+ " "+ matriz[i][1]+ " "+ matriz[i][2]+ " "+ matriz[i][3]+ " "+ matriz[i][4]);
		}
	}
	@Test 
	public void fianlizarPedidoCorrectoTest() throws NumberFormatException, SQLException {
		ServicePedido sp = new ServicePedido();
		List<Pedido> lista = sp.buscarPorEstado(EstadoPedido.PROCESADA);
		pc.finalizarPedido(lista.get(0).getNumOrden().toString());
		
	}
	
	

}
