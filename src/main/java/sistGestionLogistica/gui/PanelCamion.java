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
	private Integer idAux;

	public PanelCamion() {
		
	}

	public void inicializar(App aplicacion) { //App seria el JFrame
		aplicacion.setTitle("Sistema de Gestion Logistica - Camiones");		

		
		PanelCamion panel = new PanelCamion();
		panel.setLayout(null);
		panel.setBackground(Color.yellow);
		
		Integer altoP = aplicacion.getHeight()/10;
		Integer anchoP = aplicacion.getWidth()/8;
		
		aplicacion.camionesActivated();
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
		
		panel.add(btnAlta);
		panel.add(btnBaja);
		panel.add(btnEditar);
		
		btnEditar.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds((anchoP), (4*altoP), (6*anchoP), (4*altoP));
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
		
		//---------panel buscar-----------
		
		JPanel panelBuscar = new JPanel(new GridBagLayout());
		panelBuscar.setBounds((anchoP), (altoP), (6*anchoP), (2*altoP));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = (anchoP);
		c.weighty = (altoP);
		
		//----------------------
		
		c.fill = GridBagConstraints.HORIZONTAL;

		textField_ID = new JTextField();
		c.gridx = 1;
		c.gridy = 0;
		panelBuscar.add(textField_ID, c);
		
		textField_Patente = new JTextField();
		c.gridx = 1;
		c.gridy = 1;
		panelBuscar.add(textField_Patente, c);
		
		textField_Marca = new JTextField();
		c.gridx = 3;
		c.gridy = 0;
		panelBuscar.add(textField_Marca, c);
		
		textField_Modelo = new JTextField();
		c.gridx = 3;
		c.gridy = 1;
		panelBuscar.add(textField_Modelo, c);
		
		//-------------------------
		
		c.fill = GridBagConstraints.CENTER;
		
		JLabel lbl_4 = new JLabel("Id");
		c.gridx = 0;
		c.gridy = 0;
		panelBuscar.add(lbl_4, c);
		
		JLabel lbl_5 = new JLabel("Patente");
		c.gridx = 0;
		c.gridy = 1;
		panelBuscar.add(lbl_5, c);
				
		JLabel lbl_6 = new JLabel("Marca");
		c.gridx = 2;
		c.gridy = 0;
		panelBuscar.add(lbl_6, c);
		
		JLabel lbl_7 = new JLabel("Modelo");
		c.gridx = 2;
		c.gridy = 1;
		panelBuscar.add(lbl_7, c);
		
		//----------------------
		
		c.fill = GridBagConstraints.HORIZONTAL;

		textField_KM = new JTextField();
		c.gridx = 5;
		c.gridy = 0;
		panelBuscar.add(textField_KM, c);
		
		textField_CostoKM = new JTextField();
		c.gridx = 5;
		c.gridy = 1;
		panelBuscar.add(textField_CostoKM, c);
		
		textField_CostoHora = new JTextField();
		c.gridx = 7;
		c.gridy = 0;
		panelBuscar.add(textField_CostoHora, c);
		
		textField_FechaCompra = new JTextField();
		c.gridx = 7;;
		c.gridy = 1;
		panelBuscar.add(textField_FechaCompra, c);
				
		//-------------------------
		
		c.fill = GridBagConstraints.CENTER;
				
		JLabel lbl_0 = new JLabel("Km recorridos");
		c.gridx = 4;
		c.gridy = 0;
		panelBuscar.add(lbl_0, c);
		
		JLabel lbl_1 = new JLabel("Costo por Km");
		c.gridx = 4;
		c.gridy = 1;
		panelBuscar.add(lbl_1, c);
		
		JLabel lbl_2 = new JLabel("Costo por Hora");
		c.gridx = 6;
		c.gridy = 0;
		panelBuscar.add(lbl_2, c);
		
		JLabel lbl_3 = new JLabel("Fecha");
		c.gridx = 6;
		c.gridy = 1;
		panelBuscar.add(lbl_3, c);
		
		//----------------------
		
		c.fill = GridBagConstraints.CENTER;
		
		JButton btnBuscar = new JButton("Buscar"+"\n"+"Actualizar");
		c.gridx = 8;
		c.gridheight = 2;
		c.gridy = 0;
		panelBuscar.add(btnBuscar, c);
		
		panelBuscar.setVisible(true);
		panel.add(panelBuscar);
		
		table_Camiones.getColumnModel().getColumn(0).setPreferredWidth(35);
		
		
		//---------accion click-------
		
		table_Camiones.addMouseListener(new MouseAdapter() {
				
			public void mouseClicked(MouseEvent e) {
				System.out.println("Camion -> click Modificar");
				int fila = table_Camiones.rowAtPoint(e.getPoint());
				
				if(fila>-1) {
					idAux = Integer.valueOf((String) table_Camiones.getValueAt(fila,0));
					btnEditar.setEnabled(true);
				}
				
			}
		});
		
		//---------------------------
		
		//--------Acciones Botones--------------	
		
		btnAlta.addActionListener(e-> {	//AccionAlta
			
			System.out.println("Camion -> Alta");
			AgregarEditarCamion aC = new AgregarEditarCamion();
			aC.agregar();			
		});
		btnBaja.addActionListener(e-> { //AccionBaja
			
			System.out.println("Camion -> Baja");
			BajaCamion bC = new BajaCamion();
			bC.setVisible(true);
		});
		btnEditar.addActionListener(e-> {	//AccionEditar
			
			System.out.println("Camion -> Editar");
			AgregarEditarCamion eC = new AgregarEditarCamion();
			eC.editarCamion(idAux);			
		});
		btnBuscar.addActionListener(new AccionBuscar());
		

	}
	
	
	class AccionBuscar implements ActionListener {
		 
		 public void actionPerformed(ActionEvent e) {
			 
			 System.out.println("Camion -> Buscar");
			 
			 CamionController cc=new CamionController();
			 
			 try { 
				this.actualizarTabla(cc.buscarCamion(textField_ID.getText(), textField_Patente.getText(), textField_Marca.getText(), textField_Modelo.getText(), textField_CostoKM.getText(), textField_CostoHora.getText(), textField_KM.getText(), textField_FechaCompra.getText()));
			
			 } catch (DateTimeParseException | NumberFormatException | DatosInvalidosException | SQLException e1) {
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
