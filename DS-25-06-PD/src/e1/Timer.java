package e1;

public class Timer extends Observer implements Mode {
    private static final Timer instance = new Timer();
    public static Timer getInstance() { return instance; }
    private Timer() { }
    private int target_time;

    //getter
    protected int getTarget_time() {
        return target_time;
    }

    //setter
    protected void setTarget_time(int time) {
        target_time = time;
    }

    @Override
    public void update(Subject s) {
        if (s instanceof Termostato) {
            Termostato t = (Termostato) s;

            if (t.getMode() == Timer.getInstance() &&
                    target_time > 0 &&
                    target_time <= t.getElapsedTime()) {
                t.setMode(Off.getInstance());
                t.TurnOff();
            }
        }
    }

    @Override
    public void Timer(Termostato t, int time) {
        if (time > 0) {
            target_time = t.getElapsedTime() + time;
            t.TurnOn();
        } else t.setMode(Off.getInstance());
    }

    @Override
    public void Program(Termostato t, float target_temperature) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void ScreenInfo(Termostato t) {
        System.out.println(t.getCurrentTemp()+" ON  T "+(target_time-t.getElapsedTime()));
    }
}
