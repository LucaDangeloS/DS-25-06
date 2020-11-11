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
        int i=0, max=100;
        StringBuilder sb = new StringBuilder();
        String[] output = new String[max]; //máximo de 100 enfrentamientos

        while (cm.BattleContinues(HeroArmy, BeastArmy) && i < max) {
            sb.delete(0,sb.length()); //reset StringBuilder from last turn
            sb.append("Turn "+(i+1)+"\n");
            sb.append(cm.battle(HeroArmy, BeastArmy));
            output[i] = sb.toString();
            i++;
        }
        if (i == max) output[i-1] = "La batalla está durando demasiado...\n"+cm.declareWinner(HeroArmy, BeastArmy);
        else output[i] = cm.declareWinner(HeroArmy, BeastArmy);

        return output;
    }

    public static void main(String[] args) {
        Bestias cs = new Bestias("Magh'ul", 60, 47, Razas_Bestias.Orco);
        Bestias cs2 = new Bestias("Ushulum", 50, 35, Razas_Bestias.Orco);
        Bestias cs3 = new Bestias("Uruk-hai", 70, 45, Razas_Bestias.Trasgo);
        Heroes bs = new Heroes("Gandalf", 45, 33, Razas_Heroes.Humano);
        Heroes bs2 = new Heroes("Legolas", 32, 15, Razas_Heroes.Elfo);
        Heroes bs3 = new Heroes("Frodo", 20, 10, Razas_Heroes.Hobbit);

        new RNG(5);

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
            if (s != null) System.out.println(s);
        }
    }

}
