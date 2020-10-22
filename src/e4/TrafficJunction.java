package e4;
import java.util.HashMap;

public class TrafficJunction {

    public enum Pos {
        NORTH,
        SOUTH,
        EAST,
        WEST;

        private static final Pos[] list = Pos.values();

        private static Pos FirstPos() { //returns NORTH
            return list[0];
        }

        private static Pos getNextPos(int i) { //devuelve la siguiente posicion, y si es la ultima vuelve al principio
            if (i >= list.length-1) return list[0];
            else return list[i+1];
        }
    }

    private TrafficLight north = new TrafficLight(Pos.NORTH, 0, TrafficLight.Colors.GREEN);
    private TrafficLight south = new TrafficLight(Pos.SOUTH, 0, TrafficLight.Colors.RED);
    private TrafficLight east = new TrafficLight(Pos.EAST, 0, TrafficLight.Colors.RED);
    private TrafficLight west = new TrafficLight(Pos.WEST, 0, TrafficLight.Colors.RED);

    HashMap<Pos, TrafficLight> Tfs = new HashMap<>();
    //añadimos una correspondencia con un hashmap de cada posicion a una TrafficLight para poder enumerar todas facilmente sin ser "hardcodeado".


    public TrafficJunction () {
        //Crea las entradas de los semáforos en el HashMap, usando como keys las posiciones {NORTH...}
        Tfs.put(Pos.NORTH, north);
        Tfs.put(Pos.SOUTH, south);
        Tfs.put(Pos.EAST, east);
        Tfs.put(Pos.WEST, west);
    }

    public void timesGoesBy () {
        Pos pos = Pos.FirstPos(), adv_pos = Pos.getNextPos(pos.ordinal());
        boolean cycle_next = false;
        int i;

        for (i = Tfs.size(); i > 0; i--, pos = Pos.getNextPos(pos.ordinal())) { //recorre todos los semáforos
            //recorre las posiciones ordenadamente y actualiza la correspondiente entrada de TrafficLight

            //si el semáforo terminó su ciclo se almacena en varaibles la posicion del siguiente semáforo y un bool que indica que es para avanzar de rojo
            if (Tfs.get(pos).reached_counter_limit())  {
                cycle_next = true;
                adv_pos = Pos.getNextPos(pos.ordinal());
            }

            //si hay que avanzar un semáforo de rojo a verde se cumplen las conficiones pasadas como parámetro al metodo cycle.
            Tfs.get(pos).cycle(cycle_next && pos == adv_pos);
        }
        cycle_next = true;
        //por ultimo para cubrir el caso de que termine el ciclo de todos lo semáforos se recorren todos,
        //y si no se encuntra ninguno que no esté en rojo resetea el primer semáforo de TrafficJunction. Utilizando la variable cycle_next para guardar la comprobacion
        for (i = Tfs.size(); i > 0; i--, pos = Pos.getNextPos(pos.ordinal()))
            if (!Tfs.get(pos).is_red()) cycle_next = false;

        if (cycle_next) Tfs.get(Pos.FirstPos()).cycle(true);

    }

    public void amberJunction ( boolean active ) {
        Pos pos = Pos.FirstPos();
        int i;

        for (i = Tfs.size(); i > 0; i--, pos = Pos.getNextPos(pos.ordinal())) {//recorre todos los semáforos
            Tfs.get(pos).blinkAmber(active); //sets active
        }

    }

    @Override
    public String toString () {
        return north.toString()+south.toString()+east.toString()+west.toString();
    }
}