package sistGestionLogistica.sistema;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import sistGestionLogistica.gui.PanelCamion;
import sistGestionLogistica.gui.PanelPlanta;
import sistGestionLogistica.gui.PanelInsumo;


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
	

	}
	
	public GridBagConstraints gridbag;
	private JMenu menuPlantas;
	private JMenu menuCamiones;
	private JMenu menuInsumos;
	private JMenuBar barraMenu;
	private JMenuItem gestorPlantas;
	private JMenuItem gestorCamiones;
	private JMenuItem gestorInsumos;
	
	private PanelPlanta pP;
	private PanelCamion pC;
	private PanelInsumo pI;
	 
	
	//----------------METODOS----------------
	
	
	private void inicializar() {
		
		//this.panel=new JPanel(new GridBagLayout());
		this.menuPlantas = new JMenu("Plantas");
		this.menuCamiones = new JMenu("Camiones");
		this.menuInsumos = new JMenu("Insumos");
		this.barraMenu = new JMenuBar();
		this.gridbag = new GridBagConstraints();
		this.gestorPlantas = new JMenuItem("Gestionar plantas");
		this.gestorCamiones = new JMenuItem("Gestionar camiones");
		this.gestorInsumos=new JMenuItem("Gestionar insumos");
		
		this.pC= new PanelCamion();
		this.pP = new PanelPlanta();
		this.pI = new PanelInsumo();
		
		pC.inicializar(this);
		pP.inicializar(this);
		pI.inicializar(this);
		
		menuPlantas.add(gestorPlantas);
		menuCamiones.add(gestorCamiones);
		menuInsumos.add(gestorInsumos);
		barraMenu.add(menuPlantas);
		barraMenu.add(menuCamiones);
		barraMenu.add(menuInsumos);
		this.setJMenuBar(barraMenu);
		
		
		//Pantalla de inicio?---------
		
		/*JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.red);
		panel.add(new Label("Sistema de Gestion Logistica"));
		
		this.setContentPane(panel);
		this.revalidate();
		this.repaint();*/
		
		JOptionPane.showMessageDialog(null, "¡Bienvenido!");
		
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
		
		
	}
	
	//-------------otros metodos-------------------
	
	public void camionesActivated() {
		this.gestorCamiones.setEnabled(false);
		this.gestorPlantas.setEnabled(true);
		this.gestorInsumos.setEnabled(true);
	}
	public void plantasActivated() {
		this.gestorCamiones.setEnabled(true);
		this.gestorPlantas.setEnabled(false);
		this.gestorInsumos.setEnabled(true);
	}
	public void insumosActivated() {
		this.gestorCamiones.setEnabled(true);
		this.gestorPlantas.setEnabled(true);
		this.gestorInsumos.setEnabled(false);
	}
	
}
	
