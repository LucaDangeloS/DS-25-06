package e1;

public class Manual implements Mode {
    private static final Manual instance = new Manual();
    public static Manual getInstance() { return instance; }
    private Manual() {}

    @Override
    public void ScreenInfo(Termostato t) {
        System.out.println(t.getCurrentTemp()+" ON  M");
    }

    @Override
    public void Manual(Termostato t) {
        t.TurnOn();
    }
}
