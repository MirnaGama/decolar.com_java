package exceptions;

public class DataDoVooNaoInformadaException extends Exception {
	
	public String getMessage() {
		return "A data do voo não foi informada.";
	}
}

