package sistGestionLogistica.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import excepciones.DatosInvalidosException;
import excepciones.PedidoCanceladoException;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.Insumo;
import sistGestionLogistica.dominio.ItemDetalle;
import sistGestionLogistica.dominio.Pedido;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.Ruta;
import sistGestionLogistica.enums.EstadoPedido;
import sistGestionLogistica.servicios.ServiceInsumo;
import sistGestionLogistica.servicios.ServiceItemDetalle;
import sistGestionLogistica.servicios.ServicePedido;
import sistGestionLogistica.servicios.ServicePlanta;
import sistGestionLogistica.servicios.ServiceStockInsumo;

public class PedidoController {

	public PedidoController() {
		// TODO Auto-generated constructor stub
	}
	
	

	public void crearPedido(String numOrden, String idPlantaDestino, String fechaMaxima, List<ItemDetalle> items) throws SQLException, DatosInvalidosException {
		Integer numeroOrden=-1, idDestino=-1;
		LocalDate fechaMax;

		Planta destino;
        ItemDetalle item;
		ServicePedido sp= new ServicePedido();
		ServicePlanta splanta= new ServicePlanta();
		ServiceItemDetalle sid = new ServiceItemDetalle();
		
		//parseamos todos los datos que no sean String
		numeroOrden= Integer.valueOf(numOrden);
		idDestino= Integer.valueOf(idPlantaDestino);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		fechaMax = LocalDate.parse(fechaMaxima, formatter);
		
		//validamos numOrden
		if(numeroOrden <=0) throw new DatosInvalidosException("El numero de orden deber ser mayor a cero.");
		if(sp.buscarPorNumOrden(numeroOrden).getNumOrden() > 0) throw new DatosInvalidosException("El numero de orden ya existe.");
		
	
		
		//creamos la planta destino
		destino =splanta.buscarPorId(idDestino);
		if(destino.getId() <= 0) throw new DatosInvalidosException("La planta destino no existe.");
		
		//creamos el pedido
		Pedido pedido = new Pedido(numeroOrden,destino, LocalDate.now(),fechaMax, items, EstadoPedido.CREADA);
		sp.registrarPedido(pedido);
		//Agrego los items a la db 
		for(ItemDetalle unItem : items) {
			item = new ItemDetalle(Integer.valueOf(numOrden), unItem.getInsumo(), unItem.getCantidad());
			sid.registrarItem(item);
		}
	
		
	}
	//Si devuelve vacio cancela el pedido
	public List<Planta> plantasConStock(Integer numOrden) throws SQLException, PedidoCanceladoException{
		ServicePedido sp = new ServicePedido();
		ServiceStockInsumo ssi= new ServiceStockInsumo();
		Pedido pedido = sp.buscarPorNumOrden(numOrden);
		List<Planta> resultado = ssi.plantasConStock(pedido.getItem());
		if(resultado.isEmpty()) {
			sp.cambiarEstado(numOrden, EstadoPedido.CANCELADA);
			throw new PedidoCanceladoException("No existen plantas con esa cantidad de Stock. su pedido fue Cancelado.");
		}
		return resultado;
	}
	
	public String[][] buscarPedido(String estado ) throws SQLException{
		Integer numOrden=-1, idDestino=-1;
		LocalDate fechaMax=LocalDate.MIN;
		LocalDate fechaSol=LocalDate.MIN;
		List<ItemDetalle> item=null; 
		EstadoPedido estadoPedido;
		Planta planta=null;
	   
		estadoPedido = EstadoPedido.valueOf(estado);
		
		Pedido pe = new Pedido(numOrden, planta, fechaSol, fechaMax, item, estadoPedido);
		ServicePedido sp = new ServicePedido();
		return this.aMatriz(sp.buscarPedido(pe));
	}
	public String[][] aMatriz(List<Pedido> listaPedido){
		
		String[][] matriz = new String[listaPedido.size()][4];
		for(int i=0; i<listaPedido.size();i++) {
			matriz[i][0]= listaPedido.get(i).getNumOrden().toString();
			matriz[i][1]=  listaPedido.get(i).getPlantaDestino().getNombre().toString();
			matriz[i][2]=  listaPedido.get(i).getFechaSolicitud().toString();
			matriz[i][3]=  listaPedido.get(i).getFechaEntrega().toString();
			matriz[i][4]=  listaPedido.get(i).getEstado().toString();
		}
		return matriz;
		
	}
}
