package e1;

class Humano extends Heroes {

    Humano(String name, int HP, int RES) {
        super(name, HP, RES);
    }
}
class Elfo extends Heroes {

    Elfo(String name, int HP, int RES) {
        super(name, HP, RES);
    }

    @Override
    public void attack(Personajes P) {
        if (P instanceof Orco)
            P.calcDMG(this.getATK()+10 - P.getRES());
        else super.attack(P);
    }
}
class Hobbit extends Heroes {

    Hobbit(String name, int HP, int RES) {
        super(name, HP, RES);
    }

    @Override
    public void attack(Personajes P) {
        if (P instanceof Trasgo)
            P.calcDMG(this.getATK()-5 - P.getRES());
        else super.attack(P);
    }
}

class Orco extends Bestias {

    Orco(String name, int HP, int RES) {
        super(name, HP, RES);
    }

    @Override
    public void attack(Personajes P) {
        if (this.getHP() > 0)
            P.calcDMG(this.getATK() - P.getRES()*0.9);
    }
}
class Trasgo extends Bestias {

    Trasgo(String name, int HP, int RES) {
        super(name, HP, RES);
    }
}
