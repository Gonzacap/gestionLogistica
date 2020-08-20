package sistGestionLogistica.dominio;

public class StockInsumo {
		private Integer idStockInsumo;
		private Planta planta;
     	private Insumo insumo;
		private Integer cantidad ;
		private Integer puntoReposicion;
		
		public StockInsumo(Integer idStockInsumo, Planta planta, Insumo insumo, Integer cantidad,
				Integer puntoReposicion) {
			super();
			this.idStockInsumo = idStockInsumo;
			this.planta = planta;
			this.insumo = insumo;
			this.cantidad = cantidad;
			this.puntoReposicion = puntoReposicion;
		}

		public StockInsumo() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Integer getIdStockInsumo() {
			return idStockInsumo;
		}

		public Planta getPlanta() {
			return planta;
		}

		public Insumo getInsumo() {
			return insumo;
		}

		public Integer getCantidad() {
			return cantidad;
		}

		public Integer getPuntoReposicion() {
			return puntoReposicion;
		}

		@Override
		public String toString() {
			return "StockInsumo [idStockInsumo=" + idStockInsumo + ", planta=" + planta + ", insumo=" + insumo
					+ ", cantidad=" + cantidad + ", puntoReposicion=" + puntoReposicion + "]";
		}
		
		
		
		
		
}
