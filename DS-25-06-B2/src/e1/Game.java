package e1;

import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

import static java.lang.Math.min;

public class Game {
    private final Ejercito<Heroes> HeroArmy = new Ejercito<>();
    private final Ejercito<Bestias> BeastArmy = new Ejercito<>();
    private final CombatMechanics cm = new CombatMechanics();

    public String[] batalla() {
        StringBuilder sb = new StringBuilder();
        String[] output = new String[100]; //máximo de 100 enfrentamientos
        int i=1;

        while (cm.BattleContinues(HeroArmy, BeastArmy)) {
            sb.delete(0,sb.length()); //reset StringBuilder from last turn
            sb.append("Turn "+i+"\n");
            sb.append(cm.battle(HeroArmy, BeastArmy));

            output[i-1] = sb.toString();
            i++;
        }
        output[i] = cm.declareWinner(HeroArmy, BeastArmy);

        return output;
    }

    public static void pl (Object o) {
        System.out.println(o.toString());
    }

    public static void main(String[] args) {
        Heroes bs = new Heroes("pene1", 10, 10, Heroes.RAZAS.Humano);
        Bestias cs = new Bestias("pene1", 10, 10, Bestias.RAZAS.Orco);
        Heroes bs2 = new Heroes("pene2", 10, 10, Heroes.RAZAS.Humano);
        Bestias cs2 = new Bestias("pene2", 10, 10, Bestias.RAZAS.Orco);

        new RNG(5);

        Ejercito<Heroes> EjHeroes = new Ejercito<>();
        Ejercito<Bestias> EjBestias = new Ejercito<>();

        EjHeroes.add(bs);
        EjHeroes.add(bs2);
        EjBestias.add(cs);
        EjBestias.add(cs2);

        pl(EjHeroes.toString());
        pl(EjBestias.toString());

    }

}
