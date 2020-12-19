package e1;

public final class Termostato extends Subject {
    private int elapsedTime = 0;
    private float current_temperature;
    private boolean on = false;
    private Logger log = new Logger();
    private Mode op_mode = Off.getInstance(); //se inicia en Off

    Termostato() {
        attach(Timer.getInstance());
        attach(Program.getInstance());
        attach(log);
    }

    //mode setter
    protected void setMode(Mode mode) {
        this.op_mode = mode;
        updateObservers();
    }
    //public setters
    public void newTemperature(float currentTemperature) {
        this.current_temperature = currentTemperature;
        this.elapsedTime = this.elapsedTime+5;
        updateObservers();
    }

    //auxiliary getters
    protected int getElapsedTime() { return this.elapsedTime; }
    protected Mode getMode() { return this.op_mode; }
    //public getters
    public void screenInfo() { op_mode.ScreenInfo(this); }
    public boolean isOn() { return on; }
    public float getCurrentTemp() { return this.current_temperature; }
    public void printLog() {
        for (String s : log.retrieveLog()) {
            System.out.println(s);
        }
    }

    //Auxiliary methods
    protected void TurnOff() {
        this.on = false;
        this.elapsedTime = 0;
    }
    protected void TurnOn() {
        this.on = true;
    }
    //public methods, modes
    public void Off() {
        this.op_mode.Off(this);
    }
    public void Timer(int time) {
        this.op_mode.Timer(this, time);
    }
    public void Program(float target_temperature) {
        this.op_mode.Program(this, target_temperature);
    }
    public void Manual() {
        this.op_mode.Manual(this);
    }


}
