package classesIniciais;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Quarto implements Serializable {

	public Reserva reserva;
	private Hotel hotel;
	private int numeracaoQuarto;
	private int contadorDias = 0;

	public Quarto(Hotel hotel, int numeracaoQuarto) {
		super();
		this.hotel = hotel;
		this.numeracaoQuarto = numeracaoQuarto;

	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public int getNumeracaoQuarto() {
		return numeracaoQuarto;
	}

	public void setNumeracaoQuarto(int numeracaoQuarto) {
		this.numeracaoQuarto = numeracaoQuarto;
	}

	public int getContadorDias() {
		return contadorDias;
	}

	public void setContadorDias(int contadorDias) {
		this.contadorDias += contadorDias;
	}

	HashMap<String, Boolean> map = new HashMap<String, Boolean>();

	public HashMap<String, Boolean> tornarQuartoOcupado(String dataEntrada, String dataSaida) throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		Date dt1 = df.parse(dataEntrada);
		Date dt2 = df.parse(dataSaida);
		Calendar calend = Calendar.getInstance();
		calend.setTime(dt1);

		for (Date dt = dt1; dt.compareTo(dt2) <= 0;) {

			calend.add(Calendar.DATE, +1);
			dt = calend.getTime();
			String dataFormatada = df.format(calend.getTime());

			this.setContadorDias(1);

			this.map.put(dataFormatada, true);

		}
		return map;
	}

	public HashMap<String, Boolean> tornarQuartoDisponivel(String dataEntrada, String dataSaida) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		Date dt1 = df.parse(dataEntrada);
		Date dt2 = df.parse(dataSaida);
		Calendar calend = Calendar.getInstance();
		calend.setTime(dt1);

		for (Date dt = dt1; dt.compareTo(dt2) <= 0;) {
			calend.add(Calendar.DATE, +1);
			dt = calend.getTime();
			String dataFormatada = df.format(calend.getTime());

			this.map.put(dataFormatada, false);
		}

		return map;
	}

	public Boolean verificarDisponibilidade(String dataEntrada, String dataSaida) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		int contador = 0;
		boolean result = false;

		Date dt1 = df.parse(dataEntrada);
		Date dt2 = df.parse(dataSaida);
		Calendar calend = Calendar.getInstance();
		calend.setTime(dt1);

		for (Date dt = dt1; dt.compareTo(dt2) <= 0;) {

			calend.add(Calendar.DATE, +1);
			dt = calend.getTime();
			String dataFormatada = df.format(calend.getTime());

			Boolean check = this.map.get(dataFormatada);
			if (check == null || !check) {

			} else {

				contador += 1;
			}
		}

		if (contador >= 1) {

		} else if (contador == 0) {

			result = true;
		}

		return result;
	}

}

