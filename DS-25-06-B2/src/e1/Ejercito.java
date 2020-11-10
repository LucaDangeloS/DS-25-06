package e1;

import java.util.ArrayList;
import static java.lang.Math.max;

public class Ejercito<E extends Personajes> {
    private final ArrayList<E> fila =  new ArrayList<>();


    public void add(E unit) {
        if (!fila.contains(unit)) fila.add(unit);
    }

    public void remove(int index) {
        fila.remove(index);
    }

    //Getters
    public String getFaction() {
        if (!fila.isEmpty()) return fila.get(0).getFaction();
        else return "nada";
    }
    public int length() {
        return this.length();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ejercito de "+this.getFaction()+"\n");
        for (E unit : fila) {
            sb.append("\t"+unit.name+" : "+unit.getRaza()+" (HP: "+unit.getHP()+") (RES: "+unit.getRES()+")");
            sb.append("\n");
        }
        return sb.toString();
    }

    public Ejercito<? extends Personajes> hasMore(Ejercito<? extends Personajes> O) {
        if (O.length() > this.length()) return O;
        else return this;
    }
}

//Personajes
abstract class Personajes {
    private int HP;
    private int RES;
    private int ATK;
    public final String name; //la hago public porque va a ser final cuando se cree el personaje

    Personajes (String name, int HP, int RES) {
        this.name = name;
        this.HP = HP;
        this.RES = RES;
        this.ATK = 0;
    }

    public void resetATK() {this.ATK = 0; }

    public int getHP() { return this.HP; }
    public int getRES() { return this.RES; }
    public int getATK() { return this.ATK; }

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

    //Getters
    public String getRaza(){ return this.raza.toString(); }
    public String getFaction() { return "Heroes"; }


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

    //Getters
    public String getRaza(){ return this.raza.toString(); }
    public String getFaction() { return "Bestias"; }

    int DiceRoll() {
        this.ATK = RNG.Roll(max_roll);
        return this.ATK;
    }
}