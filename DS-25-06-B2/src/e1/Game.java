package e1;

import java.util.Arrays;

import static java.lang.Math.min;

public class Game {
    private final Ejercito<Heroes> HeroArmy;
    private final Ejercito<Bestias> BeastArmy;
    private final CombatMechanics cm = new CombatMechanics();
    private String[] log;

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
        log = output;
        return output;
    }

    @Override
    public String toString() {
        var output = new StringBuilder();
        for (String s : log) {
            if (s != null) output.append(s);
        }
        return output.toString();
    }
}
