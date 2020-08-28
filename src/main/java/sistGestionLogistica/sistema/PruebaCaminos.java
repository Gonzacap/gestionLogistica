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
		EnvioDetalleDaoMysql envioDao = new EnvioDetalleDaoMysql();
		  ServiceCamion sc = new ServiceCamion();
		  Comparator<Camion> comparator = new CompareCamion();
			PriorityQueue<Camion> cola= new PriorityQueue<Camion>(comparator);
			List<Camion> todosCamiones = sc.buscarCamion(new Camion(-1,"","","",-1.0,-1.0,-1,LocalDate.MIN));
			List<Camion> camionesAsignados = envioDao.camionesAsignados();
			
			for(Camion c: camionesAsignados) {
			  todosCamiones.remove(c);
				
			}
			System.out.println("asignados: " +camionesAsignados);
			System.out.println("");
			System.out.println("todos: " + todosCamiones);
			

	}
}
