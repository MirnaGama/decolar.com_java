package exceptions;

public class ClienteNaoCadastradoException extends Exception {

	public String getMessage() {
		return "Cliente n�o cadastrado.";
	}
}

