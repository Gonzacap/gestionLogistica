package sistGestionLogistica.controller;

import java.sql.SQLException;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.dominio.Insumo;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.StockInsumo;
import sistGestionLogistica.servicios.ServiceInsumo;
import sistGestionLogistica.servicios.ServicePlanta;
import sistGestionLogistica.servicios.ServiceStockInsumo;

public class StockInsumoController {

	public StockInsumoController() {
		// TODO Auto-generated constructor stub
	}
	public void agregarStockInsumo(String idPlanta, String idInsumo, String cantidad, String puntoReposicion) throws DatosInvalidosException, SQLException {
		Integer idPlant=-1, idIns=-1, cant=-1,ptoRep=-1;
		Planta planta;
		Insumo insumo;
		
		// parseamos todos los datos que no sean String
		if(!idPlanta.isBlank()) idPlant= Integer.valueOf(idPlanta);
		if(!idInsumo.isBlank()) idIns = Integer.valueOf(idInsumo);
		if(!cantidad.isBlank()) cant= Integer.valueOf(cantidad);
		if(!puntoReposicion.isBlank()) ptoRep = Integer.valueOf(puntoReposicion);
		
		//validamos los datos
		if(idPlant>=0 && idIns>=0 && cant>=0 && ptoRep>=0) {
			
			if(!this.existeStock(idPlant, idIns)) {
				//obtenemos la planta
				ServicePlanta sp = new ServicePlanta();
				planta = sp.buscarPorId(idPlant);
				//obtenemos el insumo
				ServiceInsumo si=new ServiceInsumo();
				insumo = si.buscarPorId(idIns);
				
				ServiceStockInsumo ssi=new ServiceStockInsumo();
				ssi.crearStock(new StockInsumo(-1,planta, insumo,cant,ptoRep));
				
				
			}else throw new DatosInvalidosException("El stock ya existe");
						
		}else throw new DatosInvalidosException("Complete los datos correctamente");
		
		
		
	}
	
	public Boolean existeStock(Integer idPlanta, Integer idInsumo) throws SQLException {
		ServiceStockInsumo ssi=new ServiceStockInsumo();
		
		return ssi.existeStock(idPlanta, idInsumo);
	}

}
