package sistGestionLogistica.gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.CamionController;

import javax.swing.border.TitledBorder;

public class PanelCamion extends JFrame {
	private JTable table_Camiones;
	private JTextField textField_ID;
	private JTextField textField_Patente;
	private JTextField textField_Marca;
	private JTextField textField_Modelo;
	private JTextField textField_KM;
	private JTextField textField_CostoKM;
	private JTextField textField_CostoHora;
	private JTextField textField_FechaCompra;


	/*public static void main(String[] args) {
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
	}*/


	public PanelCamion() {
		super("Sistema de Gestion Loistica - Camiones");
		initialize();
	}

	private void initialize() {
		//frame = new JFrame();
		
		setBounds(100, 100, 720, 480);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		//---------Botones-----------------
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setBounds(23, 37, 89, 23);
		btnAlta.addActionListener(new AccionAlta());
		panel.add(btnAlta);
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.setBounds(307, 37, 89, 23);
		btnBaja.addActionListener(new AccionBaja());
		panel.add(btnBaja);
		
		JButton btnModificar = new JButton("Modificar Por ID");
		btnModificar.setBounds(136, 37, 130, 23);
		btnModificar.addActionListener(new AccionModificar());
		panel.add(btnModificar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(419, 37, 89, 23);
		btnBuscar.addActionListener(new AccionBuscar());
		panel.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 208, 671, 222);
		panel.add(scrollPane);
		
		table_Camiones = new JTable();
		scrollPane.setViewportView(table_Camiones);
		table_Camiones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Camiones.setToolTipText("");
		table_Camiones.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Patente", "Marca", "Modelo", "KM", "CostoKM", "CostoHora", "FechaCompra"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(35, 86, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Patente");
		lblNewLabel_1.setBounds(23, 117, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Marca");
		lblNewLabel_2.setBounds(23, 150, 46, 14);
		panel.add(lblNewLabel_2);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(76, 83, 86, 20);
		panel.add(textField_ID);
		textField_ID.setColumns(10);
		
		textField_Patente = new JTextField();
		textField_Patente.setBounds(79, 114, 86, 20);
		panel.add(textField_Patente);
		textField_Patente.setColumns(10);
		
		textField_Marca = new JTextField();
		textField_Marca.setBounds(79, 147, 86, 20);
		panel.add(textField_Marca);
		textField_Marca.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Modelo");
		lblNewLabel_3.setBounds(221, 86, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("KM Recorridos");
		lblNewLabel_4.setBounds(221, 117, 75, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Costo por KM");
		lblNewLabel_5.setBounds(221, 150, 75, 14);
		panel.add(lblNewLabel_5);
		
		textField_Modelo = new JTextField();
		textField_Modelo.setBounds(307, 83, 86, 20);
		panel.add(textField_Modelo);
		textField_Modelo.setColumns(10);
		
		textField_KM = new JTextField();
		textField_KM.setBounds(307, 114, 86, 20);
		panel.add(textField_KM);
		textField_KM.setColumns(10);
		
		textField_CostoKM = new JTextField();
		textField_CostoKM.setBounds(306, 147, 86, 20);
		panel.add(textField_CostoKM);
		textField_CostoKM.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Costo Hora");
		lblNewLabel_6.setBounds(419, 86, 75, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Fecha de Compra");
		lblNewLabel_7.setBounds(419, 117, 89, 14);
		panel.add(lblNewLabel_7);
		
		textField_CostoHora = new JTextField();
		textField_CostoHora.setBounds(526, 83, 86, 20);
		panel.add(textField_CostoHora);
		textField_CostoHora.setColumns(10);
		
		textField_FechaCompra = new JTextField();
		textField_FechaCompra.setBounds(526, 114, 86, 20);
		panel.add(textField_FechaCompra);
		textField_FechaCompra.setColumns(10);
		table_Camiones.getColumnModel().getColumn(0).setPreferredWidth(35);
		
		//----------------------------
		

	}
	
	class AccionAlta implements ActionListener {
		 
		 public void actionPerformed(ActionEvent e) {
			 AgregarCamion aC = new AgregarCamion();
			 aC.frame.setVisible(true);
			 System.out.println("Camion -> Alta");

		 }
	}
	
	class AccionBaja implements ActionListener {
		 
		 public void actionPerformed(ActionEvent e) {
			 BajaCamion bC = new BajaCamion();
			 bC.setVisible(true);
			 System.out.println("Camion -> Baja");

		 }
	}
	
	class AccionModificar implements ActionListener {
		 
		 public void actionPerformed(ActionEvent e) {
			 EditarCamion eC = new EditarCamion();
			 eC.setVisible(true);
			 System.out.println("Camion -> Modificar");

		 }
	}
	
	class AccionBuscar implements ActionListener {
		 
		 public void actionPerformed(ActionEvent e) {
			 System.out.println("Camion -> Buscar");
			// DefaultTableModel modelo = (DefaultTableModel) table_Camiones.getModel();
			 CamionController cc=new CamionController();
			 try {
				 
				this.actualizarTabla(cc.buscarCamion(textField_ID.getText(), textField_Patente.getText(), textField_Marca.getText(), textField_Modelo.getText(), textField_CostoKM.getText(), textField_CostoHora.getText(), textField_KM.getText(), textField_FechaCompra.getText()));
			} catch (DateTimeParseException | NumberFormatException | DatosInvalidosException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 }

		private void actualizarTabla(String[][] aMostrar) throws DateTimeParseException, NumberFormatException, DatosInvalidosException, SQLException {
			
			table_Camiones.setModel(new DefaultTableModel(
					aMostrar,
					new String[] {
						"ID", "Patente", "Marca", "Modelo", "KM", "CostoKM", "CostoHora", "FechaCompra"
					}
				) {
					Class[] columnTypes = new Class[] {
						Object.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
			
		}
	}
}
