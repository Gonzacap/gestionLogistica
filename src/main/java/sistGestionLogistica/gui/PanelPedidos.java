package sistGestionLogistica.gui;

import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.PlantaController;
import sistGestionLogistica.sistema.App;

public class PanelPedidos extends JPanel {

	private JTable table_Plantas;
	private JTextField textField_ID;
	private JTextField textField_Nombre;
	private JTextField textField_Tipo;

	public PanelPedidos() {

	}
	
	public void inicializar(App aplicacion) {
		aplicacion.setTitle("Sistema de Gestion Logistica - Pedidos");		
		
		PanelPedidos panel = new PanelPedidos();
		panel.setLayout(null);
		panel.setBackground(Color.orange);
		
		aplicacion.pedidosActivated();
		aplicacion.setContentPane(panel);
		aplicacion.revalidate();
		aplicacion.repaint();
		
		//---------Botones-----------------
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setBounds(23, 37, 89, 23);
		JButton btnBaja = new JButton("Baja");
		btnBaja.setBounds(307, 37, 89, 23);
		JButton btnModificar = new JButton("Modificar Por ID");
		btnModificar.setBounds(136, 37, 130, 23);
		JButton btnAgregarStock = new JButton("Agregar Stock");
		btnAgregarStock.setBounds(419, 37, 89, 23);
		
		panel.add(btnAlta);
		panel.add(btnBaja);
		panel.add(btnModificar);
		panel.add(btnAgregarStock);
		
		btnModificar.setEnabled(false);
		btnAgregarStock.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 208, 671, 222);
		panel.add(scrollPane);
		
		//-------tabla-------
		
		table_Plantas = new JTable();
		scrollPane.setViewportView(table_Plantas);
		table_Plantas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Plantas.setToolTipText("");
		
		table_Plantas.setModel(new DefaultTableModel(new Object[][] {},new String[] {"ID", "Nombre"/*, "Tipo"*/}) {
			
			Class[] columnTypes = new Class[] {
				Object.class, String.class//, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		//--------------------
		
		//----------panel buscar----------
		
		JPanel panelBuscar = new JPanel(new GridBagLayout());
		panelBuscar.setBounds(50, 80, 700, 120);
		
		GridBagConstraints p = new GridBagConstraints();
		p.gridheight = 1;
		p.gridwidth = 1;
		p.weightx = 50;
		p.weighty = 80;
		
		//----------------------
		
		p.fill = GridBagConstraints.HORIZONTAL;
		
		textField_ID = new JTextField();
		p.gridx = 1;
		p.gridy = 0;
		panelBuscar.add(textField_ID, p);
		
		textField_Nombre = new JTextField();
		p.gridx = 1;
		p.gridy = 1;
		panelBuscar.add(textField_Nombre, p);
		
		//-------------------------
		
		p.fill = GridBagConstraints.CENTER;
		
		JLabel lblId = new JLabel("ID");
		p.gridx = 0;
		p.gridy = 0;
		panelBuscar.add(lblId, p);
		JLabel lblNom = new JLabel("Nombre");
		p.gridx = 0;
		p.gridy = 1;
		panelBuscar.add(lblNom, p);
		
		//---------------------
		
		p.fill = GridBagConstraints.CENTER;
		
		JButton btnBuscar = new JButton("Buscar"+"\n"+"Actualizar");
		p.gridx = 8;
		p.gridheight = 1;
		p.gridy = 0;
		panelBuscar.add(btnBuscar, p);
		
		panelBuscar.setVisible(true);
		panel.add(panelBuscar);
		
		//--------------------
	
		table_Plantas.getColumnModel().getColumn(0).setPreferredWidth(35);
		
		
		//--------Acciones Botones--------------	
		
		btnAlta.addActionListener(e-> {	//AccionAlta
			
			System.out.println("Pedidos -> Alta");
			
			
		});
		btnBaja.addActionListener(e-> { //AccionBaja
			
			System.out.println("Pedidos -> Baja");
			

		});
		btnAgregarStock.addActionListener(e-> {	//agregar stock
		
			System.out.println("Pedidos -> Editar Stock");
			

		});
		//btnBuscar.addActionListener(new AccionBuscar());
	
	//---------accion click-------
	
	table_Plantas.addMouseListener(new MouseAdapter() { //
		
		
		
		public void mouseClicked(MouseEvent e) {
			System.out.println("Plantas -> click Seleccionar");
			int fila = table_Plantas.rowAtPoint(e.getPoint());
			int columna = table_Plantas.columnAtPoint(e.getPoint());
			
			if(fila>-1 && columna>-1) {
				Integer idAux = Integer.valueOf((String) table_Plantas.getValueAt(fila,columna));
				//EditarCamion eC = new EditarCamion(idAux);
				//eC.setVisible(true);
				btnAgregarStock.setEnabled(true);
				btnModificar.setEnabled(true);
			}
			
		}
	});
	
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
