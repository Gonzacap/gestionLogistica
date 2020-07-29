package sistGestionLogistica.dominio;

import sistGestionLogistica.enums.UnidadMedida;

public abstract class Insumo {
	private int idInsumo;
	private String descripcion;
	private double costo;
	private double precio;
	private UnidadMedida unidadMedida;

	public int getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(int idInsumo) {
		this.idInsumo = idInsumo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Insumo(int idInsumo, String descripcion, double costo, double precio, UnidadMedida unidadMedida) {
		
		this.idInsumo = idInsumo;
		this.descripcion = descripcion;
		this.costo = costo;
		this.precio = precio;
		this.unidadMedida = unidadMedida;
	}

	public abstract double pesoPorUnidad();
}
