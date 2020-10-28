package e4;
import java.util.EnumMap;
import java.util.HashMap;

public class TrafficLight {
    public enum Colors {
        GREEN,
        AMBER,
        RED;

        private static final Colors[] list = Colors.values();

        private static Colors getNextColor(int i) {
            if (i >= list.length-1) return list[0];
            else return list[i+1];
        }
    }
    private final EnumMap<Colors, Integer> ColorsMap = new EnumMap<>(Colors.class);
    private final HashMap<Boolean, String> BlinkingAmber = new HashMap<>();

    private final TrafficJunction.Pos pos;
    private final Colors init_color;
    private Colors color;
    private boolean blink;
    private int counter;

    public TrafficLight(TrafficJunction.Pos position, int counter, Colors color) {
        ColorsMap.put(Colors.GREEN, 15);
        ColorsMap.put(Colors.AMBER, 5);
        BlinkingAmber.put(true, "AMBER ON");
        BlinkingAmber.put(false, "AMBER OFF");

        this.pos = position;
        this.init_color = color;
        this.color = color;
        this.blink = false;
        this.counter = counter;
    }

    private String getColorCount(){
        if (this.color == Colors.AMBER) return BlinkingAmber.get(this.blink)+this.getCounter();//para los colores rojo y ambar on omite el color
        return this.color.toString()+this.getCounter();//para el color verde y ambar off muestra el contador
    }

    private String getPos(){
        return this.pos.toString();
    }

    private String getCounter(){
        if (this.blink || this.color == Colors.RED) return "";
        else return " "+this.counter;
    }

    @Override
    public String toString () {
        return "["+this.getPos()+":"+" "+this.getColorCount()+"]";//devuelve la posicion del semáforo más su color y contador, si conviene
    }

    private void reset_counter() {
        this.counter = 0;
    }

    public boolean reached_counter_limit() { //si el contador llegó al ultimo segundo de su cuenta total devuelve true
        if (this.color == Colors.AMBER && !this.blink) {
            return this.counter == this.ColorsMap.get(this.color);
        } else return false;
    }

    private void reset() {
        this.reset_counter();
        this.color = this.init_color;
    }

    private void cycle_colors(boolean advance_red){ //cicla entre los colores

        if (this.blink) { //caso de que el semáforo esté parpadeando
            this.color = Colors.AMBER;
        } else if (this.color == Colors.RED) { //caso de que el semáforo intente salir del rojo
            if (advance_red) {
                this.color = Colors.getNextColor(this.color.ordinal());
                this.reset_counter();
            }
        } else if (this.counter > this.ColorsMap.get(this.color)) {
            //si el contador de la instancia actual supera el "umbral" del contador del color, se cicla el color al siguiente y se resetea el contador.
            this.color = Colors.getNextColor(this.color.ordinal());
            this.reset_counter();
        }
    }

    public boolean is_red() {
        return this.color == Colors.RED;
    }

    public void cycle(boolean advance_red) { //pasa entre ciclos del semáforo y los segundos de cada color del semáforo
        if (!this.blink) {
            this.counter++;
            cycle_colors(advance_red);
        }
    }

    public void blinkAmber(boolean active) { //activa el ambar parpadeante
        if (this.blink && !active) {//si estaba activado y se desactiva reinicia los semáforos
            this.blink = false;
            this.reset();
        }
        else if (active){
            this.blink = true;
            this.reset_counter();
            this.cycle_colors(false);
        }
    }
}
