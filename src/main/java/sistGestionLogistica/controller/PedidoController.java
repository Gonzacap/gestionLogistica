package sistGestionLogistica.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.ItemDetalle;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.Ruta;
import sistGestionLogistica.enums.EstadoPedido;
import sistGestionLogistica.servicios.ServicePlanta;

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
}
