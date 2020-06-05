package exceptions;

public class QuartoJaCadastradoException extends Exception {

	public String getMessage() {
		return "Quarto já cadastrado.";
	}
}
