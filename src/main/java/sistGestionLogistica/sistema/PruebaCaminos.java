package sistGestionLogistica.sistema;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sistGestionLogistica.dominio.GrafoLogistica;
import sistGestionLogistica.dominio.Planta;
import sistGestionLogistica.dominio.Ruta;
import sistGestionLogistica.servicios.ServiceGrafoLogistica;

public class PruebaCaminos {

	public static void main(String[] args) throws SQLException{
		
		ServiceGrafoLogistica sgl= new ServiceGrafoLogistica();
		GrafoLogistica gf = sgl.inicializarGrafo();
		
		ArrayList<ArrayList<Ruta>> l = new ArrayList<ArrayList<Ruta>>();
		
		Planta p1 = new Planta();
		Planta p2 = new Planta();
		
		for(Planta p: gf.getListaPlanta()) {
			//System.out.println(p.getNombre());
			if(p.getId()==3) {
				p1=p;
			}
			if(p.getId()==2) {
				p2=p;
			}
			
		}
		/*System.out.println("rutas:");
		for(Ruta r: gf.getListaRuta()) {
			System.out.println(r.getIdRuta()+" "+r.getDuracionViaje()+" "+r.getDistancia());
		}*/
		
		System.out.println("planta origen "+p1.getNombre());
		System.out.println("planta destino "+p2.getNombre());
		
		l = sgl.caminoMinimoKm(gf, p1, p2);
		
		//System.out.println("esto es un separador");
		
		for(Ruta r: l.get(0)) {
			System.out.println(r.getIdRuta());
		}
		
	}
}
