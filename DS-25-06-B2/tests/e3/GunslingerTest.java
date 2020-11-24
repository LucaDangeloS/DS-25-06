package e3;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GunslingerTest {
    Gunslinger G1;
    Gunslinger G2;

    @Test
    void action_getLoads_rivalAction_getRivalLoads() {
        G1 = new G_protective(); //Only reload and shoot when safe
        G2 = new G_protect_behavior2(); //Reload, Shoot, Protect, Reload, Shoot, Protect...
        List<GunslingerAction> g1_testArray = new ArrayList<>();

        //Always Reload Turn 1:
        assertEquals("RELOAD", G1.action().toString()); //action test
        assertEquals("RELOAD", G2.action().toString());

        G1.rivalAction(GunslingerAction.RELOAD); //rival action test
        G2.rivalAction(GunslingerAction.RELOAD);

        g1_testArray.add(GunslingerAction.RELOAD);

        assertEquals("PROTECT", G1.action().toString());
        assertEquals("SHOOT", G2.action().toString());

        G1.rivalAction(GunslingerAction.SHOOT);
        G2.rivalAction(GunslingerAction.PROTECT);

        g1_testArray.add(GunslingerAction.SHOOT);

        assertEquals(1, G1.getLoads()); //getLoads test
        assertEquals(0, G2.getLoads());

        assertEquals(0, G1.getRivalLoads()); //getRivaLoads test
        assertEquals(1, G2.getRivalLoads());

        assertArrayEquals(g1_testArray.toArray(), G1.getRivalActions().toArray()); //getRivalactions test
    }


    @Test
    void setBehavior() {
        G1 = new Gunslinger();
        G1.setBehavior(new Quick_Behavior()); //Reload, shoot, reload....

        G2 = new G_quicktrigger(); //Same behavior, but set with the method on a new Gunslinger instance.

        assertEquals("RELOAD", G1.action().toString());
        assertEquals("RELOAD", G2.action().toString());

        G1.rivalAction(GunslingerAction.RELOAD);
        G2.rivalAction(GunslingerAction.RELOAD);

        assertEquals("SHOOT", G1.action().toString());
        assertEquals("SHOOT", G2.action().toString());

        //Draw!
    }
}