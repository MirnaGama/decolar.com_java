package exceptions;

public class CpfCadastradoEmOutroClienteException extends Exception {

	public String getMessage() {
		return "Este CPF est� cadastrado em outro cliente.";
	}
}

