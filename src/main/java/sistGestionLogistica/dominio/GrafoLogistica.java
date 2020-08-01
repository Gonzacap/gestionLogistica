package sistGestionLogistica.dominio;
import java.util.ArrayList;
import java.util.List;

public class GrafoLogistica {
	
	private List<Ruta> listaRuta = new ArrayList<Ruta>();
	//Capaz que va un Set aca porque los Vertices no se repiten en un grafo, 
	// pero hay que ver como va en la base de datos
	private List<Planta> listaPlanta = new ArrayList<Planta>();

	public GrafoLogistica() {
		super();
	}
	
	public List<Ruta> getListaRuta() {
		return listaRuta;
	}
	
	public List<Planta> getListaPlanta() {
		return listaPlanta;
	}
	
	//conecto dos rutas y las agrego a la lista de rutas 
		public void conectar(Planta origen, Planta destino , double distancia , double duracionViaje, double pesoMaximo ) {
			
			listaRuta.add(new Ruta(distancia, duracionViaje,pesoMaximo,origen,destino));
			
		}
		
		//registro una planta y la agrego a la lista de plantas 
		
		public void registrarPlanta(String nombre) {
			 if(listaPlanta.isEmpty()){
				 listaPlanta.add(new Planta(1 , nombre)); //genero el primer id
			 }
			 else{
				 listaPlanta.add(new Planta(listaPlanta.get(listaPlanta.size() - 1).getId() + 1 , nombre)); // busco el id de la ultima planta agregada y sumo 1 para el nuevo id
			 }
		}
	

}
