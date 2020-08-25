package sistGestionLogistica.comparator;

import java.util.Comparator;

import sistGestionLogistica.dominio.Camion;
import sistGestionLogistica.dominio.Estado;

public class CompareCamion implements Comparator<Camion> {

	@Override
	public int compare(Camion o1, Camion o2) {
		// TODO Auto-generated method stub
		return (o1.getKm() - o2.getKm());
	}

}
