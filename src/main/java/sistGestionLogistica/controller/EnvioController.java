package sistGestionLogistica.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import excepciones.DatosInvalidosException;
import excepciones.PedidoCanceladoException;
import sistGestionLogistica.dominio.EnvioDetalle;
import sistGestionLogistica.dominio.GrafoLogistica;
import sistGestionLogistica.dominio.ItemDetalle;
import sistGestionLogistica.dominio.Pedido;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.Ruta;
import sistGestionLogistica.enums.EstadoPedido;
import sistGestionLogistica.servicios.ServiceEnvioDetalle;
import sistGestionLogistica.servicios.ServiceGrafoLogistica;
import sistGestionLogistica.servicios.ServicePedido;
import sistGestionLogistica.servicios.ServicePlanta;
import sistGestionLogistica.servicios.ServiceStockInsumo;

public class EnvioController {
	
   
	public void agregarEnvio(String numOrden, List<Ruta> camino) throws SQLException, DatosInvalidosException {
		
		Integer numeroOrden=-1;
		numeroOrden= Integer.valueOf(numOrden);
		ServiceEnvioDetalle sed = new ServiceEnvioDetalle();
		ServicePedido sp = new ServicePedido();
		
		
		
		if(numeroOrden>0 ) {
			EnvioDetalle envio = new EnvioDetalle(numeroOrden, sed.asignarCamion(), camino);
			sed.registrarItem(envio);
			sp.cambiarEstado(numeroOrden, EstadoPedido.PROCESADA);
		}else throw new DatosInvalidosException("Error ");
		
		
		
		
	}
	
	public String[][] buscarItems(String numOrden ) throws SQLException{
		Integer numeroOrden = Integer.valueOf(numOrden);
		ServiceEnvioDetalle sed = new ServiceEnvioDetalle ();
		
		return this.aMatriz(sed.buscarPorNumOrden(numeroOrden));
	}
	// rutasOptimas acepta solo "KILOMETRO" o "TIEMPO"
	public ArrayList<ArrayList<Ruta>> calcularCaminos(Integer numOrden, Integer idPlanta/*Planta plantaI*/, String rutasOptimas) throws SQLException{
		ArrayList<ArrayList<Ruta>> lista = new ArrayList<ArrayList<Ruta>>();
		ServicePedido spedido= new ServicePedido();
		Pedido pedido = spedido.buscarPorNumOrden(numOrden);
		ServicePlanta splanta = new ServicePlanta();
		Planta plantaInicio= splanta.buscarPorId(idPlanta);
		//Planta plantaInicio= plantaI;
		ServiceGrafoLogistica sGrafo = new ServiceGrafoLogistica();
		GrafoLogistica grafo= sGrafo.inicializarGrafo();
		
		switch(rutasOptimas) {
		case "KILOMETRO":
			lista=sGrafo.caminoMinimoKm(grafo, plantaInicio , pedido.getPlantaDestino());
			break;
		case "TIEMPO":
			lista=sGrafo.caminoMinimoTiempo(grafo, plantaInicio, pedido.getPlantaDestino());
			break;
		
		}
		return lista;
	}
	
	//Si devuelve vacio cancela el pedido
	public List<Planta> plantasConStock(Integer numOrden) throws SQLException, PedidoCanceladoException{
		ServicePedido sp = new ServicePedido();
		ServiceStockInsumo ssi= new ServiceStockInsumo();
		Pedido pedido = sp.buscarPorNumOrden(numOrden);
		List<Planta> resultado = ssi.plantasConStock(pedido.getItem());
		resultado.remove(pedido.getPlantaDestino());
		if(resultado.isEmpty()) {
			sp.cambiarEstado(numOrden, EstadoPedido.CANCELADA);
			JOptionPane.showMessageDialog(null,"No hay planta con stock para realizar el pedido", "Stock insuficiente",JOptionPane.ERROR_MESSAGE);
			throw new PedidoCanceladoException("No existen plantas con esa cantidad de Stock. su pedido fue Cancelado.");
			
		}
		return resultado;
	}
	
	//Muestro info del envio 
	public String[][] aMatriz(EnvioDetalle envio){
		
		String[][] matriz = new String[1][4];
		
	
			
			matriz[1][0]= envio.getNumOrden().toString();
			matriz[1][1]= envio.getCamionAsignado().getPatente().toString();
			matriz[1][2]= envio.getCostoEnvio().toString();
			
		
		
		return matriz;
	}
	

}
