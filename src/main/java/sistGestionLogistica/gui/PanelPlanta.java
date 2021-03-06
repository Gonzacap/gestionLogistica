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

public class PanelPlanta extends JPanel {

	private JTable table_Plantas;
	private JTextField textField_ID;
	private JTextField textField_Nombre;
	private JTextField textField_Tipo;
	private Integer idAux;

	public PanelPlanta() {

	}
	
	public void inicializar(App aplicacion) {
		aplicacion.setTitle("Sistema de Gestion Logistica - Plantas");		
		
		PanelPlanta panel = new PanelPlanta();
		panel.setLayout(null);
		panel.setBackground(Color.white);
		
		Integer altoP = aplicacion.getHeight()/10;
		Integer anchoP = aplicacion.getWidth()/8;
		
		aplicacion.plantasActivated();
		aplicacion.setContentPane(panel);
		aplicacion.revalidate();
		aplicacion.repaint();
		
		//---------Botones-----------------
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setBounds((anchoP), (altoP/5), 90, 25);
		JButton btnBaja = new JButton("Baja");
		btnBaja.setBounds((anchoP+110), (altoP/5), 90, 25);
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds((anchoP+2*110), (altoP/5), 90, 25);
		JButton btnAgregarStock = new JButton("Agregar Stock");
		btnAgregarStock.setBounds((anchoP+3*110), (altoP/5), 150, 25);
		
		panel.add(btnAlta);
		panel.add(btnBaja);
		panel.add(btnEditar);
		panel.add(btnAgregarStock);
		
		btnEditar.setEnabled(false);
		btnAgregarStock.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds((anchoP), (4*altoP), (6*anchoP), (4*altoP));
		panel.add(scrollPane);
		
		//-------tabla-------
		
		table_Plantas = new JTable();
		scrollPane.setViewportView(table_Plantas);
		table_Plantas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Plantas.setToolTipText("");
		
		table_Plantas.setModel(new DefaultTableModel(new Object[][] {},new String[] {"ID", "Nombre"}) {
			
			Class[] columnTypes = new Class[] {
				Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		//--------------------
		
		//----------panel buscar----------
		
		JPanel panelBuscar = new JPanel(new GridBagLayout());
		panelBuscar.setBounds((anchoP), (altoP), (6*anchoP), (2*altoP));
		
		GridBagConstraints p = new GridBagConstraints();
		p.gridheight = 1;
		p.gridwidth = 1;
		p.weightx = (anchoP);
		p.weighty = (altoP);
		
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
			
			System.out.println("Planta -> Alta");
			AgregarEditarPlanta aP = new AgregarEditarPlanta();
			aP.agregar();
			
		});
		btnBaja.addActionListener(e-> { //AccionBaja
			
			System.out.println("Planta -> Baja");
			BajaPlanta bp = new BajaPlanta();
			bp.setVisible(true);

		});
		btnEditar.addActionListener(e-> {	//editar
			
			System.out.println("Planta -> Editar Planta");
			AgregarEditarPlanta eP = new AgregarEditarPlanta();
			eP.editarPlanta(idAux);

		});
		btnAgregarStock.addActionListener(e-> {	//agregar stock
		
			System.out.println("Planta -> Editar Stock");
			aplicacion.mostrarPanelStock(idAux);
		});
		btnBuscar.addActionListener(new AccionBuscar());
	
	//---------accion click-------
	
	table_Plantas.addMouseListener(new MouseAdapter() { //
		
		
		
		public void mouseClicked(MouseEvent e) {
			System.out.println("Plantas -> click Seleccionar");
			int fila = table_Plantas.rowAtPoint(e.getPoint());
			
			if(fila>-1){
				idAux = Integer.valueOf((String) table_Plantas.getValueAt(fila,0));
				btnAgregarStock.setEnabled(true);
				btnEditar.setEnabled(true);
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
			
			table_Plantas.setModel(new DefaultTableModel(aMostrar,	new String[] {"ID", "Nombre"}) 
			{
				Class[] columnTypes = new Class[] {
					Object.class, String.class
				};
					
				public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
				}
			});
			
		}
	}
	
}
