package exceptions;

public class HotelNaoCadastradoException extends Exception {
	
	public String getMessage() {
		return "Hotel n�o encontrado.";
	}
}

