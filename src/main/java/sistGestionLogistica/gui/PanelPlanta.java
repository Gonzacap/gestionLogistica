package sistGestionLogistica.gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import sistGestionLogistica.sistema.App;

public class PanelPlanta extends JPanel {

	private JTable table_Plantas;
	private JTextField textField_ID;
	private JTextField textField_Nombre;
	private JTextField textField_Tipo;
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelCamion window = new PanelCamion();
					window.frame.setVisible(true);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //esta linea no se si anda ahre
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/


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
		
		//---------Botones-----------------
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setBounds(23, 37, 89, 23);
		JButton btnBaja = new JButton("Baja");
		btnBaja.setBounds(307, 37, 89, 23);
		JButton btnModificar = new JButton("Modificar Por ID");
		btnModificar.setBounds(136, 37, 130, 23);
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(419, 37, 89, 23);
		
		panel.add(btnAlta);
		panel.add(btnBaja);
		panel.add(btnModificar);
		panel.add(btnBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 208, 671, 222);
		panel.add(scrollPane);
		
		//-------tabla-------
		
		table_Plantas = new JTable();
		scrollPane.setViewportView(table_Plantas);
		table_Plantas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_Plantas.setToolTipText("");
		
		table_Plantas.setModel(new DefaultTableModel(new Object[][] {},new String[] {"ID", "Nombre", "Tipo"}) {
			
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
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

		});
		btnModificar.addActionListener(e-> {
		
			System.out.println("Planta -> Modificar");
			AgregarEditarPlanta aP = new AgregarEditarPlanta();
			aP.editar();

		});
		btnBuscar.addActionListener(e-> {
			
			System.out.println("Planta -> Buscar");

		});

		
	}
	
	
	/*class AccionBuscar implements ActionListener {
		 
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
	}*/
	
	
}
