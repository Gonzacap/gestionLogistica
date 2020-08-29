package sistGestionLogistica.gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.*;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.GrafoLogisticaController;
import sistGestionLogistica.controller.PlantaController;
import sistGestionLogistica.dominio.GrafoLogistica;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.servicios.ServiceGrafoLogistica;
import sistGestionLogistica.sistema.App;

public class PanelPageRank extends JPanel {
	
	
	private JButton btnActualizar;
	private JTextArea areaParaMatriz;
	private Map<Planta,Double> pageRank;

	public PanelPageRank() {

	}
	
	public void inicializar(App aplicacion) {
		aplicacion.setTitle("Sistema de Gestion Logistica - Plantas");		
		
		PanelPlanta panel = new PanelPlanta();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		Integer altoP = aplicacion.getHeight()/10;
		Integer anchoP = aplicacion.getWidth()/8;
		
		aplicacion.pageRankActivated();
		aplicacion.setContentPane(panel);
		aplicacion.revalidate();
		aplicacion.repaint();
		
		//---------Panel_Boton-----------------
		
		JPanel panelActualizar = new JPanel(new GridBagLayout());
		panelActualizar.setBounds((anchoP), (altoP), (6*anchoP), (2*altoP));
		
		GridBagConstraints p = new GridBagConstraints();
		p.gridheight = 1;
		p.gridwidth = 1;
		p.weightx = (anchoP);
		p.weighty = (altoP);
		
		p.fill = GridBagConstraints.CENTER;
		
		btnActualizar = new JButton("Actualizar");
		p.gridx = 0;
		p.gridy = 0;
		panelActualizar.add(btnActualizar, p);
		
		panelActualizar.setVisible(true);
		panel.add(panelActualizar);
		
		//--------------------
	
		areaParaMatriz = new JTextArea();
		//areaParaMatriz.setBounds(anchoP, 3*(altoP/2), (3*anchoP), (altoP));
		areaParaMatriz.setBounds((anchoP), (4*altoP), (6*anchoP), (4*altoP));
		panel.add(areaParaMatriz);
		
		//--------Acciones Botones--------------	
		
		btnActualizar.addActionListener(e-> {	//AccionAlta
			
			try {
			System.out.println("Matriz -> Visualizar");
			
			GrafoLogisticaController gc = new GrafoLogisticaController();
			pageRank = gc.pageRank();
			
			pageRank.forEach((k,v) -> areaParaMatriz.append("Planta: " + k.getId() +" - " + k.getNombre() + "   PageRank: " + v+System.getProperty("line.separator")));
			
			areaParaMatriz.append(System.getProperty("line.separator"));
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//aplicacion.revalidate();
			//aplicacion.repaint();
		});

	
	
	}
	
	
}
