package sistGestionLogistica.gui;

import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;

import javax.swing.*;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.CamionController;

public class AgregarEditarCamion {

	private JFrame frame;
	private JPanel panel;
	private JTextField textField_id;
	private JTextField textField_Patente;
	private JTextField textField_Marca;
	private JTextField textField_Modelo;
	private JTextField textField_KmRec;
	private JTextField textField_CostoKm;
	private JTextField textField_CostoHs;
	private JTextField textField_Fecha;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarEditarCamion window = new AgregarEditarCamion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public AgregarEditarCamion() {
		//super();
		inicializar();
	}

	public void inicializar() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		textField_Patente = new JTextField();
		textField_Patente.setBounds(140, 25, 100, 20);
		textField_Marca = new JTextField();
		textField_Marca.setBounds(140, 50, 100, 20);
		textField_Modelo = new JTextField();
		textField_Modelo.setBounds(140, 75, 100, 20);
		textField_KmRec = new JTextField();
		textField_KmRec.setBounds(140, 100, 100, 20);
		textField_CostoKm = new JTextField();
		textField_CostoKm.setBounds(140, 125, 100, 20);
		textField_CostoHs = new JTextField();
		textField_CostoHs.setBounds(140, 150, 100, 20);
		textField_Fecha = new JTextField();
		textField_Fecha.setBounds(140, 175, 100, 20);

		
		textField_Patente.setColumns(10);
		textField_Marca.setColumns(10);
		textField_Modelo.setColumns(10);
		textField_KmRec.setColumns(10);
		textField_CostoKm.setColumns(10);
		textField_CostoHs.setColumns(10);
		textField_Fecha.setColumns(10);		
		
		
		JLabel lblPatente = new JLabel("Patente");
		lblPatente.setBounds(30, 25, 100, 14);
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(30, 50, 100, 14);
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(30, 75, 100, 14);
		JLabel lblKmRecorrido = new JLabel("Km Recorrido");
		lblKmRecorrido.setBounds(30, 100, 100, 14);
		JLabel lblCostoKm = new JLabel("Costo por Km");
		lblCostoKm.setBounds(30, 125, 100, 14);
		JLabel lblCostoHora = new JLabel("Costo por Hora");
		lblCostoHora.setBounds(30, 150, 100, 14);
		JLabel lblFecha = new JLabel("Fecha De Compra");
		lblFecha.setBounds(30, 175, 100, 14);
		
		
		panel.add(lblPatente);
		panel.add(lblMarca);		
		panel.add(lblModelo);
		panel.add(lblKmRecorrido);
		panel.add(lblCostoKm);
		panel.add(lblCostoHora);
		panel.add(lblFecha);
		
		
		panel.add(textField_Patente);
		panel.add(textField_Marca);
		panel.add(textField_Modelo);
		panel.add(textField_KmRec);
		panel.add(textField_CostoKm);
		panel.add(textField_CostoHs);
		panel.add(textField_Fecha);
		
		//------label fecha----------
		
		JLabel lblNotaFecha = new JLabel("Formato de fecha: dd/MM/aaaa");
		lblNotaFecha.setBounds(100, 210, 220, 20);
		panel.add(lblNotaFecha);
		
	}
	
	public void agregar() {
		
		frame.setTitle("Agregar Camion");
		frame.setVisible(true);
		
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(290, 100, 90, 25);
		panel.add(btnAgregar);
		
		
		btnAgregar.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e) {
				
				CamionController cc= new CamionController();
				
				try {
					cc.agregarCamion(textField_Patente.getText(), textField_Marca.getText(), textField_Modelo.getText(), textField_CostoKm.getText(), textField_CostoHs.getText(), textField_KmRec.getText(), textField_Fecha.getText());
					JOptionPane.showMessageDialog(frame,"El camion fue dado de alta con exito.", "Alta Exitosa",JOptionPane.INFORMATION_MESSAGE);
					
				} catch (DateTimeParseException | DatosInvalidosException | NumberFormatException e1) {
					//Mensaje de error
					//e1.printStackTrace();
				} catch (SQLException e1) {
					//Mensaje de error
					//System.out.println("Oops!");
					JOptionPane.showMessageDialog(frame,"Verifique su conexion a la Base de Datos.","Error en la Base de Datos",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} 
			}				
		});
		
	}
	
	public void editarCamion(Integer id) {
		this.textField_id = new JTextField();
		this.textField_id.setText(id.toString());
		editar();
	}
	
	public void editar() {
		this.inicializar();
		frame.setTitle("Editar Camion");
		frame.setVisible(true);
		
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setBounds(290, 100, 90, 25);
		panel.add(btnEditar);
		
		
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CamionController cc= new CamionController();
				try {
					cc.editarCamion(textField_id.getText(),textField_Patente.getText(), textField_Marca.getText(), textField_Modelo.getText(), textField_CostoKm.getText(), textField_CostoHs.getText(), textField_KmRec.getText(), textField_Fecha.getText());
					JOptionPane.showMessageDialog(
							frame,"El camion fue editado con exito.", "Edicion Exitosa",JOptionPane.INFORMATION_MESSAGE);
				} catch (DateTimeParseException | DatosInvalidosException | NumberFormatException e1) {
					//Mensaje de error
					JOptionPane.showMessageDialog(
							frame,"Por favor verifique sus datos.","Datos Invalidos",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (SQLException e1) {
					//Mensaje de error
					JOptionPane.showMessageDialog(
							frame,"Verifique su conexion a la Base de Datos.","Error en la Base de Datos",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
			
	}
	
}