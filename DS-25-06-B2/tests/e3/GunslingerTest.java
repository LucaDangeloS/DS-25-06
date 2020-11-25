package e3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GunslingerTest {
    Gunslinger G1;
    Gunslinger G2;

    @Test
    void All_in_one_Gunslinger_test() {
        G1 = new Gunslinger(); //Only reload and shoot when safe
        G1.setBehavior(new Protective_Behavior());
        G2 = new Gunslinger(); //Reload, Shoot, Protect, Reload, Shoot, Protect...
        G2.setBehavior(new Quick_Protect_Behavior());
        List<GunslingerAction> g1_testArray = new ArrayList<>();

        //Always Reload Turn 1:
        assertEquals("RELOAD", G1.action().toString()); //action() test
        assertEquals("RELOAD", G2.action().toString());

        G1.rivalAction(GunslingerAction.RELOAD); //rivalAction() test
        G2.rivalAction(GunslingerAction.RELOAD);

        g1_testArray.add(GunslingerAction.RELOAD);

        assertEquals("PROTECT", G1.action().toString());
        assertEquals("SHOOT", G2.action().toString());

        G1.rivalAction(GunslingerAction.SHOOT);
        G2.rivalAction(GunslingerAction.PROTECT);

        g1_testArray.add(GunslingerAction.SHOOT);

        assertEquals(1, G1.getLoads()); //getLoads() test
        assertEquals(0, G2.getLoads());

        assertEquals(0, G1.getRivalLoads()); //getRivaLoads() test
        assertEquals(1, G2.getRivalLoads());

        assertArrayEquals(g1_testArray.toArray(), G1.getRivalActions().toArray()); //getRivalactions() test

        // setBehavior() test
        G1.setBehavior(new Quick_Behavior()); //Reload, shoot, reload....
        //G2 still has the same behavior: reload, shoot, protect...
        assertEquals("SHOOT", G1.action().toString()); //G1 shoots
        assertEquals("PROTECT", G2.action().toString()); //G1 Protects

        G1.rivalAction(GunslingerAction.RELOAD);
        G2.rivalAction(GunslingerAction.SHOOT);

        assertEquals("RELOAD", G1.action().toString()); //G1 reloads
        assertEquals("RELOAD", G2.action().toString()); //G2 Reloads

        G1.rivalAction(GunslingerAction.RELOAD);
        G2.rivalAction(GunslingerAction.RELOAD);

        assertEquals("SHOOT", G1.action().toString()); //G1 shoots
        assertEquals("SHOOT", G2.action().toString()); //G2 shoots

        //Draw!
    }
}