package sistGestionLogistica.servicios;

import java.sql.SQLException;
import java.util.List;
import sistGestionLogistica.dao.ItemDetalleDao;
import sistGestionLogistica.dao.ItemDetalleDaoMysql;
import sistGestionLogistica.dominio.ItemDetalle;

public class ServiceItemDetalle {

		private ItemDetalleDao itemDao = new ItemDetalleDaoMysql();
		
		public Boolean registrarItem(ItemDetalle item) throws SQLException {
			
			return this.itemDao.save(item);
		}
		
		public Boolean actualizarItem(ItemDetalle item) throws SQLException {
			
			return this.itemDao.update(item);
		}
			
		public List<ItemDetalle> buscarPorNumOrden(Integer numOrden) throws SQLException {
			return this.itemDao.buscarNumOrden(numOrden);
		}
		public List<ItemDetalle> buscarTodos() throws SQLException{
			return this.itemDao.buscarTodos();
		}
	
		
		
}
