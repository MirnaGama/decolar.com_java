package exceptions;

public class CpfNaoInformadoException extends Exception {

	public String getMessage() {
		return "O CPF não foi informado.";
	}
}

