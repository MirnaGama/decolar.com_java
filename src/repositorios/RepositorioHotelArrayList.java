package repositorios;

import java.util.ArrayList;

import classesIniciais.Hotel;
import exceptions.HotelJaCadastradoException;
import exceptions.HotelNaoCadastradoException;
import exceptions.LocalNaoEncontradoException;
import exceptions.LocalNaoInformadoException;
import exceptions.NaoHaHoteisCadastradosException;
import exceptions.NomeHotelNaoInformadoException;
import exceptions.ResultadoNaoEncontradoException;
import interfaces.IRepositorioHotel;
import utils.WriteObject;

import java.util.ArrayList;

public class RepositorioHotelArrayList implements IRepositorioHotel {

	public ArrayList<Hotel> hoteis;
	private String dbName = "repositorioHotelArrayList";

	public RepositorioHotelArrayList() {
		hoteis = new ArrayList();
		WriteObject wo = new WriteObject();
		if(!wo.existFile(dbName+".data")) {
			saveData();
		}
		loadData();
	}
	
	private void loadData() {
		WriteObject wo = new utils.WriteObject();
		Object obj = wo.loadObject(dbName + ".data");
		this.hoteis = (ArrayList<Hotel>) obj;
	}
	
	private void saveData() {
		WriteObject wo = new WriteObject();
		wo.saveObject(this.hoteis, dbName + ".data");
	}
	

	public ArrayList<Hotel> getHoteis() {
		return this.hoteis;
	}

	@Override
	public void inserir(Hotel hotel)
			throws HotelJaCadastradoException, NomeHotelNaoInformadoException, LocalNaoInformadoException {
		if (!hotel.getNome().isEmpty() && !hotel.getLocal().isEmpty()) {
			if (existeNome(hotel.getNome()) == false) {
				hoteis.add(hotel);
				saveData();
			} else {
				throw new HotelJaCadastradoException();
			}
		}

		if (hotel.getNome().isEmpty()) {
			throw new NomeHotelNaoInformadoException();
		} else if (hotel.getLocal().isEmpty()) {
			throw new LocalNaoInformadoException();
		}
	}

	@Override
	public void remover(Hotel hotel) throws HotelNaoCadastradoException {
		if (existeNome(hotel.getNome()) == true) {
			hoteis.remove(hotel);
		} else {
			System.out.println("Hotel não encontrado.");
			throw new HotelNaoCadastradoException();
		}
	}

	@Override
	public void atualizar(Hotel hotel) throws HotelNaoCadastradoException {
		if (existeNome(hotel.getNome()) == true) {
			for (int i = 0; i < this.hoteis.size(); i++) {
				if (this.hoteis.get(i).getNome().equalsIgnoreCase(hotel.getNome())) {
					this.hoteis.remove(i);
					this.hoteis.add(hotel);
				}
			}
		} else {
			throw new HotelNaoCadastradoException();
		}
	}

	@Override
	public void buscarDestino(String local) throws LocalNaoInformadoException {

		if (local.isEmpty()) { // Local está vazio.
			throw new LocalNaoInformadoException();
		}

		if (existeLocal(local) == true) { // Local existe.
			System.out.println("Lista de hoteis disponíveis em " + local + ":");
			for (int i = 0; i < this.hoteis.size(); i++) {
				if (hotelDisponivel(local) == true) {
					if (this.hoteis.get(i).getLocal().equalsIgnoreCase(local)) {
						System.out.println(this.hoteis.get(i).getNome());
					}
				}
			}
		} else {
			System.out.println("Não existem hoteis disponíveis em " + local + ".");
		}

	}

	@Override
	public boolean existeLocal(String local) {
		boolean result = false;

		for (int i = 0; i < this.hoteis.size(); i++) {
			if (this.hoteis.get(i).getLocal().equalsIgnoreCase(local)) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean existeNome(String nome) {
		boolean result = false;

		for (Hotel h : hoteis) {
			if (h.getNome().equalsIgnoreCase(nome)) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public boolean hotelDisponivel(String local) {
		boolean result = false;

		for (int i = 0; i < this.hoteis.size(); i++) {
			if (this.hoteis.get(i).getLocal().equalsIgnoreCase(local)) {
				if (this.hoteis.get(i).getLotado() == false) {
					result = true;
				}
			}
		}
		return result;
	}

	@Override
	public Hotel procurarHotel(String local, String nome)
			throws HotelNaoCadastradoException, LocalNaoInformadoException, NomeHotelNaoInformadoException {
		Hotel h = null;

		if (existeLocal(local) == true && existeNome(nome) == true) {
			for (int i = 0; i < this.hoteis.size(); i++) {
				if (this.hoteis.get(i).getNome().equalsIgnoreCase(nome)
						&& this.hoteis.get(i).getLocal().equalsIgnoreCase(local)) {
					h = this.hoteis.get(i);
					return h;
				}
			}
		}
		if (!existeLocal(local)) {
			if (local.isEmpty()) {
				throw new LocalNaoInformadoException();
			} else {
				throw new HotelNaoCadastradoException();
			}
		} else if (!existeNome(nome)) {
			if (nome.isEmpty()) {
				throw new NomeHotelNaoInformadoException();
			} else {
				throw new HotelNaoCadastradoException();
			}
		}
		return h;
	}

	public Hotel procurarHotelPorNome(String nome) throws HotelNaoCadastradoException {
		Hotel h = null;

		if (existeNome(nome)) {
			for (int i = 0; i < this.hoteis.size(); i++) {
				if (this.hoteis.get(i).getNome().equalsIgnoreCase(nome)) {
					h = this.hoteis.get(i);
				}
			}
		} else {
			throw new HotelNaoCadastradoException();
		}
		return h;
	}

	public boolean buscarPorLocal(String destino) throws LocalNaoEncontradoException {
		String resposta;
		boolean result = false;
		boolean encontrou = false;
		boolean lotado = true;

		for (int i = 0; i < this.hoteis.size(); i++) {
			if (this.hoteis.get(i).getLocal().equalsIgnoreCase(destino)) {
				encontrou = true;
				if (this.hoteis.get(i).getQuantidadeDisponiveis() > 0) {
					System.out.println(this.hoteis.get(i).toString());
					System.out.println();
					lotado = false;
				}
			}
		}

		if (encontrou == true) {
			if (lotado == true) {
				System.out.println("Não há hotéis com quartos disponíveis.");
				encontrou = false;
			}

		} else {
			throw new LocalNaoEncontradoException();
		}

		return encontrou;
	}

	public void verHoteis() throws NaoHaHoteisCadastradosException {
		if(!this.hoteis.isEmpty()) {
			System.out.println("Hotéis encontrados:");
			for (Hotel h : hoteis) {
				System.out.println(h.toString());
				System.out.println("---------------------------");
			}
		} else {
			throw new NaoHaHoteisCadastradosException();
		}
	}



	public void verHotelMaisProcurado() throws ResultadoNaoEncontradoException {
		Hotel hotel = null;

		for (int i = 0; i < this.hoteis.size(); i++) {
			if (i == 0) {
				hotel = this.hoteis.get(i);
			} else {
				if (this.hoteis.get(i).getBuscas() > hotel.getBuscas()) {
					hotel = hoteis.get(i);
				}
			}
		}
		
		if (hotel.getBuscas() > 0) {
			System.out.println("O hotel mais procurado é: " + hotel.getNome());
			System.out.println("Com o total de "+hotel.getBuscas()+" buscas.");
		} else {
			throw new ResultadoNaoEncontradoException();
		}
	}

	public void verLocalMaisProcurado() throws ResultadoNaoEncontradoException {
		Hotel frequencia = null;

		for (int i = 0; i < this.hoteis.size(); i++) {
			if (i == 0) {
				frequencia = this.hoteis.get(i);
			} else {
				if (this.hoteis.get(i).getLocalMaisProcurado() > frequencia.getLocalMaisProcurado()) {
					frequencia = hoteis.get(i);
				}
			}
		}
		if (frequencia.getLocalMaisProcurado() > 0) {
			System.out.println("O local mais procurado é: " + frequencia.getLocal());
			System.out.println("Com o total de  " + frequencia.getLocalMaisProcurado() + " buscas.");
		} else {
			throw new ResultadoNaoEncontradoException();
		}

	}

	public void verHotelComMaiorAvaliacao() throws ResultadoNaoEncontradoException {
		Hotel maiorAvaliacao = null;
		double media = 0;

		for (int i = 0; i < this.hoteis.size(); i++) {
			if (i == 0) {
				maiorAvaliacao = this.hoteis.get(i);
				media = maiorAvaliacao.getMediaGeral();
			} else {
				if (this.hoteis.get(i).getMediaGeral() > maiorAvaliacao.getMediaGeral()) {
					maiorAvaliacao = hoteis.get(i);
					media = maiorAvaliacao.getMediaGeral();
				}
			}
		}
		if (maiorAvaliacao != null) {
			if (media == 0) {
				System.out.println("Ainda não há avaliações nos hoteis.");
				return;
			}
			
			System.out.println("O hotel com a melhor avaliação é: " + maiorAvaliacao.getNome());
			System.out.println("Com a média geral: "+media);
		} else {
			throw new ResultadoNaoEncontradoException();
		}
	}

	public void verHotelComMenorAvaliacao() throws ResultadoNaoEncontradoException {
		Hotel menorAvaliacao = null;
		double media = 0;

		for (int i = 0; i < this.hoteis.size(); i++) {
			if (i == 0) {
				menorAvaliacao = this.hoteis.get(i);
				media = menorAvaliacao.getMediaGeral();
			} else {
				if (this.hoteis.get(i).getMediaGeral() < menorAvaliacao.getMediaGeral()) {
					menorAvaliacao = hoteis.get(i);
					media = menorAvaliacao.getMediaGeral();
				}
			}
		}
		if (menorAvaliacao != null) {
			if (media == 0) {
				System.out.println("Ainda não há avaliações nos hoteis.");
				return;
			}
			
			System.out.println("O hotel com a pior avaliação é: " + menorAvaliacao.getNome());
			System.out.println("Com a média geral: "+media);
		} else {
			throw new ResultadoNaoEncontradoException();
		}
	}


}
