package sistGestionLogistica.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.controller.*;
import sistGestionLogistica.dao.*;
import sistGestionLogistica.dominio.*;
import sistGestionLogistica.servicios.ServiceInsumo;
import sistGestionLogistica.servicios.ServiceItemDetalle;
import sistGestionLogistica.servicios.ServicePlanta;


public class AgregarEditarPedido {

	private JTextField numOrden;
	private JFrame frame;
	private JPanel panel;
	private JComboBox<String> plantas;
	private JTextField FechaMaxEntrega;
	private Integer alto;
	private Integer ancho;
	private JTable tableItemDetalle;
	private ArrayList<ItemDetalle> items ;
	private Vector<Integer> plantaID;
	private Vector<String> plantaLbl;
	private JButton btnActualizar;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarEditarPedido window = new AgregarEditarPedido();
					window.agregar();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AgregarEditarPedido() {
		//super();
		inicializar();
	}
	
	private void inicializar(){
		
		items = new ArrayList<ItemDetalle>();
		
		alto = 100;
		ancho = 100;
		
		frame = new JFrame();
		frame.setBounds(ancho, alto, 5*ancho, 3*alto);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 5*ancho, 3*alto);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(true);
		
		items = new ArrayList<ItemDetalle>();
		
		//----------string para el combo box-------
		
		Planta p= new Planta(-1,"");
		ServicePlanta ser = new ServicePlanta();
		
		ArrayList<Planta> lista =  (ArrayList<Planta>) ser.buscarPlanta(p);
		this.plantaID= new Vector<>();
		this.plantaLbl= new Vector<>();
		
		for(int i = 0; i < lista.size(); i++){
			
			plantaLbl.add(lista.get(i).getId()+" - "+lista.get(i).getNombre());
			plantaID.add(lista.get(i).getId());
			
		}
		System.out.print("\n");
		//--------------
		
		numOrden = new JTextField();
		numOrden.setBounds(200, 25, 120, 20);
		plantas = new JComboBox<String>(plantaLbl);
		plantas.setBounds(200, 50, 120, 20);
		FechaMaxEntrega = new JTextField("31/12/2020");
		FechaMaxEntrega.setBounds(200, 75, 120, 20);
		JLabel lblNumOrden = new JLabel("Numero de Orden");
		lblNumOrden.setBounds(25, 25, 150, 20);
		JLabel lblPlantas = new JLabel("Plantas");
		lblPlantas.setBounds(25, 50, 150, 20);
		JLabel lblFecha = new JLabel("Fecha maxima de entrega");
		lblFecha.setBounds(25, 75, 150, 20);
		
		panel.add(numOrden);
		panel.add(lblNumOrden);
		panel.add(plantas);
		panel.add(FechaMaxEntrega);
		panel.add(lblPlantas);
		panel.add(lblFecha);
		
		//-------------------
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(ancho, 3*(alto/2), (3*ancho), (alto));
		panel.add(scrollPane);
		
		//-------tabla-------
		
		tableItemDetalle = new JTable();
		scrollPane.setViewportView(tableItemDetalle);
		tableItemDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableItemDetalle.setToolTipText("");
		
		tableItemDetalle.setModel(new DefaultTableModel(new Object[][] {},new String[] {"Insumo", "Cantidad", "Precio"}) {
			
			Class[] columnTypes = new Class[] {
				/*Object.class, */String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		//--------------------
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(200, 110, 120, 20);
		panel.add(btnActualizar);
		
		btnActualizar.addActionListener(new AccionBuscar());
		
	}
	

	public void agregar() throws SQLException{
		
		frame.setTitle("Crear Pedido");
		frame.setVisible(true);
		
		JButton btnAgregar = new JButton("Agregar Items");
		btnAgregar.setBounds(50, 110, 120, 20);
		panel.add(btnAgregar);
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(350, 110, 120, 20);
		panel.add(btnCrear);

		btnAgregar.addActionListener(new ActionListener(){
				
				@Override
				public void actionPerformed(ActionEvent e) {
				
				try {
					AgregarEditarInsumosAPedido ai = new AgregarEditarInsumosAPedido(items);
					ai.setVisible(true);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
				btnActualizar.doClick();
			}				
		});
		
		btnCrear.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				System.out.println("Creando pedido");
				
				for(ItemDetalle i: items) {
					i.setNumOrden(Integer.valueOf(numOrden.getText()));				
				}
				
				PedidoController pc = new PedidoController();
				try {
					pc.crearPedido(numOrden.getText(), plantaID.get(plantas.getSelectedIndex()).toString(), FechaMaxEntrega.getText(), items);
					
				
				} catch (SQLException | DatosInvalidosException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
				
				System.out.println("Pedido creado");
			}				
		});
		
		
	}
	
	public void verDetalle(Pedido p) throws SQLException {
		
		frame.setTitle("Detalles del Pedido");
		frame.setVisible(true);
		
		panel.remove(plantas);
		panel.remove(numOrden);
		
		JLabel numOrden = new JLabel(p.getNumOrden().toString());
		numOrden.setBounds(200, 25, 120, 20);
		JLabel nomPlanta = new JLabel(p.getPlantaDestino().getNombre());
		nomPlanta.setBounds(200, 50, 120, 20);
		
		ServiceItemDetalle sid = new ServiceItemDetalle ();
		this.items = (ArrayList<ItemDetalle>) sid.buscarPorNumOrden(p.getNumOrden());
		
		for(ItemDetalle i: items) {
			System.out.println(i.getInsumo().getIdInsumo());
		}
		
		btnActualizar.doClick();
		
	}
	
	
	/*public void editarStock(Integer id) {
		

	}
	
	public void editar(){
		
		//this.inicializar();	
		frame.setTitle("Editar Insumo en Stock");
		frame.setVisible(true);
		
		
		//JButton btnAgregar = new JButton("Editar");
		//btnAgregar.setBounds(252, 105, 89, 23);
		//panel.add(btnAgregar);
		
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StockInsumoController ic= new StockInsumoController();
				
				//try {
					//pc.registrarPlanta(textField_Nombre.getText());
					
					JOptionPane.showMessageDialog(frame,"La planta fue editada con exito.", "Edicion Exitosa",JOptionPane.INFORMATION_MESSAGE);
				
				//} catch (DateTimeParseException | DatosInvalidosException | NumberFormatException e1) {
					//Mensaje de error
					//JOptionPane.showMessageDialog(frame,"Por favor verifique sus datos.","Datos Invalidos",JOptionPane.ERROR_MESSAGE);
					//e1.printStackTrace();
				//}
			}
		});

		
	}*/
	
	//-----------------actualizar----------------
	
	class AccionBuscar implements ActionListener {
	 
		@Override
		public void actionPerformed(ActionEvent e) {
		 
			System.out.println("Actualizando tabla de items");
		 
			ItemDetalleController ic=new ItemDetalleController();
				 
			try {
				this.actualizarTabla(ic.aMatriz2(items));
				System.out.println("Actualizar Ok");
					
			} catch (DatosInvalidosException | SQLException e1) {
				//e1.printStackTrace();
			}
		}	

		private void actualizarTabla(String[][] aMostrar) throws NumberFormatException, DatosInvalidosException, SQLException {
				
			tableItemDetalle.setModel(new DefaultTableModel(aMostrar,	new String[] {"Insumo", "Cantidad", "Precio"}) {
				
				Class[] columnTypes = new Class[] {
						/*Object.class, */String.class, String.class, String.class
				};
				
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});		
		}
	}
	
}

