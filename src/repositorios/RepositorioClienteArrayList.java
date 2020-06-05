package repositorios;

import java.util.ArrayList;

import classesIniciais.Cliente;
import classesIniciais.Hotel;
import exceptions.ClienteJaCadastradoException;
import exceptions.ClienteNaoCadastradoException;
import exceptions.CpfCadastradoEmOutroClienteException;
import exceptions.CpfNaoCadastradoException;
import exceptions.CpfNaoInformadoException;
import exceptions.IdadeNaoPermitidaException;
import exceptions.ResultadoNaoEncontradoException;
import interfaces.IRepositorioCliente;
import utils.WriteObject;

import java.util.ArrayList;

public class RepositorioClienteArrayList implements IRepositorioCliente {

	private ArrayList<Cliente> clientes;
	private String dbName = "repositorioClienteArrayList";

	public RepositorioClienteArrayList() {
		clientes = new ArrayList<>();
		WriteObject wo = new WriteObject();
		if (!wo.existFile(dbName + ".data")) {
			saveData();
		}
		loadData();
	}

	public ArrayList<Cliente> getClientes() {
		return this.clientes;
	}

	private void loadData() {
		WriteObject wo = new WriteObject();
		Object obj = wo.loadObject(dbName + ".data");
		this.clientes = (ArrayList<Cliente>) obj;
	}

	private void saveData() {
		WriteObject wo = new WriteObject();
		wo.saveObject(this.clientes, dbName + ".data");
	}

	public void inserir(Cliente cliente) throws ClienteJaCadastradoException, IdadeNaoPermitidaException,
			CpfNaoInformadoException, CpfCadastradoEmOutroClienteException {

		if (!cliente.getCpf().isEmpty()) {
			if (!existeCliente(cliente)) {
				if (existe(cliente.getCpf())) {
					throw new CpfCadastradoEmOutroClienteException();
				}
				if (cliente.getIdade() >= 18) {
					clientes.add(cliente);
	                saveData();
				} else {
					throw new IdadeNaoPermitidaException();
				}
			} else {
				throw new ClienteJaCadastradoException();
			}
			
		} else {
			throw new CpfNaoInformadoException();
		}	
	}

	public void remover(Cliente cliente) throws ClienteNaoCadastradoException {

		if (existe(cliente.getCpf())) {
			this.clientes.remove(cliente);
		} else {
			throw new ClienteNaoCadastradoException();
		}

	}

	public void atualizar(Cliente cliente) throws ClienteNaoCadastradoException {

		if (existe(cliente.getCpf())) {
			for (int i = 0; i < clientes.size(); ++i) {
				if (this.clientes.get(i).getCpf().equalsIgnoreCase(cliente.getCpf())) {

					this.clientes.remove(i);
					this.clientes.add(cliente);

				}
			}
		} else {
			throw new ClienteNaoCadastradoException();
		}
	}

	public Cliente procurarCliente(String cpf) throws CpfNaoCadastradoException, CpfNaoInformadoException {
		Cliente c = null;

		if (existe(cpf)) {
			for (int i = 0; i < clientes.size(); i++) {
				if (this.clientes.get(i).getCpf().equalsIgnoreCase(cpf)) {
					c = this.clientes.get(i);

				}
			}
		}
		if (!existe(cpf)) {

			if (cpf.isEmpty()) {
				throw new CpfNaoInformadoException();
			} else {
				throw new CpfNaoCadastradoException();
			}
		}

		return c;
	}

	public boolean existe(String cpf) {
		boolean resultado = false;

		for (int i = 0; i < this.clientes.size(); i++) {
			if (this.clientes.get(i).getCpf().equals(cpf)) {
				resultado = true;
			}
		}
		return resultado;

	}

	public boolean existeCliente(Cliente cliente) {
		boolean resultado = false;

		for (int i = 0; i < clientes.size(); ++i) {
			if (clientes.get(i) == cliente) {
				resultado = true;
			}
		}
		return resultado;
	}

	public void clienteQueMaisReserva() throws ResultadoNaoEncontradoException {
		Cliente cliente = null;

		for (int i = 0; i < this.clientes.size(); i++) {
			if (i == 0) {
				cliente = this.clientes.get(i);
			} else {
				if (this.clientes.get(i).getQtdReservasFeitas() > cliente.getQtdReservasFeitas()) {
					cliente = clientes.get(i);
				}
			}
		}
		if (cliente.getQtdReservasFeitas() > 0) {
			System.out.println("O cliente que mais faz reservas é: " + cliente.getNome() + ", com o total de "
					+ cliente.getQtdReservasFeitas() + " reserva(s) feita(s).");
		} else {
			throw new ResultadoNaoEncontradoException();
		}
	}

	public void clienteQueMaisVoa() throws ResultadoNaoEncontradoException {
		Cliente cliente = null;

		for (int i = 0; i < this.clientes.size(); i++) {
			if (i == 0) {
				cliente = this.clientes.get(i);
			} else {
				if (this.clientes.get(i).getPassagensCompradas() > cliente.getPassagensCompradas()) {
					cliente = clientes.get(i);
				}
			}
		}
		if (cliente.getPassagensCompradas() > 0) {
			System.out.println("O cliente que mais compra passagens é: " + cliente.getNome() + ", com o total de "
					+ cliente.getPassagensCompradas() + " passagem(ns) comprada(s).");
		} else {
			throw new ResultadoNaoEncontradoException();
		}

	}
}

