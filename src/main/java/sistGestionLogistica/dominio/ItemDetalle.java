package sistGestionLogistica.dominio;

public class ItemDetalle{
	Integer idDetalle;
	Integer numOrden;
    Insumo insumo;
    Integer cantidad;
    Double precio;
	public ItemDetalle(Integer idDetalle, Integer numOrden, Insumo insumo, Integer cantidad, Double precio) {
		super();
		this.idDetalle = idDetalle;
		this.numOrden = numOrden;
		this.insumo = insumo;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	public Integer getIdDetalle() {
		return idDetalle;
	}
	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
	}
	public Integer getNumOrden() {
		return numOrden;
	}
	public void setNumOrden(Integer numOrden) {
		this.numOrden = numOrden;
	}
	public Insumo getInsumo() {
		return insumo;
	}
	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "ItemDetalle [idDetalle=" + idDetalle + ", numOrden=" + numOrden + ", insumo=" + insumo + ", cantidad="
				+ cantidad + ", precio=" + precio + "]";
	}
    
    
    
   

    
}
