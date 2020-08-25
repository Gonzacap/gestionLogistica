package sistGestionLogistica.dominio;

import java.util.ArrayList;

public class Estado {

	private Planta actual;
	private Double peso;
	private Double acumulado;
	private Ruta rutaEntrante;
	private ArrayList<Ruta> camino;
	
	public Estado(Planta a, Double p, Double d, Ruta r){
		this.actual = a;
		this.peso = p;
		this.acumulado = d;
		this.rutaEntrante = r;
	}
	
	//constructor nodo inicial
	public Estado(Planta a, Double p, Double d){
		this.actual = a;
		this.peso = p;
		this.acumulado = d;
		this.rutaEntrante = null;
		this.camino = new ArrayList<>();
	}
	
	public Planta getActual() {
		return actual;
	}

	public void setActual(Planta actual) {
		this.actual = actual;
	}

	public Double getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(Double pesoAculado) {
		this.acumulado = pesoAculado;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public void setCamino(ArrayList<Ruta> camino, Ruta r) {
		this.camino = null;
		this.camino = new ArrayList<>();
		this.camino = camino;
		this.camino.add(r);
	}
	
	public Planta getPlanta() {
		return actual;
	}
	
	public Double getPeso() {
		return this.peso;
	}
	
	public ArrayList<Ruta> getCamino(){
		return this.camino;
	}
	
	public Ruta getRuta() {
		return this.rutaEntrante;
	}
	
}
