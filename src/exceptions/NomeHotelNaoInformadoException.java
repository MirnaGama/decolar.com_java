package exceptions;

public class NomeHotelNaoInformadoException extends Exception {
	
	public String getMessage() {
		return "O nome do hotel não foi informado.";
	}
}

