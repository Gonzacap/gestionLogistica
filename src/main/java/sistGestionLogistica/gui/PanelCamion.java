package sistGestionLogistica.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PanelCamion extends JFrame {


	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelCamion window = new PanelCamion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/


	public PanelCamion() {
		super("Sistema de Gestion Loistica - Camiones");
		initialize();
	}

	private void initialize() {
		//frame = new JFrame();
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		//---------Botones-----------------
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setBounds(23, 37, 89, 23);
		btnAlta.addActionListener(new AccionAlta());
		panel.add(btnAlta);
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.setBounds(221, 37, 89, 23);
		btnBaja.addActionListener(new AccionBaja());
		panel.add(btnBaja);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(122, 37, 89, 23);
		btnModificar.addActionListener(new AccionModificar());
		panel.add(btnModificar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(320, 37, 89, 23);
		btnBuscar.addActionListener(new AccionBuscar());
		panel.add(btnBuscar);
		
		//----------------------------
		

	}
	
	class AccionAlta implements ActionListener {
		 
		 public void actionPerformed(ActionEvent e) {
			 
			 System.out.println("Camion -> Alta");

		 }
	}
	
	class AccionBaja implements ActionListener {
		 
		 public void actionPerformed(ActionEvent e) {
			 
			 System.out.println("Camion -> Baja");

		 }
	}
	
	class AccionModificar implements ActionListener {
		 
		 public void actionPerformed(ActionEvent e) {
			 
			 System.out.println("Camion -> Modificar");

		 }
	}
	
	class AccionBuscar implements ActionListener {
		 
		 public void actionPerformed(ActionEvent e) {
			 
			 System.out.println("Camion -> Buscar");

		 }
	}
	
}
