package interfaces;

import java.util.ArrayList;

import classesIniciais.Voo;
import exceptions.DataDoVooNaoInformadaException;
import exceptions.DestinoDoVooNaoInformadoException;
import exceptions.NaoHaVoosCadastradosException;
import exceptions.NumeroDoVooNaoInformadoException;
import exceptions.OrigemDoVooNaoInformadaException;
import exceptions.ResultadoNaoEncontradoException;
import exceptions.VooJaCadastradoException;
import exceptions.VooNaoCadastradoException;

public interface IRepositorioVoo {
	public void inserir(Voo voo) throws VooNaoCadastradoException, NumeroDoVooNaoInformadoException, DestinoDoVooNaoInformadoException, OrigemDoVooNaoInformadaException, VooJaCadastradoException, DataDoVooNaoInformadaException;
	public void remover (Voo voo) throws VooNaoCadastradoException;
	public Voo procurar(String numeroDoVoo) throws VooNaoCadastradoException, NumeroDoVooNaoInformadoException;
	public boolean existe(String numeroDoVoo);
	public void atualizar(Voo voo) throws VooNaoCadastradoException;
	public boolean buscarPorDestino(String destino);
	public void verVoos() throws NaoHaVoosCadastradosException;
	public void verDestinoMaisProcurado() throws ResultadoNaoEncontradoException;
	public void verVooMaisProcurado() throws ResultadoNaoEncontradoException;
	public void verVooComMaiorAvaliacao() throws ResultadoNaoEncontradoException;
	public void verVooComMenorAvaliacao() throws ResultadoNaoEncontradoException;
	public ArrayList<Voo> getVoos();
}
