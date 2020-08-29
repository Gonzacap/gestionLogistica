package sistGestionLogistica.sistema;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;

import javax.swing.*;

import excepciones.DatosInvalidosException;
import sistGestionLogistica.gui.*;


public class App extends JFrame{
	
	public App() {
	}
	
	public static void main(String[] args) throws DateTimeParseException, NumberFormatException, DatosInvalidosException, SQLException {
		
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
	private JMenu menuMatriz;
	private JMenu menuPageRank;
	private JMenuBar barraMenu;
	private JMenuItem gestorPlantas;
	private JMenuItem gestorCamiones;
	private JMenuItem gestorInsumos;
	private JMenuItem gestorPedidos;
	private JMenuItem gestorRutas;
	private JMenuItem gestorStock;
	private JMenuItem visualizarMatriz;
	private JMenuItem visualizarPageRank;
	
	private PanelPlanta pP;
	private PanelCamion pC;
	private PanelInsumo pI;
	private PanelPedidos pD;
	private PanelRutas pR;
	private PanelStock pS;
	private PanelMatrizCaminoMinimo pM;
	private PanelPageRank ppR;
	private PanelInicio inicio;
	
	//----------------METODOS----------------
	
	
	private void inicializar() throws DateTimeParseException, NumberFormatException, DatosInvalidosException, SQLException {
		
		//this.panel=new JPanel(new GridBagLayout());
		this.menuPlantas = new JMenu("Plantas");
		this.menuCamiones = new JMenu("Camiones");
		this.menuInsumos = new JMenu("Insumos");
		this.menuPedidos = new JMenu("Pedidos");
		this.menuRutas = new JMenu("Rutas");
		this.menuMatriz = new JMenu("Matriz");
		this.menuPageRank = new JMenu("PageRank");
		this.barraMenu = new JMenuBar();
		this.gridbag = new GridBagConstraints();
		this.gestorPlantas = new JMenuItem("Gestionar Plantas");
		this.gestorCamiones = new JMenuItem("Gestionar Camiones");
		this.gestorInsumos =new JMenuItem("Gestionar Insumos");
		this.gestorPedidos =new JMenuItem("Gestionar Pedidos");
		this.gestorRutas =new JMenuItem("Gestionar Rutas");
		this.gestorStock =new JMenuItem("Stocks a Reponer");
		this.visualizarMatriz =new JMenuItem("Visualizar Matriz");
		this.visualizarPageRank =new JMenuItem("Visualizar PageRank");
		
		this.pP = new PanelPlanta();
		this.pI = new PanelInsumo();
		this.pC = new PanelCamion();
		this.pD = new PanelPedidos();
		this.pR = new PanelRutas();
		this.pM = new PanelMatrizCaminoMinimo();
		this.ppR = new PanelPageRank();

		//--------------------------
		
		menuPlantas.add(gestorPlantas);
		menuInsumos.add(gestorInsumos);
		menuInsumos.add(gestorStock);
		menuPedidos.add(gestorPedidos);
		menuRutas.add(gestorRutas);
		menuCamiones.add(gestorCamiones);
		menuMatriz.add(visualizarMatriz);
		menuPageRank.add(visualizarPageRank);
		barraMenu.add(menuPlantas);
		barraMenu.add(menuCamiones);
		barraMenu.add(menuInsumos);
		barraMenu.add(menuPedidos);
		barraMenu.add(menuRutas);
		barraMenu.add(menuMatriz);
		barraMenu.add(menuPageRank);
		this.setJMenuBar(barraMenu);
		
		
		//Pantalla de inicio?---------
		
		this.inicio = new PanelInicio();
		
		this.setTitle("Sistema de Gestion Logistica");
		this.setContentPane(this.inicio);
		this.setVisible(true);
		this.revalidate();
		//this.repaint();
		
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
			try {
				pR.inicializar(this);
			} catch (DateTimeParseException | NumberFormatException | DatosInvalidosException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
			this.revalidate();
			this.repaint();
		});
		
		gestorStock.addActionListener(e->{
			this.pS = new PanelStock();			
			System.out.println("App -> Panel Stocks");
			pS.inicializar(this);
			pS.pantallaInsumosAReponer(this);
			this.revalidate();
			this.repaint();
		});
		gestorStock.addActionListener(e->{
			this.pS = new PanelStock();			
			System.out.println("App -> Panel Stocks");
			pS.inicializar(this);
			pS.pantallaInsumosAReponer(this);
			this.revalidate();
			this.repaint();
		});
		visualizarMatriz.addActionListener(e->{
			
			System.out.println("App -> Visualizar Matriz");
			pM.inicializar(this);			
			this.revalidate();
			this.repaint();
		});
		visualizarPageRank.addActionListener(e->{
			
			System.out.println("App -> Visualizar PageRank");
			ppR.inicializar(this);			
			this.revalidate();
			this.repaint();
		});
		
	}
	
	//-------------otros metodos-------------------
	
	public void mostrarPanelStock(Integer idAux) {
		System.out.println("App -> Panel Stock");
		this.pS = new PanelStock();
		pS.inicializar(this,idAux);
		pS.pantallaStockPlanta(this);
		this.revalidate();
		this.repaint();
	}
	public void volverStock() {
		this.remove(pS);
		this.pS = null;
		pP.inicializar(this);
		this.revalidate();
		this.repaint();
	}
	
	public void camionesActivated() {
		this.visualizarPageRank.setEnabled(true);
		this.visualizarMatriz.setEnabled(true);
		this.gestorCamiones.setEnabled(false);
		this.gestorPlantas.setEnabled(true);
		this.gestorInsumos.setEnabled(true);
		this.gestorPedidos.setEnabled(true);
		this.gestorRutas.setEnabled(true);
		this.gestorStock.setEnabled(true);
	}
	public void plantasActivated() {
		this.visualizarPageRank.setEnabled(true);
		this.visualizarMatriz.setEnabled(true);
		this.gestorCamiones.setEnabled(true);
		this.gestorPlantas.setEnabled(false);
		this.gestorInsumos.setEnabled(true);
		this.gestorPedidos.setEnabled(true);
		this.gestorRutas.setEnabled(true);
		this.gestorStock.setEnabled(true);
	}
	public void insumosActivated() {
		this.visualizarPageRank.setEnabled(true);
		this.visualizarMatriz.setEnabled(true);
		this.gestorCamiones.setEnabled(true);
		this.gestorPlantas.setEnabled(true);
		this.gestorInsumos.setEnabled(false);
		this.gestorPedidos.setEnabled(true);
		this.gestorRutas.setEnabled(true);
		this.gestorStock.setEnabled(true);
	}
	public void pedidosActivated() {
		this.visualizarPageRank.setEnabled(true);
		this.visualizarMatriz.setEnabled(true);
		this.gestorCamiones.setEnabled(true);
		this.gestorPlantas.setEnabled(true);
		this.gestorInsumos.setEnabled(true);
		this.gestorPedidos.setEnabled(false);
		this.gestorRutas.setEnabled(true);
		this.gestorStock.setEnabled(true);
	}
	public void rutasActivated() {
		this.visualizarPageRank.setEnabled(true);
		this.visualizarMatriz.setEnabled(true);
		this.gestorCamiones.setEnabled(true);
		this.gestorPlantas.setEnabled(true);
		this.gestorInsumos.setEnabled(true);
		this.gestorPedidos.setEnabled(true);
		this.gestorRutas.setEnabled(false);
		this.gestorStock.setEnabled(true);
	}
	public void stockActivated() {
		this.visualizarPageRank.setEnabled(true);
		this.visualizarMatriz.setEnabled(true);
		this.gestorCamiones.setEnabled(true);
		this.gestorPlantas.setEnabled(true);
		this.gestorInsumos.setEnabled(true);
		this.gestorPedidos.setEnabled(true);
		this.gestorRutas.setEnabled(true);
		this.gestorStock.setEnabled(false);
	}
	public void stockEnabled() {
		this.visualizarPageRank.setEnabled(false);
		this.visualizarMatriz.setEnabled(false);
		this.gestorCamiones.setEnabled(false);
		this.gestorPlantas.setEnabled(false);
		this.gestorInsumos.setEnabled(false);
		this.gestorPedidos.setEnabled(false);
		this.gestorRutas.setEnabled(false);
		this.gestorStock.setEnabled(false);
	}
	public void matrizActivated() {
		this.visualizarPageRank.setEnabled(true);
		this.visualizarMatriz.setEnabled(false);
		this.gestorCamiones.setEnabled(true);
		this.gestorPlantas.setEnabled(true);
		this.gestorInsumos.setEnabled(true);
		this.gestorPedidos.setEnabled(true);
		this.gestorRutas.setEnabled(true);
		this.gestorStock.setEnabled(true);
	}
	public void pageRankActivated() {
		this.visualizarPageRank.setEnabled(false);
		this.visualizarMatriz.setEnabled(true);
		this.gestorCamiones.setEnabled(true);
		this.gestorPlantas.setEnabled(true);
		this.gestorInsumos.setEnabled(true);
		this.gestorPedidos.setEnabled(true);
		this.gestorRutas.setEnabled(true);
		this.gestorStock.setEnabled(true);
	}
	
	class PanelInicio extends JPanel {
		
		private ImageIcon img;
		
		@Override
		public void paint(Graphics g) {
			img = new ImageIcon(getClass().getResource("PantallaInicio.jpg"));
			Dimension d = getSize();
			g.drawImage(img.getImage(), 0, 0, d.width ,d.height, null);
			this.setOpaque(false);
			super.paintComponent(g);
		}
	}
	
}
	
