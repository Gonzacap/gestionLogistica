package sistGestionLogistica.controller;

import java.util.List;

import sistGestionLogistica.dao.StockInsumoDaoMysql;
import sistGestionLogistica.dominio.Insumo;
import sistGestionLogistica.dominio.InsumoGeneral;
import sistGestionLogistica.dominio.InsumoLiquido;
import sistGestionLogistica.dominio.ItemDetalle;

public class ItemDetalleController {
	
	public ItemDetalleController() {
		
	}

	//recive una lista de ItemDetalle y la pasa a una matriz String
	
	public String[][] aMatriz(List<ItemDetalle> listaItems){
		
		String[][] matriz = new String[listaItems.size()][4];
		StockInsumoDaoMysql aux = new StockInsumoDaoMysql();
		
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
			StockInsumoDaoMysql aux = new StockInsumoDaoMysql();
			
			for(int i=0; i<listaItems.size();i++) {
				
				matriz[i][0]= listaItems.get(i).getInsumo().getIdInsumo().toString();
				matriz[i][1]= listaItems.get(i).getCantidad().toString();
				matriz[i][2]= listaItems.get(i).getPrecio().toString();
			}
			
			return matriz;
		}
	
}
