package sistGestionLogistica.dominio;

public class ItemDetalle{

	Integer numOrden;
    Insumo insumo;
    Integer cantidad;
    Double precio;
    
    public ItemDetalle(){
    	
    };
    
	public ItemDetalle( Integer numOrden, Insumo insumo, Integer cantidad) {
		
		this.numOrden = numOrden;
		this.insumo = insumo;
		this.cantidad = cantidad;
		this.precio = this.insumo.costo * this.cantidad;
	}
	
	public ItemDetalle(Insumo insumo, Integer cantidad) {
		
		this.insumo = insumo;
		this.cantidad = cantidad;
		this.precio = this.insumo.costo * this.cantidad;
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
	public void setPrecio() {
		this.precio = this.insumo.costo * this.cantidad;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return " numOrden=" + numOrden + ", insumo=" + insumo + ", cantidad="
				+ cantidad + ", precio=" + precio + "]";
	}
    
    
  
   

    
}
