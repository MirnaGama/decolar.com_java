package exceptions;

public class NaoHaVoosCadastradosException extends Exception {

	public String getMessage() {
		return "N�o h� voos cadastrados.";
	}
}

