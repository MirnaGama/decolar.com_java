package exceptions;

public class OrigemDoVooNaoInformadaException extends Exception {
	
	public String getMessage() {
		return "A origem do voo não foi informada.";
	}
}

