package sistGestionLogistica.dominio;

import java.util.Date;
import java.util.List;

import sistGestionLogistica.enums.EstadoPedido;

public class Pedido {
  private Integer idPedido;
  private Integer numOrden;
  private Planta plantaDestino;
  private Date fechaSolicitud;
  private Date fechaEntrega;
  private Double costoEnvio;
  private List<Ruta> rutaAsignada  ;
  private List<ItemDetalle> item; 
  private Camion camionAsignado ;
  private EstadoPedido estado;
  
  

  
  
  
  
}
