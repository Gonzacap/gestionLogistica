package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import excepciones.DatosInvalidosException;
import excepciones.GrafoException;
import sistGestionLogistica.controller.EnvioController;
import sistGestionLogistica.controller.GrafoLogisticaController;
import sistGestionLogistica.dominio.GrafoLogistica;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.Ruta;
import sistGestionLogistica.servicios.ServiceGrafoLogistica;

public class GrafoLogisticaControllerTest {
	GrafoLogisticaController glc = new GrafoLogisticaController();

	//Sis se ejecutan los test de a uno quedan mas entendibles
	@Test
	public void pageRankTest() throws DatosInvalidosException, SQLException, GrafoException {
		
		Map<Planta,Double> mapa = glc.pageRank();
		System.out.println("-----------Resultado PageRank:");
		System.out.println(mapa);
	}
	@Test
	public void matPorTiempoTest() throws SQLException {
		Double[][] matriz = glc.matPorTiempo();
		ServiceGrafoLogistica sg = new ServiceGrafoLogistica();
		GrafoLogistica graf = sg.inicializarGrafo(); 
		List<Planta> plantas = graf.getListaPlanta();
		
		System.out.println("---------Matriz por tiempo:");
		for(int i=0;i<matriz.length;i++) {
			String mostrar=plantas.get(i).getId()+"\t\t";
			for(int j=0; j<matriz.length;j++) {
				mostrar=mostrar+matriz[i][j]+"\t";
			}
			System.out.println(mostrar);
		}
		
	}
	@Test
	public void matPorKmTest() throws SQLException {
		Double[][] matriz = glc.matPorKm();
		ServiceGrafoLogistica sg = new ServiceGrafoLogistica();
		GrafoLogistica graf = sg.inicializarGrafo(); 
		List<Planta> plantas = graf.getListaPlanta();
	
		System.out.println("---------Matriz por kilometro:");
		for(int i=0;i<matriz.length;i++) {
			String mostrar=plantas.get(i).getId()+"\t\t";
			for(int j=0; j<matriz.length;j++) {
				mostrar=mostrar+matriz[i][j]+"\t";
			}
			System.out.println(mostrar);
		}
		
	}

}
