package sistGestionLogistica.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.*;
import sistGestionLogistica.dao.*;
import sistGestionLogistica.dominio.*;
import sistGestionLogistica.servicios.ServiceInsumo;
import sistGestionLogistica.servicios.ServicePlanta;


public class AgregarEditarDetallesEnvio {

	private JTextField numOrden;
	private JFrame frame;
	private JPanel panel;
	private JComboBox<String> plantas;
	private JComboBox<String> comboRecorridoPor;
	private JComboBox<String> cominoOptimo;
	private JLabel lblPlanta;
	private JLabel lblKmT;
	private JLabel lblCamino;
	private JButton btnCalcular;
	private JButton btnMostrar;
	private JButton btnAgregar;
	//private ArrayList<ItemDetalle> items ;
	//private Vector<Integer> plantaID;
	//private Vector<String> plantaLbl;
	private Integer alto;
	private Integer ancho;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarEditarDetallesEnvio window = new AgregarEditarDetallesEnvio();
					//window.agregar();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AgregarEditarDetallesEnvio() {
		//super();
		inicializar();
	}
	
	private void inicializar(){
		
		alto = 100;
		ancho = 100;
		
		frame = new JFrame();
		frame.setBounds(ancho, alto, 5*ancho, 3*alto);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 5*ancho, 3*alto);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(true);
		
		frame.setTitle("Agregar Detalles Envio");
		frame.setVisible(true);
		
		//----------string para el combo box-------
		/*
		Planta p= new Planta(-1,"");
		ServicePlanta ser = new ServicePlanta();
		
		ArrayList<Planta> lista =  (ArrayList<Planta>) ser.buscarPlanta(p);
		this.plantaID= new Vector<>();
		this.plantaLbl= new Vector<>();
		
		for(int i = 0; i < lista.size(); i++){
			
			plantaLbl.add(lista.get(i).getId()+" - "+lista.get(i).getNombre());
			plantaID.add(lista.get(i).getId());
			
		}
		System.out.print("\n");
		*/
		//--------------
		
		
		plantas = new JComboBox<String>();
		plantas.setBounds(200, 25, 120, 20);
		comboRecorridoPor = new JComboBox<String>();
		comboRecorridoPor.setBounds(200, 50, 120, 20);
		cominoOptimo = new JComboBox<String>();
		cominoOptimo.setBounds(200, 100, 120, 20);
		lblPlanta = new JLabel("PlantaOrigen");
		lblPlanta.setBounds(25, 25, 150, 20);
		lblKmT = new JLabel("Recorrido por...");
		lblKmT.setBounds(25, 50, 150, 20);
		lblCamino = new JLabel("Camino Optimo");
		lblCamino.setBounds(25, 100, 150, 20);
		btnCalcular = new JButton("Calcular Camino");
		btnCalcular.setBounds(25, 75, 150, 20);
		btnMostrar = new JButton("Mostrar Recorrido");
		btnMostrar.setBounds(25, 125, 150, 20);
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(350, 90, 120, 20);
				
		panel.add(plantas);
		panel.add(comboRecorridoPor);
		panel.add(cominoOptimo);
		panel.add(lblPlanta);
		panel.add(lblKmT);
		panel.add(lblCamino);
		panel.add(btnCalcular);
		panel.add(btnMostrar);
		panel.add(btnAgregar);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(ancho, 3*(alto/2), (3*ancho), (alto));
		panel.add(textArea);

		btnMostrar.addActionListener(new ActionListener(){
				
				@Override
				public void actionPerformed(ActionEvent e) {

				
			}				
		});

		btnCalcular.addActionListener(new ActionListener(){
				
				@Override
				public void actionPerformed(ActionEvent e) {

				
			}				
		});
		btnAgregar.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {

			
		}				
	});
		
	}
	
}

