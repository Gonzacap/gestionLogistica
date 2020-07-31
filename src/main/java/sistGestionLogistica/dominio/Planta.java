package sistGestionLogistica.dominio;

import java.util.ArrayList;
import java.util.List;

public class Planta {
	private Integer id ; 
	private String nombre;
	private List<StockInsumo> listaStock;
	
	public Planta(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.listaStock = new ArrayList<StockInsumo>();
	}
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<StockInsumo> getListaStock() {
		return listaStock;
	}
	public void setListaStock(List<StockInsumo> listaStock) {
		this.listaStock = listaStock;
	}

	
	
	
	

}
