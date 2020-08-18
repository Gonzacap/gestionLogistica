package sistGestionLogistica.controller;

import java.sql.SQLException;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.dominio.Insumo;
import sistGestionLogistica.dominio.InsumoGeneral;
import sistGestionLogistica.dominio.InsumoLiquido;
import sistGestionLogistica.enums.UnidadMedida;
import sistGestionLogistica.servicios.ServiceCamion;
import sistGestionLogistica.servicios.ServiceInsumo;

public class InsumoController {

	public InsumoController() {
		// TODO Auto-generated constructor stub
	}
	
	public void agregarInsumo(String descripcion, String unidadMedida, String costo,String precio, String peso, String densidad, String tipo) throws DatosInvalidosException, SQLException {
		Double precioFinal=0.0, costoFinal=0.0,densidadFinal=0.0,pesoFinal=0.0;
		Insumo insumo;
		Integer id = null;
		
		// parseamos todos los datos que no sean String
		precioFinal = Double.valueOf(precio);
		costoFinal = Double.valueOf(costo);
		if(!peso.equals("")) pesoFinal = Double.valueOf(peso);
		if(!densidad.equals("")) densidadFinal = Double.valueOf(densidad);
		
		//validamos los datos
		if(!descripcion.equals("") && !tipo.equals("") && !unidadMedida.equals("")) {
			
			if(precioFinal<=0 || costoFinal<=0) throw new DatosInvalidosException("Los valores no pueden ser menores que 0");
			ServiceInsumo si=new ServiceInsumo();
			id = si.ultimoId() + 1;
			if(tipo.toUpperCase().equals("GENERAL")) {
				insumo= new InsumoGeneral(id,descripcion.toUpperCase(),costoFinal, precioFinal,UnidadMedida.valueof(unidadMedida) , pesoFinal);
				si.crearInsumo(insumo);	
			}
			
			if(tipo.toUpperCase().equals("LIQUIDO")) {
				insumo = new InsumoLiquido(id,descripcion.toUpperCase(),costoFinal, precioFinal,UnidadMedida.valueof(unidadMedida) ,densidadFinal);
				si.crearInsumo(insumo);	
			}				
			
		} 
		else throw new DatosInvalidosException("Por favor rellene los campos");
		
		
	}
	
	public void editarInsumo(String idInsumo,String descripcion, String unidadMedida, String costo,String precio, String peso, String densidad, String tipo) throws DatosInvalidosException, SQLException{
		
		Double precioFinal=0.0, costoFinal=0.0,densidadFinal=0.0,pesoFinal=0.0;
		Integer id=0;
		Insumo insumo;
		
		// parseamos todos los datos que no sean String
		id = Integer.valueOf(idInsumo);
		precioFinal = Double.valueOf(precio);
		costoFinal = Double.valueOf(costo);
		if(!peso.equals("")) pesoFinal = Double.valueOf(peso);
		if(!densidad.equals("")) densidadFinal = Double.valueOf(densidad);
		
		//validamos los datos
				if(!descripcion.equals("") && !tipo.equals("") && !unidadMedida.equals("")) {
					
					if(precioFinal<=0 || costoFinal<=0) throw new DatosInvalidosException("Los valores no pueden ser menores que 0");
					ServiceInsumo si=new ServiceInsumo();
					
					if(tipo.toUpperCase().equals("GENERAL")) {
						insumo= new InsumoGeneral(id,descripcion.toUpperCase(),costoFinal, precioFinal,UnidadMedida.valueof(unidadMedida) , pesoFinal);
						si.editarInsumo(insumo);
					}
					
					if(tipo.toUpperCase().equals("LIQUIDO")) {
						insumo = new InsumoLiquido(id,descripcion.toUpperCase(),costoFinal, precioFinal,UnidadMedida.valueof(unidadMedida) ,densidadFinal);
						si.editarInsumo(insumo);
					}				
					
				} 
				else throw new DatosInvalidosException("Por favor rellene los campos");
		
		
	}
	public void borrarInsumo(String idInsumo) throws DatosInvalidosException, SQLException,NumberFormatException {
		Integer id;
		id=Integer.valueOf(idInsumo);
		
		if(this.existeId(id)) {
			ServiceInsumo ser = new ServiceInsumo();
			ser.borrar(id);
		}
		else throw new DatosInvalidosException();
	}
	
	private Boolean existeId(Integer id) throws SQLException {
		ServiceInsumo i=new ServiceInsumo();
		if(i.buscarPorId(id).getIdInsumo()<0) return false;
		return true;
	}

}
