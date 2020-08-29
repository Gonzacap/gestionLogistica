package sistGestionLogistica.sistema;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.JOptionPane;

import sistGestionLogistica.comparator.CompareCamion;
import sistGestionLogistica.dao.EnvioDetalleDaoMysql;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.GrafoLogistica;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.Ruta;
import sistGestionLogistica.servicios.ServiceCamion;
import sistGestionLogistica.servicios.ServiceGrafoLogistica;

public class PruebaCaminos {

	public static void main(String[] args) throws SQLException{
		
		ServiceGrafoLogistica sg = new ServiceGrafoLogistica();
		GrafoLogistica grap = sg.inicializarGrafo();
		
		List<Double> asd =sg.pageRank(grap);
		
		
		for(Double pr: asd) {
			System.out.println(pr);
		}

	}
}
