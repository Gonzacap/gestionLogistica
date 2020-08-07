package sistGestionLogistica.sistema;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import sistGestionLogistica.gui.PanelCamion;
import sistGestionLogistica.gui.PanelPlanta;

public class App extends JFrame {

	private JFrame frmSistemaDeGestion;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmSistemaDeGestion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public App() {
		initialize();
	}


	private void initialize() {
		
		// 1. Crea el marco		
		frmSistemaDeGestion = new JFrame();
		frmSistemaDeGestion.setTitle("Sistema de Gestion Logistica");
		
		// 2. Opcional: ¿Qué sucede cuando se cierra el marco?
		frmSistemaDeGestion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeGestion.getContentPane().setLayout(null);
		// 3. Crear componentes y ponerlos en el marco.
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frmSistemaDeGestion.getContentPane().add(panel);
		panel.setLayout(null);
		// 4. Tamaño del marco.
		frmSistemaDeGestion.setBounds(100, 100, 450, 300);//frmSistemaDeGestion.pack();
		// 5. Enséñalo.
		//frmSistemaDeGestion.setVisible (true); //esto no afecta en nada
		
		//barra menu
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 433, 21);
		panel.add(menuBar);
		//menu acciones
		JMenu menuAcciones = new JMenu("Acciones");
		menuBar.add(menuAcciones);
		
		//items menu
		JMenuItem menuCamiones = new JMenuItem("Gestion de Camiones");
		menuAcciones.add(menuCamiones);
		menuCamiones.addActionListener(new abrirPanelCamion());
		JMenuItem menuPlantas = new JMenuItem("Gestion de Plantas");
		menuAcciones.add(menuPlantas);
		menuPlantas.addActionListener(new abrirPanelPlanta());
		
	}
	
	

	class abrirPanelCamion implements ActionListener {
		 
		 public void actionPerformed(ActionEvent e) {
			 
			 System.out.println("cambio de pantalla a PanelCamion");

			 PanelCamion pC = new PanelCamion();
			 pC.setVisible(true);
			 
		 }
	}
	
	class abrirPanelPlanta implements ActionListener {
		 
		 public void actionPerformed(ActionEvent e) {
			 
			 System.out.println("cambio de pantalla a PanelPlanta");

			 PanelPlanta pP = new PanelPlanta();
			 pP.setVisible(true);
		 }
	}
	
	

}
