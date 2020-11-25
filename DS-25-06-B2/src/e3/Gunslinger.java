package e3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gunslinger {
    private int loads = 0;
    private Behavior behavior;
    private final List<GunslingerAction> rivalActions = new ArrayList<>();

    public GunslingerAction action() {
        GunslingerAction b = this.behavior.action(this);

        switch (b) {
            case SHOOT:
                this.loads--;
                break;

            case RELOAD:
                this.loads++;
                break;

            case MACHINE_GUN:
                this.loads = this.loads - 5;
                break;
        }
        return b;
    }

    public int getLoads() {
        return this.loads;
    }

    public void rivalAction(GunslingerAction action) {
        rivalActions.add(action);
    }

    public List<GunslingerAction> getRivalActions() {
        return Collections.unmodifiableList(this.rivalActions);
    }

    public int getRivalLoads() {
        int load = 0;

        for (GunslingerAction ga : rivalActions) {
            if (ga == GunslingerAction.RELOAD) load++;
            else if (ga == GunslingerAction.SHOOT) load--;
        }

        return load;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }
}