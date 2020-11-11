package e1;

import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

import static java.lang.Math.min;

public class Game {
    private final Ejercito<Heroes> HeroArmy;
    private final Ejercito<Bestias> BeastArmy;
    private final CombatMechanics cm = new CombatMechanics();

    Game(Ejercito<Heroes> EH, Ejercito<Bestias> EB) {
        HeroArmy = EH;
        BeastArmy = EB;
    }

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
        output[i-1] = cm.declareWinner(HeroArmy, BeastArmy);

        return output;
    }



    public static void pl (Object o) {
        System.out.println(o.toString());
    }

    public static void main(String[] args) {
        Heroes bs = new Heroes("pene1", 60, 10, Heroes.RAZAS.Humano);
        Bestias cs = new Bestias("pene1", 60, 10, Bestias.RAZAS.Orco);
        Heroes bs2 = new Heroes("pene2", 60, 10, Heroes.RAZAS.Humano);
        Bestias cs2 = new Bestias("pene2", 60, 10, Bestias.RAZAS.Orco);
        Heroes bs3 = new Heroes("pene3", 60, 10, Heroes.RAZAS.Humano);
        Bestias cs3 = new Bestias("pene3", 60, 10, Bestias.RAZAS.Orco);

        new RNG();

        Ejercito<Heroes> EjHeroes = new Ejercito<>();
        Ejercito<Bestias> EjBestias = new Ejercito<>();

        var game = new Game(EjHeroes, EjBestias);

        EjHeroes.add(bs);
        EjHeroes.add(bs2);
        EjBestias.add(cs);
        EjBestias.add(cs2);
        EjHeroes.add(bs3);
        EjBestias.add(cs3);

        for (String s: game.batalla()) {
            if (s != null) pl(s);
        }
    }

}
