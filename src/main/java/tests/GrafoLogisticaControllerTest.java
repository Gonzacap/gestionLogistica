package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import excepciones.DatosInvalidosException;
import excepciones.GrafoException;
import sistGestionLogistica.controller.EnvioController;
import sistGestionLogistica.controller.GrafoLogisticaController;
import sistGestionLogistica.dominio.Ruta;

public class GrafoLogisticaControllerTest {

	@Test
	public void test() throws DatosInvalidosException, SQLException, GrafoException {
//		GrafoLogisticaController gflc = new GrafoLogisticaController();
//		
//		gflc.conectarPlantas("25", "2", "85", "1", "2");
		
		EnvioController ec = new EnvioController();
		ArrayList<ArrayList<Ruta>> lista = ec.calcularCaminos(33, 3, "KILOMETRO");
		System.out.println(lista);
	}

}
