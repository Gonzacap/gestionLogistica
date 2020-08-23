package sistGestionLogistica.dominio;

import java.util.ArrayList;

public class Estado {

	private Ruta actual;
	private Double pesoK;
	private Double pesoT;
	private ArrayList<Integer> camino;
	
	public Estado(Ruta a, Double K, Double T){
		this.actual = a;
		this.pesoK = K;
		this.pesoT = T;
	}
	
	public Double getPesoT() {
		return this.pesoT;
	}
	public Double getPesoK() {
		return this.pesoK;
	}
}
