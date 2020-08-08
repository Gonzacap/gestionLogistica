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
	public void agregarStockInsumo(StockInsumo s) {
		this.listaStock.add(s);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode()) ;
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planta other = (Planta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
	

}
