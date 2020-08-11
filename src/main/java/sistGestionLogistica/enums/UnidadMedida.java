package sistGestionLogistica.enums;

public enum UnidadMedida {
  KG,PIEZA,GR,M,LT,M2,M3,CM3;
	
	public static UnidadMedida valueof(String unidadMedida) {
		UnidadMedida unidad;
		switch(unidadMedida) {
		case "KG": unidad= UnidadMedida.KG;
		break;
		case "PIEZA": unidad = UnidadMedida.PIEZA;
		break;
		case "GR": unidad = UnidadMedida.GR;
		break;
		case "LT": unidad = UnidadMedida.LT;
		break;
		case "M2": unidad = UnidadMedida.M2;
		break;
		case "M3": unidad = UnidadMedida.M3;
		break;
		case "CM3": unidad = UnidadMedida.CM3;
		break;
		default: throw new NumberFormatException("Error de conversion UnidadMedida");
			
		}
		return unidad;
	}
}
