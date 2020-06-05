package cadastros;

import classesIniciais.Cliente;
import exceptions.ClienteJaCadastradoException;
import exceptions.ClienteNaoCadastradoException;
import exceptions.CpfCadastradoEmOutroClienteException;
import exceptions.CpfNaoCadastradoException;
import exceptions.CpfNaoInformadoException;
import exceptions.IdadeNaoPermitidaException;
import exceptions.ResultadoNaoEncontradoException;
import interfaces.IRepositorioCliente;

public class CadastroClientes {

	private IRepositorioCliente clientes;

	public CadastroClientes(IRepositorioCliente cliente) {
		super();
		this.clientes = cliente;

	}

	public void inserir(Cliente cliente) throws ClienteJaCadastradoException, IdadeNaoPermitidaException,
			CpfNaoInformadoException, CpfCadastradoEmOutroClienteException {
		if (cliente.getIdade() >= 18) {
			this.clientes.inserir(cliente);
		} else {
			throw new IdadeNaoPermitidaException();
		}
	}

	public void remover(Cliente cliente) throws ClienteNaoCadastradoException {
		this.clientes.remover(cliente);
	}

	public void atualizar(Cliente cliente) throws ClienteNaoCadastradoException {
		this.clientes.atualizar(cliente);
	}

	public Cliente procurarCliente(String cpf) throws CpfNaoCadastradoException, CpfNaoInformadoException {
		return this.clientes.procurarCliente(cpf);
	}

	public boolean existe(String cpf) {
		return this.clientes.existe(cpf);
	}

	public boolean existeCliente(Cliente cliente) {
		return this.clientes.existeCliente(cliente);
	}

	public void clienteQueMaisReserva() throws ResultadoNaoEncontradoException {
		this.clientes.clienteQueMaisReserva();
	}

	public void clienteQueMaisVoa() throws ResultadoNaoEncontradoException {
		this.clientes.clienteQueMaisVoa();
	}

}
