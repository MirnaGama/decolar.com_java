package exceptions;

public class VooJaCadastradoException extends Exception {
	
	public String getMessage() {
		return "Este voo j� foi cadastrado.";
	}
}
