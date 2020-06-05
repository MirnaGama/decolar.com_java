package exceptions;

public class ResultadoNaoEncontradoException extends Exception {
	
	public String getMessage() {
		return "Resultado não encontrado.";
	}
}

