package sistGestionLogistica.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.servicios.ServiceCamion;
import sistGestionLogistica.servicios.ServicePlanta;

public class PlantaController {

	public PlantaController() {
		// TODO Auto-generated constructor stub
	}
	//faltan las Excepciones
	public void registrarPlanta(String nombre) throws DatosInvalidosException {
		
		//validamos que lo ingresado no este vacio y que el nombre no sea repetido
		if(!nombre.isEmpty() && !this.existeNombre(nombre)) {
			Planta p = new Planta(-1,nombre.toUpperCase());
			ServicePlanta sp = new ServicePlanta();
			sp.registrarPlanta(p);
			
		}
<<<<<<< HEAD
		else throw new DatosInvalidosException("Por favor rellene los campos");
=======
		else throw new DatosInvalidosException("Por favor, ingrese un nombre");
>>>>>>> 5f5df83a2aaa4c330373ccb68857b9353192d2a3
	}
	
	
	//No devuelve listaStockInsumos
	public String[][] buscarPlanta(String idPlanta, String nombre) 
			throws DatosInvalidosException, SQLException,DateTimeParseException,NumberFormatException {

		Integer id=-1;
		
		//Formateamos los parametros de busqueda
		
		if(!idPlanta.isEmpty()) {
			id=Integer.valueOf(idPlanta);	
		}
		else throw new DatosInvalidosException("Por favor rellene los campos");
		
		//creamos el modelo de la planta a buscar
		Planta p= new Planta(id,nombre);
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
}
