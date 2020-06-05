package exceptions;

public class DestinoDoVooNaoInformadoException extends Exception {

	public String getMessage() {
		return "O destino do voo não foi informado.";
	}
}

