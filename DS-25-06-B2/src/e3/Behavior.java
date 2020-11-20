package e3;

import e3.GunslingerAction;

public interface Behavior {
    GunslingerAction action(Gunslinger g);
}

class G_Behavior implements Behavior {

    @Override
    public GunslingerAction action(Gunslinger g) {
        if (g.getLoads() == 0) {
            return GunslingerAction.RELOAD;
        } else return GunslingerAction.SHOOT;
    }
}