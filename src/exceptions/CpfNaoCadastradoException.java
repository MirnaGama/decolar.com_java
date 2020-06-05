package exceptions;

public class CpfNaoCadastradoException extends Exception {

	public String getMessage() {
		return "Este CPF não está cadastrado.";
	}
}

