package e1;

public class Program extends Observer implements Mode {
    private static final Program instance = new Program();
    public static Program getInstance() { return instance; }
    float target_temperature;
    private Program() {}

    protected float getTargetTemperature() {
        return this.target_temperature;
    }

    //setter
    protected void setTarget_temperature(float temp) {
        target_temperature = temp;
    }

    @Override
    public void update(Subject s) {
        if (s instanceof Termostato) {
            Termostato t = (Termostato) s;

            if (t.getMode() == Program.getInstance()){
                if (t.getCurrentTemp() < target_temperature) t.TurnOn();
                else if (t.isOn()) t.TurnOff();
            }
        }
    }

    @Override
    public void Timer(Termostato t, int time) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void Program(Termostato t, float target_temperature) {
//        this.target_temperature = target_temperature; //temperature assignation already occurs on the previous state
    }

    @Override
    public void ScreenInfo(Termostato t) {
        String aux = t.isOn() ? "ON " : "OFF";
        System.out.println(t.getCurrentTemp()+" "+aux+" P "+target_temperature);
    }
}