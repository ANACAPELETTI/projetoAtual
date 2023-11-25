package functions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Classification {
	public static char classifica(List<Double> lastLayer) throws InterruptedException {

		//char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R','S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		char[] alphabet = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		//char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H','I','J'};
		char letraClassificada = ' ';

		int resultado = softMax(lastLayer);

		letraClassificada = alphabet[resultado];

		return letraClassificada;
	}

	public static int softMax(List<Double> x) throws InterruptedException {

	

		double resultado = Collections.max(x);

		int posicao = x.indexOf(resultado);

		// System.out.println("Valor : "+resultado+", Posição: "+posicao);

		return posicao;
	}
}
