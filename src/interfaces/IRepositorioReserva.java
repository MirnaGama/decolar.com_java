package interfaces;

import classesIniciais.Quarto;
import classesIniciais.Reserva;
import exceptions.DataDeEntradaNaoInformadaException;
import exceptions.DataDeSaidaNaoInformadaException;
import exceptions.ReservaJaCadastradaException;
import exceptions.ReservaNaoCadastradaException;

public interface IRepositorioReserva {

	public void inserir(Reserva reserva) throws ReservaJaCadastradaException;
	public void remover(Reserva reserva) throws ReservaNaoCadastradaException;
	public Reserva procurar(Quarto quarto) throws ReservaNaoCadastradaException;
	public boolean existe(Quarto quarto);
	public void atualizar(Reserva reserva) throws ReservaNaoCadastradaException;

}
