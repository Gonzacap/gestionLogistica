package sistGestionLogistica.gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import excepciones.DatosInvalidosException;
import excepciones.GrafoException;
import sistGestionLogistica.controller.GrafoLogisticaController;
import sistGestionLogistica.controller.PlantaController;
import sistGestionLogistica.dao.PlantaDaoMysql;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.servicios.ServicePlanta;
import sistGestionLogistica.sistema.App;

public class PanelRutas extends JPanel {

	private JTable table_Plantas;
	private JTextField textField_Distancia;
	private JTextField textField_DViaje;
	private JTextField textField_PesoMax;
	private JComboBox POrigen;
	private JComboBox PDestino;
	
	
	public PanelRutas() {

	}
	
	public void inicializar(App aplicacion) throws DateTimeParseException, NumberFormatException, DatosInvalidosException, SQLException {
		aplicacion.setTitle("Sistema de Gestion Logistica - Rutas");		
		
		PanelRutas panel = new PanelRutas();
		panel.setLayout(null);
		panel.setBackground(Color.gray);
		
		aplicacion.rutasActivated();
		aplicacion.setContentPane(panel);
		aplicacion.revalidate();
		aplicacion.repaint();
		
		//---------Botones-----------------
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setBounds(23, 37, 89, 23);		
		panel.add(btnAlta);	
		
		/*
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 208, 671, 222);
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
		});*/
		
		
		//----------string para el combo box-------
		
		PlantaController pc=new PlantaController();
		String[][] plantas = pc.buscarPlanta("", "");
		Vector<String> plantaId= new Vector<>() ;
		for(int i = 0; i< plantas.length; i++){
			plantaId.add(plantas[i][0]+" - "+plantas[i][1]) ;
		}
		
		//----------panel agregar----------
		
		JPanel panelBuscar = new JPanel(new GridBagLayout());
		panelBuscar.setBounds(50, 80, 700, 120);
		
		GridBagConstraints p = new GridBagConstraints();
		p.gridheight = 1;
		p.gridwidth = 1;
		p.weightx = 50;
		p.weighty = 80;
		
		//----------------------
		
		p.fill = GridBagConstraints.HORIZONTAL;
		
		textField_Distancia = new JTextField();
		p.gridx = 1;
		p.gridy = 0;
		panelBuscar.add(textField_Distancia, p);
		
		textField_DViaje = new JTextField();
		p.gridx = 1;
		p.gridy = 1;
		panelBuscar.add(textField_DViaje, p);
		
		textField_PesoMax = new JTextField();
		p.gridx = 1;
		p.gridy = 2;
		panelBuscar.add(textField_PesoMax, p);
		
		//-------------------------
		
		p.fill = GridBagConstraints.CENTER;
		
		JLabel lblDis = new JLabel("Distancia");
		p.gridx = 0;
		p.gridy = 0;
		panelBuscar.add(lblDis, p);
		JLabel lblDur = new JLabel("Duracion");
		p.gridx = 0;
		p.gridy = 1;
		panelBuscar.add(lblDur, p);
		JLabel lblPM = new JLabel("Peso Maximo");
		p.gridx = 0;
		p.gridy = 2;
		panelBuscar.add(lblPM, p);
		JLabel lblPO = new JLabel("Duracion");
		p.gridx = 0;
		p.gridy = 3;
		panelBuscar.add(lblPO, p);
		JLabel lblPD = new JLabel("Duracion");
		p.gridx = 0;
		p.gridy = 4;
		panelBuscar.add(lblPD, p);

		//-------------------------------
		
		POrigen = new JComboBox(plantaId);
		p.gridx = 1;
		p.gridy = 3;
		panelBuscar.add(POrigen, p);
		PDestino = new JComboBox(plantaId);
		p.gridx = 1;
		p.gridy = 4;
		panelBuscar.add(PDestino, p);
		
		//---------------------
		
		panelBuscar.setVisible(true);
		panel.add(panelBuscar);
		
		
		//--------Acciones Botones--------------	
		
		btnAlta.addActionListener(e-> {	//AccionAlta
			
			System.out.println("Planta -> Alta");
			
			
			Integer idOr = (POrigen.getSelectedIndex()+1);
			Integer idDest = (PDestino.getSelectedIndex()+1);
			
			if(idOr!=idDest) {
			
				GrafoLogisticaController gc= new GrafoLogisticaController();
			
				try {
					gc.conectarPlantas(textField_Distancia.getText(), textField_DViaje.getText(), textField_PesoMax.getText(), 
							idOr.toString(), idDest.toString());
				} catch (DatosInvalidosException | SQLException | GrafoException e1) {
					//e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,"La ruta due dada de alta con exito.", "Alta Exitosa",JOptionPane.INFORMATION_MESSAGE);
			}
			else{
				JOptionPane.showMessageDialog(null,"La planta de origen debe ser distinta a la de destino ", "Alta no completada",JOptionPane.ERROR_MESSAGE);
			}
			
		});
		
	
	//---------accion click-------
	
	/*table_Plantas.addMouseListener(new MouseAdapter() { //
		
		
		
		public void mouseClicked(MouseEvent e) {
			System.out.println("Plantas -> click Seleccionar");
			int fila = table_Plantas.rowAtPoint(e.getPoint());
			int columna = table_Plantas.columnAtPoint(e.getPoint());
			
			if(fila>-1 && columna>-1) {
				Integer idAux = Integer.valueOf((String) table_Plantas.getValueAt(fila,columna));
				btnAgregarStock.setEnabled(true);
				btnModificar.setEnabled(true);
			}
			
		}
	});*/
	
	}
	
}
