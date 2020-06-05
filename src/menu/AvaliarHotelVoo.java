package menu;

import java.io.Serializable;

import classesIniciais.Hotel;
import classesIniciais.Voo;

public class AvaliarHotelVoo implements Serializable {

	public void avaliarVoo(Voo voo, int nota) {
		voo.setNotas(nota);
		voo.setFrequencia(1);
		voo.setMediaGeral();
		System.out.println("Agradecemos pela sua avaliação! \nAvaliação geral do voo: " + voo.getMediaGeral());
	}

	public void avaliarHotel(Hotel hotel, int nota2) {
		hotel.setNotas(nota2);
		hotel.setFrequencia(1);
		hotel.setMediaGeral();
		System.out.println("Agradecemos pela sua avaliação! \nAvaliação Geral: " + hotel.getMediaGeral());
	}

}
