package exceptions;

public class CpfCadastradoEmOutroClienteException extends Exception {

	public String getMessage() {
		return "Este CPF está cadastrado em outro cliente.";
	}
}

