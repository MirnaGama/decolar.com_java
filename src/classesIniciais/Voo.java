package classesIniciais;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Voo implements Serializable {
	private ArrayList<Cliente> clientes;
	private String numeroDoVoo, origem, destino;
	private String data;
	private int qtdPassagensTotal, qtdPassagensDisponiveis;
	private int qtdPassagensCompradas;
	private double precoPassagem;
	private boolean lotado;
	private double notas;
	private double mediaGeral;
	private int frequencia;
	private int destinoMaisProcurado;
	private int buscas;

	public Voo(String numeroDoVoo, int qtdPassagensTotal, String data, String origem, String destino,
			double precoPassagem) {
		super();
		this.clientes = new ArrayList();
		this.numeroDoVoo = numeroDoVoo;
		this.qtdPassagensTotal = qtdPassagensTotal;
		this.qtdPassagensDisponiveis = qtdPassagensTotal;
		this.data = data;
		this.origem = origem;
		this.destino = destino;
		this.precoPassagem = precoPassagem;
		this.lotado = false;
		this.mediaGeral = 0;
		this.frequencia = 0;
		this.buscas = 0;
	}

	public void verPassageiros() {
		if (!clientes.isEmpty()) {
			System.out.println("Lista de passageiros do voo " + this.numeroDoVoo + ":");
			for (Cliente c : clientes) {
				System.out.println(c.getNome());
			}
		} else {
			System.out.println("Não há passageiros neste voo.");
		}
	}

	public void setCliente(Cliente c) {
		this.clientes.add(c);
	}

	public String getNumeroDoVoo() {
		return this.numeroDoVoo;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getOrigem() {
		return this.origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public double getPrecoPassagem() {
		return this.precoPassagem;
	}

	public void setPrecoPassagem(double precoPassagem) {
		this.precoPassagem = precoPassagem;
	}

	public int getQtdPassagensTotal() {
		return qtdPassagensTotal;
	}

	public int getQtdPassagensDisponiveis() {
		return this.qtdPassagensDisponiveis;
	}

	public void setQtdPassagensDisponiveis(int quantidade) {
		this.qtdPassagensDisponiveis -= quantidade;
	}

	public void setFrequencia(int valor) {
		this.frequencia += valor;
	}

	public int getQtdPassagensCompradas() {
		return this.qtdPassagensCompradas;
	}

	public boolean isLotado() {
		return this.lotado;
	}

	public void setLotado() {
		this.lotado = true;
	}

	public void setQtdPassagensCompradas(int quantidade) {
		this.qtdPassagensCompradas += quantidade;
	}

	public double getNotas() {
		return notas;
	}

	public void setNotas(int nota) {
		this.notas += nota;
	}

	public int getFrequencia() {
		return frequencia;
	}

	public void setMediaGeral() {
		mediaGeral = (this.getNotas() / this.getFrequencia());
	}

	public double getMediaGeral() {
		return mediaGeral;
	}

	public int getDestinoMaisProcurado() {
		return this.destinoMaisProcurado;
	}

	public void setDestinoMaisProcurado(int quantidade) {
		this.destinoMaisProcurado += quantidade;
	}

	public void setBuscas(int quantidade) {
		this.buscas += quantidade;
	}

	public int getBuscas() {
		return this.buscas;
	}

	public String toString() {
		String numero = "Numero: " + this.getNumeroDoVoo();
		String data = "Data: " + this.getData();
		String origem_Destino = "Origem/Destino: " + this.getOrigem() + "/" + this.getDestino();
		String valor = "Valor da passagem/pessoa: R$" + this.getPrecoPassagem();
		String qtdPassagensTotal = "Quantidade de passagens total: " + this.getQtdPassagensTotal();
		String qtdPassagensDisponiveis = "Quantidade de passagens disponiveis: " + this.getQtdPassagensDisponiveis();
		String qtdPassagensCompradas = "Quantidade de passagens compradas " + this.getQtdPassagensCompradas();
		String relatorio = numero + "\n" + data + "\n" + origem_Destino + "\n" + valor + "\n" + qtdPassagensTotal + "\n"
				+ qtdPassagensDisponiveis + "\n" + qtdPassagensCompradas;

		return relatorio;
	}

}
