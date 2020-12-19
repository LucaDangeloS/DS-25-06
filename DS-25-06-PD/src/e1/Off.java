package e1;

public class Off implements Mode {
    private static final Off instance = new Off();
    public static Off getInstance() { return instance; }
    private Off() {}

    @Override
    public void ScreenInfo(Termostato t) {
        System.out.println(t.getCurrentTemp()+" OFF O");
    }

    @Override
    public void Off(Termostato t) {
        t.TurnOff();
    }
}
