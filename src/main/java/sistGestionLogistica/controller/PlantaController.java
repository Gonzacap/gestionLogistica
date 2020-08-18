package sistGestionLogistica.controller;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.List;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.servicios.ServicePlanta;

public class PlantaController {

	public PlantaController() {
		// TODO Auto-generated constructor stub
	}
	

	public void registrarPlanta(String nombre) throws DatosInvalidosException {
		
		//validamos que lo ingresado no este vacio y que el nombre no sea repetido
		if(!nombre.isEmpty() && !this.existeNombre(nombre)) {
			Planta p = new Planta(-1,nombre.toUpperCase());
			ServicePlanta sp = new ServicePlanta();
			sp.registrarPlanta(p);
			
		}
		else throw new DatosInvalidosException("Por favor, ingrese un nombre");

	}
	
	public void editarPlanta(String idPlanta, String nombre) throws DatosInvalidosException, SQLException {
		
		Integer id = Integer.valueOf(idPlanta);
		
		if(this.existeId(id)) {
			
			if(!nombre.isEmpty() && !this.existeNombre(nombre)) {
				
				Planta p = new Planta(id,nombre.toUpperCase());
				System.out.println("Editando planta");
				ServicePlanta sp = new ServicePlanta();
				sp.editarPlanta(p);
				System.out.println("Planta editada");
			
			}
			else throw new DatosInvalidosException("Por favor, ingrese un nombre");
		}
		else throw new DatosInvalidosException("ID inexistente");
		
	}
	
	public String[][] buscarPlanta(String idPlanta, String nombre) 
			throws DatosInvalidosException, SQLException,DateTimeParseException,NumberFormatException {
		
		
		//para buscar todos los id: idPlanta=-1
		//para buscar todos los nombre: nombre=""

		Integer id=-1;		
		if(!idPlanta.equals("")) id=Integer.valueOf(idPlanta);

		
		//creamos el modelo de la planta a buscar
		Planta p= new Planta(id,nombre.toUpperCase());
		ServicePlanta ser = new ServicePlanta();
		
		//buscamos las plantas  que coincidan con la busqueda y las devolvemos como matriz de string
		return this.aMatriz(ser.buscarPlanta(p));
		
		
	}
		
	public String[][] aMatriz(List<Planta> listaPlanta){
		String[][] matriz = new String[listaPlanta.size()][2];
		
		for(int i=0; i<listaPlanta.size();i++) {
			matriz[i][0]= listaPlanta.get(i).getId().toString();
			matriz[i][1]= listaPlanta.get(i).getNombre();
		}
		
		return matriz;
	}

	private Boolean existeNombre(String nombre) {
		//seteamos una planta para buscar por nombre
		Planta p = new Planta(-1,nombre.toUpperCase());
		ServicePlanta sp = new ServicePlanta();
		//si el resultado es vacio devolvemos false
		if(sp.buscarPlanta(p).isEmpty()) return false;
		return true;
	}
	
	private Boolean existeId(Integer id) throws SQLException {
		ServicePlanta sp = new ServicePlanta();
		if(sp.buscarPorId(id).getId()<0) return false;
		return true;
	}
	
	public void borrarPlanta(String idPlanta) throws DatosInvalidosException, SQLException,NumberFormatException {
		
		Integer id=Integer.valueOf(idPlanta);
		
		if(this.existeId(id)) {
			ServicePlanta ser = new ServicePlanta();
			ser.borrar(id);
		}
		else throw new DatosInvalidosException();
	}
	

}
