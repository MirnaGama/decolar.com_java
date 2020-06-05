package exceptions;

public class ReservaJaCadastradaException extends Exception {

	public String getMessage() {
		return "Reserva já cadastrada.";
	}
}

