package sistGestionLogistica.servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.GrafoException;
import sistGestionLogistica.dominio.GrafoLogistica;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.Ruta;

public class ServiceGrafoLogistica {

	public ServiceGrafoLogistica() {
		// TODO Auto-generated constructor stub
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
	public void conectar(GrafoLogistica gf, double distancia, double duracionViaje, double pesoMaximo, Planta origen, Planta destino) throws GrafoException, SQLException {
		
		//si las plantas no estan en el grafo se agrega
		if(!gf.getListaPlanta().contains(origen)) this.agregarPlanta(gf, origen);
		if(!gf.getListaPlanta().contains(destino)) this.agregarPlanta(gf, destino);
		
		//creamos la ruta y la agregamos al grafo
		ServiceRuta sr = new ServiceRuta();
		Ruta ruta = new Ruta(-1,distancia,duracionViaje,pesoMaximo, origen,destino);
		this.agregarRuta(gf, ruta);
		
		
		
	}
	
	
	//devuelve true si existe la ruta en la base de datos
	private boolean existeRuta(Ruta r) throws SQLException {
		ServiceRuta sr = new ServiceRuta();
		List<Ruta> li = sr.buscarRuta(r);
		if(li.isEmpty()) return true;
		return false;
	}

	//devuelve true si existe la planta en la base de datos
	private Boolean existePlanta(Planta p) {
		ServicePlanta sp=new ServicePlanta();
		List<Planta> li = sp.buscarPlanta(p);
		if(li.isEmpty()) return true;
		return false;
	}

}
