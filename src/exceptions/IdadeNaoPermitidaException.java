package exceptions;

public class IdadeNaoPermitidaException extends Exception {

	public String getMessage() {
		return "Cliente menor de idade.";
	}
}

