package exceptions;

public class CpfNaoCadastradoException extends Exception {

	public String getMessage() {
		return "Este CPF n�o est� cadastrado.";
	}
}

