package repositorios;

import java.util.ArrayList;

import classesIniciais.Quarto;
import exceptions.QuartoJaCadastradoException;
import exceptions.QuartoNaoCadastradoException;
import interfaces.IRepositorioQuarto;
import utils.WriteObject;

import java.io.Serializable;
import java.text.ParseException;

public class RepositorioQuartoArrayList implements IRepositorioQuarto,Serializable {

	public ArrayList<Quarto> quartos;
	private String dbName = "repositorioQuartoArrayList";
	private String nomehotel = null;

      public RepositorioQuartoArrayList(String nomehotel) {
	  this.quartos = new ArrayList<>();
	  this.nomehotel = nomehotel;
	  WriteObject wo = new WriteObject();
	  if(!wo.existFile(this.nomehotel+"_"+dbName+".data")) {
       saveData();
	    }
	 loadData();
	 }

	 private void loadData() {
	 WriteObject wo = new WriteObject();
	 Object obj = wo.loadObject(this.nomehotel+"_"+dbName+".data");
	 this.quartos = (ArrayList<Quarto>) obj;
	 }
	
	 private void saveData() {
	 WriteObject wo = new WriteObject();
	 wo.saveObject(this.quartos, this.nomehotel+"_"+dbName+".data");
	 }

	public ArrayList<Quarto> getQuartos() {
		return quartos;
	}

	@Override
	public void inserir(Quarto quarto) throws QuartoJaCadastradoException {
		if (!existe(quarto.getNumeracaoQuarto())) {
		quartos.add(quarto);
		saveData();
		} else {
			throw new QuartoJaCadastradoException();
		}
		
	}


	@Override
	public void atualizar(Quarto quarto) throws QuartoNaoCadastradoException {
		if (existe(quarto.getNumeracaoQuarto())) {
			for (int i = 0; i < quartos.size(); i++) {
				if (this.quartos.get(i).getNumeracaoQuarto() == quarto.getNumeracaoQuarto()) {
					this.quartos.remove(i);
					this.quartos.add(quarto);
				}
			}
		} else {
			throw new QuartoNaoCadastradoException();
		}
	}

	@Override
	public Quarto procurar(int numeracaoQuarto) throws QuartoNaoCadastradoException {
		Quarto q = null;

		if (existe(numeracaoQuarto)) {
			for (int i = 0; i < quartos.size(); i++) {
				if (this.quartos.get(i).getNumeracaoQuarto() == numeracaoQuarto) {
					q = this.quartos.get(i);
				}
			}
		} else {
			throw new QuartoNaoCadastradoException();
		}
		return q;
	}

	@Override
	public boolean existe(int numeracaoQuarto) {
		boolean result = false;

		for (int i = 0; i < quartos.size(); i++) {
			if (quartos.get(i).getNumeracaoQuarto() == numeracaoQuarto) {
				result = true;
			}
		}
		return result;
	}
	
	public ArrayList<Quarto> getRepositorioQuarto() {
		return this.quartos;
	}
	

}
