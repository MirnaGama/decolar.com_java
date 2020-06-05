package exceptions;

public class HotelNaoCadastradoException extends Exception {
	
	public String getMessage() {
		return "Hotel não encontrado.";
	}
}

