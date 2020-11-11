package e1;

import static java.lang.Math.max;
import static java.lang.StrictMath.round;

//Personajes
abstract class Personajes {
    private int HP;
    private int ATK;
    private final int RES;
    public final String name; //la hago public porque va a ser final cuando se cree el personaje

    Personajes (String name, int HP, int RES) {
        this.name = name;
        this.HP = HP;
        this.RES = RES;
        this.ATK = 0;
    }

    public void resetATK() {this.ATK = 0; }
    protected void calcDMG(double damage) {
        this.HP -= round(damage < 0 ? 0 : damage);
    }
    abstract public void attack(Personajes P, Modifiers mod);

    public int getHP() { return this.HP; }
    public int getRES() { return this.RES; }
    abstract public int getATK();
    @Override
    public String toString() {
        return this.name+" (HP: "+this.getHP()+")";
    }

    abstract String getRaza();
    abstract String getFaction();

    abstract int DiceRoll();
}

//Heroes
class Heroes extends Personajes {
    private final RAZAS raza;
    private int ATK;
    private final int max_roll = 91;

    public enum RAZAS {
        Elfo,
        Hobbit,
        Humano
    }

    //Constructor
    Heroes(String name, int HP, int RES, RAZAS raza) {
        super(name, HP, RES);
        this.raza = raza;
    }

    //Modificadoras
    @Override
    public void attack(Personajes P, Modifiers mod) {
        P.calcDMG(this.ATK+mod.H_ATK - P.getRES()*mod.B_RES);
    }

    //Getters
    public String getRaza(){ return this.raza.toString(); }
    public String getFaction() { return "Heroes"; }
    public int getATK() { return this.ATK; }

    int DiceRoll() {
        this.ATK = max(RNG.Roll(max_roll),RNG.Roll(max_roll));
        return this.ATK;
    }
}

//Bestias
class Bestias extends Personajes {
    private final RAZAS raza;
    private int ATK;
    private final int max_roll = 101;

    public enum RAZAS {
        Orco,
        Trasgo
    }

    //Constructor
    Bestias(String name, int HP, int RES, RAZAS raza) {
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
    public String getRaza(){ return this.raza.toString(); }
    public String getFaction() { return "Bestias"; }
    public int getATK() { return this.ATK; }

    int DiceRoll() {
        this.ATK = RNG.Roll(max_roll);
        return this.ATK;
    }
}