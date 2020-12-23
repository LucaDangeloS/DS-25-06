package e2;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class WorkingGroup extends GroupComponent {
    private float coste;
    private final List<GroupComponent> workers = new ArrayList<>();

    //Constructor
    WorkingGroup(String name) {
        super(name, 0);
    }

    //setters
    private void ajustarCoste(float coste) {this.coste = this.getCoste() + coste;}
    @Override
    void add(GroupComponent c) {
        if (workers.contains(c)) throw new IllegalArgumentException("Element already exists"); //no se puede añadir el mismo trabajador 2 veces
        workers.add(c);
        ajustarCoste(c.getCoste());
    }
    @Override
    void remove(GroupComponent c) {
        if (workers.contains(c)) { //el trabajador no está en este grupo
            workers.remove(c);
            ajustarCoste(-c.getCoste());
        } else throw new NoSuchElementException();
    }

    //getters
    @Override
    public float getCoste() {
        return this.coste;
    }


}
