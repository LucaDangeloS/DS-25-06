package e1;

public interface Mode {
    default void Manual(Termostato t) {
        t.setMode(Manual.getInstance());
        t.Manual();
    }
    default void Timer(Termostato t, int time) {
        Timer.getInstance().setTarget_time(time);
        t.setMode(Timer.getInstance());
        t.Timer(time);
    }
    default void Program(Termostato t, float target_temperature) {
        Program.getInstance().setTarget_temperature(target_temperature);
        t.setMode(Program.getInstance());
        t.Program(target_temperature);
    }
    default void Off(Termostato t) {
        t.setMode(Off.getInstance());
        t.Off();
    }

    void ScreenInfo(Termostato t);
}
