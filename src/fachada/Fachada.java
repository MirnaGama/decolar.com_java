package fachada;

import java.util.ArrayList;

import cadastros.CadastroClientes;
import cadastros.CadastroHoteis;
import cadastros.CadastroReservas;
import cadastros.CadastroVoos;
import classesIniciais.Cliente;
import classesIniciais.Hotel;
import classesIniciais.Quarto;
import classesIniciais.Reserva;
import classesIniciais.Voo;
import exceptions.ClienteJaCadastradoException;
import exceptions.ClienteNaoCadastradoException;
import exceptions.CpfCadastradoEmOutroClienteException;
import exceptions.CpfNaoCadastradoException;
import exceptions.CpfNaoInformadoException;
import exceptions.DataDeEntradaNaoInformadaException;
import exceptions.DataDeSaidaNaoInformadaException;
import exceptions.DataDoVooNaoInformadaException;
import exceptions.DestinoDoVooNaoInformadoException;
import exceptions.HotelJaCadastradoException;
import exceptions.HotelNaoCadastradoException;
import exceptions.IdadeNaoPermitidaException;
import exceptions.LocalNaoEncontradoException;
import exceptions.LocalNaoInformadoException;
import exceptions.NaoHaHoteisCadastradosException;
import exceptions.NaoHaVoosCadastradosException;
import exceptions.NomeHotelNaoInformadoException;
import exceptions.NumeroDoVooNaoInformadoException;
import exceptions.OrigemDoVooNaoInformadaException;
import exceptions.QuartoJaCadastradoException;
import exceptions.QuartoNaoCadastradoException;
import exceptions.ReservaJaCadastradaException;
import exceptions.ReservaNaoCadastradaException;
import exceptions.ResultadoNaoEncontradoException;
import exceptions.VooJaCadastradoException;
import exceptions.VooNaoCadastradoException;
import interfaces.IRepositorioCliente;
import interfaces.IRepositorioHotel;
import interfaces.IRepositorioReserva;
import interfaces.IRepositorioVoo;
import menu.AvaliarHotelVoo;
import menu.ComprarVooHotel;
import repositorios.RepositorioClienteArrayList;
import repositorios.RepositorioHotelArrayList;
import repositorios.RepositorioQuartoArrayList;
import repositorios.RepositorioVooArrayList;

public class Fachada {

	private static Fachada instance;
	private IRepositorioCliente repClientes;
	private IRepositorioHotel repHoteis;
	private IRepositorioVoo repVoos;
	private IRepositorioReserva repReservas;
	private ComprarVooHotel cvh;
	private AvaliarHotelVoo ahv;
	private CadastroClientes cadastroClientes;
	private CadastroHoteis cadastroHoteis;
	private CadastroVoos cadastroVoos;
	private CadastroReservas cadastroReservas;

	public static Fachada getInstance() {
		if (instance == null) {
			instance = new Fachada();
		}

		return instance;

	}

	public Fachada() {
		this.repClientes = new RepositorioClienteArrayList();
		this.repHoteis = new RepositorioHotelArrayList();
		this.repVoos = new RepositorioVooArrayList();
		this.ahv = new AvaliarHotelVoo();
		this.cvh = new ComprarVooHotel();
		this.cadastroClientes = new CadastroClientes(repClientes);
		this.cadastroHoteis = new CadastroHoteis(repHoteis);
		this.cadastroVoos = new CadastroVoos(repVoos, cvh);
		this.cadastroReservas = new CadastroReservas(repReservas);
	}

	public void inserirCliente(Cliente cliente) throws ClienteJaCadastradoException, IdadeNaoPermitidaException,
			CpfNaoInformadoException, CpfCadastradoEmOutroClienteException {
		this.cadastroClientes.inserir(cliente);
	}

	public void removerCliente(Cliente cliente) throws ClienteNaoCadastradoException {
		this.cadastroClientes.remover(cliente);
	}

	public void atualizarCliente(Cliente cliente) throws ClienteNaoCadastradoException {
		this.cadastroClientes.atualizar(cliente);
	}

	public Cliente procurarCliente(String cpf) throws CpfNaoCadastradoException, CpfNaoInformadoException {
		return this.cadastroClientes.procurarCliente(cpf);
	}

	public boolean existeCpf(String cpf) {
		return this.cadastroClientes.existe(cpf);
	}

	public boolean existeCliente(Cliente cliente) {
		return this.cadastroClientes.existeCliente(cliente);
	}

	public void inserirHotel(Hotel hotel)
			throws HotelJaCadastradoException, NomeHotelNaoInformadoException, LocalNaoInformadoException {
		this.cadastroHoteis.inserir(hotel);
	}

	public void removerHotel(Hotel hotel) throws HotelNaoCadastradoException {
		this.cadastroHoteis.remover(hotel);
	}

	public void atualizarHotel(Hotel hotel) throws NomeHotelNaoInformadoException, HotelNaoCadastradoException {
		this.cadastroHoteis.atualizar(hotel);
	}

	public void buscarDestino(String local) throws LocalNaoInformadoException {
		this.cadastroHoteis.buscarDestino(local);
	}

	public boolean existeLocal(String local) {
		return this.cadastroHoteis.existeLocal(local);
	}

	public boolean existeNome(String nome) {
		return this.cadastroHoteis.existeNome(nome);
	}

	public boolean hotelDisponivel(String local) {
		return this.cadastroHoteis.hotelDisponivel(local);
	}

	public Hotel procurarHotel(String local, String nome)
			throws HotelNaoCadastradoException, LocalNaoInformadoException, NomeHotelNaoInformadoException {
		return this.cadastroHoteis.procurarHotel(local, nome);
	}

	public Hotel procurarHotelPorNome(String nome) throws HotelNaoCadastradoException {
		return this.cadastroHoteis.procurarHotelPorNome(nome);
	}

	public boolean buscarPorLocal(String destino) throws LocalNaoEncontradoException {
		return this.cadastroHoteis.buscarPorLocal(destino);
	}

	public void inserirVoo(Voo voo)
			throws VooJaCadastradoException, NumeroDoVooNaoInformadoException, DestinoDoVooNaoInformadoException,
			OrigemDoVooNaoInformadaException, DataDoVooNaoInformadaException, VooNaoCadastradoException {
		this.cadastroVoos.inserir(voo);
	}

	public void removerVoo(Voo voo) throws VooNaoCadastradoException {
		this.cadastroVoos.remover(voo);
	}

	public Voo procurarVoo(String numeroDoVoo) throws VooNaoCadastradoException, NumeroDoVooNaoInformadoException {
		return this.cadastroVoos.procurar(numeroDoVoo);
	}

	public boolean existeNumeroDoVoo(String numeroDoVoo) {
		return this.cadastroVoos.existe(numeroDoVoo);
	}

	public void atualizarVoo(Voo voo) throws VooNaoCadastradoException {
		this.cadastroVoos.atualizar(voo);
	}

	public boolean buscarPorDestino(String destino) {
		return this.cadastroVoos.buscarPorDestino(destino);
	}

	public void comprarPassagem(Voo voo, int quantidade, Cliente c) {
		this.cvh.comprarPassagem(voo, quantidade, c);
	}

	public void reservarQuarto(Hotel hotel, Cliente c) {
		this.cvh.reservarQuarto(hotel, c);
	}

	public void avaliarHotel(Hotel hotel, int nota) {
		this.ahv.avaliarHotel(hotel, nota);
	}

	public void avaliarVoo(Voo voo, int nota) {
		this.ahv.avaliarVoo(voo, nota);
	}

	public void verVoos() throws NaoHaVoosCadastradosException {
		this.cadastroVoos.verVoos();
	}

	public void verHoteis() throws NaoHaHoteisCadastradosException {
		this.cadastroHoteis.verHoteis();
	}

	public void clienteQueMaisReserva() throws ResultadoNaoEncontradoException {
		this.cadastroClientes.clienteQueMaisReserva();
	}

	public void clienteQueMaisVoa() throws ResultadoNaoEncontradoException {
		this.cadastroClientes.clienteQueMaisVoa();
	}

	public void verHotelMaisProcurado() throws ResultadoNaoEncontradoException {
		this.cadastroHoteis.verHotelMaisProcurado();
	}

	public void verVooMaisProcurado() throws ResultadoNaoEncontradoException {
		this.cadastroVoos.verVooMaisProcurado();
	}

	public void inserirReserva(Reserva reserva)
			throws ReservaJaCadastradaException, DataDeEntradaNaoInformadaException, DataDeSaidaNaoInformadaException {
		this.cadastroReservas.inserir(reserva);
	}

	public void removerReserva(Reserva reserva) throws ReservaNaoCadastradaException {
		this.cadastroReservas.remover(reserva);
	}

	public boolean existeReserva(Quarto quarto) {
		return this.cadastroReservas.existe(quarto);
	}

	public Reserva procurarReserva(Quarto quarto) throws ReservaNaoCadastradaException {
		return this.cadastroReservas.procurar(quarto);
	}

	public void atualizarReserva(Reserva reserva) throws ReservaNaoCadastradaException {
		this.cadastroReservas.atualizar(reserva);
	}

	public ArrayList<Hotel> getHoteis() {
		return this.cadastroHoteis.getHoteis();
	}

	public void verDestinoMaisProcurado() throws ResultadoNaoEncontradoException {
		this.cadastroVoos.verDestinoMaisProcurado();
	}

	public void verLocalMaisProcurado() throws ResultadoNaoEncontradoException {
		this.cadastroHoteis.verLocalMaisProcurado();
	}

	public void verHotelComMaiorAvaliacao() throws ResultadoNaoEncontradoException {
		this.cadastroHoteis.verHotelComMaiorAvaliacao();
	}

	public void verVooComMaiorAvaliacao() throws ResultadoNaoEncontradoException {
		this.cadastroVoos.verVooComMaiorAvaliacao();
	}

	public void verVooComMenorAvaliacao() throws ResultadoNaoEncontradoException {
		this.cadastroVoos.verVooComMenorAvaliacao();
	}

	public void verHotelComMenorAvaliacao() throws ResultadoNaoEncontradoException {
		this.cadastroHoteis.verHotelComMenorAvaliacao();
	}

	public ArrayList<Voo> getVoos() {
		return this.cadastroVoos.getVoos();
	}
}

