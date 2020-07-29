package sistGestionLogistica.dominio;

import sistGestionLogistica.enums.UnidadMedida;

public class InsumoLiquido extends Insumo{
	
	public InsumoLiquido(int idInsumo, String descripcion, double costo, double precio, UnidadMedida unidadMedida) {
		super(idInsumo, descripcion, costo, precio, unidadMedida);
		// TODO Auto-generated constructor stub
	}

	private double densidad;
   
	public double pesoPorUnidad() {
		
		return densidad;
	}

}
