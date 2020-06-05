package exceptions;

public class HotelJaCadastradoException extends Exception {
	
	public String getMessage() {
		return "Hotel já cadastrado.";
	}
}

