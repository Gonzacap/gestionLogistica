package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.StockInsumoController;
import sistGestionLogistica.dao.StockInsumoDaoMysql;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.servicios.ServiceStockInsumo;

public class StockInsumoControllerTest {

	@Test
	public void test() throws DatosInvalidosException, SQLException {
//		StockInsumoController sic= new StockInsumoController();
//		sic.agregarStockInsumo("3", "3", "23", "100");
//		ServiceStockInsumo ssi= new ServiceStockInsumo();
//		System.out.println(ssi.stockTotal(8));
//		
//		String[][] m= sic.faltante("", "");
//		
//		System.out.println(m);
//		StockInsumoDaoMysql si = new StockInsumoDaoMysql();
//		List<Planta> p = si.plantasConInsumo(7, 10);
//		
//		System.out.println(p);
		
		ServiceStockInsumo ss = new ServiceStockInsumo();
		Integer[][] matriz = new Integer[2][2];
		matriz[0][0]= 3;
		matriz[0][1]= 10;
		matriz[1][0]= 8;
		matriz[1][1]= 11;
		
		List<Planta> p= ss.plantasConStock(matriz);
		System.out.println(p);
		
	}

}
