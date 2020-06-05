package classesIniciais;

import repositorios.RepositorioQuartoArrayList;

import java.io.Serializable;
import java.util.ArrayList;

public class Hotel implements Serializable {
	
	private String nome;
	private String local;
	private double valorDiaria;
	private int quantidadeQuartos;
	private int quantidadeDisponiveis;
	private int reservas;
	private boolean lotado = false;
	private double notas;
	private int frequencia;
	private double mediaGeral;
	private RepositorioQuartoArrayList repQuartos;
	private int localMaisProcurado;
	private int qtdNotasDez;
	private int buscas;
	public ArrayList<Cliente> clientes;
	

	public Hotel(String nome, String local, double valorDiaria, int quantidadeQuartos, RepositorioQuartoArrayList repositorioQuartoArrayList) {
		super();
		this.clientes = new ArrayList();
		this.nome = nome;
		this.local = local;
		this.valorDiaria = valorDiaria;
		this.quantidadeQuartos = quantidadeQuartos;
		this.quantidadeDisponiveis = quantidadeQuartos;
		this.repQuartos = new RepositorioQuartoArrayList(nome);
		this.mediaGeral = 0;
		this.buscas = 0;
		this.reservas = 0;
		this.frequencia = 0;
	}
	
	
	public void verHospedes() {
		if(!clientes.isEmpty()) {
			System.out.println("Lista de hóspedes do hotel " + this.nome + ":");
			for(Cliente c : clientes) {
				System.out.println(c.getNome());
			}
		} else {
			System.out.println("Não há hóspedes neste hotel");
		}
	}
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(Cliente c) {
		this.clientes.add(c);
	}


	public int getQtdNotasDez() {
		return qtdNotasDez;
	}

	public void setQtdNotasDez(int qtdNotasDez) {
		this.qtdNotasDez += qtdNotasDez;
	}
	
	public int getLocalMaisProcurado() {
		return localMaisProcurado;
	}

	public void setLocalMaisProcurado(int localMaisProcurado) {
		this.localMaisProcurado += localMaisProcurado;
	}

	public double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public int getQuantidadeQuartos() {
		return quantidadeQuartos;
	}

	public void setQuantidadeQuartos(int quantidadeQuartos) {
		this.quantidadeQuartos = quantidadeQuartos;
	}

	public int getQuantidadeDisponiveis() {
		return quantidadeDisponiveis;
	}

	public void setQuantidadeDisponiveis(int quantidade) {
		this.quantidadeDisponiveis -= quantidade;
	}

	public void setReservas(int quantidade) {
		this.reservas += quantidade;
	}

	public int getReservas() {
		return reservas;
	}

	public void setLotado(boolean lotado) {
		this.lotado = lotado;
	}

	public boolean getLotado() {
		return this.lotado;
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

    public void setFrequencia(int valor) {
		this.frequencia += valor;
	}

    public void setMediaGeral() {
    	this.mediaGeral = (this.getNotas() / this.getFrequencia());
    }
    
	public double getMediaGeral() {
		return this.mediaGeral;
	}

	public void checkOut(int quantidade) {
		this.quantidadeDisponiveis += quantidade;
		this.reservas -= quantidade;
	}

	public RepositorioQuartoArrayList getRepQuartos() {
		return repQuartos;
	}

	public void setRepQuartos(RepositorioQuartoArrayList repQuartos) {
		this.repQuartos = repQuartos;
	}
	
	public void setBuscas(int quantidade) {
		this.buscas += quantidade;
	}
	
	public int getBuscas() {
		return this.buscas;
	}

	public String toString() {
		return "Nome: " + this.getNome() + "\nLocal: " + this.getLocal() + "\nValor por dia: " + this.getValorDiaria()
				+ "\nQuantidade de Quartos: " + this.getQuantidadeQuartos() + "\nQuartos Disponíveis: "
				+ this.getQuantidadeDisponiveis() + "\nQuartos Reservados: " + this.getReservas();
	}
}

