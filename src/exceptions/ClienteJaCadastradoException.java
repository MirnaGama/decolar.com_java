package exceptions;

public class ClienteJaCadastradoException extends Exception {

	public String getMessage() {
		return "Este cliente já foi cadastrado.";
	}
}

