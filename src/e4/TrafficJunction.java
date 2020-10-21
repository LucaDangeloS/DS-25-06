package e4;

import e3.Clock;

public class TrafficJunction {
    /**
     * Creates a trafic junction with four traffic lights named north , south ,
     * east and west . The north traffic light has just started its green cycle .
     */
    public TrafficJunction () {

    }
    /**
     * Indicates that a second of time has passed , so the traffic light with
     * the green or amber light should add 1 to its counter . If the counter
     * passes its maximum value the color of the traffic light must change .
     * If it changes to red then the following traffic light changes to green .
     * The order is : north , south , east , west and then again north .
     */
    public void timesGoesBy () {

    }
    /**
     * If active is true all the traffic lights of the junction must change to
     * blinking amber ( meaning a non - controlled junction ).
     * If active is false it resets the traffic lights cycle and started again
     * with north at green and the rest at red .
     * param active true or false
     */
    public void amberJunction ( boolean active ) {

    }
    /**
     * Returns a String with the state of the traffic lights .
     * The format for each traffic light is the following :
     * - For red colors : "[ name : RED ]"
     * - For green colors : "[ name : GREEN counter ]"
     * - For yellow colors with blink at OFF : "[ name : YELLOW OFF counter ]
     * - For yellow colors with blink at ON : "[ name : YELLOW ON ]
     * Examples :
     * [ NORTH : GREEN 2][ SOUTH : RED ][ EAST : RED ][ WEST : RED ]
     * [ NORTH : AMBER OFF 5][ SOUTH : RED ][ EAST : RED ][ WEST : RED ]
     * [ NORTH : AMBER ON ][ SOUTH : AMBER ON ][ EAST : AMBER ON ][ WEST : AMBER ON ]
     * @return String that represents the state of the traffic lights
     */
    @Override
    public String toString () {

    }

    public static void main(String[] args) {

    }
}