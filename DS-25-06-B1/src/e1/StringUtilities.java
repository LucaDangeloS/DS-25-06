package e1;

import java.util.Random;

public class StringUtilities {

    public static boolean isValidMix (String a , String b , String c) {
        int length_a = a.length(), length_b = b.length(); //variables para evitar llamar el metodo length tantas veces

        if (c.length() != a.length() + b.length()) return false; //Si la longitud del string c no coincide ya se anula

        for (int i=0,a_pos=0,b_pos=0; i < c.length()-1; ++i) {
            if ( !(a.charAt(a_pos) == c.charAt(i) || b.charAt(b_pos) == c.charAt(i)) ) return false; //Si encuentra algun caracter que no coincide ya se anula el string
            if (a_pos < length_a-1 && a.charAt(a_pos) == c.charAt(i)) a_pos++; //Avanza una posicion en el string a si encontró un carácter de allí
            if (b_pos < length_b-1 && b.charAt(b_pos) == c.charAt(i)) b_pos++; //Avanza una posicion en el string b si encontró un carácter de allí
        }
        return true;
    }

    public static String generateRandomValidMix (String a , String b) {
        StringBuilder sb = new StringBuilder(); //creamos una instancia de la clase stringbuilder
        Random random = new Random(); //creamos una instancia de la clase random para decidir de cual string vamos a insertar un caracter en el string
        int rand; //creamos la variable donde vamos a guardar el numero generado aleatoriamente
        int length_a=a.length(), length_b=b.length(); //variables de longitud de strings a y b

        for (int i=0, a_pos=0, b_pos=0; i < length_a+length_b; i++) {

            if (a_pos < length_a && b_pos < length_b) { //Si aun quedan dos ramas de opciones de caracteres por elegir
                rand = random.nextInt(2);

                if (rand == 1) {
                    sb.append(a.charAt(a_pos));
                    a_pos++;
                } else {
                    sb.append(b.charAt(b_pos));
                    b_pos++;
                }
            } else if (a_pos < length_a) { //Si el caracter que queda es del string a:
                sb.append(a.charAt(a_pos));
                a_pos++;
            } else {
                sb.append(b.charAt(b_pos)); //Si el caracter que queda es del string b:
                b_pos++;
            }
        }

        return sb.toString(); //Convertimos la instancia en string para el return
    }

//    public static void main(String[] args) {
//        String str = generateRandomValidMix("Bye","World");
//        System.out.println(str);
//    }
}