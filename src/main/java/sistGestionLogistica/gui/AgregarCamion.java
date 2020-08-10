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
import com.mysql.cj.jdbc.Driver;

public class AgregarCamion {

	JFrame frame;
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
		frame = new JFrame("Agregar Camiones");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		textField_Patente = new JTextField();
		textField_Patente.setBounds(130, 25, 86, 20);
		textField_marca = new JTextField();
		textField_marca.setBounds(130, 50, 86, 20);
		textField_Modelo = new JTextField();
		textField_Modelo.setBounds(130, 75, 86, 20);
		textField_KmRec = new JTextField();
		textField_KmRec.setBounds(130, 100, 86, 20);
		textField_CostoKm = new JTextField();
		textField_CostoKm.setBounds(130, 125, 86, 20);
		textField_CostoHs = new JTextField();
		textField_CostoHs.setBounds(130, 150, 86, 20);
		textField_Fecha = new JTextField();
		textField_Fecha.setBounds(130, 175, 86, 20);

		
		textField_Patente.setColumns(10);
		textField_marca.setColumns(10);
		textField_Modelo.setColumns(10);
		textField_KmRec.setColumns(10);
		textField_CostoKm.setColumns(10);
		textField_CostoHs.setColumns(10);
		textField_Fecha.setColumns(10);		
		
		
		JLabel lblPatente = new JLabel("Patente");
		lblPatente.setBounds(25, 25, 46, 14);
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(25, 50, 46, 14);
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(25, 75, 46, 14);
		JLabel lblKmRecorrido = new JLabel("Km Recorrido");
		lblKmRecorrido.setBounds(27, 100, 86, 14);
		JLabel lblCostoKm = new JLabel("Costo por Km");
		lblCostoKm.setBounds(25, 125, 78, 14);
		JLabel lblCostoHora = new JLabel("Costo por Hora");
		lblCostoHora.setBounds(25, 150, 78, 14);
		JLabel lblFecha = new JLabel("Fecha De Compra");
		lblFecha.setBounds(25, 175, 92, 14);
		
		
		panel.add(lblPatente);
		panel.add(lblMarca);		
		panel.add(lblModelo);
		panel.add(lblKmRecorrido);
		panel.add(lblCostoKm);
		panel.add(lblCostoHora);
		panel.add(lblFecha);
		
		
		panel.add(textField_Patente);
		panel.add(textField_marca);
		panel.add(textField_Modelo);
		panel.add(textField_KmRec);
		panel.add(textField_CostoKm);
		panel.add(textField_CostoHs);
		panel.add(textField_Fecha);

		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(252, 105, 89, 23);
		panel.add(btnAgregar);
		JLabel lblNotaFecha = new JLabel("Formato de fecha: dd/MM/aaaa");
		lblNotaFecha.setBounds(93, 211, 217, 39);
		panel.add(lblNotaFecha);
		
		btnAgregar.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e) {
				
				CamionController cc= new CamionController();
				
				try {
					cc.agregarCamion(textField_Patente.getText(), textField_marca.getText(), textField_Modelo.getText(), textField_CostoKm.getText(), textField_CostoHs.getText(), textField_KmRec.getText(), textField_Fecha.getText());
					JOptionPane.showMessageDialog(frame,"El camion fue dado de alta con exito.", "Alta Exitosa",JOptionPane.INFORMATION_MESSAGE);
					
				} catch (DateTimeParseException | DatosInvalidosException | NumberFormatException e1) {
					//Mensaje de error
					//e1.printStackTrace();
				} catch (SQLException e1) {
					
					//Mensaje de error
					JOptionPane.showMessageDialog(frame,"Verifique su conexion a la Base de Datos.","Error en la Base de Datos",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} 
			}				
		});
		
		
	}
}
