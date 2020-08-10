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
	
	// agrego a la lista de rutas 
		public void registrarRuta(Ruta ruta) {
			
//			listaRuta.add(new Ruta(-1,distancia, duracionViaje,pesoMaximo,origen,destino));
			listaRuta.add(ruta);
			
		}
		
		//registro una planta y la agrego a la lista de plantas 
		
		public void registrarPlanta(Planta p) {
			
				 listaPlanta.add(p); 
		
		}
	

}
