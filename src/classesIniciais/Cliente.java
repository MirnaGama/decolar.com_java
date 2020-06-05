package classesIniciais;

import java.io.Serializable;

public class Cliente implements Serializable {
	private String nome;
	private String cpf;
	private int idade;
	private int qtdPassagensCompradas = 0;
	private int qtdReservasFeitas = 0;

	public Cliente(String nome, String cpf, int idade) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;

	}

	public int getQtdReservasFeitas() {
		return this.qtdReservasFeitas;
	}

	public void setQtdReservasFeitas(int qtdReservasFeitas) {
		this.qtdReservasFeitas += qtdReservasFeitas;
	}

	public int getPassagensCompradas() {
		return this.qtdPassagensCompradas;
	}

	public void setQtdPassagensCompradas(int qtdPassagensCompradas) {
		this.qtdPassagensCompradas += qtdPassagensCompradas;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return this.idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String toString() {
		return "Nome: " + getNome() + "\nCPF: " + getCpf() + "\nIdade: " + getIdade();
	}

	public void relatorioCliente() {
		System.out.println("-----Relatório CLIENTE-----");
		System.out.println("");
		System.out.println("Nome: " + getNome());
		System.out.println("CPF: " + getCpf());
		System.out.println("Idade: " + getIdade());
	}

}
