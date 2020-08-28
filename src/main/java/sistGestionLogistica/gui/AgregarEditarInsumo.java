package sistGestionLogistica.gui;

import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;

import javax.swing.*;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.CamionController;
import sistGestionLogistica.controller.InsumoController;

public class AgregarEditarInsumo {

	private JFrame frame;
	private JPanel panel;
	private JTextField textField_id;
	private JTextField textField_Descripcion;
	private JComboBox  comboBox_UnidadMedida;
	private JTextField textField_Costo;
	private JTextField textField_Precio;
	private JComboBox  comboBox_Tipo;
	private JTextField textField_Densidad;
	private JTextField textField_Peso;
	
	
	private String[] unidadM = {"KG","PIEZA","GR","M","LT","M2","M3","CM3"};
	private String[] tipo = {"LIQUIDO","GENERAL"};
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarEditarInsumo window = new AgregarEditarInsumo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public AgregarEditarInsumo() {
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
		
		comboBox_Tipo = new JComboBox(tipo);
		comboBox_Tipo.setBounds(140, 25, 100, 20);
		textField_Descripcion = new JTextField();
		textField_Descripcion.setBounds(140, 50, 100, 20);
		comboBox_UnidadMedida = new JComboBox(unidadM);
		comboBox_UnidadMedida.setBounds(140, 75, 100, 20);
		textField_Costo = new JTextField();
		textField_Costo.setBounds(140, 100, 100, 20);
		textField_Precio = new JTextField();
		textField_Precio.setBounds(140, 125, 100, 20);
		textField_Densidad = new JTextField();
		textField_Densidad.setBounds(140, 150, 100, 20);
		textField_Peso = new JTextField();
		textField_Peso.setBounds(140, 175, 100, 20);
		

		textField_Descripcion.setColumns(10);
	  //comboBox_UnidadMedida).setColumns(10);
		textField_Costo.setColumns(10);
		textField_Precio.setColumns(10);
        
		JLabel lblTipo = new JLabel("Tipo Insumo");
		lblTipo.setBounds(30, 25, 100, 14);
		JLabel lblDescipcion = new JLabel("Descripcion");
		lblDescipcion.setBounds(30, 50, 100, 14);
		JLabel lblUnidadMedida = new JLabel("Unidad de Medida");
		lblUnidadMedida.setBounds(30, 75, 100, 14);
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setBounds(30, 100, 100, 14);
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(30, 125, 100, 14);		
		JLabel lblDensidad = new JLabel("Densidad");
		lblDensidad.setBounds(30, 150, 100, 14);	
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(30, 175, 100, 14);		
		
	    panel.add(lblTipo);
		panel.add(lblDescipcion);
		panel.add(lblUnidadMedida);		
		panel.add(lblCosto);
		panel.add(lblPrecio);
		panel.add(lblDensidad);
		panel.add(lblPeso);
		panel.add(comboBox_Tipo);
		panel.add(textField_Descripcion);
		panel.add(comboBox_UnidadMedida);
		panel.add(textField_Costo);
		panel.add(textField_Precio);
		panel.add(textField_Densidad);
		panel.add(textField_Peso);
		
		//Porque aparece liquido primero
		textField_Peso.setEnabled(false);
		
		comboBox_Tipo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(comboBox_Tipo.getSelectedItem().equals("GENERAL")) {
					textField_Densidad.setEnabled(false);
					textField_Peso.setEnabled(true);
				}
				else if(comboBox_Tipo.getSelectedItem().equals("LIQUIDO")) {
					textField_Peso.setEnabled(false);
					textField_Densidad.setEnabled(true);
					
				}
				
				
			}
		});
		
		
	
		
	}
	
	public void agregar() {
		
		frame.setTitle("Agregar Insumo");
		frame.setVisible(true);
		
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(290, 100, 90, 25);
		panel.add(btnAgregar);
		
		
		btnAgregar.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e) {
				
				InsumoController ic = new InsumoController();
				
				try {
					ic.agregarInsumo(textField_Descripcion.getText(), comboBox_UnidadMedida.getSelectedItem().toString(),textField_Costo.getText(), textField_Precio.getText(),textField_Peso.getText() ,textField_Densidad.getText(), comboBox_Tipo.getSelectedItem().toString());
					JOptionPane.showMessageDialog(frame,"El insumo fue dado de alta con exito.", "Alta Exitosa",JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
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
	
    public void editarInsumo(Integer id) {
		this.textField_id = new JTextField();
		this.textField_id.setText(id.toString());
		editar();
	}
	
	public void editar() {
		this.inicializar();
		frame.setTitle("Editar Insumo");
		frame.setVisible(true);
		
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(290, 100, 90, 25);
		panel.add(btnEditar);
		
		
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsumoController ic = new InsumoController();
				try {
					ic.editarInsumo(textField_id.getText(), textField_Descripcion.getText(), comboBox_UnidadMedida.getSelectedItem().toString(),textField_Costo.getText(), textField_Precio.getText(),textField_Peso.getText() ,textField_Densidad.getText(), comboBox_Tipo.getSelectedItem().toString());
					JOptionPane.showMessageDialog(
							frame,"El insumo fue editado con exito.", "Edicion Exitosa",JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
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