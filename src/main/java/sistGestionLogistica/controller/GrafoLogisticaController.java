package sistGestionLogistica.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import excepciones.DatosInvalidosException;
import excepciones.GrafoException;
import sistGestionLogistica.dominio.Estado;
import sistGestionLogistica.dominio.GrafoLogistica;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.servicios.ServiceGrafoLogistica;
import sistGestionLogistica.servicios.ServicePlanta;

public class GrafoLogisticaController {

	public GrafoLogisticaController() {
		// TODO Auto-generated constructor stub
	}
	
	public void conectarPlantas(String txtDistancia, String txtDuracionViaje, String txtPesoMaximo, String txtIdOrigen, String txtIdDestino) throws DatosInvalidosException, SQLException, GrafoException {
		Double distancia, duracion, peso;
		Integer idPlantaOrigen, idPlantaDestino;
		Planta plantaOrigen,plantaDestino;
		//verificamos que todos los datos sean ingresados
		if(txtDistancia.isBlank() || txtDuracionViaje.isBlank() || txtPesoMaximo.isBlank() || txtIdOrigen.isBlank() || txtIdDestino.isBlank()) throw new DatosInvalidosException("Completar todos los datos");
		//formateamos los datos
		distancia = Double.valueOf(txtDistancia);
		duracion = Double.valueOf(txtDuracionViaje);
		peso = Double.valueOf(txtPesoMaximo);
		idPlantaOrigen = Integer.valueOf(txtIdOrigen);
		idPlantaDestino = Integer.valueOf(txtIdDestino);
		
		//buscamos las plantas
		ServicePlanta sp = new ServicePlanta();
		
		List<Planta> lista = sp.buscarPlanta(new Planta(idPlantaOrigen,""));
		if(lista.size()>0) plantaOrigen = lista.get(0);
		else throw new DatosInvalidosException("Planta Origen Invalida");
		
		lista = sp.buscarPlanta(new Planta(idPlantaDestino,""));
		if(lista.size()>0) plantaDestino = lista.get(0);
		else throw new DatosInvalidosException("Planta Destino Invalida");
		
		
		//creamos el grafo
		ServiceGrafoLogistica sgl= new ServiceGrafoLogistica();
		GrafoLogistica gf = sgl.inicializarGrafo();
		
		//conectamos
		sgl.conectar(gf, distancia, duracion, peso, plantaOrigen, plantaDestino);
		
	}
	
	public List<Integer> caminoMinimoTiempo() {
		return null;
		
	}
	
	public List<Integer> caminoMinimoKm() {
		return null;
		
	}
	
	public List<Integer> dijkstra(){
		
		PriorityQueue<Estado> cola = new PriorityQueue();
		
		
		return null;
		
	}

}
