package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;

import org.junit.Test;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.PlantaController;

public class PlantaControllerTest {

	@Test
	public void test() throws DateTimeParseException, NumberFormatException, DatosInvalidosException, SQLException {
		PlantaController pc = new PlantaController();
		String[][] ma;
		pc.registrarPlanta("plantaDeLosPies");
		
		ma= pc.buscarPlanta("", "");
		
		System.out.println(ma);
		
		
	}

}
