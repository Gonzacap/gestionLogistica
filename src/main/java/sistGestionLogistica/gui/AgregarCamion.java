package sistGestionLogistica.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.CamionController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;

public class AgregarCamion {

	private JFrame frame;
	private JTextField textField_CostoKm;
	private JTextField textField_Patente;
	private JTextField textField_CostoHs;
	private JTextField textField_Fecha;
	private JTextField textField_marca;
	private JTextField textField_Modelo;
	private JTextField textField_KmRec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarCamion window = new AgregarCamion();
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
	public AgregarCamion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField_CostoKm = new JTextField();
		textField_CostoKm.setBounds(129, 131, 86, 20);
		panel.add(textField_CostoKm);
		textField_CostoKm.setColumns(10);
		
		textField_Patente = new JTextField();
		textField_Patente.setBounds(129, 23, 86, 20);
		panel.add(textField_Patente);
		textField_Patente.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Patente");
		lblNewLabel.setBounds(27, 26, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(29, 52, 46, 14);
		panel.add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(27, 77, 46, 14);
		panel.add(lblModelo);
		
		JLabel lblKmRecorrido = new JLabel("Km Recorrido");
		lblKmRecorrido.setBounds(27, 109, 86, 14);
		panel.add(lblKmRecorrido);
		
		textField_CostoHs = new JTextField();
		textField_CostoHs.setColumns(10);
		textField_CostoHs.setBounds(129, 156, 86, 20);
		panel.add(textField_CostoHs);
		
		textField_Fecha = new JTextField();
		textField_Fecha.setColumns(10);
		textField_Fecha.setBounds(129, 181, 86, 20);
		panel.add(textField_Fecha);
		
		JLabel lblModelo_1_1 = new JLabel("Costo por Km");
		lblModelo_1_1.setBounds(27, 134, 78, 14);
		panel.add(lblModelo_1_1);
		
		JLabel lblModelo_1_1_1 = new JLabel("Costo por Hora");
		lblModelo_1_1_1.setBounds(27, 159, 78, 14);
		panel.add(lblModelo_1_1_1);
		
		JLabel lblModelo_1_1_1_1 = new JLabel("Fecha De Compra");
		lblModelo_1_1_1_1.setBounds(27, 184, 92, 14);
		panel.add(lblModelo_1_1_1_1);
		
		textField_marca = new JTextField();
		textField_marca.setColumns(10);
		textField_marca.setBounds(129, 49, 86, 20);
		panel.add(textField_marca);
		
		textField_Modelo = new JTextField();
		textField_Modelo.setColumns(10);
		textField_Modelo.setBounds(129, 74, 86, 20);
		panel.add(textField_Modelo);
		
		textField_KmRec = new JTextField();
		textField_KmRec.setColumns(10);
		textField_KmRec.setBounds(129, 106, 86, 20);
		panel.add(textField_KmRec);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CamionController cc= new CamionController();
				
		
					try {
						cc.agregarCamion(textField_Patente.getText(), textField_marca.getText(), textField_Modelo.getText(), textField_CostoKm.getText(), textField_CostoHs.getText(), textField_KmRec.getText(), textField_Fecha.getText());
					} catch (DateTimeParseException e1) {
						JOptionPane.showMessageDialog(frame,
							    "Por favor verifique sus datos.",
							    "Datos Invalidos",
							    JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(frame,
							    "Verifique su conexion a la Base de Datos.",
							    "Error en la Base de Datos",
							    JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (DatosInvalidosException e1) {
						JOptionPane.showMessageDialog(frame,
							    "Por favor verifique sus datos.",
							    "Datos Invalidos",
							    JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
		
				
				
			}
		});
		btnAgregar.setBounds(252, 105, 89, 23);
		panel.add(btnAgregar);
	}
}
