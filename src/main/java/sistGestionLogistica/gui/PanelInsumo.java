package sistGestionLogistica.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import sistGestionLogistica.sistema.App;


public class PanelInsumo extends JPanel {


	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelInsumo window = new PanelInsumo();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/


	public PanelInsumo() {

	}

	public void inicializar(App aplicacion) {
		aplicacion.setTitle("Sistema de Gestion Logistica - Insumos");

		PanelInsumo panel= new PanelInsumo();
		panel.setLayout(null);
		panel.setBackground(Color.blue);
		
		aplicacion.insumosActivated();
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
					
					System.out.println("Insumo -> Alta");
					
				});
				btnBaja.addActionListener(e-> { //AccionBaja
					
					System.out.println("Insumo -> Baja");

				});
				btnModificar.addActionListener(e-> {
				
					System.out.println("Insumo -> Modificar");

				});
				btnBuscar.addActionListener(e-> {
					
					System.out.println("Insumo -> Buscar");

				});

			}
	
	
}
