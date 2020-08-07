package sistGestionLogistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.CamionController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;

public class BajaCamion extends JFrame {

	private JPanel contentPane;
	private JTextField textField_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaCamion frame = new BajaCamion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BajaCamion() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 424, 256);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingresar ID de camion a dar de Baja");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(90, 29, 247, 44);
		panel.add(lblNewLabel);
		
		textField_id = new JTextField();
		textField_id.setBounds(159, 84, 86, 20);
		panel.add(textField_id);
		textField_id.setColumns(10);
		
		JButton btnNewButton = new JButton("DAR DE BAJA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
CamionController cc = new CamionController();
				
				try {
					cc.borrarCamion(textField_id.getText());
					
					JOptionPane.showMessageDialog(contentPane,
						    "El camion fue dado de baja con exito.", "Baja Exitosa",JOptionPane.INFORMATION_MESSAGE);
					
				} catch (DateTimeParseException | DatosInvalidosException | NumberFormatException e1) {
					//Mensaje de error
					JOptionPane.showMessageDialog(contentPane,
						    "El id ingresado no es valido.",
						    "Error al Ingresar datos",
						    JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (SQLException e1) {
					
					//Mensaje de error
					JOptionPane.showMessageDialog(contentPane,
						    "Verifique su conexion a la Base de Datos.",
						    "Error en la Base de Datos",
						    JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(146, 148, 119, 31);
		panel.add(btnNewButton);
	}

}
