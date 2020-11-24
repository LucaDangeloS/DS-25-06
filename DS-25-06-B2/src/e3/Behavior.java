package e3;

import java.util.Collections;
import java.util.Random;

public interface Behavior {
    GunslingerAction action(Gunslinger g);
}

class Main_Behavior implements Behavior {
    private boolean delayed_reload = false;
    private GunslingerAction lastAction;

    @Override
    public GunslingerAction action(Gunslinger g) {
        int turn_n = g.getRivalActions().size()-1;
        int loads = g.getLoads();
        int rivalLoads = g.getRivalLoads();
        var ra = g.getRivalActions();


        if (loads >= 5) {
            if (!delayed_reload) return GunslingerAction.MACHINE_GUN;
            else {
                delayed_reload = false; //avoid Draw when suing machine_gun
                return GunslingerAction.PROTECT;
            }
        }
        if (rivalLoads >= 4) {
            if (loads > 0) return GunslingerAction.SHOOT; //Try a Draw when enemy machine_gun ready
            else return GunslingerAction.RELOAD;
        }
        if (loads == 0 && rivalLoads < 1) return GunslingerAction.RELOAD; //Reload safely
        if (turn_n > 15 && Collections.frequency(ra,GunslingerAction.PROTECT) >= 6) { //Avoid stalling with enemy protect spam after reloads
            if (ra.get(turn_n) == GunslingerAction.PROTECT && ra.get(turn_n-1) == GunslingerAction.RELOAD) {
                return GunslingerAction.PROTECT;
            } else { //Avoid getting caught on 4th and 5th reloads
                if (loads == 3 && lastAction == GunslingerAction.RELOAD) {
                    delayed_reload = true;
                    lastAction = GunslingerAction.PROTECT;
                    return GunslingerAction.PROTECT;
                }
                if (loads == 4 && lastAction == GunslingerAction.RELOAD) {
                    lastAction = GunslingerAction.PROTECT;
                    return GunslingerAction.PROTECT;
                }
                else {
                    lastAction = GunslingerAction.RELOAD;
                    return GunslingerAction.RELOAD;
                }
            }
        }

        else switch(ra.get(turn_n)) {
            case SHOOT:
                if (rivalLoads == 0 && Collections.frequency(ra,GunslingerAction.PROTECT) >= 3) //Avoid stalling with protect spam behaviors
                    return GunslingerAction.RELOAD; //Reload when safe
                else if (loads > 0 && !delayed_reload) return GunslingerAction.SHOOT;
                else return GunslingerAction.PROTECT;
            case RELOAD:
                if (turn_n >= 3 && ra.get(turn_n-1) == GunslingerAction.PROTECT &&
                        (ra.get(turn_n-2) == GunslingerAction.SHOOT || ra.get(turn_n-2) == GunslingerAction.PROTECT)) {
                    //Covers the case where the cycle is reload, shoot, protect, reload or reload, shoot, protect, protect, reload
                    delayed_reload = true;
                    return GunslingerAction.PROTECT;
                }
                else if (loads > 0)
                    return GunslingerAction.SHOOT;
                else return GunslingerAction.PROTECT;
            case PROTECT:
                if (delayed_reload && loads > 0) return GunslingerAction.SHOOT; //shoot when no protect is detected in their behavior
                else if (delayed_reload) return GunslingerAction.RELOAD; //if it's a protect spam behavior, reload
                if (rivalLoads < 1) return GunslingerAction.RELOAD; //reload safely
                else return GunslingerAction.PROTECT; //otherwise protect
            default:
                return GunslingerAction.PROTECT;
        }
    }
}

class Random_Behavior implements Behavior { //Random behavior

    @Override
    public GunslingerAction action(Gunslinger g) {
        int loads = g.getLoads();
        Random rand = new Random();

        if (loads >= 5) return GunslingerAction.MACHINE_GUN;
        switch (rand.nextInt(3 )) {
            case 0:
                return GunslingerAction.PROTECT;
            case 1:
                if (loads > 0) return GunslingerAction.SHOOT;
                else return GunslingerAction.RELOAD;
            default:
                return GunslingerAction.RELOAD;
        }
    }
}

class Quick_Behavior implements Behavior { //Reload, shoot, reload, shoot...

    @Override
    public GunslingerAction action(Gunslinger g) {
        int loads = g.getLoads();

        if (loads == 0) return GunslingerAction.RELOAD;
        else return GunslingerAction.SHOOT;
    }
}

class Protective_Behavior implements Behavior { //Shoot and reload when safe, otherwise protect

    @Override
    public GunslingerAction action(Gunslinger g) {
        int loads = g.getLoads();
        int rivalLoads = g.getRivalLoads();
        var turn_n = g.getRivalActions().size()-1;

        if (turn_n == -1) return GunslingerAction.RELOAD;
        if (rivalLoads == 0 && loads > 0) return GunslingerAction.SHOOT;
        else if (rivalLoads == 0) return GunslingerAction.RELOAD;

        if (rivalLoads > 0 && rivalLoads < 4) return GunslingerAction.PROTECT; //shen when enemy Machine_gun ready
        else if (loads > 0) return GunslingerAction.SHOOT;
        else return GunslingerAction.RELOAD;
    }
}

class Quick_Protect_Behavior1 implements Behavior { //Reload, Protect, Shoot, Reload...
    GunslingerAction la;

    @Override
    public GunslingerAction action(Gunslinger g) {
        int loads = g.getLoads();

        if (loads == 0) return GunslingerAction.RELOAD;
        else if (loads == 1 && la != GunslingerAction.PROTECT) {
            la = GunslingerAction.PROTECT;
            return GunslingerAction.PROTECT;
        } else {
            la = GunslingerAction.SHOOT;
            return GunslingerAction.SHOOT;
        }
    }
}

class Quick_Protect_Behavior2 implements Behavior { //Reload, Shoot, Protect, Reload...
    GunslingerAction la = GunslingerAction.PROTECT;

    @Override
    public GunslingerAction action(Gunslinger g) {
        int loads = g.getLoads();

        if (loads == 0 && la == GunslingerAction.PROTECT) return GunslingerAction.RELOAD;
        else if (la == GunslingerAction.SHOOT) {
            la = GunslingerAction.PROTECT;
            return GunslingerAction.PROTECT;
        } else {
            la = GunslingerAction.SHOOT;
            return GunslingerAction.SHOOT;
        }
    }
}

class Quick_Protect_Behavior3 implements Behavior { //Reload, Protect, Protect, Shoot, Reload...
    GunslingerAction la = GunslingerAction.MACHINE_GUN;

    @Override
    public GunslingerAction action(Gunslinger g) {
        int loads = g.getLoads();

    if (loads == 0 && la == GunslingerAction.MACHINE_GUN) {
        la = GunslingerAction.RELOAD;
        return GunslingerAction.RELOAD;
    }
        else if (la == GunslingerAction.SHOOT || la == GunslingerAction.PROTECT) {
            if (la == GunslingerAction.PROTECT) la = GunslingerAction.MACHINE_GUN;
            else la = GunslingerAction.PROTECT;
            return GunslingerAction.PROTECT;
        } else {
            la = GunslingerAction.SHOOT;
            return GunslingerAction.SHOOT;
        }
    }
}