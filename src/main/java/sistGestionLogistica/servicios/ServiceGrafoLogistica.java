package sistGestionLogistica.servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;

import excepciones.GrafoException;
import sistGestionLogistica.comparator.CompareRutas;
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

	//-----CAMINO MINIMO-----
	
	public  List<Estado> adyacenciasT(GrafoLogistica graph, Planta planta ){
		
		List<Estado> ady = new ArrayList<>();
		
		for(Ruta r: graph.getListaRuta()) {
			if(planta.equals(r.getPlantaOrigen())) {
				ady.add(new Estado(r.getPlantaDestino(),r.getDuracionViaje(),9999999.0,r));
			}
		}
		
		return ady;
	}
	
	public  List<Estado> adyacenciasKm(GrafoLogistica graph, Planta planta ){
		
		List<Estado> ady = new ArrayList<>();
		
		for(Ruta r: graph.getListaRuta()) {
			if(planta.equals(r.getPlantaOrigen())) {
				ady.add(new Estado(r.getPlantaDestino(),r.getDistancia(),9999999.0,r));
			}
		}
		
		return ady;
	}
	
	public ArrayList<ArrayList<Ruta>> caminoMinimoTiempo(GrafoLogistica graph, Planta inicio, Planta fin){
		
		PriorityQueue<Estado> cola = new PriorityQueue(1,new CompareRutas()); // La cola de prioridad.
		Vector<Planta> marcado = new Vector(graph.getListaPlanta().size()); // Este arreglo nos permitira determinar los nodos procesados.
		ArrayList<ArrayList<Ruta>> salida = new ArrayList<ArrayList<Ruta>>();
		Boolean hayCamino = false;
		Double maximo = 9999999.0;
		
		System.out.println("dijkstra tiempo - camino minimo");
		
		List<Estado> adyacencias;
		
		cola.add(new Estado(inicio,0.0,0.0)); // Agregamos el primer elemento que es el nodo inicial, peso y distancia inicial cero
		
		while(!cola.isEmpty()) { // Mientras existan nodos por procesar.
		
			Estado e = cola.poll(); // Se desencola el elemento minimo.
			marcado.add(e.getPlanta()); // Se marca el nodo como visitado.
			
			//System.out.println(e.getActual().getNombre());
			
			//if (e.getPlanta().equals(fin)) return e.getCamino() ; // Retornamos el caminino, hemos llegado al nodo destino.
			
			if (e.getPlanta().equals(fin)) {	//retorno una lisata de caminos
				
				if(!hayCamino) {
					maximo = e.getAcumulado();
					salida.add(e.getCamino()); 
					hayCamino = true;
				}
				else if(e.getAcumulado()<=maximo) {
					salida.add(e.getCamino());
				}
			}
			
			adyacencias = this.adyacenciasT(graph, e.getPlanta()); // hacer un metodo que retorne la lista de rutas salientes de una planta
			
			for(Estado p: adyacencias) { // Se recorren las adyacencias de la planta e
				
				// Si no ha sido procesado el nodo p y la distancia hacia p2 es menor a la distancia
				// entonces hemos encontrado un camino mas corto a i.
				
				if(!marcado.contains(p.getPlanta()) && (e.getAcumulado()+p.getPeso())< p.getAcumulado() ) {
					
					p.setAcumulado(e.getAcumulado()+p.getPeso());
					p.setCamino(e.getCamino(), p.getRuta());
					
					/*for(Ruta r: p.getCamino()) {
						System.out.println(r.getIdRuta());
					}*/
					
					cola.add(p);
				}
			}
			
		}
		
		if(hayCamino) {
			return salida;
		}
		else {
			System.out.println("no encontro camino");
			return salida; // Si no se puede llegar al destino, retornar vacio;
		}
	}
	
	public ArrayList<ArrayList<Ruta>> caminoMinimoKm(GrafoLogistica graph, Planta inicio, Planta fin){
		
		PriorityQueue<Estado> cola = new PriorityQueue(1,new CompareRutas()); // La cola de prioridad.
		Vector<Planta> marcado = new Vector(graph.getListaPlanta().size()); // Este arreglo nos permitira determinar los nodos procesados.
		ArrayList<ArrayList<Ruta>> salida = new ArrayList<ArrayList<Ruta>>();
		Boolean hayCamino = false;
		Double maximo = 9999999.0;
		
		System.out.println("dijkstra km - camino minimo");
		
		List<Estado> adyacencias;
		
		cola.add(new Estado(inicio,0.0,0.0)); // Agregamos el primer elemento que es el nodo inicial, peso y distancia inicial cero
		
		while(!cola.isEmpty()) { // Mientras existan nodos por procesar.
		
			Estado e = cola.poll(); // Se desencola el elemento minimo.
			marcado.add(e.getPlanta()); // Se marca el nodo como visitado.
			
			//if (e.getPlanta().equals(fin)) return e.getCamino() ; // Retornamos el caminino, hemos llegado al nodo destino.
			
			if (e.getPlanta().equals(fin)) {	//retorno una lisata de caminos
				
				if(!hayCamino) {
					maximo = e.getAcumulado();
					salida.add(e.getCamino()); 
					hayCamino = true;
				}
				else if(e.getAcumulado()<=maximo) {
					salida.add(e.getCamino());
				}
			}
			
			adyacencias = this.adyacenciasKm(graph, e.getPlanta()); // hacer un metodo que retorne la lista de rutas salientes de una planta
			
			for(Estado p: adyacencias) { // Se recorren las adyacencias de la planta e
				
				// Si no ha sido procesado el nodo p y la distancia hacia p2 es menor a la distancia
				// entonces hemos encontrado un camino mas corto a i.
				
				if(!marcado.contains(p.getPlanta()) && (e.getAcumulado()+p.getPeso())< p.getAcumulado() ) {
						
					p.setAcumulado(e.getAcumulado()+p.getPeso());
					p.setCamino(e.getCamino(), p.getRuta());
					
					/*for(Ruta r: p.getCamino()) {
						System.out.println(r.getIdRuta());
					}*/
					
					cola.add(p);
				}
			}
			
		}
		
		if(hayCamino) {
			System.out.println("se encontraron "+salida.size()+" caminos");
			return salida;
		}
		else {
			System.out.println("no encontro camino");
			return salida; // Si no se puede llegar al destino, retornar vacio;
		}
	}
	public Double[][] matrizKilometros(GrafoLogistica grafo) {
		return this.matrizKoT(grafo, "K");
	}
	public Double[][] matrizTiempo(GrafoLogistica grafo) {
		return this.matrizKoT(grafo, "T");
	}
	
	private Double[][] matrizKoT(GrafoLogistica grafo, String tipo) {
		//creamos la matriz con la cantidad de plantas
		Double matriz[][] = new Double[grafo.getListaPlanta().size()][grafo.getListaPlanta().size()];
		ServiceRuta sr= new ServiceRuta();
		for(int i=0; i<grafo.getListaPlanta().size();i++) {
			for(int j=0;j<grafo.getListaPlanta().size();j++) {
				//si la planta es la misma setea en 0 
				if(i==j) matriz[i][j]=0.0;
				else {
					List<Ruta> ruta;
					//calcula el camino minimo dependiendo si es por Kilometro o tiempo
					if(tipo.equals("K")) ruta= this.caminoMinimoKm(grafo, grafo.getListaPlanta().get(i), grafo.getListaPlanta().get(j)).get(0);
					else ruta= this.caminoMinimoTiempo(grafo, grafo.getListaPlanta().get(i), grafo.getListaPlanta().get(j)).get(0);
					//si la ruta es vacia setea en 0
					if(ruta.isEmpty()) matriz[i][j] =0.0;
					//sino setea los kilometros o el tiempo de la ruta
					else {
						if(tipo.equals("K")) matriz[i][j] = sr.kilometrosRuta(ruta);
						else matriz[i][j] = sr.tiempoRuta(ruta);
					}
				}
				
			}
			
		}
		return matriz;
	
	}
	
	public Integer gradoEntrada(GrafoLogistica grafo, Planta p) throws SQLException {
		Integer grado=0;
		List<Ruta> rutas = grafo.getListaRuta();
//		ServiceRuta sr = new ServiceRuta();
//		rutas= sr.buscarTodos();
		for(Ruta r : rutas) {
			if(r.getPlantaDestino().equals(p)) {
				grado++;
			}
		}
		
		return grado;
	}
	public Integer gradoSalida(GrafoLogistica grafo,Planta p) throws SQLException {
		Integer grado=0;
		List<Ruta> rutas = grafo.getListaRuta();
//		ServiceRuta sr = new ServiceRuta();
//		rutas= sr.buscarTodos();
		for(Ruta r : rutas) {
			if(r.getPlantaOrigen().equals(p)) {
				grado++;
			}
		}
		
		return grado;
	}
	public List<Planta> plantasEntrada(GrafoLogistica grafo,Planta p){
		List<Planta> lista = new ArrayList<Planta>();
		List<Ruta> rutas = grafo.getListaRuta();
		for(Ruta r : rutas) {
			if(r.getPlantaDestino().equals(p)) {
				lista.add(r.getPlantaOrigen());
			}
		}
		return lista;
		
	}
	public List<Double> pageRank(GrafoLogistica grafo) throws SQLException{
		List<Planta> plantas = grafo.getListaPlanta();
		Double d= 0.5;
		
		List<Double> pgActual= this.inicializaPG(plantas.size());
		List<List<Planta>> entrantes = this.obtenerEntrantes(grafo, plantas);
		List<Double> pgAnterior= new ArrayList<Double>();
		do {
			pgAnterior= this.copiarLista(pgActual);
			//recorremos la lista de plantas por indice
			for(int i=0; i<plantas.size();i++) {
				//agregamos el factor de amortiguacion que definimos en 0.5
				Double auxNuevo= d;
				//iteramos en los nodos entrantes
				for(Planta p : entrantes.get(i)) {
					// sumamos el factor de amortiguacion multiplicado por el pageRank de la planta entrante
					// dividido el grado de salida de la planta entrante
					auxNuevo+= d*(pgActual.get(plantas.indexOf(p))/ this.gradoSalida(grafo, p));
				}
				//guardamos el double truncado a 6 decimales
				pgActual.set(i,this.truncateTo(auxNuevo, 6));
			}
			//cuando las listas sean iguales quiere decir que el algoritmo se estabilizó
		}while(!(pgActual.containsAll(pgAnterior) && pgAnterior.containsAll(pgActual)));
		
		return pgActual;
	}

	private List<Double> inicializaPG(Integer total) {
		List<Double> inicializada= new ArrayList<Double>();
		for(int i=0; i<total;i++) inicializada.add(1.0);
		return inicializada;
	}
	private List<Double> copiarLista(List<Double> lista){
		List<Double> nueva= new ArrayList<Double>();
		for(Double d : lista) nueva.add(d.doubleValue());
		return nueva;
	}
	private List<List<Planta>> obtenerEntrantes(GrafoLogistica grafo, List<Planta> lista) {
		List<List<Planta>> resultado = new ArrayList<List<Planta>>();
		for(Planta p : lista) {
			resultado.add(this.plantasEntrada(grafo, p));
		}
		
		return resultado;
	}
	private double truncateTo( double unroundedNumber, int decimalPlaces ){
	    int truncatedNumberInt = (int)( unroundedNumber * Math.pow( 10, decimalPlaces ) );
	    double truncatedNumber = (double)( truncatedNumberInt / Math.pow( 10, decimalPlaces ) );
	    return truncatedNumber;
	}
	
}
