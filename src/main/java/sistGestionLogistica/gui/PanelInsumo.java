package sistGestionLogistica.gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.PlantaController;
import sistGestionLogistica.sistema.App;


public class PanelInsumo extends JPanel {

	public PanelInsumo() {

	}
	
	private JTable table_Insumos;
	private JTextField textField_ID;
	private JTextField textField_Desc;
	private JTextField textField_UM;
	private JTextField textField_Costo;
	private JTextField textField_Precio;
	private JTextField textField_Algo;

	public void inicializar(App aplicacion) {
		aplicacion.setTitle("Sistema de Gestion Logistica - Insumos");

		PanelInsumo panel= new PanelInsumo();
		panel.setLayout(null);
		panel.setBackground(Color.cyan);
		
		aplicacion.insumosActivated();
		aplicacion.setContentPane(panel);
		aplicacion.revalidate();
		aplicacion.repaint();
		
		//---------Botones-----------------
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setBounds(20, 37, 90, 25);
		JButton btnBaja = new JButton("Baja");
		btnBaja.setBounds(130, 37, 90, 25);
		JLabel lblModificar = new JLabel("Seleccione el Id de un Insumo de la tabla para poder editarlo");
		lblModificar.setBounds(240, 37, 350, 25);
		
		panel.add(btnAlta);
		panel.add(btnBaja);
		panel.add(lblModificar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 208, 671, 222);
		panel.add(scrollPane);
		
		//-------tabla-------
		
		table_Insumos = new JTable();
		scrollPane.setViewportView(table_Insumos);
		table_Insumos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Insumos.setToolTipText("");
		table_Insumos.setModel(new DefaultTableModel(
		new Object[][] {
			},
			new String[] {
				"ID", "Descripcion", "Unidad de Medida", "Costo", "Precio", "Algo¿"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
		};
		public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		//--------------------
		
		//---------panel buscar-----------
				
		JPanel panelBuscar = new JPanel(new GridBagLayout());
		panelBuscar.setBounds(50, 80, 700, 120);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 50;
		c.weighty = 80;
		
		//----------------------
		
		c.fill = GridBagConstraints.HORIZONTAL;
		textField_ID = new JTextField();
		c.gridx = 1;
		c.gridy = 0;
		panelBuscar.add(textField_ID, c);
		textField_Desc = new JTextField();
		c.gridx = 1;
		c.gridy = 1;
		panelBuscar.add(textField_Desc, c);
		textField_UM = new JTextField();
		c.gridx = 3;
		c.gridy = 0;
		panelBuscar.add(textField_UM, c);	
		textField_Costo = new JTextField();
		c.gridx = 3;
		c.gridy = 1;
		panelBuscar.add(textField_Costo, c);
		textField_Precio = new JTextField();
		c.gridx = 5;
		c.gridy = 0;
		panelBuscar.add(textField_Precio, c);
		
		//-------------------------
		
		c.fill = GridBagConstraints.CENTER;
		
		JLabel lblId = new JLabel("Id");
		c.gridx = 0;
		c.gridy = 0;
		panelBuscar.add(lblId, c);
		JLabel lblDesc = new JLabel("Descripcipon");
		c.gridx = 0;
		c.gridy = 1;
		panelBuscar.add(lblDesc, c);		
		JLabel lblUM = new JLabel("Unidad de Medida");
		c.gridx = 2;
		c.gridy = 0;
		panelBuscar.add(lblUM, c);
		JLabel lblCosto = new JLabel("Costo");
		c.gridx = 2;
		c.gridy = 1;
		panelBuscar.add(lblCosto, c);
		JLabel lblPrecio = new JLabel("Precio");
		c.gridx = 4;
		c.gridy = 0;
		panelBuscar.add(lblPrecio, c);
		
		//----------------------
			
		c.fill = GridBagConstraints.CENTER;
		
		JButton btnBuscar = new JButton("Buscar"+"\n"+"Actualizar");
		c.gridx = 4;
		c.gridwidth = 2;
		c.gridy = 1;
		panelBuscar.add(btnBuscar, c);
			
		panelBuscar.setVisible(true);
		panel.add(panelBuscar);
				
		table_Insumos.getColumnModel().getColumn(0).setPreferredWidth(35);
			
		
      btnAlta.addActionListener(e-> {	//AccionAlta
			
			System.out.println("Camion -> Alta");
			
			AgregarEditarInsumo aI = new AgregarEditarInsumo();
			aI.agregar();
			//AgregarCamion aC = new AgregarCamion();
			//aC.frame.setVisible(true);
			
		});
				
		//---------accion click-------
		
		/*table_Insumos.addMouseListener(new MouseAdapter() {
						
		public void mouseClicked(MouseEvent e) {
			System.out.println("Camion -> click Modificar");
			int fila = table_Insumos.rowAtPoint(e.getPoint());
			int columna = table_Insumos.columnAtPoint(e.getPoint());
				
				if(fila>-1 && columna>-1) {
					Integer idAux = Integer.valueOf((String) table_Insumos.getValueAt(fila,columna));
					
					AgregarEditarCamion eC = new AgregarEditarCamion();
					eC.editarCamion(idAux);
				}
			}
		});*/
				
		//---------------------------
		
		/*table_Plantas.addMouseListener(new MouseAdapter() { //
			
			
			
			public void mouseClicked(MouseEvent e) {
				System.out.println("Plantas -> click Seleccionar");
				int fila = table_Plantas.rowAtPoint(e.getPoint());
				int columna = table_Plantas.columnAtPoint(e.getPoint());
				
				if(fila>-1 && columna>-1) {
					Integer idAux = Integer.valueOf((String) table_Plantas.getValueAt(fila,columna));
					//btnAgregarStock.setEnabled(true);
					//btnModificar.setEnabled(true);
				}
				
			}
		});*/
		
		}
		
		//-----------------buscar-actualizar----------------
		
		/*class AccionBuscar implements ActionListener {
			 
			@Override
			 public void actionPerformed(ActionEvent e) {
				 
				 System.out.println("Planta -> Buscar");
				 
				 PlantaController pc=new PlantaController();
				 
				 try {
					this.actualizarTabla(pc.buscarPlanta(textField_ID.getText(), textField_Nombre.getText()));
					System.out.println("Buscar OK");
				
				 } catch (DatosInvalidosException | SQLException e1) {
					//e1.printStackTrace();
				}
			 }	

			private void actualizarTabla(String[][] aMostrar) throws NumberFormatException, DatosInvalidosException, SQLException {
				
				table_Plantas.setModel(new DefaultTableModel(aMostrar,	new String[] {"ID", "Nombre"}) 
				{
					Class[] columnTypes = new Class[] {
						Object.class, String.class//, String.class
					};
						
					public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
					}
				});
				
			}
		}*/
	
	
}
