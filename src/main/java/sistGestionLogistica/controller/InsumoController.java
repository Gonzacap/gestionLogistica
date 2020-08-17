package sistGestionLogistica.controller;

import java.sql.SQLException;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.dominio.Insumo;
import sistGestionLogistica.dominio.InsumoGeneral;
import sistGestionLogistica.dominio.InsumoLiquido;
import sistGestionLogistica.enums.UnidadMedida;
import sistGestionLogistica.servicios.ServiceInsumo;

public class InsumoController {

	public InsumoController() {
		// TODO Auto-generated constructor stub
	}
	
	public void agregarInsumo(String descripcion, String unidadMedida, String costo,String precio, String peso, String densidad, String tipo) throws DatosInvalidosException, SQLException {
		Double precioFinal=0.0, costoFinal=0.0,densidadFinal=0.0,pesoFinal=0.0;
		Insumo insumo;
		
		// parseamos todos los datos que no sean String
		precioFinal = Double.valueOf(precio);
		costoFinal = Double.valueOf(costo);
		if(!peso.equals("")) pesoFinal = Double.valueOf(peso);
		if(!densidad.equals("")) densidadFinal = Double.valueOf(densidad);
		
		//validamos los datos
		if(!descripcion.equals("") && !tipo.equals("") && !unidadMedida.equals("")) {
			
			if(precioFinal<=0 || costoFinal<=0) throw new DatosInvalidosException("Los valores no pueden ser menores que 0");
			ServiceInsumo si=new ServiceInsumo();
			
			if(tipo.toUpperCase().equals("GENERAL")) {
				insumo= new InsumoGeneral(-1,descripcion.toUpperCase(),costoFinal, precioFinal,UnidadMedida.valueof(unidadMedida) , pesoFinal);
				si.crearInsumo(insumo);	
			}
			
			if(tipo.toUpperCase().equals("LIQUIDO")) {
				insumo = new InsumoLiquido(-1,descripcion.toUpperCase(),costoFinal, precioFinal,UnidadMedida.valueof(unidadMedida) ,densidadFinal);
				si.crearInsumo(insumo);	
			}				
			
		} 
		else throw new DatosInvalidosException("Por favor rellene los campos");
		
		
	}

}
