package cadastros;

import classesIniciais.Quarto;
import classesIniciais.Reserva;
import exceptions.DataDeEntradaNaoInformadaException;
import exceptions.DataDeSaidaNaoInformadaException;
import exceptions.ReservaJaCadastradaException;
import exceptions.ReservaNaoCadastradaException;
import interfaces.IRepositorioReserva;

public class CadastroReservas {

	private IRepositorioReserva reservas;

	public CadastroReservas(IRepositorioReserva reservas) {
		super();
		this.reservas = reservas;
	}

	public void inserir(Reserva reservas) throws ReservaJaCadastradaException, DataDeEntradaNaoInformadaException, DataDeSaidaNaoInformadaException {
		this.reservas.inserir(reservas);
	}

	public void remover(Reserva reservas) throws ReservaNaoCadastradaException {
		this.reservas.remover(reservas);
	}

	public Reserva procurar(Quarto quarto) throws ReservaNaoCadastradaException {
		return this.reservas.procurar(quarto);
	}

	public boolean existe(Quarto quarto) {
		return this.reservas.existe(quarto);
	}

	public void atualizar(Reserva reservas) throws ReservaNaoCadastradaException {
		this.reservas.atualizar(reservas);
	}

}

