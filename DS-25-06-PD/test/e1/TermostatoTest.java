package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TermostatoTest {
    Termostato t = new Termostato();

    @Test
    void Timer() {
        t.Timer(15);

        t.newTemperature(23); //5 minutos pasan
        t.newTemperature(24); //5 minutos pasan

        assertEquals(24, t.getCurrentTemp()); //cambios de temperatura se registran bien
        assertEquals(Timer.getInstance(), t.getMode()); //se encuentra en el modo timer

        t.newTemperature(25); //5 minutos pasan

        assertEquals(Off.getInstance(), t.getMode()); //al acabar el timer se encuentra en modo Off
        assertFalse(t.isOn()); //variable de control está apagada

        t.Timer(20);
        assertThrows(UnsupportedOperationException.class, () -> t.Program(28)); //no permite el cambio de timer a program
    }

    @Test
    void Program() {
        t.Timer(0); //no deberia cambiar su estado
        assertEquals(Off.getInstance(), t.getMode()); //Está por lo tanto apagado
        t.newTemperature(25);

        t.Timer(20);
        t.Manual();
        t.Program(28);

        assertThrows(UnsupportedOperationException.class, () -> t.Timer(20)); //no permite el cambio de program a timer
        assertEquals(25, t.getCurrentTemp());
        assertTrue(t.isOn()); //está encendido
        assertEquals(Program.getInstance(), t.getMode()); //sigue en modo program con temperatura de consigna de 28 grados

        t.newTemperature(28); //al alcanzar la temperatura deseada, se apaga
        assertFalse(t.isOn()); //está apagado
        assertEquals(Program.getInstance(), t.getMode()); //aún conserva el modo program

        t.newTemperature(22); //si baja la temperatura de la de consigna
        assertTrue(t.isOn()); //está encendido
    }

    @Test
    void Manual() {
        t.newTemperature(20);
        t.Manual();
        t.newTemperature(25);
        assertEquals(Manual.getInstance(), t.getMode());
        t.TurnOff();
    }



    @Test
    void screenInfo() {
        t.Timer(15);

        t.newTemperature(23); //5 minutos pasan
        t.newTemperature(24); //5 minutos pasan

        t.screenInfo(); //24.0 ON  T 5

        t.Off();
        t.screenInfo(); //24.0 OFF O
        t.Manual();
        t.screenInfo(); //24.0 ON  M
        t.Timer(5);
        t.newTemperature((float) 26.5);
        t.screenInfo(); //26.5 OFF O

        t.Program(29);
        t.screenInfo(); //26.5 ON P 29.0
        t.newTemperature((float) 29.5);
        t.screenInfo(); //29.5 OFF P 29.0
    }

    @Test
    void logger() {
        t.newTemperature(24);
        t.Manual();
        t.newTemperature((float) 25.5);
        t.Off();
        t.Timer(13);
        t.newTemperature(26);
        t.newTemperature((float) 27.5);
        t.newTemperature(28);
        t.newTemperature((float)27.5);
        t.Program(30);
        t.newTemperature(29);
        t.newTemperature((float) 30.5);
        t.newTemperature(31);
        t.Off();
        t.printLog();
    }
}