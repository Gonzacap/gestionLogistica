package sistGestionLogistica.controller;

import java.sql.SQLException;
import java.util.List;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.dominio.Insumo;
import sistGestionLogistica.dominio.InsumoGeneral;
import sistGestionLogistica.dominio.InsumoLiquido;
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
	
	
	//devuelve una matriz con columnas ID_PLANTA, ID_INSUMO, NOMBRE_PLANTA, DESCRIPCION_INSUMO, CANTIDAD, PTO_PEDIDO, STOCK_TOTAL	
	public String[][] faltante(String idPlanta, String idInsumo){	// para buscar todos enviar faltantes("","")
		Integer idPlan=-1, idIns= -1;
		ServiceStockInsumo ssi=new ServiceStockInsumo();
		//Formateamos los parametros de busqueda
		if(!idPlanta.isBlank()) idPlan = Integer.valueOf(idPlanta);
		if(!idInsumo.isBlank()) idIns = Integer.valueOf(idInsumo);
		//buscamos las plantas que tengan faltantes
		return this.aMatriz(ssi.faltantes(idPlan, idIns));
	}
	
	public String[][] aMatriz(List<StockInsumo> listaStockInsumo){
		String[][] matriz = new String[listaStockInsumo.size()][7];
		ServiceStockInsumo ssi=new ServiceStockInsumo();		
		
		for(int i=0; i<listaStockInsumo.size();i++) {
			matriz[i][0]= listaStockInsumo.get(i).getPlanta().getId().toString();
			matriz[i][1]= listaStockInsumo.get(i).getInsumo().getIdInsumo().toString();
			matriz[i][2]= listaStockInsumo.get(i).getPlanta().getNombre();
			matriz[i][3]= listaStockInsumo.get(i).getInsumo().getDescripcion();
			matriz[i][4]= listaStockInsumo.get(i).getCantidad().toString();
			matriz[i][5]= listaStockInsumo.get(i).getPuntoReposicion().toString();
			matriz[i][6]= ssi.stockTotal(listaStockInsumo.get(i).getInsumo().getIdInsumo()).toString();

		}
		
		return matriz;
	}

}
