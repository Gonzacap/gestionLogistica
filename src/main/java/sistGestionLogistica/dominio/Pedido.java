package sistGestionLogistica.dominio;

import java.util.Date;

import sistGestionLogistica.enums.EstadoPedido;

public class Pedido {
  private Integer idPedido;
  private Integer numOrden;
  //private Planta plantaDestino;
  private Date fechaSolicitud;
  private Date fechaEntrega;
  private Double costoEnvio;
//private Ruta rutaAsignada  es una lista de Ruta? mmm ;
  //private List<Insumo> 
  private Camion camionAsignado ;
  private EstadoPedido estado;
  
  

  
  
  
  
}
