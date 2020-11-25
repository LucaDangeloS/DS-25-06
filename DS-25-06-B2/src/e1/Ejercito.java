package e1;

import java.util.ArrayList;

public class Ejercito<E extends Personajes> {
    private final ArrayList<E> army = new ArrayList<>();

    //Modificadoras
    public void add(E unit) {
        if (!army.contains(unit)) army.add(unit);
    }
    private void remove(int index) {
        army.remove(index);
    }
    public String fight(Ejercito<? extends Personajes> E, int fila) {
        var sb = new StringBuilder();
        Personajes enemy = E.get(fila);
        Personajes ally = this.get(fila);

        sb.append("\tLucha entre "+ally.toString()+" y "+enemy.toString()+"\n");

        ally.attack(enemy);
        enemy.attack(ally);

        if (enemy.getHP() < 0) sb.append("\t"+enemy.name+" "+enemy.getClass().getSimpleName()+" se muere!\n");
        if (ally.getHP() < 0) sb.append("\t"+ally.name+" "+ally.getClass().getSimpleName()+" se muere!\n");

        return sb.toString();
    }
    public void clear() {
        army.clear();
    }
    public void reset() {
        Personajes p;
        for (int i = army.size()-1; i>=0; i--) { //se borran del final al principio para que los index
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
        if (!army.isEmpty()) return army.get(0).getFaction();
        else return "nada";
    }
    public int length() {
        return army.size();
    }
    public boolean isEmpty() {
        return army.isEmpty();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ejercito de "+this.getFaction()+"\n");
        for (E unit : army) {
            sb.append("\t"+unit.name+" : "+unit.getClass().getSimpleName()+" (HP: "+unit.getHP()+") (RES: "+unit.getRES()+")");
            sb.append("\n");
        }
        return sb.toString();
    }
    public E get(int index) {
        return army.get(index);
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