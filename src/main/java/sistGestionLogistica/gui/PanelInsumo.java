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
		btnAlta.setBounds(23, 37, 89, 23);
		JButton btnBaja = new JButton("Baja");
		btnBaja.setBounds(221, 37, 89, 23);
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(122, 37, 89, 23);
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(320, 37, 89, 23);
		
		panel.add(btnAlta);
		panel.add(btnBaja);
		panel.add(btnModificar);
		panel.add(btnBuscar);
		
		//----------------------------

		
		//--------Acciones Botones--------------	
			
		btnAlta.addActionListener(e-> {	//AccionAlta
					
			System.out.println("Insumo -> Alta");
					
		});
		btnBaja.addActionListener(e-> { //AccionBaja
					
			System.out.println("Insumo -> Baja");

		});
		btnModificar.addActionListener(e-> {
				
			System.out.println("Insumo -> Modificar");

		});
		btnBuscar.addActionListener(e-> {
					
		System.out.println("Insumo -> Buscar");

		});

		//---------accion click-------
		
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
