package menu;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class DataMenu {

	public String data() {
		Scanner sc = new Scanner(System.in);
		Calendar c = new GregorianCalendar();
		Calendar hoje = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		boolean result = true;
		boolean result2 = true;
		String dataString = null;
		String hojeString = sdf.format(hoje.getTime()); 

		int diaMaximo = c.getMaximum(GregorianCalendar.DAY_OF_MONTH);
		int diaMinimo = c.getMinimum(GregorianCalendar.DAY_OF_MONTH);

		int mesMaximo = c.getMaximum(GregorianCalendar.MONTH);
		int mesMinimo = c.getMinimum(GregorianCalendar.MONTH);

		while (result2 == true) {

			System.out.println("Insira o dia:");
			String stringDia = sc.nextLine();

			int intDia;
			while (true) {
				if (stringDia.length() <= 2 && stringDia.length() >= 1) {
					try {
						intDia = Integer.parseInt(stringDia);
						c.set(Calendar.DAY_OF_MONTH, intDia);
						break;
					} catch (NumberFormatException e) {
						
					}
				}

				System.out.println(
						"Ops! Entrada (" + stringDia + ") inválida. Digite o dia novamente, apenas algarismos.");
				stringDia = sc.nextLine();
			}

			System.out.println("Insira o mês: ");
			String stringMes = sc.nextLine();

			int intMes;

			while (true) {
				if (stringMes.length() <= 2 && stringMes.length() >= 1) {
					try {
						intMes = Integer.parseInt(stringMes);
						intMes -= 1; 
						c.set(Calendar.MONTH, intMes);
						break;
					} catch (NumberFormatException e) {

					}
				}

				System.out.println(
						"Ops! Entrada (" + stringMes + ") inválida. Digite o mês novamente, apenas algarismos");
				stringMes = sc.nextLine();
			}

			System.out.println("Insira o ano: ");
			String stringAno = sc.nextLine();

			int intAno;
			while (true) {
				if (stringAno.length() == 4) {
					try {
						intAno = Integer.parseInt(stringAno);
						c.set(Calendar.YEAR, intAno);
						break;
					} catch (NumberFormatException e) {

					}
				}

				System.out.println(
						"Ops! Entrada (" + stringAno + ") inválida. Digite o ano novamente, usando 4 algarismos.");
				stringAno = sc.nextLine();
			}

			try {

				if (intDia > diaMaximo || intDia < diaMinimo || intMes > mesMaximo || intMes < mesMinimo
						|| intAno < hoje.get(Calendar.YEAR)) {
					System.out.println("Data inválida. Favor, repetir processo. ");
					result2 = true;

				} else if (c.before(hoje) == true) {

					System.out.println("Ops! Datas apenas a partir de " + hojeString + ". Favor, repetir processo.");
					result2 = true;
				} else {
					c = new GregorianCalendar(intAno, intMes, intDia);
					c.setLenient(false);
					c.getTime();
					dataString = sdf.format(c.getTime());

					result2 = false;

				}

			} catch (java.lang.IllegalArgumentException x) {
				System.out.println("Data inválida. Favor, repetir processo. ");
			}

		}

		return dataString;
	}
}

