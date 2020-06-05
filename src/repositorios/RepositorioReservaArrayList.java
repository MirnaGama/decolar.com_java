package repositorios;

import java.io.Serializable;
import java.util.ArrayList;

import classesIniciais.Quarto;
import classesIniciais.Reserva;
import exceptions.ReservaJaCadastradaException;
import exceptions.ReservaNaoCadastradaException;
import interfaces.IRepositorioReserva;
import utils.WriteObject;

public class RepositorioReservaArrayList implements IRepositorioReserva {
	private ArrayList<Reserva> reservas;
	private String dbName = "repositorioReservaArrayList";

	public RepositorioReservaArrayList() {
		this.reservas = new ArrayList<>();
		WriteObject wo = new WriteObject();
		  if(!wo.existFile(dbName+".data")) {
	       saveData();
		    }
		 loadData();
	}
	
	 private void loadData() {
		 WriteObject wo = new WriteObject();
		 Object obj = wo.loadObject(dbName + ".data");
		 this.reservas = (ArrayList<Reserva>) obj;
		 }
		
		 private void saveData() {
		 WriteObject wo = new WriteObject();
		 wo.saveObject(this.reservas, dbName + ".data");
		 }

	public void inserir(Reserva reserva) throws ReservaJaCadastradaException {
		if (!existe(reserva.getQuarto())) {
			this.reservas.add(reserva);
			saveData();
		} else {
           throw new ReservaJaCadastradaException();
		}
	}

	public void remover(Reserva reserva) throws ReservaNaoCadastradaException {
		if (existe(reserva.getQuarto()) == true) {
			this.reservas.remove(reserva);
		} else {
			throw new ReservaNaoCadastradaException();
		}

	}

	@Override
	public Reserva procurar(Quarto quarto) throws ReservaNaoCadastradaException {
		Reserva r = null;
		if (existe(quarto)) {
			for (int i = 0; i < reservas.size(); i++) {
				if (this.reservas.get(i).getQuarto().equals(quarto)) {
					r = this.reservas.get(i);
				}
			}
		} else {
			throw new ReservaNaoCadastradaException();

		}
		return r;
	}

	@Override
	public boolean existe(Quarto quarto) {
		boolean result = false;

		for (Reserva r : reservas) {
			if (r.getQuarto().equals(quarto)) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public void atualizar(Reserva reserva) throws ReservaNaoCadastradaException {
		if (existe(reserva.getQuarto())) {
			for (int i = 0; i < reservas.size(); i++) {
				if (this.reservas.get(i).getQuarto().equals(reserva.getQuarto())) {
					this.reservas.remove(i);
					this.reservas.add(reserva);
				}
			}
		} else {
			throw new ReservaNaoCadastradaException();
		}
	}

	public ArrayList<Reserva> getReservas() {
		return this.reservas;
	}

}


