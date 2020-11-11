package e1;

import static java.lang.Math.min;

class Modifiers {
    public int H_ATK = 0; //H de Heroes
    public double H_RES = 1;
    public int B_ATK = 0; //B de Bestias
    public double B_RES = 1;
}

class RaceEffects {
    public Modifiers applyRaceEffects(Heroes H, Bestias B) {
        var mod = new Modifiers();
        if (B.getRaza() == (Razas_Bestias.Orco)) mod.H_RES = 0.9;

        if (H.getRaza() == (Razas_Heroes.Elfo) && B.getRaza() == (Razas_Bestias.Orco)) {
            mod.H_ATK = 10;
        } else if (H.getRaza() == (Razas_Heroes.Hobbit) && B.getRaza() == (Razas_Bestias.Trasgo)) {
            mod.H_ATK = -5;
        }
        return mod;
    }
}

public class CombatMechanics extends RaceEffects {

    public boolean BattleContinues(Ejercito<Heroes> H, Ejercito<Bestias> B) {
        return min(H.length(),B.length()) > 0;
    }

    public String declareWinner(Ejercito<Heroes> H, Ejercito<Bestias> B) {
        if (H.length() != B.length())
            return H.hasMore(B).getFaction()+" Ganan!!!"; //metodo hasMore devuelve H o B dependiendo de cual tenga más tropas
        else return "Empate!!!";
    }

    public String battle(Ejercito<Heroes> H, Ejercito<Bestias> B) {
        StringBuilder sb = new StringBuilder();
        var mod = new Modifiers();
        int n_combat=min(H.length(), B.length()), i;

        //bucle de tirada de dados y efectos de razas
        for (i = 0; i < n_combat; i++) {
            H.DiceRoll(i);
            B.DiceRoll(i);
            System.out.println("Heroe: "+H.get(i).getATK()+" Bestia: "+B.get(i).getATK());
            mod = applyRaceEffects(H.get(i), B.get(i));
            sb.append(H.fight(B, i, mod));
        } //bucle de control de batallas en cada turno
        H.reset(); //se quitan las tropas muertas al final del turno para no
        B.reset();  //modificar los Ejercitos durante los siguiente combates del turno
        return sb.toString();
    }
}
