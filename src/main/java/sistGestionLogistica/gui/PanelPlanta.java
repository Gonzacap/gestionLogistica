package sistGestionLogistica.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import sistGestionLogistica.gui.PanelCamion.AccionBuscar;
import sistGestionLogistica.sistema.App;

public class PanelPlanta extends JPanel {	//JInternalFrameż

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelCamion window = new PanelCamion();
					window.frame.setVisible(true);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //esta linea no se si anda ahre
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/


	public PanelPlanta() {

	}
	
	public void inicializar(App aplicacion) {
		aplicacion.setTitle("Sistema de Gestion Logistica - Plantas");		
		
		PanelPlanta panel = new PanelPlanta();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		aplicacion.plantasActivated();
		aplicacion.setContentPane(panel);
		aplicacion.revalidate();
		aplicacion.repaint();
		
		//---------Botones-----------------
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setBounds(23, 37, 89, 23);
		JButton btnBaja = new JButton("Baja");
		btnBaja.setBounds(221, 37, 89, 23);
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(122, 37, 89, 23);
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(320, 37, 89, 23);
		
		panel.add(btnAlta);
		panel.add(btnBaja);
		panel.add(btnModificar);
		panel.add(btnBuscar);
		
		//----------------------------

		
		//--------Acciones Botones--------------	
		
		btnAlta.addActionListener(e-> {	//AccionAlta
			
			System.out.println("Planta -> Alta");
			
		});
		btnBaja.addActionListener(e-> { //AccionBaja
			
			System.out.println("Planta -> Baja");

		});
		btnModificar.addActionListener(e-> {
		
			System.out.println("Planta -> Modificar");

		});
		btnBuscar.addActionListener(e-> {
			
			System.out.println("Planta -> Buscar");

		});

	}
	
}
