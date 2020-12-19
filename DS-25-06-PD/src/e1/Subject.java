package e1;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private final List<Observer> Observers = new ArrayList<>();

    void attach(Observer o) { Observers.add(o); }
    void deattach(Observer o) { Observers.remove(o); }

    //Update Observers
    void updateObservers() {
        for (Observer ob : Observers) {
            ob.update(this);
        }
    }
}
