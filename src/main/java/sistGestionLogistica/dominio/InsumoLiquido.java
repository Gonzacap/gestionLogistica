package sistGestionLogistica.dominio;

import sistGestionLogistica.enums.UnidadMedida;

public class InsumoLiquido extends Insumo{
	
	private Double densidad;
	
	 public InsumoLiquido() {
			super();
		}
	
	public InsumoLiquido(int idInsumo, String descripcion, double costo, double precio, UnidadMedida unidadMedida, Double densidad) {
		super(idInsumo, descripcion, costo, precio, unidadMedida);
		this.densidad= densidad;
		// TODO Auto-generated constructor stub
	}

	public InsumoLiquido( String descripcion, double costo, double precio, UnidadMedida unidadMedida, Double densidad) {
		super( descripcion, costo, precio, unidadMedida);
		this.densidad= densidad;
		// TODO Auto-generated constructor stub
	}

	//0.001
	
	public Double getDensidad() {
		return densidad;
	}



	public void setDensidad(double densidad) {
		this.densidad = densidad;
	}



	@Override
	public double pesoPorUnidad() {
		double resultado=0;
		switch(this.unidadMedida) {
		case LT:
			resultado= densidad * 0.001;   //[KG/M3] * [M3/LT] para que quede en [KG/LT] que es el peso por unidad
			break;
		case M3: 
			resultado= densidad;		// la densidad ya esta en [KG/M3]
			break;
		case CM3:
			resultado= densidad * 0.000001; //[KG/M3] * [M3/CM3] para que quede en [KG/CM3] que es el peso por unidad
			break;
		}
		
		return resultado;
	}

}
