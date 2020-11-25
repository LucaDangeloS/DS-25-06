package e1;

import static java.lang.Math.min;

public class Game {
    private final Ejercito<Heroes> HeroArmy;
    private final Ejercito<Bestias> BeastArmy;
    private final RaceEffects cm = new RaceEffects();
    private String[] log;

    Game(Ejercito<Heroes> EH, Ejercito<Bestias> EB) {
        HeroArmy = EH;
        BeastArmy = EB;
    }

    private boolean BattleContinues(Ejercito<Heroes> H, Ejercito<Bestias> B) {
        return min(H.length(),B.length()) > 0;
    }

    public String[] batalla() {
        int j=0, i=0, max=100, n_combat=min(HeroArmy.length(), BeastArmy.length());;
        StringBuilder sb = new StringBuilder();
        String[] output = new String[max]; //máximo de 100 enfrentamientos
        var mod = new Modifiers();

        while (BattleContinues(HeroArmy, BeastArmy) && j < max) {
            sb.delete(0,sb.length()); //reset StringBuilder from last turn
            sb.append("Turn "+(i+1)+"\n");
            n_combat = min(HeroArmy.length(), BeastArmy.length());

            //bucle de tirada de dados y efectos de razas
            for (i = 0; i < n_combat; i++) {
                HeroArmy.DiceRoll(i);
                BeastArmy.DiceRoll(i);

                mod = cm.applyRaceEffects(HeroArmy.get(i), BeastArmy.get(i));
                sb.append(HeroArmy.fight(BeastArmy, i, mod));
            } //bucle de control de batallas en cada turno

            HeroArmy.reset(); //se quitan las tropas muertas al final del turno para no
            BeastArmy.reset();  //modificar los Ejercitos durante los siguiente combates del turno

            output[j] = sb.toString();
            j++;
        }
        if (j == max) output[j-1] = "La batalla está durando demasiado...\n"+declareWinner(HeroArmy, BeastArmy);
        else output[j] = declareWinner(HeroArmy, BeastArmy);
        log = output;
        return output;
    }

    private String declareWinner(Ejercito<Heroes> H, Ejercito<Bestias> B) {
        if (H.length() != B.length())
            return H.hasMore(B).getFaction()+" Ganan!!!"; //metodo hasMore devuelve H o B dependiendo de cual tenga más tropas
        else return "Empate!!!";
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
