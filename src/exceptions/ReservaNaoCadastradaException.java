package exceptions;

public class ReservaNaoCadastradaException extends Exception {

	public String getMessage() {
		return "Reserva n�o cadastrada.";
	}
}
