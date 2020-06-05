package exceptions;

public class NumeroDoVooNaoInformadoException extends Exception {

	public String getMessage() {
		return "O número do voo não foi informado.";
	}
}

