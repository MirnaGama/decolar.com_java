package exceptions;

public class NaoEntrouNoSistemaException extends Exception {

	public String getMessage() {
		return "Não entrou no sistema.";
	}
}

