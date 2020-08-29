package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.CamionController;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.servicios.ServiceCamion;

public class CamionControllerTest {
	CamionController cc= new CamionController();

	@Test
	public void testAgregarCamion() throws DateTimeParseException, NumberFormatException, SQLException, DatosInvalidosException {
		
			cc.agregarCamion("AAA149", "Ford", "hache", "1200", "100", "1300", "01/11/2010");
		
			
		}
	
	
	@Test 
	public void testEditarCamion() throws DateTimeParseException, NumberFormatException, DatosInvalidosException, SQLException {
		//editar por idCamion
			cc.editarCamion("1","GMD345", "Ford", "ka", "1200", "100", "1300", "01/11/1999");
	}
	
	@Test 
	public void testBorrarCamion() throws NumberFormatException, DatosInvalidosException, SQLException {
			//insertar id del camion a borrar
			cc.borrarCamion("9");
		
	}
	
	@Test
	public void buscarCamionTest() throws SQLException, DateTimeParseException, NumberFormatException, DatosInvalidosException {
			//al enviar todo vacio busca todos los camiones
			String[][] lis = cc.buscarCamion("", "", "", "", "", "", "", "");
			for(int i=0; i<lis.length;i++) {
				System.out.println(lis[i][0]+"\t"+lis[i][1]);
			}
		
	}

}
