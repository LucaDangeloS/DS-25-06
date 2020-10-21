package e4;
import java.util.EnumMap;

public class TrafficLight {
    public enum Colors {
        GREEN,
        AMBER,
        RED;

        private static Colors[] list = Colors.values();

        public static Colors getColor(int i) {
            if (i > list.length-1) return list[0];
            else return list[i];
        }
    }
    public enum Pos {NORTH, SOUTH, EAST, WEST}
    public EnumMap<Colors, Integer> ColorsMap = new EnumMap<>(Colors.class);


    private final Pos pos;
//    private final int red_time;
    private Colors color;

    private int counter;

    public TrafficLight(int red_time, Pos position, int counter, Colors color) {
        ColorsMap.put(Colors.RED, red_time);
        ColorsMap.put(Colors.GREEN, 15);
        ColorsMap.put(Colors.AMBER, 5);

        this.pos = position;
        this.color = color;
        this.counter = counter;
    }

    public Colors getColor(int i){
        return this.color;
    }

    public int getCounter(){
        return this.counter;
    }

    private void cycle_colors(){
        if (this.counter > this.ColorsMap.get(this.color)) getColor(this.color.ordinal()+1);
    }

    public void cycle() {
        this.counter++;
        cycle_colors();
    }

    public static void main(String[] args) {

    }
}
