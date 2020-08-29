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

	private JFrame frame;
	private JPanel panel;
	private JComboBox<String> comboPlantas;
	private JComboBox<String> comboRecorridoPor;
	private JComboBox<String> comboCaminoOptimo;
	private JLabel lblPlanta;
	private JLabel lblKmT;
	private JLabel lblCamino;
	private JButton btnCalcular;
	private JButton btnMostrar;
	private JButton btnAgregar;
	private ArrayList<Planta> lista;
	private Vector<String> plantaLbl;
	private Vector<String> caminosLbl;
	private Integer alto;
	private Integer ancho;
	private Integer nroPedido;
	private Integer plantaAux;
	private String[] kmt = {"KILOMETRO","TIEMPO"};
	private ArrayList<ArrayList<Ruta>> caminos;

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
		JOptionPane.showMessageDialog(null,
				"Seleccione planta y recorrido para calcular el camino", "Atencion!",JOptionPane.INFORMATION_MESSAGE);
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();
		
		//----------string para el combo box-------
		
		EnvioController ec = new EnvioController();
		
		try {
			this.lista = (ArrayList<Planta>) ec.plantasConStock(this.nroPedido);
			this.plantaLbl= new Vector<String>();
			
			for(int i = 0; i < lista.size(); i++){
				
				plantaLbl.add(lista.get(i).getId()+" - "+lista.get(i).getNombre());
			}
			
		} catch (SQLException | PedidoCanceladoException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			this.lista = new ArrayList<Planta>();
			this.plantaLbl= new Vector<String>();
			frame.dispose();
		}
		
		//--------------
		
		comboPlantas= new JComboBox<String>(plantaLbl);
		comboPlantas.setBounds(200, 25, 120, 20);
		comboRecorridoPor = new JComboBox<String>(kmt);
		comboRecorridoPor.setBounds(200, 50, 120, 20);
		comboCaminoOptimo = new JComboBox<String>();
		comboCaminoOptimo.setBounds(200, 100, 120, 20);
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
				
		panel.add(comboPlantas);
		panel.add(comboRecorridoPor);
		panel.add(comboCaminoOptimo);
		panel.add(lblPlanta);
		panel.add(lblKmT);
		panel.add(lblCamino);
		panel.add(btnCalcular);
		panel.add(btnMostrar);
		panel.add(btnAgregar);
		
		comboRecorridoPor.setEnabled(false);
		btnCalcular.setEnabled(false);
		comboCaminoOptimo.setEnabled(false);
		btnMostrar.setEnabled(false);
		btnAgregar.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(ancho, 3*(alto/2), (3*ancho), (alto));
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(ancho, 3*(alto/2), (3*ancho), (alto));
		panel.add(textArea);
		//scrollPane.add(textArea);
		//panel.add(scrollPane);

		//------------------------
		
		comboPlantas.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				comboRecorridoPor.setEnabled(true);
			}				
		});
		comboRecorridoPor.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnCalcular.setEnabled(true);
			}				
		});
		btnCalcular.addActionListener(new ActionListener(){
	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				EnvioController ec = new EnvioController();
				
				try {
					comboCaminoOptimo.removeAllItems();
					plantaAux = lista.get(comboPlantas.getSelectedIndex()).getId();
					
					caminos = new ArrayList<ArrayList<Ruta>>(ec.calcularCaminos(nroPedido, plantaAux, comboRecorridoPor.getSelectedItem().toString()));
					
					if(caminos.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"No hay camino a recorrer para llegar a la planta de destino", "Atencion!",JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						
					
						for(Integer i=1; i<=caminos.size(); i++) {
							//caminosLbl.add("camino "+i.toString());
							comboCaminoOptimo.addItem("camino "+i.toString());
						}
					
						comboCaminoOptimo.setEnabled(true);
						frame.revalidate();
						//frame.repaint();
					}
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			}				
		});		
		comboCaminoOptimo.addActionListener(new ActionListener(){
				
				@Override
			public void actionPerformed(ActionEvent e) {
				btnMostrar.setEnabled(true);
			}				
		});
		btnMostrar.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(Ruta r: caminos.get(comboCaminoOptimo.getSelectedIndex())) {
					
					textArea.append(r.toString());
					textArea.append(System.getProperty("line.separator"));
				}
				
				btnAgregar.setEnabled(true);		
				
			}	
		});
		btnAgregar.addActionListener(new ActionListener(){
				
			@Override
			public void actionPerformed(ActionEvent e) {	
				EnvioController ec = new EnvioController();
				try {
					ec.agregarEnvio(nroPedido.toString(),(List<Ruta>) caminos.get(comboCaminoOptimo.getSelectedIndex()),plantaAux);
					JOptionPane.showMessageDialog(null,
							"Detalles de envio agregado a pedido correctamente", "Envio Agregado",JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					
					//System.out.println(caminos.get(comboCaminoOptimo.getSelectedIndex()).size());
				} catch (SQLException e1) {
					//TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DatosInvalidosException e1) {
					//TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}	
	});
		
		
	}
	
}

