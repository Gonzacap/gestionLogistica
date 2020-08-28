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
import excepciones.PedidoCanceladoException;
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
	//private Vector<Integer> plantaID;
	private ArrayList<Planta> lista;
	private Vector<String> plantaLbl;
	private Integer alto;
	private Integer ancho;
	private Integer nroPedido;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarEditarDetallesEnvio window = new AgregarEditarDetallesEnvio(-1);
					//window.agregar();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AgregarEditarDetallesEnvio(Integer nro) {
		//super();
		this.nroPedido = nro;
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
		
		EnvioController ec = new EnvioController();
		
		try {
			this.lista = (ArrayList<Planta>) ec.plantasConStock(this.nroPedido);
			this.plantaLbl= new Vector<String>();
			
			for(int i = 0; i < lista.size(); i++){
				
				plantaLbl.add(lista.get(i).getId()+" - "+lista.get(i).getNombre());
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		} catch (PedidoCanceladoException e1) {
			
			btnCalcular.setEnabled(false);
			btnMostrar.setEnabled(false);
			btnAgregar.setEnabled(false);
			JOptionPane.showMessageDialog(null,"No hay planta con stock para realizar el pedido", "Stock insuficiente",JOptionPane.ERROR_MESSAGE);
			//e1.printStackTrace();
		}
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(ancho, 3*(alto/2), (3*ancho), (alto));
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(ancho, 3*(alto/2), (3*ancho), (alto));
		
		scrollPane.add(textArea);
		panel.add(scrollPane);

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

