package e2;


public class Code {

	public static boolean isKeypadValid(char[][] keypad) {
		int i, j, x, y, array_length = -1; //inicializacion de array_length en -1
		StringBuilder sb = new StringBuilder(); //clase StringBuffer para montar un string con el array de strings
		String aux_string;
		char guardado;

		if (keypad == null) return false;//si es null no es válido

		if (keypad[0][0] != '1') return false; //Comprobacion posicion 1 igual a 1

		for (i = 0; i < keypad.length; i++) if (keypad[i] == null) return false;

		for (i = 0; i < keypad.length; i++) {//Comprueba que es un rectangulo
			if (array_length == -1) array_length = keypad[i].length;
			//en la primera fila el array_length vale -1 para poder sobreescribirlo con el valor de la fila numero 1

			if (array_length != keypad[i].length) return false; //si alguna de las filas del keypad es distinta, return false.
		}

		//Comprueba que sigue el orden alfanumérico
		if (keypad.length > 1 && keypad[0][0]+1 == keypad[1][0]) { //caso de que los numeros vayan {{1},{2},{3},...}

			for (int l = 0; l < keypad[0].length; l++) {
				for (int k = 0; k < keypad.length; k++) sb.append(keypad[k][l]); //monta el string con el keypad
			}
			aux_string = sb.toString();
			i = 0;

			//ciclo para los numeros
			guardado = '1'; //empieza en numero

			for (int k = aux_string.charAt(i); i < aux_string.length()-1 && (k<='9' && k>='0'); i++, k=aux_string.charAt(i)){

				if (k != guardado) {
					return false;
				} else {
					if (guardado != '9')
						guardado = (char) (guardado+1);
					else guardado = '0';
				}
			}//para cuando acaban los numeros

			//ciclo para las letras
			guardado = 'A'; //empieza en A

			for (int k = aux_string.charAt(i); i < aux_string.length()-1 && (k<='Z' && k>='A'); i++, k=aux_string.charAt(i)){

				if (k != guardado) {
					return false;
				} else guardado = (char) (guardado+1);
			}//para cuando acaban las letras

		} else if (keypad[0].length > 1 && keypad[0][0]+1 == keypad[0][1]) { //caso de que los numeros vayan {{1,2,3},{4,5,...}}

			for (int k = 0; k < keypad.length; k++) sb.append(keypad[k]); //monta el string con el keypad
			aux_string = sb.toString();
			i = 0;

			//ciclo para los numeros
			guardado = '1'; //empieza en 1

			for (int k = aux_string.charAt(i); i < aux_string.length()-1 && (k<='9' && k>='0'); i++, k=aux_string.charAt(i)){

				if (k != guardado) {
					return false;
				} else {
					if (guardado != '9')
						guardado = (char) (guardado+1);
					else guardado = '0';
				}
			} //para cuando acaban los numeros

			//ciclo para las letras
			guardado = 'A'; //empieza en A

			for (int k = aux_string.charAt(i); i < aux_string.length()-1 && (k<='Z' && k>='A'); i++, k=aux_string.charAt(i)){
				if (k != guardado) {
					return false;
				} else guardado = (char) (guardado+1);
			} //para cuando acaban las letras
		}


		for (i = 0; i < keypad.length; i++) {//Comprueba que no hay existen duplicados en el teclado.
			for (j = 0; j < keypad[i].length; j++) {
				guardado = keypad[i][j];
				for (x = i; x < keypad.length; x++) {
					for (y = j + 1; y < keypad[x].length; y++) {
						if (guardado == keypad[x][y]) {
							return false;
						}
					}
				}
			}
		}
		return true;

	}

	public static boolean areMovementsValid(String[] movements) {

		if (movements == null || movements.length == 0) return false;//Comprueba que la longitud del String no sea nula.

		for (int i = 0; i < movements.length; i++) { //recorre la lista de palabras de movements

			if (movements[i] == null || movements[i].length() == 0) return false;

			for (int j = 0; j < movements[i].length(); j++) { //recorre cada letra de cada palabra de movements
				if (movements[i].charAt(j) != 'U' && movements[i].charAt(j) != 'D' && movements[i].charAt(j) != 'L' && movements[i].charAt(j) != 'R')
					return false; //si alguna es distinta de UDLR, no es válido
			}
		}
		return true; //si pasa, es válido
	}

	public static String obtainCode(char[][] keypad, String[] movements){
		StringBuilder ret = new StringBuilder(), sb = new StringBuilder();
		String aux_string;
		char  c;
		int i,j, row_jump = keypad[0].length, pos = 0;

		if (isKeypadValid(keypad) && areMovementsValid(movements)) {

			for (int k = 0; k < keypad.length; k++) sb.append(keypad[k]); //monta el string con el keypad

			aux_string = sb.toString();

			for (j = 0; j < movements.length;j++) {
				//nos movemos por el string para grabar le input, siendo los saltos entre filas del keypad saltos fijos de valor row_jump.
				//paso entonces la matriz del keypad a un string y voy dando saltos dependiendo de los movimientos que se piden
				//para saber si se puede dar un determinado salto hago uso de cálculos con módulo (%)
				//depende de si es hacia la derecha o izquierda utilizo el modulo de row_jump
				//y si es arriba o abajo se compara con el length de la cadena total.
				for (i = 0; i < movements[j].length(); i++) {
					c = movements[j].charAt(i);

					switch(c){
						case 'U':
							if (pos - row_jump >= 0 && pos - row_jump < aux_string.length()) //solo avanza si no se ecnuentra en los limites superiores del teclado
								pos = pos - row_jump;
							break;

						case 'D':
							if (pos + row_jump >= 0 && pos + row_jump < aux_string.length()) //solo avanza si no se ecnuentra en los limites inferiores del teclado
								pos = pos + row_jump;
							break;

						case 'R':
							if ((pos+1) % row_jump != 0 && row_jump != 1) pos++; //solo avanza si no se ecnuentra en los limites de la derecha del teclado
							break;

						case 'L':
							if ((pos+1) % row_jump != 1 && row_jump != 1) pos--; //solo avanza si no se ecnuentra en los limites de la izquierda del teclado
							break;
					}
				}
				ret.append(aux_string.charAt(pos)); //luego añade la tecla en la que paró al stringbuilder
			}
		} else throw new IllegalArgumentException("Keypad no válido");
		return ret.toString(); //devuelve el string
    }
}

