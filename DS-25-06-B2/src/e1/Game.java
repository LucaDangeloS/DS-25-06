package e1;

public class Game {

    public static void main(String[] args) {
        Heroes bs = new Heroes("pene", 10, 10, Heroes.RAZAS.Humano);
        Bestias cs = new Bestias("pene", 10, 10, Bestias.RAZAS.Orco);
        Heroes bs2 = new Heroes("pene", 10, 10, Heroes.RAZAS.Humano);
        Bestias cs2 = new Bestias("pene", 10, 10, Bestias.RAZAS.Orco);

        new RNG();

        System.out.println(bs.DiceRoll()+" "+bs2.DiceRoll());
        System.out.println(bs.DiceRoll()+" "+bs2.DiceRoll());
        System.out.println(bs.DiceRoll()+" "+bs2.DiceRoll());
        System.out.println(bs.DiceRoll()+" "+bs2.DiceRoll());
        System.out.println("---------------");
        System.out.println(cs.DiceRoll()+" "+cs2.DiceRoll());
        System.out.println(cs.DiceRoll()+" "+cs2.DiceRoll());
        System.out.println(cs.DiceRoll()+" "+cs2.DiceRoll());
        System.out.println(cs.DiceRoll()+" "+cs2.DiceRoll());
        System.out.println("---------------");

    }

}
