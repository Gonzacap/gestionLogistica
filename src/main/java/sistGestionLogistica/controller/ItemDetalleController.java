package sistGestionLogistica.controller;

import java.sql.SQLException;
import java.util.List;

import sistGestionLogistica.dao.StockInsumoDaoMysql;
import sistGestionLogistica.dominio.Insumo;
import sistGestionLogistica.dominio.InsumoGeneral;
import sistGestionLogistica.dominio.InsumoLiquido;
import sistGestionLogistica.dominio.ItemDetalle;
import sistGestionLogistica.servicios.ServiceItemDetalle;


public class ItemDetalleController {
	
	public ItemDetalleController() {
		
	}
	public String[][] buscarItems(String numOrden ) throws SQLException{
		Integer numeroOrden = Integer.valueOf(numOrden);
		ServiceItemDetalle sid = new ServiceItemDetalle ();
		
		return this.aMatriz(sid.buscarPorNumOrden(numeroOrden));
	}
	//recive una lista de ItemDetalle y la pasa a una matriz String
	
	public String[][] aMatriz(List<ItemDetalle> listaItems){
		
		String[][] matriz = new String[listaItems.size()][4];
		
		
		for(int i=0; i<listaItems.size();i++) {
			
			matriz[i][0]= listaItems.get(i).getNumOrden().toString();
			matriz[i][1]= listaItems.get(i).getInsumo().getIdInsumo().toString();
			matriz[i][2]= listaItems.get(i).getCantidad().toString();
			matriz[i][3]= listaItems.get(i).getPrecio().toString();
		}
		
		return matriz;
	}
	
	//recive una lista de ItemDetalle y la pasa a una matriz String
	
		public String[][] aMatriz2(List<ItemDetalle> listaItems){
			
			String[][] matriz = new String[listaItems.size()][3];
			
			
			for(int i=0; i<listaItems.size();i++) {
				
				matriz[i][0]= listaItems.get(i).getInsumo().getIdInsumo().toString();
				matriz[i][1]= listaItems.get(i).getCantidad().toString();
				matriz[i][2]= listaItems.get(i).getPrecio().toString();
			}
			
			return matriz;
		}
	
}
