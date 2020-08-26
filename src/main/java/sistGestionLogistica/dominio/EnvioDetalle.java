package sistGestionLogistica.dominio;

import java.util.List;

public class EnvioDetalle {
   
    Integer numOrden;
    Camion camionAsignado;
    Double costoEnvio;
    private List<Ruta> rutaAsignada;
    
    public EnvioDetalle() {};
    
	public EnvioDetalle(Integer numOrden, Camion camionAsignado, Double costoEnvio, List<Ruta> rutaAsignada
			) {
		
		this.numOrden = numOrden;
		this.camionAsignado = camionAsignado;
		this.costoEnvio = costoEnvio(rutaAsignada, camionAsignado.getCostoKM(), camionAsignado.getCostoHora()) ;
		
		
	}
	
	public Integer getNumOrden() {
		return numOrden;
	}
	public void setNumOrden(Integer numOrden) {
		this.numOrden = numOrden;
	}
	public Camion getCamionAsignado() {
		return camionAsignado;
	}
	public void setCamionAsignado(Camion camionAsignado) {
		this.camionAsignado = camionAsignado;
	}
	public Double getCostoEnvio() {
		return costoEnvio;
	}
	public void setCostoEnvio() {
		this.costoEnvio = costoEnvio(rutaAsignada, camionAsignado.getCostoKM(), camionAsignado.getCostoHora()) ;
	}
	public void setCostoEnvio(Double costoEnvio) {
		this.costoEnvio = costoEnvio ;
	}
	public List<Ruta> getRutaAsignada() {
		return rutaAsignada;
	}
	public void setRutaAsignada(List<Ruta> rutaAsignada) {
		this.rutaAsignada = rutaAsignada;
	}

	public Double costoEnvio(List<Ruta> ruta , Double costoPorKm , Double costoPorHora) {
		Double porKm=.0, porHora=.0;
		for(Ruta r : ruta) {
			porKm += costoPorKm * r.getDistancia();
			porHora += costoPorHora * r.getDuracionViaje();
		}
		
		return porKm + porHora;
	}
	
    
    
}
