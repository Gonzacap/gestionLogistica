package excepciones;

import java.awt.Component;

import javax.swing.JOptionPane;

public class DatosInvalidosException extends Exception {

	public DatosInvalidosException() {
		// TODO Auto-generated constructor stub
	}

	public DatosInvalidosException(String message) {
		super(message);
		
		JOptionPane.showMessageDialog(null,message,"Por favor verifique sus datos.",JOptionPane.ERROR_MESSAGE);
		// TODO Auto-generated constructor stub
	}

	public DatosInvalidosException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DatosInvalidosException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DatosInvalidosException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
