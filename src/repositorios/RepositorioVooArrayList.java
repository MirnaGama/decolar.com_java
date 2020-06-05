package repositorios;

import java.util.ArrayList;

import classesIniciais.Reserva;
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
import utils.WriteObject;

import java.util.ArrayList;

public class RepositorioVooArrayList implements IRepositorioVoo {
	private ArrayList<Voo> voos;
	private String dbName = "repositorioVooArrayList";
	private int nota, mediaGeral;
	private int qtdAvaliacoes = 0;

	public RepositorioVooArrayList() {
		this.voos = new ArrayList<>();
		WriteObject wo = new WriteObject();
		  if(!wo.existFile(dbName+".data")) {
	       saveData();
		    }
		 loadData();
	}
	
	 private void loadData() {
		 WriteObject wo = new WriteObject();
		 Object obj = wo.loadObject(dbName + ".data");
		 this.voos = (ArrayList<Voo>) obj;
		 }
		
		 private void saveData() {
		 WriteObject wo = new WriteObject();
		 wo.saveObject(this.voos, dbName + ".data");
		 }

	@Override
	public void inserir(Voo voo) throws VooJaCadastradoException, NumeroDoVooNaoInformadoException,
			DataDoVooNaoInformadaException, DestinoDoVooNaoInformadoException, OrigemDoVooNaoInformadaException {
		if (!voo.getNumeroDoVoo().isEmpty() && !voo.getData().isEmpty() && !voo.getDestino().isEmpty()
				&& !voo.getOrigem().isEmpty()) {
			if (!existe(voo.getNumeroDoVoo())) {
				this.voos.add(voo);
				saveData();

			} else {
				throw new VooJaCadastradoException();
			}
		}
		if (voo.getNumeroDoVoo().isEmpty()) {
			throw new NumeroDoVooNaoInformadoException();

		} else if (voo.getData().isEmpty()) {
			throw new DataDoVooNaoInformadaException();
		} else if (voo.getDestino().isEmpty()) {
			throw new DestinoDoVooNaoInformadoException();
		} else if (voo.getOrigem().isEmpty()) {
			throw new OrigemDoVooNaoInformadaException();
		}
	}

	@Override
	public void remover(Voo voo) throws VooNaoCadastradoException {
		if (existe(voo.getNumeroDoVoo()) == true) {
			this.voos.remove(voo);

		} else {
			throw new VooNaoCadastradoException();
		}

	}

	@Override
	public Voo procurar(String numeroDoVoo) throws VooNaoCadastradoException, NumeroDoVooNaoInformadoException {
		Voo v = null;
		if (existe(numeroDoVoo)) {
			for (int i = 0; i < voos.size(); i++) {
				if (this.voos.get(i).getNumeroDoVoo().equals(numeroDoVoo)) {
					v = this.voos.get(i);
				}
			}
		}
		if (!existe(numeroDoVoo)) {
			if (numeroDoVoo.isEmpty()) {
				throw new NumeroDoVooNaoInformadoException();

			} else {
				throw new VooNaoCadastradoException();
			}
		}
		return v;
	}

	@Override
	public boolean existe(String numeroDoVoo) {
		boolean result = false;

		for (Voo v : voos) {
			if (v.getNumeroDoVoo().equals(numeroDoVoo)) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public void atualizar(Voo voo) throws VooNaoCadastradoException {
		if (existe(voo.getNumeroDoVoo())) {
			for (int i = 0; i < this.voos.size(); i++) {
				if (this.voos.get(i).getNumeroDoVoo().equals(voo.getNumeroDoVoo())) {
					this.voos.remove(voo);
					this.voos.add(voo);
				}
			}
		} else {
			throw new VooNaoCadastradoException();
		}
	}

	public boolean buscarPorDestino(String destino) {
		String resposta;
		boolean result = false;
		boolean encontrou = false;
		boolean lotado = true;

		for (int i = 0; i < this.voos.size(); i++) {
			if (this.voos.get(i).getDestino().equalsIgnoreCase(destino)) {
				encontrou = true;
				if (this.voos.get(i).getQtdPassagensDisponiveis() > 0) {
					System.out.println(this.voos.get(i).toString());
					System.out.println();
					lotado = false;
				}
			}
		}

		if (encontrou == true) {
			if (lotado == true) {
				System.out.println("Não há voos com passagens disponíveis.");
				encontrou = false;
			}

		}

		return encontrou;
	}

	public ArrayList<Voo> getVoos() {
		return this.voos;
	}

	public void verVoos() throws NaoHaVoosCadastradosException {
		if(!this.voos.isEmpty()) {
			System.out.println("Voos encontrados:");
			for (Voo v : voos) {
				System.out.println(v);
				System.out.println("--------------------------------");
			}
		} else {
			throw new NaoHaVoosCadastradosException();
		}
	}

	public void verVooMaisProcurado() throws ResultadoNaoEncontradoException {
		Voo voo = null;

		for (int i = 0; i < this.voos.size(); i++) {
			if (i == 0) {
				voo = this.voos.get(i);
			} else {
				if (this.voos.get(i).getBuscas() > voo.getBuscas()) {
					voo = this.voos.get(i);
				}
			}
		}
		if (voo.getBuscas() > 0) {
			System.out.println("O voo mais procurado é: " + voo.getNumeroDoVoo());
			System.out.println("Com o total de "+ voo.getBuscas() +" buscas.");
		} else {
			throw new ResultadoNaoEncontradoException();
		}

	}

	public void verDestinoMaisProcurado() throws ResultadoNaoEncontradoException {
		Voo frequencia = null;

		for (int i = 0; i < this.voos.size(); i++) {
			if (i == 0) {
				frequencia = this.voos.get(i);
			} else {
				if (this.voos.get(i).getDestinoMaisProcurado() > frequencia.getDestinoMaisProcurado()) {
					frequencia = this.voos.get(i);
				}
			}
		}
		if (frequencia.getBuscas() > 0) {
			System.out.println("O destino mais procurado é: " + frequencia.getDestino());
			System.out.println("Com o total de " + frequencia.getDestinoMaisProcurado() + " buscas.");
		} else {
			throw new ResultadoNaoEncontradoException();
		}
	}

	public void verVooComMaiorAvaliacao() throws ResultadoNaoEncontradoException {
		Voo melhorAvaliacao = null;
		double media = 0;

		for (int i = 0; i < this.voos.size(); i++) {
			if (i == 0) {
				melhorAvaliacao = this.voos.get(i);
				media = melhorAvaliacao.getMediaGeral();
			} else {
				if (this.voos.get(i).getMediaGeral() > melhorAvaliacao.getMediaGeral()) {
					melhorAvaliacao = this.voos.get(i);
					media = melhorAvaliacao.getMediaGeral();
				}
			}
		}
		if (melhorAvaliacao != null) {
			if (media == 0) {
				System.out.println("Ainda não há avaliações nos voos.");
				return;
			}
			
			System.out.println("O voo com a melhor avaliação é: " + melhorAvaliacao.getNumeroDoVoo());
			System.out.println("Com a média geral: "+media);
		} else {
			throw new ResultadoNaoEncontradoException();
		}
	}

	public void verVooComMenorAvaliacao() throws ResultadoNaoEncontradoException {
		Voo menorAvaliacao = null;
		double media = 0;

		for (int i = 0; i < this.voos.size(); i++) {
			if (i == 0) {
				menorAvaliacao = this.voos.get(i);
				media = menorAvaliacao.getMediaGeral();
			} else {
				if (this.voos.get(i).getMediaGeral() < menorAvaliacao.getMediaGeral()) {
					menorAvaliacao = this.voos.get(i);
					media = menorAvaliacao.getMediaGeral();
				}
			}
		}
		if (menorAvaliacao != null) {
			if (media == 0) {
				System.out.println("Ainda não há avaliações nos voos.");
				return;
			}
			
			System.out.println("O voo com a pior avaliação é: " + menorAvaliacao.getNumeroDoVoo());
			System.out.println("Com a média geral: "+media);
		} else {
			throw new ResultadoNaoEncontradoException();
		}

	}
}

