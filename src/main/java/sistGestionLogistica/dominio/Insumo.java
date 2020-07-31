package sistGestionLogistica.dominio;

import sistGestionLogistica.enums.UnidadMedida;

public abstract class Insumo {
	protected Integer idInsumo;
	protected String descripcion;
	protected Double costo;
	protected Double precio;
	protected UnidadMedida unidadMedida;

	public Integer getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(Integer idInsumo) {
		this.idInsumo = idInsumo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Insumo(Integer idInsumo, String descripcion, Double costo, Double precio, UnidadMedida unidadMedida) {
		super();
		this.idInsumo = idInsumo;
		this.descripcion = descripcion;
		this.costo = costo;
		this.precio = precio;
		this.unidadMedida = unidadMedida;
	}

	public abstract double pesoPorUnidad();
}
