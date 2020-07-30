package sistGestionLogistica.dominio;

import sistGestionLogistica.enums.UnidadMedida;

public class InsumoGeneral extends Insumo {
	
	
     public InsumoGeneral(int idInsumo, String descripcion, double costo, double precio, UnidadMedida unidadMedida) {
		super(idInsumo, descripcion, costo, precio, unidadMedida);
		// TODO Auto-generated constructor stub
	}


	private double peso;


	@Override
	public double pesoPorUnidad() {
		// TODO Auto-generated method stub
		return peso;
	}
     
}
