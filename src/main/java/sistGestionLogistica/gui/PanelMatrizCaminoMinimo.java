package sistGestionLogistica.gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.PlantaController;
import sistGestionLogistica.gui.PanelPlanta.AccionBuscar;
import sistGestionLogistica.sistema.App;

public class PanelMatrizCaminoMinimo extends JPanel {
	
	
	private JButton btnActualizar;
	private JTextArea areaParaMatriz;

	public PanelMatrizCaminoMinimo() {

	}
	
	public void inicializar(App aplicacion) {
		aplicacion.setTitle("Sistema de Gestion Logistica - Plantas");		
		
		PanelPlanta panel = new PanelPlanta();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		Integer altoP = aplicacion.getHeight()/10;
		Integer anchoP = aplicacion.getWidth()/8;
		
		aplicacion.matrizActivated();
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
	
		//--------Acciones Botones--------------	
		
		btnActualizar.addActionListener(e-> {	//AccionAlta
			
			System.out.println("Matriz -> Visualizar");
			
			
		});

	
	
	}
	
	
}
