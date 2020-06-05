package classesIniciais;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reserva implements Serializable {

	private Cliente cliente;
	private Hotel hotel;
	private Quarto quarto;
	private int quantidadeDeDias = 0;
	private double valorTotalDaReserva;

	public Reserva(Hotel hotel, Quarto quarto, Cliente cliente) {
		this.cliente = cliente;
		this.quarto = quarto;
		this.hotel = hotel;

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public double getValorTotalDaReserva() {
		double valorDiaria = getHotel().getValorDiaria();
		this.valorTotalDaReserva = (getQuantidadeDeDias() * valorDiaria);
		return valorTotalDaReserva;
	}

	public int getQuantidadeDeDias() {
		this.quantidadeDeDias = getQuarto().getContadorDias();

		return this.quantidadeDeDias;
	}

	public void setQuantidadeDeDias(int quantidadeDeDias) {
		this.quantidadeDeDias = quantidadeDeDias;
	}

}
