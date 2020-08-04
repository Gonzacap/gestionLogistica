package sistGestionLogistica.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCamion {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the application.
	 */
	public PanelCamion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		//---------Botones-----------------
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(23, 37, 89, 23);
		panel.add(btnCrear);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(221, 37, 89, 23);
		panel.add(btnConsultar);
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.setBounds(122, 37, 89, 23);
		panel.add(btnBaja);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(320, 37, 89, 23);
		panel.add(btnEliminar);
		
		//----------------------------
		

		
		btnCrear.addActionListener(	new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			}
		});
		

	}
}
