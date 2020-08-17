package sistGestionLogistica.sistema;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import sistGestionLogistica.gui.PanelCamion;
import sistGestionLogistica.gui.PanelPlanta;
import sistGestionLogistica.gui.PanelRutas;
import sistGestionLogistica.gui.PanelInsumo;
import sistGestionLogistica.gui.PanelPedidos;


public class App extends JFrame{
	
	public App() {
	}
	
	public static void main(String[] args) {
		
		App aplicacion = new App();
		
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Dimension tamPant = miPantalla.getScreenSize();
		int alturaP = tamPant.height;
		int anchoP = tamPant.width;
		aplicacion.setSize(3*(anchoP/5),3*(alturaP/5));
		aplicacion.setLocation((anchoP/5),(alturaP/5));
		
		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aplicacion.inicializar();
		aplicacion.setTitle("Sistema de Gestion Logistica");
		aplicacion.setVisible(true);
		
		JOptionPane.showMessageDialog(null, "¡Bienvenido!");

	}
	
	public GridBagConstraints gridbag;
	private JMenu menuPlantas;
	private JMenu menuCamiones;
	private JMenu menuInsumos;
	private JMenu menuPedidos;
	private JMenu menuRutas;
	private JMenuBar barraMenu;
	private JMenuItem gestorPlantas;
	private JMenuItem gestorCamiones;
	private JMenuItem gestorInsumos;
	private JMenuItem gestorPedidos;
	private JMenuItem gestorRutas;
	
	private PanelPlanta pP;
	private PanelCamion pC;
	private PanelInsumo pI;
	private PanelPedidos pD;
	private PanelRutas pR; 
	
	//----------------METODOS----------------
	
	
	private void inicializar() {
		
		//this.panel=new JPanel(new GridBagLayout());
		this.menuPlantas = new JMenu("Plantas");
		this.menuCamiones = new JMenu("Camiones");
		this.menuInsumos = new JMenu("Insumos");
		this.menuPedidos = new JMenu("Pedidos");
		this.menuRutas = new JMenu("Rutas");
		this.barraMenu = new JMenuBar();
		this.gridbag = new GridBagConstraints();
		this.gestorPlantas = new JMenuItem("Gestionar Plantas");
		this.gestorCamiones = new JMenuItem("Gestionar Camiones");
		this.gestorInsumos =new JMenuItem("Gestionar Insumos");
		this.gestorPedidos =new JMenuItem("Gestionar Pedidos");
		this.gestorRutas =new JMenuItem("Gestionar Rutas");
		
		this.pP = new PanelPlanta();
		this.pI = new PanelInsumo();
		this.pC = new PanelCamion();
		this.pD = new PanelPedidos();
		this.pR = new PanelRutas();
		
		pP.inicializar(this);
		pI.inicializar(this);
		pD.inicializar(this);
		pR.inicializar(this);
		pC.inicializar(this);
		
		menuPlantas.add(gestorPlantas);
		menuInsumos.add(gestorInsumos);
		menuPedidos.add(gestorPedidos);
		menuRutas.add(gestorRutas);
		menuCamiones.add(gestorCamiones);
		barraMenu.add(menuPlantas);
		barraMenu.add(menuCamiones);
		barraMenu.add(menuInsumos);
		barraMenu.add(menuPedidos);
		barraMenu.add(menuRutas);
		this.setJMenuBar(barraMenu);
		
		
		//Pantalla de inicio?---------
		
		/*JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.red);
		panel.add(new Label("Sistema de Gestion Logistica"));
		
		this.setContentPane(panel);
		this.revalidate();
		this.repaint();*/
		
		//--------Acciones Botones--------------	
		
		gestorCamiones.addActionListener(e-> {
			
			System.out.println("App -> Panel Camiones");
			pC.inicializar(this);
			this.revalidate();
			this.repaint();
		});
		
		gestorPlantas.addActionListener(e->{
			
			System.out.println("App -> Panel Plantas");
			pP.inicializar(this);			
			this.revalidate();
			this.repaint();
		});
		
		gestorInsumos.addActionListener(e->{
			
			System.out.println("App -> Panel Insumos");
			pI.inicializar(this);			
			this.revalidate();
			this.repaint();
		});
		
		gestorPedidos.addActionListener(e->{
			
			System.out.println("App -> Panel Pedidos");
			pD.inicializar(this);			
			this.revalidate();
			this.repaint();
		});

		gestorRutas.addActionListener(e->{
	
			System.out.println("App -> Panel Rutas");
			pR.inicializar(this);			
			this.revalidate();
			this.repaint();
		});
		
		
	}
	
	//-------------otros metodos-------------------
	
	public void camionesActivated() {
		this.gestorCamiones.setEnabled(false);
		this.gestorPlantas.setEnabled(true);
		this.gestorInsumos.setEnabled(true);
		this.gestorPedidos.setEnabled(true);
		this.gestorRutas.setEnabled(true);
	}
	public void plantasActivated() {
		this.gestorCamiones.setEnabled(true);
		this.gestorPlantas.setEnabled(false);
		this.gestorInsumos.setEnabled(true);
		this.gestorPedidos.setEnabled(true);
		this.gestorRutas.setEnabled(true);
	}
	public void insumosActivated() {
		this.gestorCamiones.setEnabled(true);
		this.gestorPlantas.setEnabled(true);
		this.gestorInsumos.setEnabled(false);
		this.gestorPedidos.setEnabled(true);
		this.gestorRutas.setEnabled(true);
	}
	public void pedidosActivated() {
		this.gestorCamiones.setEnabled(true);
		this.gestorPlantas.setEnabled(true);
		this.gestorInsumos.setEnabled(true);
		this.gestorPedidos.setEnabled(false);
		this.gestorRutas.setEnabled(true);
	}
	public void RutasActivated() {
		this.gestorCamiones.setEnabled(true);
		this.gestorPlantas.setEnabled(true);
		this.gestorInsumos.setEnabled(true);
		this.gestorPedidos.setEnabled(true);
		this.gestorRutas.setEnabled(false);
	}
	
}
	
