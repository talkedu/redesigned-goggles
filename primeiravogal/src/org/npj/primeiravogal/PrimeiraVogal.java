package org.npj.primeiravogal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PrimeiraVogal {

	public static char firstChar(Stream input) {

		//Conjunto auxiliar para guardar as vogais encontradas
		Set<Character> vogaisEncontradas = new HashSet<Character>();
		
		//Variável para guardar último caractere lido
		Character ultimoCaracter = null;
		
		//Flag que determine se até então apareceu alguma consoante que tenha uma vogal como antecessora 
		Boolean temConsoanteComVogalAntecessora = false;

		while (input.hasNext()) {
			Character caractereAtual = input.getNext();
			if (isVogal(caractereAtual)) {
				if (!vogaisEncontradas.contains(caractereAtual) && temConsoanteComVogalAntecessora) {
					return caractereAtual;
				}
				vogaisEncontradas.add(caractereAtual);
			} else if (isConsoante(caractereAtual) && isVogal(ultimoCaracter)) {
				temConsoanteComVogalAntecessora = true;
			}

			ultimoCaracter = caractereAtual;
		}

		return '\u0000';
	}
	
	private static Boolean isVogal(Character c) {
		return Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U').contains(c);
	}
	
	
	private static Boolean isConsoante(Character c) {
		return Arrays.asList('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'p', 'q',
				'r', 's', 't', 'v', 'w', 'x', 'y', 'z', 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q',
				'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z').contains(c);
	}

}
