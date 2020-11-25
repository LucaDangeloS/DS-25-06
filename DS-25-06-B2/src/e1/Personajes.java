package e1;

import static java.lang.Math.max;
import static java.lang.StrictMath.round;

//Personajes
abstract class Personajes {
    private int HP;
    private final int RES;
    public final String name;

    Personajes (String name, int HP, int RES) {
        this.name = name;
        this.HP = HP;
        this.RES = RES;
    }

    public void resetATK() {
    }
    protected void calcDMG(double damage) {
        this.HP -= round(damage < 0 ? 0 : damage);
    }
    abstract public void attack(Personajes P, Modifiers mod);

    //getters
    public int getHP() { return this.HP; }
    public int getRES() { return this.RES; }
    abstract public int getATK();
    abstract Razas getRaza();
    abstract String getFaction();

    abstract void DiceRoll();

    @Override
    public String toString() {
        return this.name+" (HP: "+this.getHP()+")";
    }
}

//Heroes
class Heroes extends Personajes {
    private final Razas raza;
    private int ATK;

    //Constructor
    Heroes(String name, int HP, int RES, Razas_Heroes raza) {
        super(name, HP, RES);
        this.raza = raza;
    }

    //Modificadoras
    @Override
    public void attack(Personajes P, Modifiers mod) {
        P.calcDMG(this.ATK+mod.H_ATK - P.getRES()*mod.B_RES);
    }

    //Getters
    public Razas getRaza(){ return this.raza; }
    public String getFaction() { return "Heroes"; }
    public int getATK() { return this.ATK; }

    void DiceRoll() {
        int max_roll = 91;
        this.ATK = max(RNG.Roll(max_roll),RNG.Roll(max_roll));
    }
}

//Bestias
class Bestias extends Personajes {
    private final Razas raza;
    private int ATK;

    //Constructor
    Bestias(String name, int HP, int RES, Razas_Bestias raza) {
        super(name, HP, RES);
        this.raza = raza;
    }

    //Modificadoras
    @Override
    public void attack(Personajes P, Modifiers mod) {
        if (this.getHP() > 0)
            P.calcDMG(this.ATK+mod.B_ATK - P.getRES()*mod.H_RES);
    }

    //Getters
    public Razas getRaza(){ return this.raza; }
    public String getFaction() { return "Bestias"; }
    public int getATK() { return this.ATK; }

    void DiceRoll() {
        int max_roll = 101;
        this.ATK = RNG.Roll(max_roll);
    }
}

