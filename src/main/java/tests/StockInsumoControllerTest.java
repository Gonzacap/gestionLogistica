package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.StockInsumoController;
import sistGestionLogistica.servicios.ServiceStockInsumo;

public class StockInsumoControllerTest {

	@Test
	public void test() throws DatosInvalidosException, SQLException {
		StockInsumoController sic= new StockInsumoController();
		sic.agregarStockInsumo("3", "8", "23", "10");
		ServiceStockInsumo ssi= new ServiceStockInsumo();
		System.out.println(ssi.stockTotal(8));
		
	}

}
