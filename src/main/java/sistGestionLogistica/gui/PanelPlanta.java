package sistGestionLogistica.gui;

import java.awt.Color;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.PlantaController;
import sistGestionLogistica.gui.PanelCamion.AccionBuscar;
import sistGestionLogistica.sistema.App;

public class PanelPlanta extends JPanel {

	private JTable table_Plantas;
	private JTextField textField_ID;
	private JTextField textField_Nombre;
	private JTextField textField_Tipo;

	//Integer idAux;

	public PanelPlanta() {

	}
	
	public void inicializar(App aplicacion) {
		aplicacion.setTitle("Sistema de Gestion Logistica - Plantas");		
		
		PanelPlanta panel = new PanelPlanta();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		aplicacion.plantasActivated();
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
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(550, 37, 89, 23);
		
		panel.add(btnAlta);
		panel.add(btnBaja);
		panel.add(btnModificar);
		panel.add(btnAgregarStock);
		panel.add(btnBuscar);
		
		//btnAgregarStock.setVisible(true);
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
		
		//-------tabla-------
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(35, 86, 46, 14);
		JLabel lblNom = new JLabel("Nombre");
		lblNom.setBounds(23, 117, 46, 14);
		JLabel lblTipoP = new JLabel("Tipo");
		lblTipoP.setBounds(23, 150, 46, 14);
		
		panel.add(lblId);
		panel.add(lblNom);		
		panel.add(lblTipoP);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(76, 83, 86, 20);
		textField_Nombre = new JTextField();
		textField_Nombre.setBounds(79, 114, 86, 20);
		textField_Tipo = new JTextField();
		textField_Tipo.setBounds(79, 147, 86, 20);
		
		panel.add(textField_ID);
		panel.add(textField_Nombre);
		panel.add(textField_Tipo);
		textField_ID.setColumns(10);
		textField_Nombre.setColumns(10);
		textField_Tipo.setColumns(10);		
		
		table_Plantas.getColumnModel().getColumn(0).setPreferredWidth(35);

		
		//--------Acciones Botones--------------	
		
		btnAlta.addActionListener(e-> {	//AccionAlta
			
			System.out.println("Planta -> Alta");
			AgregarEditarPlanta aP = new AgregarEditarPlanta();
			aP.agregar();
			
		});
		btnBaja.addActionListener(e-> { //AccionBaja
			
			System.out.println("Planta -> Baja");
			BajaPlanta bp = new BajaPlanta();
			bp.setVisible(true);

		});
		btnAgregarStock.addActionListener(e-> {	//agregar stock
		
			System.out.println("Planta -> Editar Stock");
			//AgregarEditarPlanta aP = new AgregarEditarPlanta();
			//aP.editar();

		});
		btnBuscar.addActionListener(new AccionBuscar());
	
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
			}
			
		}
	});
	
	}
	
	//-----------------buscar-actualizar----------------
	
	class AccionBuscar implements ActionListener {
		 
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
			
			table_Plantas.setModel(new DefaultTableModel(aMostrar,	new String[] {"ID", "Nombre"/*, "Tipo"*/}) 
			{
				Class[] columnTypes = new Class[] {
					Object.class, String.class//, String.class
				};
					
				public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
				}
			});
			
		}
	}
	
}
