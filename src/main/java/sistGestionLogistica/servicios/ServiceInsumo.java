package sistGestionLogistica.servicios;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import sistGestionLogistica.dao.InsumoDao;
import sistGestionLogistica.dao.InsumoDaoMysql;
import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.Insumo;

public class ServiceInsumo {
	private InsumoDao insumoDao = new InsumoDaoMysql();

	public ServiceInsumo() {
		// TODO Auto-generated constructor stub
	}
	
	public Insumo crearInsumo(Insumo i) throws SQLException {
				return this.insumoDao.save(i);
		}
	
	public Insumo editarInsumo(Insumo i) throws SQLException {
		return this.insumoDao.update(i);
	}
	
	public void borrar(Integer i) throws SQLException {
		this.insumoDao.borrar(i);
	}
	public List<Insumo> buscarInsumo(Insumo i) throws SQLException {
			
			//inicializamos predicados
			Predicate<Insumo> filtroId = (t) -> (true);
			Predicate<Insumo> filtroDescripcion = (t) -> (true);
		
			
			//Aplicamos Filtros solo si corresponde
			if(i.getIdInsumo()>=0) filtroId = (t) -> (i.getIdInsumo().equals(t.getIdInsumo()));
			if(!i.getDescripcion().equals("")) filtroDescripcion = (t) -> (i.getDescripcion().equals(t.getDescripcion()));
		
			
			
			
			
			return insumoDao.buscarTodos().stream().filter(filtroId)
					.filter(filtroDescripcion).collect(Collectors.toList());
		}

	public Insumo buscarPorId(Integer id) throws SQLException {
		this.insumoDao.buscarPorId(id);
		return null;
	}
   public Integer ultimoId() throws SQLException {
	   return this.insumoDao.ultimoId();
   }
}
