package sistGestionLogistica.gui;

import java.awt.*;
import java.awt.event.*;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.CamionController;
import sistGestionLogistica.controller.StockInsumoController;
import sistGestionLogistica.dao.StockInsumoDaoMysql;
import sistGestionLogistica.dominio.Insumo;
import sistGestionLogistica.servicios.ServiceInsumo;
import sistGestionLogistica.sistema.App;


public class PanelStock extends JPanel {
	
	private PanelStock panel;
	private JTable table_Stock;
	private JTextField textField_IdPlanta;
	private JTextField textField_IdProd;
	private Integer idAux;
	private Integer altoP;
	private Integer anchoP;
	private GridBagConstraints c;
	private JPanel panelBuscar;

	public PanelStock() {
		
	}

	public void inicializar(App aplicacion, Integer id) {
		this.idAux=id;
		inicializar(aplicacion);
	}
	
	public void inicializar(App aplicacion) { //App seria el JFrame
		aplicacion.setTitle("Sistema de Gestion Logistica - Stock");		
		
		panel = new PanelStock();
		panel.setLayout(null);
		
		altoP = aplicacion.getHeight()/10;
		anchoP = aplicacion.getWidth()/8;
		
		aplicacion.setContentPane(panel);
		aplicacion.revalidate();
		aplicacion.repaint();
		
		//----------------------
		
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
		
		panelBuscar = new JPanel(new GridBagLayout());
		panelBuscar.setBounds((anchoP), (altoP), (6*anchoP), (2*altoP));
		
		c = new GridBagConstraints();
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = (anchoP);
		c.weighty = (altoP);
		
		
		c.fill = GridBagConstraints.CENTER;
		
		JButton btnBuscar = new JButton("Actualizar");
		c.gridx = 8;
		c.gridheight = 2;
		c.gridy = 0;
		panelBuscar.add(btnBuscar, c);
		
		panelBuscar.setVisible(true);
		panel.add(panelBuscar);
		
		table_Stock.getColumnModel().getColumn(0).setPreferredWidth(35);
		btnBuscar.addActionListener(new AccionBuscar());

	}
	
	public void pantallaStockPlanta(App aplicacion) {
		
		panel.setBackground(Color.blue);
		aplicacion.stockEnabled();
		
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
		
		//---------accion click-------
		/*
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
		
		//--------Acciones Botones--------------	
		
		btnAlta.addActionListener(e-> {	//AccionAlta
			
			System.out.println("Stock -> Alta");
			AgregarEditarStock aS = new AgregarEditarStock();
			try {
				aS.agregarStock(idAux);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		});
		btnEditar.addActionListener(e-> { //AccionEditar
			
			System.out.println("Stock -> Editar");
			AgregarEditarStock eS = new AgregarEditarStock();
			eS.editarStock(idAux);	
		});
		btnAtras.addActionListener(e-> {	//AccionVolver
			
			System.out.println("Volviendo a Panel Planta");
			aplicacion.volverStock();
		});
		
	}
	
	public void pantallaInsumosAReponer(App aplicacion){
		
		panel.setBackground(Color.cyan);
		aplicacion.stockActivated();
		
		//----------------------
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;

		textField_IdPlanta = new JTextField();
		c.gridx = 1;
		c.gridy = 0;
		panelBuscar.add(textField_IdPlanta, c);
		
		textField_IdProd = new JTextField();
		c.gridx = 1;
		c.gridy = 1;
		panelBuscar.add(textField_IdProd, c);
				
		//-------------------------
				
		c.fill = GridBagConstraints.CENTER;
				
		JLabel lbl_4 = new JLabel("Id Planta");
		c.gridx = 0;
		c.gridy = 0;
		panelBuscar.add(lbl_4, c);
		
		JLabel lbl_5 = new JLabel("Id Insumo");
		c.gridx = 0;
		c.gridy = 1;
		panelBuscar.add(lbl_5, c);
		
		//----------------------
		
	}	
	
	class AccionBuscar implements ActionListener {
		 
		 public void actionPerformed(ActionEvent e) {
			 
			 System.out.println("Stock -> Buscar");
			 
			 StockInsumoDaoMysql sI = new StockInsumoDaoMysql();
			 StockInsumoController sC = new StockInsumoController();
			 
			 try { 
				 
				    
				 if(idAux==null) {
//					 this.actualizarTabla(sC.aMatriz(sI.faltantes()));
					 this.actualizarTabla(sC.faltante(textField_IdPlanta.getText(),textField_IdProd.getText()));
				 }
				 else{
					 this.actualizarTabla(sC.aMatriz(sI.buscarStockPlanta(idAux)));
				 }
				 
			
			 } catch (DateTimeParseException | NumberFormatException | DatosInvalidosException | SQLException e1) {
				//e1.printStackTrace();
			}
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
