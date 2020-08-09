package sistGestionLogistica.dominio;



public class Ruta {
	private Integer idRuta;
	private Double distancia;
	private Double duracionViaje;
	private Double pesoMaximo;
	private Planta plantaOrigen;
	private Planta plantaDestino;
	
	
	

	public Ruta(double distancia, double duracionViaje, double pesoMaximo, Planta plantaOrigen, Planta plantaDestino) {
	
		this.distancia = distancia;
		this.duracionViaje = duracionViaje;
		this.pesoMaximo = pesoMaximo;
		this.plantaOrigen = plantaOrigen;
		this.plantaDestino = plantaDestino;
	}
	
	//Constructor para pruebas
	public Ruta(Integer idRuta,Planta plantaOrigen, Planta plantaDestino) {
		this.idRuta=idRuta;
		this.plantaOrigen = plantaOrigen;
		this.plantaDestino = plantaDestino;
	}




	public Integer getIdRuta() {
		return idRuta;
	}

	public void setIdRuta(int idRuta) {
		this.idRuta = idRuta;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public Double getDuracionViaje() {
		return duracionViaje;
	}

	public void setDuracionViaje(double duracionViaje) {
		this.duracionViaje = duracionViaje;
	}

	public Double getPesoMaximo() {
		return pesoMaximo;
	}

	public void setPesoMaximo(double pesoMaximo) {
		this.pesoMaximo = pesoMaximo;
	}

	public Planta getPlantaOrigen() {
		return plantaOrigen;
	}

	public void setPlantaOrigen(Planta plantaOrigen) {
		this.plantaOrigen = plantaOrigen;
	}

	public Planta getPlantaDestino() {
		return plantaDestino;
	}

	public void setPlantaDestino(Planta plantaDestino) {
		this.plantaDestino = plantaDestino;
	}
	
	@Override
	public String toString() {
		return "( "+this.plantaOrigen.getNombre()+" --> "+this.plantaDestino.getNombre()+" )";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Ruta) && ((Ruta)obj).getPlantaOrigen().equals(this.getPlantaOrigen()) 
				&& ((Ruta)obj).getPlantaDestino().equals(this.getPlantaDestino()); 
	}

}
