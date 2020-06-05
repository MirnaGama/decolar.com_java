package exceptions;

public class LocalNaoEncontradoException extends Exception {

	public String getMessage() {
		return "Local não encontrado.";
	}
}

