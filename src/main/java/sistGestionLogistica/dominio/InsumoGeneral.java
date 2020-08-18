package sistGestionLogistica.dominio;

import sistGestionLogistica.enums.UnidadMedida;

public class InsumoGeneral extends Insumo {
	private Double peso;
	
	
     public InsumoGeneral() {
		super();
	}





	public InsumoGeneral(int idInsumo, String descripcion, double costo, double precio, UnidadMedida unidadMedida, double peso) {
		super(idInsumo, descripcion, costo, precio, unidadMedida);
		this.peso = peso;
	}
	public InsumoGeneral( String descripcion, double costo, double precio, UnidadMedida unidadMedida, double peso) {
		super( descripcion, costo, precio, unidadMedida);
		this.peso = peso;
	}

	


	public Double getPeso() {
		return peso;
	}





	public void setPeso(Double peso) {
		this.peso = peso;
	}





	@Override
	public double pesoPorUnidad() {
		return peso;
	}
     
}
