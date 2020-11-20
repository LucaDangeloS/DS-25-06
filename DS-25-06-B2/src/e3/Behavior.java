package e3;

public interface Behavior {
    public abstract GunslingerAction action(Gunslinger g);
}

class G_Behavior implements Behavior {

    @Override
    public GunslingerAction action(Gunslinger g) {
        return null;
    }
}