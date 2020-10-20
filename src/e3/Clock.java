package e3;

import java.time.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Clock {
    private int hours;
    private int minutes;
    private int seconds;
    public enum Period {AM,PM}
    Period period;

    //constructor de string
    public Clock ( String s ) {

        Pattern TwentyFour_format = Pattern.compile("^[0-1][0-9]:[0-5][0-9]:[0-5][0-9] (AM|PM)$", Pattern.CASE_INSENSITIVE); //regex para formato 24h
        Pattern Twelve_format = Pattern.compile("^[0-2][0-9]:[0-5][0-9]:[0-5][0-9]$"); //regex para formato 12h
        Matcher match_twentyFour = TwentyFour_format.matcher(s); //match del regex 24h
        Matcher match_twelve = Twelve_format.matcher(s); //match del regex 12h
        boolean twentyFour_f = match_twentyFour.find() , twelve_f = match_twelve.find();//variables booleanas para los matches correspondientes
        int hours, min, sec;
        Period t_period = null;

        if (twentyFour_f || twelve_f) {
            hours = Integer.parseInt(s.substring(0,2)); //horas
            min = Integer.parseInt(s.substring(3,5)); //minutos
            sec = Integer.parseInt(s.substring(6,8)); //segundos

            if (twentyFour_f) t_period = Period.valueOf(s.substring(s.length()-2)); //AM o PM

        } else throw new IllegalArgumentException("Valores ilegales");

        if (twentyFour_f) {
            if ((hours <= 12 && hours >= 0)) { //solo es valido de 00:00:00 AM a 11:59:59 PM
                this.hours = hours;
                this.minutes = min;
                this.seconds = sec;
                this.period = t_period;
            } else throw new IllegalArgumentException("Valores ilegales");
        } else if (twelve_f) {
            if (hours < 24 && hours >= 0) {//solo es valido de 00:00:00 AM a 23:59:59 PM
                this.hours = hours % 12;
                this.minutes = min;
                this.seconds = sec;
                if (hours > 12) this.period = Period.PM;
                else this.period = Period.PM;
            } else throw new IllegalArgumentException("Valores ilegales");
        } else throw new IllegalArgumentException("Valores ilegales");
    }
    //constructor con numeros en formato 24h
    public Clock ( int hours , int minutes , int seconds ) {
        if ((hours < 0 || minutes < 0 || seconds < 0) ||
                (hours > 23 || minutes > 59 || seconds > 59)) throw new IllegalArgumentException("Valores ilegales");//comprobacion de valores válidos

        if (hours > 12) {
            this.hours = hours - 12; //si la hora dada es mayor que 12 es PM
            this.period = Period.PM;
        } else {
            this.hours = hours;
            this.period = Period.AM;
        }

        this.minutes = minutes;
        this.seconds = seconds;
    }
    //constructor con numeros en formato 12h
    public Clock ( int hours , int minutes , int seconds , Period period ) {
        if ((hours < 0 || minutes < 0 || seconds < 0) ||
                (hours > 12 || minutes > 59 || seconds > 59) ||
                (period != Period.AM && period != Period.PM) ||
                (hours == 12 && period == Period.PM)) throw new IllegalArgumentException("Valores ilegales");//comprobacion de valores válidos
                // se añade la condicion de que la hora 12:00:00 PM no existe.

        this.hours = hours;
        this.period = period;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getHours24 () {
        return this.hours + (this.period == Period.PM ? 12 : 0);
    }

    public int getHours12 () {
        return this.hours > 12 ? this.hours % 12 : this.hours;
    }

    public int getMinutes () {
        return this.minutes;
    }

    public int getSeconds () {
        return this.seconds;
    }

    public Period getPeriod () {
        return this.period;
    }

    public String printHour24 () {
        String h = String.format("%02d",this.hours + (this.period == Period.PM ? 12 : 0));
        String m = String.format("%02d",this.minutes);
        String s = String.format("%02d",this.seconds);

        return h+":"+m+":"+s;
    }

    public String printHour12 () {
        String h = String.format("%02d",this.hours > 12 ? this.hours % 12 : this.hours);
        String m = String.format("%02d",this.minutes);
        String s = String.format("%02d",this.seconds);
        String period = this.period.toString();

        return h+":"+m+":"+s+" "+period;
    }

    @Override
    public boolean equals ( Object o ) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        //
        Clock clock = (Clock) o;

        if (this.hours != clock.hours || this.seconds != clock.seconds ||
                this.minutes != clock.minutes || this.period != clock.period) return false; //si cualquier valor del objeto es distinto
                                                                                            //return false
        else return true;
    }

    @Override
    public int hashCode () {
        return (this.minutes + this.seconds + this.hours)*31 + this.period.hashCode();
    }


    public static void main(String[] args) {
        Clock c = new Clock("13:10:00");
        Clock d = new Clock(1,10,0, Period.PM);

        System.out.println(c.printHour24());
        System.out.println(c.printHour12());
        System.out.println(d.printHour24());
        System.out.println(d.printHour12());
    }
}