package sistGestionLogistica.dominio;

public class ItemDetalle{
    Insumo insumo;
    Integer cantidad;
    
    
    
	public ItemDetalle(Insumo insumo, Integer cantidad) {
		this.insumo = insumo;
		this.cantidad = cantidad;
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
    
}
