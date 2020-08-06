package sistGestionLogistica.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.servicios.ServiceCamion;

public class CamionController {

	public CamionController() {
		// TODO Auto-generated constructor stub
	}
	
	public void agregarCamion(String patente, String marca, String modelo, String costoKM, String costoHora, String km,
			String fechaCompra) throws SQLException, DatosInvalidosException,DateTimeParseException,NumberFormatException{
		LocalDate date;
		Double costok, costoh;
		Integer kilometros;
		
		// parseamos todos los datos que no sean String
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		date = LocalDate.parse(fechaCompra, formatter);
		costok= Double.valueOf(costoKM);
		costoh=Double.valueOf(costoHora);
		kilometros= Integer.valueOf(km);
		
		//validamos los datos
		if(costok>=0 && costoh>=0 && kilometros>=0 && !this.existePatente(patente)) {
			Camion cam= new Camion(-1,patente.toUpperCase(), marca.toUpperCase(), modelo.toUpperCase(), costok , costoh,kilometros, date);
			ServiceCamion ser = new ServiceCamion();
			ser.crearCamion(cam);
		}
		else throw new DatosInvalidosException();
			
		
	}
	
	public void editarCamion(String idCamion, String patente, String marca, String modelo, String costoKM, String costoHora, String km,
			String fechaCompra) throws DatosInvalidosException, SQLException,DateTimeParseException,NumberFormatException {
		LocalDate date;
		Double costok, costoh;
		Integer kilometros, id;
		
		// parseamos todos los datos que no sean String
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		date = LocalDate.parse(fechaCompra, formatter);
		costok= Double.valueOf(costoKM);
		costoh=Double.valueOf(costoHora);
		kilometros= Integer.valueOf(km);
		id=Integer.valueOf(idCamion);
		
		//validamos los datos
		if(costok>=0 && costoh>=0 && kilometros>=0 && this.existeId(id)) {
			Camion cam= new Camion(id,patente.toUpperCase(), marca.toUpperCase(), modelo.toUpperCase(), costok , costoh,kilometros, date);
			ServiceCamion ser = new ServiceCamion();
			ser.editarCamion(cam);
		}
		else throw new DatosInvalidosException();
		
		
	}
	public void borrarCamion(String idCamion) throws DatosInvalidosException, SQLException,NumberFormatException {
		Integer id;
		id=Integer.valueOf(idCamion);
		
		if(this.existeId(id)) {
			ServiceCamion ser = new ServiceCamion();
			ser.borrar(id);
		}
		else throw new DatosInvalidosException();
	}
	

	public List<Camion> buscarCamion(String idCamion, String patente, String marca, String modelo, String costoKM, String costoHora, String km,
			String fechaCompra) throws DatosInvalidosException, SQLException,DateTimeParseException,NumberFormatException {
		LocalDate date= LocalDate.MIN;
		Double costok=-1.0, costoh=-1.0;
		Integer kilometros=-1, id=-1;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		//Formateamos los parametros de busqueda
		if(idCamion != "") id=Integer.valueOf(idCamion);
		if(costoKM != "") costok= Double.valueOf(costoKM);
		if(costoHora != "") costoh=Double.valueOf(costoHora);
		if(km!= "") kilometros= Integer.valueOf(km);
		if(fechaCompra != "") date = LocalDate.parse(fechaCompra, formatter);
		
		Camion cam= new Camion(id,patente.toUpperCase(), marca.toUpperCase(), modelo.toUpperCase(), costok , costoh,kilometros, date);
		ServiceCamion ser = new ServiceCamion();
		
		
		return ser.buscarCamion(cam);
		
		
	}
	
	
	public Boolean existePatente(String patente) throws SQLException {
		ServiceCamion sc=new ServiceCamion();
		if(sc.buscarPorPatente(patente.toUpperCase()).getId()<0) return false;
		return true;
	}
	
	private Boolean existeId(Integer id) throws SQLException {
		ServiceCamion sc=new ServiceCamion();
		if(sc.buscarPorId(id).getId()<0) return false;
		return true;
	}

}
