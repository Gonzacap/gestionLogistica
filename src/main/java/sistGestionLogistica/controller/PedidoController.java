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
import sistGestionLogistica.servicios.ServicePedido;
import sistGestionLogistica.servicios.ServicePlanta;
import sistGestionLogistica.servicios.ServiceStockInsumo;

public class PedidoController {

	public PedidoController() {
		// TODO Auto-generated constructor stub
	}
	
	public void agregar(String orden,String planta , String fSolicitud, String fEntrega,String costo, String ruta , String insumo, String cantidad , String camion,String creado) 
			throws SQLException, DatosInvalidosException,DateTimeParseException,NumberFormatException
	{
           ServicePlanta sp = new ServicePlanta();
		   Integer numOrden;
		   Planta plantaDestino = null;
		   LocalDate fechaSolicitud;
		   LocalDate fechaEntrega;
		   Double costoEnvio;
		   List<Ruta> rutaAsignada = new ArrayList<>();
		   List<ItemDetalle> item = new ArrayList<>();; 
		   Camion camionAsignado  = null;
		   EstadoPedido estado;
		   
		 
		   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		   
		   numOrden = Integer.valueOf(orden);
		   plantaDestino = sp.buscarPorId(Integer.valueOf(planta));
		   fechaSolicitud = LocalDate.parse((CharSequence)fSolicitud, formatter);
		   fechaEntrega = LocalDate.parse((CharSequence)fEntrega, formatter);
		   costoEnvio = Double.valueOf(costo);
		   
		   
		
		
	}
	public void crearPedido(String numOrden, String idPlantaDestino, String fechaMaxima, List<ItemDetalle> items) throws SQLException, DatosInvalidosException {
		Integer numeroOrden=-1, idDestino=-1;
//		Integer idInsumo=-1, cantidad=-1;
		LocalDate fechaMax;
//		List<ItemDetalle> items = new ArrayList<ItemDetalle>();
//		Insumo insumo;
		Planta destino;
//		ServiceInsumo si= new ServiceInsumo();
		ServicePedido sp= new ServicePedido();
		ServicePlanta splanta= new ServicePlanta();
		
		//parseamos todos los datos que no sean String
		numeroOrden= Integer.valueOf(numOrden);
		idDestino= Integer.valueOf(idPlantaDestino);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		fechaMax = LocalDate.parse(fechaMaxima, formatter);
		
		//validamos numOrden
		if(numeroOrden <=0) throw new DatosInvalidosException("El numero de orden deber ser mayor a cero.");
		if(sp.buscarPorNumOrden(numeroOrden).getNumOrden() > 0) throw new DatosInvalidosException("El numero de orden ya existe.");
		
		//creamos los itemDetalle
//		for(int i=0;i<itemDetalle.length;i++) {
//			idInsumo=Integer.valueOf(itemDetalle[i][0]);
//			cantidad= Integer.valueOf(itemDetalle[i][1]);
//			if(cantidad>0) {
//				insumo=si.buscarPorId(idInsumo);
//				items.add(new ItemDetalle(numeroOrden,insumo,cantidad));
//			}else throw new DatosInvalidosException("Las cantidades deben ser mayor a cero.");
//		}
		
		//creamos la planta destino
		destino =splanta.buscarPorId(idDestino);
		if(destino.getId() <= 0) throw new DatosInvalidosException("La planta destino no existe.");
		
		//creamos el pedido
		Pedido pedido = new Pedido(numeroOrden,destino, LocalDate.now(),fechaMax, items, EstadoPedido.CREADA);
		sp.registrarPedido(pedido);
		
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
}
