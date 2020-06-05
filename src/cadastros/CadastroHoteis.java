package cadastros;

import classesIniciais.Hotel;
import exceptions.HotelJaCadastradoException;
import exceptions.HotelNaoCadastradoException;
import exceptions.LocalNaoEncontradoException;
import exceptions.LocalNaoInformadoException;
import exceptions.NaoHaHoteisCadastradosException;
import exceptions.NomeHotelNaoInformadoException;
import exceptions.ResultadoNaoEncontradoException;
import interfaces.IRepositorioHotel;

import java.util.ArrayList;

public class CadastroHoteis {

	private IRepositorioHotel hoteis;


	public CadastroHoteis(IRepositorioHotel hoteis) {
		super();
		this.hoteis = hoteis;
	}

	public void inserir(Hotel hotel) throws HotelJaCadastradoException, NomeHotelNaoInformadoException, LocalNaoInformadoException {
		this.hoteis.inserir(hotel);
	}

	public void remover(Hotel hotel) throws HotelNaoCadastradoException {
		this.hoteis.remover(hotel);
	}

	public void atualizar(Hotel hotel) throws NomeHotelNaoInformadoException, HotelNaoCadastradoException {
		this.hoteis.atualizar(hotel);
	}

	public Hotel procurarHotel(String local, String hotel)
			throws HotelNaoCadastradoException, LocalNaoInformadoException, NomeHotelNaoInformadoException {
		return this.hoteis.procurarHotel(local, hotel);
	}

	public boolean existeLocal(String local) {
		return this.hoteis.existeLocal(local);
	}

	public boolean existeNomeHotel(String nomeHotel) {
		return this.hoteis.existeNome(nomeHotel);
	}
	

	public void buscarDestino(String local) throws LocalNaoInformadoException {
		this.hoteis.buscarDestino(local);
		
	}

	public boolean existeNome(String nome) {
		return this.hoteis.existeNome(nome);
	}

	public boolean hotelDisponivel(String local) {
		return this.hoteis.hotelDisponivel(local);
	}

	public Hotel procurarHotelPorNome(String nome) throws HotelNaoCadastradoException {
		return this.hoteis.procurarHotelPorNome(nome);
	}

	public boolean buscarPorLocal(String destino) throws LocalNaoEncontradoException {
		return this.hoteis.buscarPorLocal(destino);
	}
	
	public void verHoteis() throws NaoHaHoteisCadastradosException {
		this.hoteis.verHoteis();
	}
	
	
	public void verHotelMaisProcurado() throws ResultadoNaoEncontradoException {
		this.hoteis.verHotelMaisProcurado();
	}
	
	public ArrayList<Hotel> getHoteis() {
		return this.hoteis.getHoteis();
	}
	
	public void verLocalMaisProcurado() throws ResultadoNaoEncontradoException {
		this.hoteis.verLocalMaisProcurado();
	}
	
	public void verHotelComMaiorAvaliacao() throws ResultadoNaoEncontradoException {
		this.hoteis.verHotelComMaiorAvaliacao();
	}
	
	public void verHotelComMenorAvaliacao() throws ResultadoNaoEncontradoException {
		this.hoteis.verHotelComMenorAvaliacao();
	}
	
}


