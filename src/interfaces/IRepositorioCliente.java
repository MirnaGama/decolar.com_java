package interfaces;

import classesIniciais.Cliente;
import exceptions.ClienteJaCadastradoException;
import exceptions.ClienteNaoCadastradoException;
import exceptions.CpfCadastradoEmOutroClienteException;
import exceptions.CpfNaoCadastradoException;
import exceptions.CpfNaoInformadoException;
import exceptions.IdadeNaoPermitidaException;
import exceptions.ResultadoNaoEncontradoException;

public interface IRepositorioCliente {

	public void inserir(Cliente cliente) throws ClienteJaCadastradoException, IdadeNaoPermitidaException, CpfNaoInformadoException, CpfCadastradoEmOutroClienteException;
	public void remover(Cliente cliente) throws ClienteNaoCadastradoException;
	public void atualizar(Cliente cliente) throws ClienteNaoCadastradoException;
	public Cliente procurarCliente(String cpf)throws CpfNaoCadastradoException, CpfNaoInformadoException;
	public boolean existe(String cpf);
	public void clienteQueMaisReserva() throws ResultadoNaoEncontradoException;
	public boolean existeCliente(Cliente cliente);
	public void clienteQueMaisVoa() throws ResultadoNaoEncontradoException;

}

