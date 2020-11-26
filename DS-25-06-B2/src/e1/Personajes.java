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
    abstract public void attack(Personajes P);

    //getters
    public int getHP() { return this.HP; }
    public int getRES() { return this.RES; }
    abstract public int getATK();
    abstract String getFaction();

    abstract void DiceRoll();

    @Override
    public String toString() {
        return this.name+" (HP: "+this.getHP()+")";
    }
}

//Heroes
abstract class Heroes extends Personajes {
    private int ATK;

    //Constructor
    Heroes(String name, int HP, int RES) {
        super(name, HP, RES);
    }

    //Modificadoras
    @Override
    public void attack(Personajes P) {
        P.calcDMG(this.ATK - P.getRES());
    }

    //Getters
    public String getFaction() { return "Heroes"; }
    public int getATK() { return this.ATK; }

    void DiceRoll() {
        this.ATK = max(RNG.Roll(91),RNG.Roll(91));
    }
}

//Bestias
abstract class Bestias extends Personajes {
    private int ATK;

    //Constructor
    Bestias(String name, int HP, int RES) {
        super(name, HP, RES);
    }

    //Modificadoras
    @Override
    public void attack(Personajes P) {
        if (this.getHP() > 0)
            P.calcDMG(this.ATK - P.getRES());
    }

    //Getters
    public String getFaction() { return "Bestias"; }
    public int getATK() { return this.ATK; }

    void DiceRoll() {
        this.ATK = RNG.Roll(101);
    }
}

