package sistGestionLogistica.controller;

import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.dominio.EnvioDetalle;
import sistGestionLogistica.dominio.ItemDetalle;
import sistGestionLogistica.servicios.ServiceEnvioDetalle;

public class EnvioController {
	
	public String[][] buscarItems(String numOrden ) throws SQLException{
		Integer numeroOrden = Integer.valueOf(numOrden);
		ServiceEnvioDetalle sed = new ServiceEnvioDetalle ();
		
		return this.aMatriz(sed.buscarPorNumOrden(numeroOrden));
	}
	
	
	
	//Muestro info del envio 
	public String[][] aMatriz(EnvioDetalle envio){
		
		String[][] matriz = new String[1][4];
		
	
			
			matriz[1][0]= envio.getNumOrden().toString();
			matriz[1][1]= envio.getCamionAsignado().getPatente().toString();
			matriz[1][2]= envio.getCostoEnvio().toString();
			
		
		
		return matriz;
	}
	

}
