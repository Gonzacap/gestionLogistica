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
		if(costok>=0 && costoh>=0 && kilometros>=0 && !patente.equals("") && !marca.equals("") && !modelo.equals("") && !this.existePatente(patente)) {
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
		if(costok>=0 && costoh>=0 && kilometros>=0 && !patente.equals("") && !marca.equals("") && !modelo.equals("") &&  this.existeId(id)) {
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
	

	public String[][] buscarCamion(String idCamion, String patente, String marca, String modelo, String costoKM, String costoHora, String km,
			String fechaCompra) throws DatosInvalidosException, SQLException,DateTimeParseException,NumberFormatException {
		LocalDate date= LocalDate.MIN;
		Double costok=-1.0, costoh=-1.0;
		Integer kilometros=-1, id=-1;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		//Formateamos los parametros de busqueda
		if(!idCamion.equals("")) id=Integer.valueOf(idCamion);
		if(!costoKM.equals("")) costok= Double.valueOf(costoKM);
		if(!costoHora.equals("")) costoh=Double.valueOf(costoHora);
		if(!km.equals("")) kilometros= Integer.valueOf(km);
		if(!fechaCompra.equals("")) date = LocalDate.parse(fechaCompra, formatter);
		if(!patente.equals("")) patente = patente.toUpperCase();
		if(!marca.equals("")) marca= marca.toUpperCase();
		if(!modelo.equals("")) modelo = modelo.toUpperCase();
		
		
		
		//creamos el modelo del camion a buscar
		Camion cam= new Camion(id,patente, marca, modelo, costok , costoh,kilometros, date);
		ServiceCamion ser = new ServiceCamion();
		
		//buscamos los camiones que coincidan con la busqueda y los devolvemos como matriz de string
		return this.aMatriz(ser.buscarCamion(cam));
		
		
	}
	
	public String[][] aMatriz(List<Camion> listaCamion){
		String[][] matriz = new String[listaCamion.size()][8];
		
		for(int i=0; i<listaCamion.size();i++) {
			matriz[i][0]= listaCamion.get(i).getId().toString();
			matriz[i][1]= listaCamion.get(i).getPatente();
			matriz[i][2]= listaCamion.get(i).getMarca();
			matriz[i][3]= listaCamion.get(i).getModelo();
			matriz[i][4]= listaCamion.get(i).getCostoKM().toString();
			matriz[i][5]= listaCamion.get(i).getCostoHora().toString();
			matriz[i][6]= listaCamion.get(i).getKm().toString();
			matriz[i][7]= listaCamion.get(i).getFechaCompra().toString();
		}
		
		return matriz;
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
