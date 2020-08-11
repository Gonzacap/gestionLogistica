package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import excepciones.DatosInvalidosException;
import excepciones.GrafoException;
import sistGestionLogistica.controller.GrafoLogisticaController;

public class GrafoLogisticaControllerTest {

	@Test
	public void test() throws DatosInvalidosException, SQLException, GrafoException {
		GrafoLogisticaController gflc = new GrafoLogisticaController();
		
		gflc.conectarPlantas("25", "2", "85", "1", "2");
	}

}
