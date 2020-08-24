package sistGestionLogistica.servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;

import excepciones.GrafoException;
import sistGestionLogistica.dominio.Estado;
import sistGestionLogistica.dominio.GrafoLogistica;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.Ruta;

public class ServiceGrafoLogistica {

	public ServiceGrafoLogistica() {
		// TODO Auto-generated constructor stub
	}
	
	public GrafoLogistica inicializarGrafo() throws SQLException {
		Planta plantaModelo = new Planta(-1,""); //modelo para buscar todas las plantas
		
		//obtenemos la lista de todas las plantas del sistemas
		ServicePlanta sp = new ServicePlanta();
		List<Planta> plantas = sp.buscarPlanta(plantaModelo);
		
		// obtenemos la lista de todas las rutas del sistema
		ServiceRuta se = new ServiceRuta();
		List<Ruta> rutas = se.buscarRuta(new Ruta(-1,-1,-1,-1,plantaModelo,plantaModelo));
		
		//creamos el grafo
		GrafoLogistica gf= new GrafoLogistica();
		
		//agregamos las plantas
		for(Planta p : plantas) gf.registrarPlanta(p);
		
		//agregamos las rutas
		for(Ruta r : rutas) gf.registrarRuta(r);
		

		return gf;
	}
	
	public void agregarPlanta(GrafoLogistica gf, Planta p) throws GrafoException {
		
		
		
		//si no existe la planta en la base de datos la creamos
		if(!this.existePlanta(p)){
			ServicePlanta sp=new ServicePlanta();
			sp.registrarPlanta(p);
			// en esta parte buscamos la planta para obtener el id
			p=sp.buscarPlanta(p).get(0);
		}
		//si ya se encuentra en la lista lanzamos una excepcion
		if(gf.getListaPlanta().contains(p)) throw new GrafoException("La planta ya se encuentra en el grafo.");
		
		gf.registrarPlanta(p);
		
	
	}
	public void conectar(GrafoLogistica gf, double distancia, double duracionViaje, double pesoMaximo, Planta origen, Planta destino) throws GrafoException, SQLException {
		
		//si las plantas no estan en el grafo se agrega
		if(!gf.getListaPlanta().contains(origen)) this.agregarPlanta(gf, origen);
		if(!gf.getListaPlanta().contains(destino)) this.agregarPlanta(gf, destino);
		
		//creamos la ruta y la agregamos al grafo
		Ruta ruta = new Ruta(-1,distancia,duracionViaje,pesoMaximo, origen,destino);
		this.agregarRuta(gf, ruta);
		
		
		
	}
	
	public void agregarRuta(GrafoLogistica gf, Ruta r) throws SQLException, GrafoException{
		
		//si no existe la ruta en la base de datos la creamos
		if(!this.existeRuta(r)){
			ServiceRuta sr=new ServiceRuta();
			sr.registrarRuta(r);
			// en esta parte buscamos la ruta para obtener el id
			r=sr.buscarRuta(r).get(0);
		}
		
		//si ya se encuentra en la lista lanzamos una excepcion
		if(gf.getListaRuta().contains(r)) throw new GrafoException("La Ruta ya se encuentra en el grafo.");
				
		gf.registrarRuta(r);
		
		
	}

	
	
	//devuelve true si existe la ruta en la base de datos
	private boolean existeRuta(Ruta r) throws SQLException {
		ServiceRuta sr = new ServiceRuta();
		List<Ruta> li = sr.buscarRuta(r);
		if(!li.isEmpty()) return true;
		return false;
	}

	//devuelve true si existe la planta en la base de datos
	private Boolean existePlanta(Planta p) {
		ServicePlanta sp=new ServicePlanta();
		List<Planta> li = sp.buscarPlanta(p);
		if(!li.isEmpty()) return true;
		return false;
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
		Vector<Planta> marcado = new Vector(graph.getListaPlanta().size()); // Este arreglo nos permitira determinar los nodos procesados.
		
		List<Planta> adyacencias;
		
		for(Planta p: graph.getListaPlanta()) {
			distancia.add(-1);
			//marcado.add(false);
		}
		
		distancia.add(0, 0); //?¡ // Valor inicial del nodo de partida.
		cola.add(new Estado(inicio,0.0,0)); // Agregamos el primer elemento que es el nodo inicial
		
		while(!cola.isEmpty()) { // Mientras existan nodos por procesar.
		
			Estado e = cola.poll(); // Se desencola el elemento minimo.
			marcado.add(e.getPlanta()); // Se marca el nodo como visitado.
			if (e.getPlanta() == fin)	return e.getCamino() ; // Retornamos el caminino?, hemos llegado al nodo destino.
			
			adyacencias = this.adyacencias(graph, e.getPlanta()); // hacer un metodo que retorne la lista de rutas salientes de una planta
			
			for(int i = 0; i < adyacencias.size(); ++i) 
			{
				
				if ( ((Dist[st.node] + graph.G[st.node][i].cost) < Dist[graph.G[st.node][i].node])){
					
					Dist[graph.G[st.node][i].node] = st.cost + graph.G[st.node][i].cost;
					pq.push(State(graph.G[st.node][i].node, st.cost + graph.G[st.node][i].cost));
				}
			}
			
			for(Planta p: adyacencias) { // Se recorren las adyacencias de la planta e
				
				// Si no ha sido procesado el nodo p y la distancia hacia p2 es menor a la distancia
				// entonces hemos encontrado un camino mas corto a i.
				
				if(!marcado.contains(p)) {
					
				}
			}
			
		}
		
		return null; // Si no se puede llegar al destino, retornar null;
		
	}

}

}
