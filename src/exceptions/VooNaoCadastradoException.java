package exceptions;

public class VooNaoCadastradoException extends Exception {
	
	public String getMessage() {
		return "Voo n�o cadastrado.";
	}
}

