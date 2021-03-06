package sistGestionLogistica.dominio;

import java.time.LocalDate;
import java.util.List;

import sistGestionLogistica.enums.EstadoPedido;

public class Pedido {

  private Integer numOrden;
  private Planta plantaDestino;
  private LocalDate fechaSolicitud;
  private LocalDate fechaEntrega;
  private List<ItemDetalle> item; 
  private EstadoPedido estado;
  private EnvioDetalle envio;
  
  public Pedido() {
	  
  };
  



public Pedido(Integer numOrden, Planta plantaDestino, LocalDate fechaSolicitud, LocalDate fechaEntrega,
		List<ItemDetalle> item, EstadoPedido estado) {
	super();
	this.numOrden = numOrden;
	this.plantaDestino = plantaDestino;
	this.fechaSolicitud = fechaSolicitud;
	this.fechaEntrega = fechaEntrega;
	this.item = item;
	this.estado = estado;
}




public Integer getNumOrden() {
	return numOrden;
}



public void setNumOrden(Integer numOrden) {
	this.numOrden = numOrden;
}



public Planta getPlantaDestino() {
	return plantaDestino;
}



public void setPlantaDestino(Planta plantaDestino) {
	this.plantaDestino = plantaDestino;
}



public LocalDate getFechaSolicitud() {
	return fechaSolicitud;
}



public void setFechaSolicitud(LocalDate fechaSolicitud) {
	this.fechaSolicitud = fechaSolicitud;
}



public LocalDate getFechaEntrega() {
	return fechaEntrega;
}



public void setFechaEntrega(LocalDate fechaEntrega) {
	this.fechaEntrega = fechaEntrega;
}


public List<ItemDetalle> getItem() {
	return item;
}



public void setItem(List<ItemDetalle> item) {
	this.item = item;
}



public EstadoPedido getEstado() {
	return estado;
}



public void setEstado(EstadoPedido estado) {
	this.estado = estado;
}




public EnvioDetalle getEnvio() {
	return this.envio;
}




public void setEnvio(EnvioDetalle envio) {
	this.envio = envio;
}

  
}
