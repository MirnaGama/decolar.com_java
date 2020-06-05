package exceptions;

public class NaoHaHoteisCadastradosException extends Exception {

	public String getMessage() {
		return "Não há hotéis cadastrados.";
	}
}

