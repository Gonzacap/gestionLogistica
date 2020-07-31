package sistGestionLogistica.dominio;

import java.sql.Date;
import java.time.LocalDate;

public class Camion {
	
	/*
	 * -	Patente
-	Modelo (marca y modelo)
-	KM Recorridos
-	Costo por KM
-	Costo por Hora
-	Fecha de compra

	 */
	private Integer id;
	private String patente;
	private String marca;
	private String modelo;
	private Double costoKM;
	private Double costoHora;
	private Integer km;
	private Date fechaCompra;  //NO ME DEJA TRAER EL DATE DE LA TABLA
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Double getCostoKM() {
		return costoKM;
	}
	public void setCostoKM(Double costoKM) {
		this.costoKM = costoKM;
	}
	public Double getCostoHora() {
		return costoHora;
	}
	public void setCostoHora(Double costoHora) {
		this.costoHora = costoHora;
	}
	public Integer getKm() {
		return km;
	}
	public void setKm(Integer km) {
		this.km = km;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date date) {
		this.fechaCompra = date;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	@Override
	public String toString() {
		return "id=" + id + "; patente=" + patente + "; marca=" + marca + "; modelo=" + modelo + "]";
	}
	
	
}
