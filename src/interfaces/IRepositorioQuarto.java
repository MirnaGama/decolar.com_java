package interfaces;

import java.util.ArrayList;

import classesIniciais.Quarto;
import exceptions.QuartoJaCadastradoException;
import exceptions.QuartoNaoCadastradoException;

public interface IRepositorioQuarto {

	public void inserir(Quarto quarto) throws QuartoJaCadastradoException;
	public void atualizar(Quarto quarto) throws QuartoNaoCadastradoException;
	public Quarto procurar(int numeracaoQuarto) throws QuartoNaoCadastradoException;
	public boolean existe(int numeracaoQuarto);
	public ArrayList<Quarto> getQuartos();
	
}


