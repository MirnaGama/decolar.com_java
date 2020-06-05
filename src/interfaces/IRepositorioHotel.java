package interfaces;

import java.util.ArrayList;

import classesIniciais.Hotel;
import exceptions.HotelJaCadastradoException;
import exceptions.HotelNaoCadastradoException;
import exceptions.LocalNaoEncontradoException;
import exceptions.LocalNaoInformadoException;
import exceptions.NaoHaHoteisCadastradosException;
import exceptions.NomeHotelNaoInformadoException;
import exceptions.ResultadoNaoEncontradoException;

public interface IRepositorioHotel {
	public void inserir(Hotel hotel) throws HotelJaCadastradoException, NomeHotelNaoInformadoException, LocalNaoInformadoException;
	public void remover(Hotel hotel) throws HotelNaoCadastradoException;
	public void atualizar(Hotel hotel) throws NomeHotelNaoInformadoException, HotelNaoCadastradoException;
	public void buscarDestino(String local) throws LocalNaoInformadoException;
	public boolean existeLocal(String local);
	public boolean existeNome(String nome);
	public boolean hotelDisponivel(String local);
	public Hotel procurarHotel(String local, String nome) throws HotelNaoCadastradoException, LocalNaoInformadoException, NomeHotelNaoInformadoException;
	public Hotel procurarHotelPorNome(String nome) throws HotelNaoCadastradoException;
	public boolean buscarPorLocal(String local) throws LocalNaoEncontradoException;
	public void verLocalMaisProcurado() throws ResultadoNaoEncontradoException;
	public void verHotelComMaiorAvaliacao() throws ResultadoNaoEncontradoException;
	public ArrayList<Hotel> getHoteis();
	public void verHoteis() throws NaoHaHoteisCadastradosException;
	public void verHotelComMenorAvaliacao() throws ResultadoNaoEncontradoException;
	public void verHotelMaisProcurado() throws ResultadoNaoEncontradoException;
}

