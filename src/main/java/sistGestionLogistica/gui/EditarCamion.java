package sistGestionLogistica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.lang.*;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.CamionController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class EditarCamion extends JFrame {

	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_patente;
	private JTextField textField_marca;
	private JTextField textField_modelo;
	private JTextField textField_KM;
	private JTextField textField_costokm;
	private JTextField textField_costohora;
	private JTextField textField_fecha;
	private JTextField textField_fecha1;
	private JTextField textField_fecha2;
	private JTextField textField_fecha3;
	private String fecha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarCamion frame = new EditarCamion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EditarCamion(Integer id) {
		this.textField_id = new JTextField();
		this.textField_id.setText(id.toString());
		Editar();
	}
	
	public EditarCamion() {
		this.textField_id = new JTextField();
		Editar();
	}
	
	public void Editar() {
		
		setTitle("Modificar Camiones");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//JLabel lblNewLabel = new JLabel("Id");
		//lblNewLabel.setBounds(71, 37, 46, 14);
		//panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Patente");
		lblNewLabel_1.setBounds(30, 25, 100, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Marca");
		lblNewLabel_2.setBounds(30, 50, 100, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Modelo");
		lblNewLabel_3.setBounds(30, 75, 100, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("KM");
		lblNewLabel_4.setBounds(30, 100, 100, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Costo KM");
		lblNewLabel_5.setBounds(30, 125, 100, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Costo Hora");
		lblNewLabel_6.setBounds(30, 150, 100, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Fecha Compra");
		lblNewLabel_7.setBounds(30, 175, 100, 14);
		panel.add(lblNewLabel_7);
		
		//---------------------------
		
		//textField_id = new JTextField();
		//textField_id.setBounds(142, 37, 86, 20);
		//panel.add(textField_id);
		//textField_id.setColumns(10);
		
		textField_patente = new JTextField();
		textField_patente.setBounds(140, 25, 100, 20);
		panel.add(textField_patente);
		textField_patente.setColumns(10);
		
		textField_marca = new JTextField();
		textField_marca.setBounds(140, 50, 100, 20);
		panel.add(textField_marca);
		textField_marca.setColumns(10);
		
		textField_modelo = new JTextField();
		textField_modelo.setBounds(140, 75, 100, 20);
		panel.add(textField_modelo);
		textField_modelo.setColumns(10);
		
		textField_KM = new JTextField();
		textField_KM.setBounds(140, 100, 100, 20);
		panel.add(textField_KM);
		textField_KM.setColumns(10);
		
		textField_costokm = new JTextField();
		textField_costokm.setBounds(140, 125, 100, 20);
		panel.add(textField_costokm);
		textField_costokm.setColumns(10);
		
		textField_costohora = new JTextField();
		textField_costohora.setBounds(140, 150, 100, 20);
		panel.add(textField_costohora);
		textField_costohora.setColumns(10);
		
		textField_fecha = new JTextField();
		textField_fecha.setBounds(140, 175, 100, 20);
		panel.add(textField_fecha);
		textField_fecha.setColumns(10);
		
		//-------------------------
		
		/*textField_fecha1 = new JTextField();
		textField_fecha1.setBounds(140, 175, 33, 20);
		panel.add(textField_fecha1);
		textField_fecha1.setColumns(10);
		
		textField_fecha2 = new JTextField();
		textField_fecha2.setBounds(174, 175, 33, 20);
		panel.add(textField_fecha2);
		textField_fecha2.setColumns(10);
		
		textField_fecha3 = new JTextField();
		textField_fecha3.setBounds(207, 175, 33, 20);
		panel.add(textField_fecha3);
		textField_fecha3.setColumns(10);*/
		
		//fecha = textField_fecha1.getText()+"/"+textField_fecha2.getText()+"/"+textField_fecha3.getText();
		
		//-----------------------------
		
		//----------------------------
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CamionController cc= new CamionController();
				try {
					cc.editarCamion(textField_id.getText(),textField_patente.getText(), textField_marca.getText(), textField_modelo.getText(), textField_costokm.getText(), textField_costohora.getText(), textField_KM.getText(), textField_fecha.getText());
					JOptionPane.showMessageDialog(contentPane,
						    "El camion fue editado con exito.", "Edicion Exitosa",JOptionPane.INFORMATION_MESSAGE);
				} catch (DateTimeParseException | DatosInvalidosException | NumberFormatException e1) {
					//Mensaje de error
					JOptionPane.showMessageDialog(contentPane,
						    "Por favor verifique sus datos.",
						    "Datos Invalidos",
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
		
		btnEditar.setBounds(290, 100, 90, 25);
		panel.add(btnEditar);
		
		
		JLabel lblNewLabel_9 = new JLabel("formato:\r\n dd/MM/aaaa");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_9.setBounds(130, 210, 140, 20);
		panel.add(lblNewLabel_9);
	}

}
