package exceptions;

public class CpfNaoInformadoException extends Exception {

	public String getMessage() {
		return "O CPF n�o foi informado.";
	}
}

