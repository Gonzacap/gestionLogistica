package sistGestionLogistica.comparator;

import java.util.Comparator;

import sistGestionLogistica.dominio.Estado;
import sistGestionLogistica.dominio.Ruta;

public class CompareRutasTiempo implements Comparator<Estado> {

	@Override
	public int compare(Estado e1, Estado e2) {
		// TODO Auto-generated method stub
		
		return (int) (e1.getPesoT() - e2.getPesoT());
	}

	
}