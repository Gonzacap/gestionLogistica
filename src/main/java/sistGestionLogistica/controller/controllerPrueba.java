package sistGestionLogistica.controller;

import java.sql.SQLException;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.dao.StockInsumoDao;
import sistGestionLogistica.dao.StockInsumoDaoMysql;
import sistGestionLogistica.dominio.Insumo;
import sistGestionLogistica.dominio.InsumoLiquido;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.StockInsumo;
import sistGestionLogistica.enums.UnidadMedida;

public class controllerPrueba {
	
	public static void main(String[] args) throws DatosInvalidosException, SQLException {
		StockInsumoController sic = new StockInsumoController();
		Planta p = new Planta(66, "planta666");
        StockInsumoDao sid = new StockInsumoDaoMysql();
		Insumo i = new InsumoLiquido("pruebaController", 456.0 , 123.1 , UnidadMedida.CM3,50.0) ;
		StockInsumo si = new StockInsumo(1, p,i,45,40);
		
	//	sic.agregarStockInsumo("1", "1", "45", "40");
	    	
          System.out.println(sid.buscarStockPlanta(1));
	}

}
