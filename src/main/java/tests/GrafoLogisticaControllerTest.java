package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import excepciones.DatosInvalidosException;
import excepciones.GrafoException;
import sistGestionLogistica.controller.EnvioController;
import sistGestionLogistica.controller.GrafoLogisticaController;
import sistGestionLogistica.dominio.GrafoLogistica;
import sistGestionLogistica.dominio.Ruta;
import sistGestionLogistica.servicios.ServiceGrafoLogistica;

public class GrafoLogisticaControllerTest {

	@Test
	public void test() throws DatosInvalidosException, SQLException, GrafoException {
//		GrafoLogisticaController gflc = new GrafoLogisticaController();
//		
//		gflc.conectarPlantas("25", "2", "85", "1", "2");
		
//		EnvioController ec = new EnvioController();
//		ArrayList<ArrayList<Ruta>> lista = ec.calcularCaminos(1111, 3, "KILOMETRO");
//		System.out.println(lista);
		ServiceGrafoLogistica sgl= new ServiceGrafoLogistica();
		GrafoLogistica grafo = sgl.inicializarGrafo();
		
		List<Double> lista =sgl.pageRank(grafo);
		System.out.println(lista);
		
	}

}
