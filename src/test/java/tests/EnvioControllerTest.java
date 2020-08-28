package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.EnvioController;
import sistGestionLogistica.dominio.Ruta;

public class EnvioControllerTest {

	@Test
	public void test() throws SQLException, DatosInvalidosException {
		EnvioController ec= new EnvioController();
		List<Ruta> ruta= ec.calcularCaminos(58, 3, "KILOMETRO").get(0);
		ec.agregarEnvio("58", ruta);
		System.out.println("llego");
		
		
	}

}
