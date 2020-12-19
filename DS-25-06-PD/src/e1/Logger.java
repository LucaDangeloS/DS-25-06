package e1;

import java.util.ArrayList;
import java.util.List;

public class Logger extends Observer {
    private final List<String> LogEvents = new ArrayList<String>();
    private Mode previousMode = Off.getInstance(); //el termostato siempre empieza en Off
    private float previousTemp;

    @Override
    public void update(Subject s) {
        if (s instanceof Termostato) {
            Termostato t = (Termostato) s;
            Mode aux = t.getMode();

            if (previousMode != aux) { //se cambia el modo del termostato
                if (aux instanceof Off && previousMode == Timer.getInstance()) LogEvents.add("Se desactiva el modo Timer.");
                else if (aux instanceof Off) LogEvents.add("Se activa el modo Off.");
                else if (aux instanceof Manual) LogEvents.add("Se activa el modo Manual.");
                else if (aux instanceof Timer) LogEvents.add("Se activa el modo Timer para "+
                        Timer.getInstance().getTarget_time()+" minutos.");
                else if (aux instanceof Program) LogEvents.add("Se activa el modo Program a "+
                        Program.getInstance().getTargetTemperature()+" grados.");
            } else if (t.getCurrentTemp() != previousTemp) { //se cambia la temperatura ambiente
                if (aux instanceof Off) LogEvents.add(t.getCurrentTemp()+" Modo Off - Calefacción apagada.");
                else if (aux instanceof Manual) LogEvents.add(t.getCurrentTemp()+" Modo Manual - Calefacción encendida.");
                else if (aux instanceof Timer) LogEvents.add(t.getCurrentTemp()+" Modo Timer (faltan "+
                        (Timer.getInstance().getTarget_time()-t.getElapsedTime())+" minutos) - Calefacción encendida.");
                else if (aux instanceof Program) LogEvents.add(t.getCurrentTemp()+" Modo Program (a "+
                        Program.getInstance().getTargetTemperature()+" grados) - Calefacción "+ (t.isOn() ? "encendida." : "apagada."));
            }
            previousMode = aux;
            previousTemp = t.getCurrentTemp();
        }
    }

    public String[] retrieveLog() {
        String[] aux = new String[LogEvents.size()];
        int i=0;

        for (String s : LogEvents) {
            aux[i] = s;
            i++;
        }

        return aux;
    }
}
