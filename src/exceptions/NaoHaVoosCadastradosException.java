package exceptions;

public class NaoHaVoosCadastradosException extends Exception {

	public String getMessage() {
		return "Não há voos cadastrados.";
	}
}

