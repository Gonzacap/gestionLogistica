package sistGestionLogistica.gui;

import java.awt.*;
import java.awt.event.*;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.CamionController;
import sistGestionLogistica.controller.StockInsumoController;
import sistGestionLogistica.sistema.App;


public class PanelStock extends JPanel {
	
	private JTable table_Stock;
	private JTextField textField_ID;
	private JTextField textField_Patente;
	private JTextField textField_Marca;
	private JTextField textField_Modelo;
	private JTextField textField_KM;
	private JTextField textField_CostoKM;
	private JTextField textField_CostoHora;
	private JTextField textField_FechaCompra;
	private Integer idAux;

	public PanelStock() {
		
	}

	public void inicializar(App aplicacion, Integer id) {
		this.idAux=id;
		inicializar(aplicacion);
	}
	
	public void inicializar(App aplicacion) { //App seria el JFrame
		aplicacion.setTitle("Sistema de Gestion Logistica - Stock");		
		
		PanelStock panel = new PanelStock();
		panel.setLayout(null);
		panel.setBackground(Color.blue);
		
		Integer altoP = aplicacion.getHeight()/10;
		Integer anchoP = aplicacion.getWidth()/8;
		
		aplicacion.stockEnabled();
		aplicacion.setContentPane(panel);
		aplicacion.revalidate();
		aplicacion.repaint();
		
		
		//---------Botones-----------------
		
		JButton btnAlta = new JButton("Agregar Insumos");
		btnAlta.setBounds((anchoP), (altoP/5), 90, 25);
		JButton btnEditar = new JButton("Editar Isumo");
		btnEditar.setBounds((anchoP+110), (altoP/5), 90, 25);
		JButton btnAtras= new JButton("Volver a Plantas");
		btnAtras.setBounds((anchoP+2*110), (altoP/5), 90, 25);
		
		panel.add(btnAlta);
		panel.add(btnEditar);
		panel.add(btnAtras);
		
		btnEditar.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds((anchoP), (4*altoP), (6*anchoP), (4*altoP));
		panel.add(scrollPane);
		
		//-------tabla-------
		
		table_Stock = new JTable();
		scrollPane.setViewportView(table_Stock);
		table_Stock.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Stock.setToolTipText("");
		table_Stock.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"ID Stock", "ID Insumo", "Planta", "Descripcion", "Cantidad", "Pto. Pedido", "Stock Total"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		//---------panel buscar-----------
		
		/*JPanel panelBuscar = new JPanel(new GridBagLayout());
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
		
		c.fill = GridBagConstraints.CENTER;
		
		JButton btnBuscar = new JButton("Buscar"+"\n"+"Actualizar");
		c.gridx = 8;
		c.gridheight = 2;
		c.gridy = 0;
		panelBuscar.add(btnBuscar, c);
		
		panelBuscar.setVisible(true);
		panel.add(panelBuscar);
		
		table_Stock.getColumnModel().getColumn(0).setPreferredWidth(35);
		
		
		//---------accion click-------
		
		table_Stock.addMouseListener(new MouseAdapter() {
				
			public void mouseClicked(MouseEvent e) {
				System.out.println("StockInsumo -> click Modificar Insumo");
				int fila = table_Stock.rowAtPoint(e.getPoint());
				
				if(fila>-1) {
					idAux = Integer.valueOf((String) table_Stock.getValueAt(fila,0));
					btnEditar.setEnabled(true);
				}
				
			}
		});*/
		
		//---------------------------
		
		//--------Acciones Botones--------------	
		
		btnAlta.addActionListener(e-> {	//AccionAlta
			
			System.out.println("Stock -> Alta");
			AgregarEditarStock aS = new AgregarEditarStock();
			aS.agregar();			
		});
		btnEditar.addActionListener(e-> { //AccionEditar
			
			System.out.println("Stock -> Editar");
			//AgregarEditarCamion eC = new AgregarEditarCamion();
			//eC.editarCamion(idAux);	
		});
		btnAtras.addActionListener(e-> {	//AccionVolver
			
			System.out.println("Volviendo a Panel Planta");
			aplicacion.volverStock();
		});
		//btnBuscar.addActionListener(new AccionBuscar());
		

	}
		
	
	class AccionBuscar implements ActionListener {
		 
		 public void actionPerformed(ActionEvent e) {
			 
			 System.out.println("Stock -> Buscar");
			 
			 StockInsumoController ic =new StockInsumoController();
			 
			 /*try { 
				this.actualizarTabla(ic.buscar()));
			
			 } catch (DateTimeParseException | NumberFormatException | DatosInvalidosException | SQLException e1) {
				//e1.printStackTrace();
			}*/
		 }

		private void actualizarTabla(String[][] aMostrar) throws DateTimeParseException, NumberFormatException, DatosInvalidosException, SQLException {
			
			table_Stock.setModel(new DefaultTableModel(aMostrar,	new String[] {"ID Stock", "ID Insumo", "Planta", "Descripcion", "Cantidad", "Pto. Pedido", "Stock Total"}) 
			{
				Class[] columnTypes = new Class[] {
					Object.class, String.class, String.class, String.class, String.class, String.class, String.class
				};
					
				public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
				}
			});
			
		}
		
	}
	
	
}
