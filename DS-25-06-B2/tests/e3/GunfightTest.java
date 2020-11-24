package e3;

import org.junit.jupiter.api.Test;

class GunfightTest {
    Gunslinger G1;
    Gunslinger G2 = new Gunslinger();

    Gunfight gf;

    @Test
    void duel_test() {
        System.out.println("Main Behavior VS Random Behavior");
        G1 = new G_Random();
        G2.setBehavior(new Main_Behavior());

        gf = new Gunfight(G1, G2);
        gf.duel();
    }

    @Test
    void duel_test2() {
        System.out.println("Main Behavior VS Protective Behavior, leads to stalling");
        G1 = new G_protective();
        G2.setBehavior(new Main_Behavior());

        gf = new Gunfight(G1, G2);
        gf.duel();
    }
}