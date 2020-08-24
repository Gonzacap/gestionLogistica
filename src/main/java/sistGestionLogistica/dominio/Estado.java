package sistGestionLogistica.dominio;

import java.util.ArrayList;

public class Estado {

	private Planta actual;
	private Double peso;
	private Integer indice;
	private ArrayList<Integer> camino;
	
	public Estado(Planta a, Double P, Integer i){
		this.actual = a;
		this.peso = P;
		this.indice = i;
	}
	
	public Planta getPlanta() {
		return actual;
	}
	
	public Double getPeso() {
		return this.peso;
	}
	
	public Integer getIndice() {
		return this.indice;
	}
	
	public ArrayList<Integer> getCamino(){
		return this.camino;
	}
	
	public void setIndice(Integer i) {
		this.indice = i;
	}
	
}
