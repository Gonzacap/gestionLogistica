package tests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.CamionController;

public class CamionControllerTest {

	@Test
	public void testAgregarCamion() {
		CamionController cc= new CamionController();
		
			try {
				cc.agregarCamion("AAA149", "Ford", "hache", "1200", "100", "1300", "01/11/2010");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DatosInvalidosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	
	@Test 
	public void testEditarCamion() {
		CamionController cc= new CamionController();
		
		try {
			cc.editarCamion("1","GMD345", "Ford", "ka", "1200", "100", "1300", "01/11/1999");
		} catch (DatosInvalidosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
