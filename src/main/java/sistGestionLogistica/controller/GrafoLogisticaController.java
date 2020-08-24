package sistGestionLogistica.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;

import excepciones.DatosInvalidosException;
import excepciones.GrafoException;
import sistGestionLogistica.dominio.Estado;
import sistGestionLogistica.dominio.GrafoLogistica;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.Ruta;
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
	
	public  List<Planta> adyacencias(GrafoLogistica graph, Planta planta ){
		
		List<Planta> ady = new ArrayList<>();
		
		for(Ruta r: graph.getListaRuta()) {
			if(planta.equals(r.getPlantaOrigen())) {
				ady.add(r.getPlantaDestino());
			}
		}
		
		return ady;
	}
	
	public List<Integer> dijkstra(GrafoLogistica graph, Comparator<Estado> comp, Planta inicio, Planta fin){
		
		PriorityQueue<Estado> cola = new PriorityQueue(1,comp); // La cola de prioridad.
		Vector<Integer> distancia = new Vector(graph.getListaPlanta().size()); // La distancia hacia todos los nodos. Inicialmente para cada vertice su valor es -1.
		Vector<Boolean> marcado = new Vector(graph.getListaPlanta().size()); // Este arreglo nos permitira determinar los nodos procesados.
		
		List<Planta> adyacencias;
		
		for(Planta p: graph.getListaPlanta()) {
			distancia.add(-1);
			marcado.add(false);
		}
		
		distancia.add(0, 0); //?¡ // Valor inicial del nodo de partida.
		cola.add(new Estado(inicio,0.0,0)); // Agregamos el primer elemento que es el nodo inicial
		
		while(!cola.isEmpty()) { // Mientras existan nodos por procesar.
		
			Estado e = cola.poll(); // Se desencola el elemento minimo.
			marcado.add(e.getIndice(), true); // Se marca el nodo como visitado.
			if (e.getPlanta() == fin)	return e.getCamino() ; // Retornamos el caminino?, hemos llegado al nodo destino.
			
			adyacencias = this.adyacencias(graph, e.getPlanta()); // hacer un metodo que retorne la lista de rutas salientes de una planta
			
			for(int i = 0; i < T; ++i) // Se recorren las adyacencias de "a".
			{
				// Si no ha sido procesado el vertice "vi" y la distancia hacia "vi" es menor a la distancia
				// en Dist entonces hemos encontrado un camino mas corto a "vi".
				if (!mark[graph.G[st.node][i].node] && ((Dist[st.node] + graph.G[st.node][i].cost) < Dist[graph.G[st.node][i].node]))
				{
					Dist[graph.G[st.node][i].node] = st.cost + graph.G[st.node][i].cost;
					pq.push(State(graph.G[st.node][i].node, st.cost + graph.G[st.node][i].cost));
				}
			}
			
		}
		
		return null; // Si no se puede llegar al destino, retornar null;
		
	}

}
