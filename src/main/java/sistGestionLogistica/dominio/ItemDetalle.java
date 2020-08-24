package sistGestionLogistica.dominio;

public class ItemDetalle{
	Integer idDetalle;
	Integer idPedido;
    Insumo insumo;
    Integer cantidad;
    
    
    
    
	public Integer getIdDetalle() {
		return idDetalle;
	}
	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
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
