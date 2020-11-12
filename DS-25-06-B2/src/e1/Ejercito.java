package e1;

import java.util.ArrayList;
import static java.lang.Math.max;
import static java.lang.StrictMath.round;

public class Ejercito<E extends Personajes> {
    private final ArrayList<E> fila = new ArrayList<>();

    //Modificadoras
    public void add(E unit) {
        if (!fila.contains(unit)) fila.add(unit);
    }
    private void remove(int index) {
        fila.remove(index);
    }
    public String fight(Ejercito<? extends Personajes> E, int i, Modifiers mod) {
        var sb = new StringBuilder();
        Personajes enemy = E.get(i);
        Personajes ally = this.get(i);

        sb.append("\tLucha entre "+ally.toString()+" y "+enemy.toString()+"\n");

        ally.attack(enemy, mod);
        enemy.attack(ally, mod);

        if (enemy.getHP() < 0) sb.append("\t"+enemy.name+" "+enemy.getRaza()+" se muere!\n");
        if (ally.getHP() < 0) sb.append("\t"+ally.name+" "+ally.getRaza()+" se muere!\n");

        return sb.toString();
    }
    public void clear() {
        fila.clear();
    }
    public void reset() {
        Personajes p;
        for (int i = fila.size()-1; i>=0; i--) { //se borran del final al principio para que los index
                                                // de las anteriores posiciones no varien al reccorrer el ArrayList
            p = this.get(i);
            if (this.length() > 0) {
                if (p.getATK() != 0) p.resetATK();
                if (p.getHP() <= 0) remove(i);
            }
        }
    }

    //Getters
    public String getFaction() {
        if (!fila.isEmpty()) return fila.get(0).getFaction();
        else return "nada";
    }
    public int length() {
        return fila.size();
    }
    public boolean isEmpty() {
        if (fila.isEmpty()) return true;
        else return false;
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
    public E get(int index) {
        return fila.get(index);
    }

    //Roll Dice y comparativa
    public void DiceRoll(int index) {
        this.get(index).DiceRoll();
    }

    public Ejercito<? extends Personajes> hasMore(Ejercito<? extends Personajes> O) {
        if (O.length() > this.length()) return O;
        else return this;
    }
}