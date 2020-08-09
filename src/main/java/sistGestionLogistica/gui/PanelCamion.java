package sistGestionLogistica.gui;

import java.awt.*;
import java.awt.event.*;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.CamionController;
import sistGestionLogistica.sistema.App;


public class PanelCamion extends JPanel {
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
		
	}

	public void inicializar(App aplicacion) { //App seria el JFrame
		aplicacion.setTitle("Sistema de Gestion Logistica - Camiones");		
		
		PanelCamion panel = new PanelCamion();
		panel.setLayout(null);
		panel.setBackground(Color.yellow);
		
		aplicacion.camionesActivated();
		aplicacion.setContentPane(panel);
		aplicacion.revalidate();
		aplicacion.repaint();
		
		
		//---------Botones-----------------
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setBounds(20, 37, 90, 25);
		JButton btnBaja = new JButton("Baja");
		btnBaja.setBounds(130, 37, 90, 25);
		JLabel lblModificar = new JLabel("Seleccione un camion de la tabla para poder editarlo");
		lblModificar.setBounds(240, 37, 300, 25);
		
		panel.add(btnAlta);
		panel.add(btnBaja);
		panel.add(lblModificar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 208, 671, 222);
		panel.add(scrollPane);
		
		//-------tabla-------
		
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
		
		//-------tabla--------
		
		//--------------------
		
		JPanel panelBuscar = new JPanel(new GridBagLayout());
		//panelBuscar.setLocation(40,80);
		panelBuscar.setBounds(50, 80, 700, 120);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 50;
		c.weighty = 80;
		
		//c.fill = GridBagConstraints.CENTER;
		
		//----------------------
		
		c.fill = GridBagConstraints.HORIZONTAL;

		textField_ID = new JTextField();
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 0;
		panelBuscar.add(textField_ID, c);
		
		textField_Patente = new JTextField();
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridy = 1;
		panelBuscar.add(textField_Patente, c);
		
		textField_Marca = new JTextField();
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 0;
		panelBuscar.add(textField_Marca, c);
		
		textField_Modelo = new JTextField();
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridy = 1;
		panelBuscar.add(textField_Modelo, c);
		
		//-------------------------
		
		c.fill = GridBagConstraints.CENTER;
		
		JLabel lbl_0 = new JLabel("Id");
		c.gridx = 4;
		c.gridwidth = 1;
		c.gridy = 0;
		panelBuscar.add(lbl_0, c);
		
		JLabel lbl_1 = new JLabel("Patente");
		c.gridx = 4;
		c.gridwidth = 1;
		c.gridy = 1;
		panelBuscar.add(lbl_1, c);
		
		JLabel lbl_2 = new JLabel("Marca");
		c.gridx = 6;
		c.gridwidth = 1;
		c.gridy = 0;
		panelBuscar.add(lbl_2, c);
		
		JLabel lbl_3 = new JLabel("Modelo");
		c.gridx = 6;
		c.gridwidth = 1;
		c.gridy = 1;
		panelBuscar.add(lbl_3, c);
		
		//----------------------
		
		c.fill = GridBagConstraints.HORIZONTAL;

		textField_KM = new JTextField();
		c.gridx = 5;
		c.gridwidth = 1;
		c.gridy = 0;
		panelBuscar.add(textField_KM, c);
		
		textField_CostoKM = new JTextField();
		c.gridx = 5;
		c.gridwidth = 1;
		c.gridy = 1;
		panelBuscar.add(textField_CostoKM, c);
		
		textField_CostoHora = new JTextField();
		c.gridx = 7;
		c.gridwidth = 1;
		c.gridy = 0;
		panelBuscar.add(textField_CostoHora, c);
		
		textField_FechaCompra = new JTextField();
		c.gridx = 7;
		c.gridwidth = 1;
		c.gridy = 1;
		panelBuscar.add(textField_FechaCompra, c);
				
		//-------------------------
		
		c.fill = GridBagConstraints.CENTER;
				
		JLabel lbl_4 = new JLabel("Id");
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 0;
		panelBuscar.add(lbl_4, c);
		
		JLabel lbl_5 = new JLabel("Patente");
		c.gridx = 0;
		c.gridwidth = 1;
		c.gridy = 1;
		panelBuscar.add(lbl_5, c);
				
		JLabel lbl_6 = new JLabel("Marca");
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 0;
		panelBuscar.add(lbl_6, c);
		
		JLabel lbl_7 = new JLabel("Modelo");
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridy = 1;
		panelBuscar.add(lbl_7, c);
		
		//----------------------
		
		c.fill = GridBagConstraints.CENTER;
		
		JButton btnBuscar = new JButton("Buscar"+"\n"+"Actualizar");
		c.gridx = 8;
		c.gridheight = 2;
		c.gridy = 0;
		panelBuscar.add(btnBuscar, c);
		
		panelBuscar.setVisible(true);
		panel.add(panelBuscar);
		
		//--------------------
		
		table_Camiones.getColumnModel().getColumn(0).setPreferredWidth(35);
		
		//----------------------------
		
		//---------accion click-------
		
		table_Camiones.addMouseListener(new MouseAdapter() {
				
			public void mouseClicked(MouseEvent e) {
				System.out.println("Camion -> click Modificar");
				int fila = table_Camiones.rowAtPoint(e.getPoint());
				int columna = table_Camiones.columnAtPoint(e.getPoint());
				
				if(fila>-1 && columna>-1) {
					Integer idAux = Integer.valueOf((String) table_Camiones.getValueAt(fila,columna));
					EditarCamion eC = new EditarCamion(idAux);
					eC.setVisible(true);
				}
				
			}
		});
		
		//---------------------------
		
		//--------Acciones Botones--------------	
		
		btnAlta.addActionListener(e-> {	//AccionAlta
			
			System.out.println("Camion -> Alta");
			AgregarCamion aC = new AgregarCamion();
			aC.frame.setVisible(true);
			
		});
		btnBaja.addActionListener(e-> { //AccionBaja
			
			System.out.println("Camion -> Baja");
			BajaCamion bC = new BajaCamion();
			bC.setVisible(true);

		});
		btnBuscar.addActionListener(new AccionBuscar());
		

	}
	
	
	class AccionBuscar implements ActionListener {
		 
		 public void actionPerformed(ActionEvent e) {
			 
			 System.out.println("Camion -> Buscar");
			 
			 DefaultTableModel modelo = (DefaultTableModel) table_Camiones.getModel();
			 CamionController cc=new CamionController();
			 
			 try { 
				this.actualizarTabla(cc.buscarCamion(textField_ID.getText(), textField_Patente.getText(), textField_Marca.getText(), textField_Modelo.getText(), textField_CostoKM.getText(), textField_CostoHora.getText(), textField_KM.getText(), textField_FechaCompra.getText()));
			
			 } catch (DateTimeParseException | NumberFormatException | DatosInvalidosException | SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
		 }

		private void actualizarTabla(String[][] aMostrar) throws DateTimeParseException, NumberFormatException, DatosInvalidosException, SQLException {
			
			table_Camiones.setModel(new DefaultTableModel(aMostrar,	new String[] {"ID", "Patente", "Marca", "Modelo", "KM", "CostoKM", "CostoHora", "FechaCompra"}) 
			{
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
