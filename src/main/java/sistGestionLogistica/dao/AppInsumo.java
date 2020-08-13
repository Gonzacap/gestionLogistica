package sistGestionLogistica.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

import sistGestionLogistica.dominio.Insumo;
import sistGestionLogistica.dominio.InsumoGeneral;
import sistGestionLogistica.dominio.InsumoLiquido;
import sistGestionLogistica.enums.UnidadMedida;

public class AppInsumo {
	public static void main(String[] args) throws SQLException {
		
		
		InsumoDaoMysql is = new InsumoDaoMysql();
		
		InsumoLiquido insumo1 = new InsumoLiquido(3, "i liquido", 24.0, 56.0, UnidadMedida.CM3, 270.0);
		InsumoLiquido insumo2 = new InsumoLiquido(4, "i2 liquido", 45.0, 16.0, UnidadMedida.KG, 465.0);
		InsumoLiquido insumo3 = new InsumoLiquido(5, "i3 liquido", 65.0, 78.0, UnidadMedida.LT, 153.0);
		
		InsumoGeneral insumo4 = new InsumoGeneral(6, "i4 general", 48.0, 36.0, UnidadMedida.CM3, 970.0);
		InsumoGeneral insumo5 =new InsumoGeneral(7, "i5 general", 46.3, 58.1, UnidadMedida.M3, 571.0);;
		InsumoGeneral insumo6=new InsumoGeneral(8, "i6 general", 48.0, 36.0, UnidadMedida.M2, 123.0);;
		
	
	 
	  
		//List<Insumo> ins = new ArrayList<>();
		//ins =   is.buscarTodos();
		
	//	for(Insumo i: ins) {
		//	System.out.println(i.getIdInsumo());
		//}
		
	    is.borrar(8);
		
	
	}

}
