package menu;

import java.util.Scanner;

import classesIniciais.Cliente;
import classesIniciais.Hotel;
import classesIniciais.Quarto;
import classesIniciais.Voo;
import exceptions.ClienteJaCadastradoException;
import exceptions.CpfCadastradoEmOutroClienteException;
import exceptions.CpfNaoInformadoException;
import exceptions.DataDoVooNaoInformadaException;
import exceptions.DestinoDoVooNaoInformadoException;
import exceptions.HotelJaCadastradoException;
import exceptions.IdadeNaoPermitidaException;
import exceptions.LocalNaoInformadoException;
import exceptions.NaoHaVoosCadastradosException;
import exceptions.NomeHotelNaoInformadoException;
import exceptions.NumeroDoVooNaoInformadoException;
import exceptions.OrigemDoVooNaoInformadaException;
import exceptions.QuartoJaCadastradoException;
import exceptions.QuartoNaoCadastradoException;
import exceptions.VooJaCadastradoException;
import exceptions.VooNaoCadastradoException;
import fachada.Fachada;
import repositorios.RepositorioQuartoArrayList;

public class ClasseTeste {
	public static void main(String[] args) {

		Fachada f = new Fachada();
		
/*		Cliente c1 = new Cliente("Mirna", "1234", 18);
		Cliente c2 = new Cliente("Felipe", "3535", 35);
		Cliente c3 = new Cliente("Carlos", "6140", 20);
		Cliente c4 = new Cliente("Rayana", "1010", 18);
		Cliente c5 = new Cliente("Raisa", "8080", 25);
		Cliente c6 = new Cliente("Bruno", "3434", 27);
		Cliente c7 = new Cliente("Jean", "9090", 45);
		Cliente c8 = new Cliente("James", "9852", 38);
		Cliente c9 = new Cliente("Priscila", "4040", 38);
		Cliente c10 = new Cliente("Anderson", "1222", 31);
		Cliente c11 = new Cliente("Enyllson", "1616", 19);
		Cliente c12 = new Cliente("Michelle", "7070", 20);
		Cliente c13 = new Cliente("Jennifer", "8081", 21);
		Cliente c14 = new Cliente("Carolina", "1011", 23);
		Cliente c15 = new Cliente("Jonathan", "2120", 26);
		Cliente c16 = new Cliente("Naomi", "1450", 28);
		Cliente c17 = new Cliente("Eric", "1617", 45);
		Cliente c18 = new Cliente("Marcela", "4587", 29);
		Cliente c19 = new Cliente("Michael", "7009", 56);
		Cliente c20 = new Cliente("Miria", "1221", 48);
		Cliente c21 = new Cliente("Patricia", "1119", 23);
		Cliente c22 = new Cliente("Pedro", "1003", 24);
		Cliente c23 = new Cliente("Giovanna", "7771", 38);
		Cliente c24 = new Cliente("Heloisa", "4045", 39);
		Cliente c25 = new Cliente("Benjamin", "1235", 40);
		Cliente c26 = new Cliente("Gabriel", "6067", 43);
		Cliente c27 = new Cliente("Pedro", "1523", 49);
		Cliente c28 = new Cliente("Lucas", "8901", 36);
		Cliente c29 = new Cliente("Alice", "5612", 45);
		Cliente c30 = new Cliente("Livia", "1092", 23);
		
		try {
			f.inserirCliente(c1);
			f.inserirCliente(c2);
			f.inserirCliente(c3);
			f.inserirCliente(c4);
			f.inserirCliente(c5);
			f.inserirCliente(c6);
			f.inserirCliente(c7);
			f.inserirCliente(c8);
			f.inserirCliente(c9);
			f.inserirCliente(c10);
			f.inserirCliente(c11);
			f.inserirCliente(c12);
			f.inserirCliente(c13);
			f.inserirCliente(c14);
			f.inserirCliente(c15);
			f.inserirCliente(c16);
		} catch (ClienteJaCadastradoException e) {
			e.getMessage();
		} catch (IdadeNaoPermitidaException e) {
			e.getMessage();
		} catch (CpfNaoInformadoException e) {
			e.getMessage();
		} catch (CpfCadastradoEmOutroClienteException e) {
			e.getMessage();
		}
		
		try {
		f.inserirCliente(c17);
		f.inserirCliente(c18);
		f.inserirCliente(c19);
		f.inserirCliente(c20);
		f.inserirCliente(c21);
		f.inserirCliente(c22);
		f.inserirCliente(c23);
		f.inserirCliente(c24);
		f.inserirCliente(c25);
		f.inserirCliente(c26);
		f.inserirCliente(c27);
		f.inserirCliente(c28);
		f.inserirCliente(c29);
		f.inserirCliente(c30);
	} catch (ClienteJaCadastradoException e) {
		e.getMessage();
	} catch (IdadeNaoPermitidaException e) {
		e.getMessage();
	} catch (CpfNaoInformadoException e) {
		e.getMessage();
	} catch (CpfCadastradoEmOutroClienteException e) {
		e.getMessage();
	}*/
		
		DataMenu dn = new DataMenu();
		Scanner sc = new Scanner(System.in);
		Menu menu = new Menu();
		menu.menuInicial(sc, f, dn);
		
		}
}


