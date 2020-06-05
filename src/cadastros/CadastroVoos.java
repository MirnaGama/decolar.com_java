package cadastros;

import java.util.ArrayList;

import classesIniciais.Cliente;
import classesIniciais.Voo;
import exceptions.DataDoVooNaoInformadaException;
import exceptions.DestinoDoVooNaoInformadoException;
import exceptions.NaoHaVoosCadastradosException;
import exceptions.NumeroDoVooNaoInformadoException;
import exceptions.OrigemDoVooNaoInformadaException;
import exceptions.ResultadoNaoEncontradoException;
import exceptions.VooJaCadastradoException;
import exceptions.VooNaoCadastradoException;
import interfaces.IRepositorioVoo;
import menu.ComprarVooHotel;

public class CadastroVoos {
	private IRepositorioVoo voos;
	private ComprarVooHotel comprarVooHotel;

	public CadastroVoos(IRepositorioVoo voos, ComprarVooHotel comprarVooHotel) {
		super();
		this.voos = voos;
		this.comprarVooHotel = comprarVooHotel;
	}

	public void inserir(Voo voo)
			throws VooJaCadastradoException, NumeroDoVooNaoInformadoException, DataDoVooNaoInformadaException,
			DestinoDoVooNaoInformadoException, OrigemDoVooNaoInformadaException, VooNaoCadastradoException {
		this.voos.inserir(voo);
	}

	public void remover(Voo voo) throws VooNaoCadastradoException {
		this.voos.remover(voo);
	}

	public Voo procurar(String numeroDoVoo) throws VooNaoCadastradoException, NumeroDoVooNaoInformadoException {
		return this.voos.procurar(numeroDoVoo);
	}

	public boolean existe(String numeroDoVoo) {
		return this.voos.existe(numeroDoVoo);
	}

	public void atualizar(Voo voo) throws VooNaoCadastradoException {
		this.voos.atualizar(voo);
	}

	public boolean buscarPorDestino(String destino) {
		return this.voos.buscarPorDestino(destino);
	}

	public void comprarPassagem(Voo voo, int qtd, Cliente cliente) {
		this.comprarVooHotel.comprarPassagem(voo, qtd, cliente);
	}

	public void verVoos() throws NaoHaVoosCadastradosException {
		this.voos.verVoos();
	}

	public void verVooMaisProcurado() throws ResultadoNaoEncontradoException {
		this.voos.verVooMaisProcurado();
	}

	public void verDestinoMaisProcurado() throws ResultadoNaoEncontradoException {
		this.voos.verDestinoMaisProcurado();
	}

	public void verVooComMaiorAvaliacao() throws ResultadoNaoEncontradoException {
		this.voos.verVooComMaiorAvaliacao();
	}

	public void verVooComMenorAvaliacao() throws ResultadoNaoEncontradoException {
		this.voos.verVooComMenorAvaliacao();
	}

	public ArrayList<Voo> getVoos() {
		return this.voos.getVoos();
	}
}
