package menu;

import classesIniciais.Cliente;
import classesIniciais.Hotel;
import classesIniciais.Voo;

public class ComprarVooHotel {

	public void comprarPassagem(Voo voo, int quantidade, Cliente c) {
		voo.setQtdPassagensCompradas(quantidade);
		voo.setQtdPassagensDisponiveis(quantidade);
		c.setQtdPassagensCompradas(quantidade);
		voo.setCliente(c);
		System.out.println("Passagem(ns) comprada(s) com sucesso!");
		System.out.println("Total: " + voo.getPrecoPassagem() * quantidade);
	}

	public void reservarQuarto(Hotel hotel, Cliente c) {
		hotel.setReservas(1);
		hotel.setQuantidadeDisponiveis(1);
		hotel.setClientes(c);
		c.setQtdReservasFeitas(1);
	}
}
