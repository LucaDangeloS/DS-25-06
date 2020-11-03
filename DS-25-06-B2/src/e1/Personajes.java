package e1;

abstract class Personajes {
    private int HP;
    private final String name;
    private int RES;

    Personajes (String name, int HP, int RES) {
        this.name = name;
        this.HP = HP;
        this.RES = RES;
    }
}

class Heroes extends Personajes {
    private final RAZAS raza;

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

    int DiceRoll() {
        return RNG.Roll(91);
    }
}

class Bestias extends Personajes {
    private final RAZAS raza;

    public enum RAZAS {
        Orco,
        Trasgo
    }

    //Constructor
    Bestias(String name, int HP, int RES, RAZAS raza) {
        super(name, HP, RES);
        this.raza = raza;
    }

    int DiceRoll() {
        return RNG.Roll(101);
    }
}