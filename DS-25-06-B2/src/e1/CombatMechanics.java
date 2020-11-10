package e1;

import static java.lang.Math.min;
public class CombatMechanics {

    public boolean BattleContinues(Ejercito<Heroes> H, Ejercito<Bestias> B) {
        return min(H.length(),B.length()) > 0;
    }

    public String declareWinner(Ejercito<Heroes> H, Ejercito<Bestias> B) {
        if (H.length() != B.length())
            return H.hasMore(B).getFaction()+" Ganan!!!"; //metodo hasMore devuelve H o B dependiendo de cual tenga más tropas
        else return "Empate!!!";
    }

    private void applyRaceEffects(Heroes H, Bestias B) {

    }

    public String battle(Ejercito<Heroes> H, Ejercito<Bestias> B) {
        StringBuilder sb = new StringBuilder();
        int n_combat = min(H.length(),B.length());
        //bucle de tirada de dados y efectos de razas
        /*
            0 -> n_combat
            tiran dados
            aplican debufos
            {resuelve la vida / resetATK
            {si muere mueve tropas (delete)
         */
        return sb.toString();
    }
}
