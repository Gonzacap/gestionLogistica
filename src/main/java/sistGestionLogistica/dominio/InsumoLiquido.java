package sistGestionLogistica.dominio;

import sistGestionLogistica.enums.UnidadMedida;

public class InsumoLiquido extends Insumo{
	
	private double densidad;
	
	public InsumoLiquido(int idInsumo, String descripcion, double costo, double precio, UnidadMedida unidadMedida) {
		super(idInsumo, descripcion, costo, precio, unidadMedida);
		// TODO Auto-generated constructor stub
	}

	
   
	//0.001
	
	@Override
	public double pesoPorUnidad() {
		
		return densidad;
	}

}
