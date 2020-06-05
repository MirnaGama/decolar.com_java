package exceptions;

public class DataDeSaidaNaoInformadaException extends Exception {

	public String getMessage() {
		return "Data de saída não informada.";
	}
}

