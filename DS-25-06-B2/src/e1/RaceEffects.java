package e1;

class Modifiers {
    public int H_ATK = 0; //H de Heroes
    public double H_RES = 1;
    public int B_ATK = 0; //B de Bestias
    public double B_RES = 1;
}

public class RaceEffects {
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
