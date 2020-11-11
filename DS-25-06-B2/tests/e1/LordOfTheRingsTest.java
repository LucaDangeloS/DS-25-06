package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LordOfTheRingsTest {
    private Ejercito<Bestias> EjBestias = new Ejercito<>();
    private Ejercito<Heroes> EjHeroes = new Ejercito<>();


    @Test
    public void BeastLegionCreation() {
        Bestias cs = new Bestias("Magh'ul", 60, 47, Bestias.RAZAS.Orco);
        Bestias cs2 = new Bestias("Ushulum ", 50, 35, Bestias.RAZAS.Trasgo);
        Bestias cs3 = new Bestias("Uruk-hai", 80, 52, Bestias.RAZAS.Orco);

        EjBestias.add(cs);
        EjBestias.add(cs2);
        EjBestias.add(cs3);

        assertSame("Ejercito de Bestias\n" +
                "\tMagh'ul : Orco (HP: 60) (RES: 47)\n" +
                "\tUshulum : Trasgo (HP: 50) (RES: 35)\n" +
                "\tUruk-hai : Orco (HP: 80) (RES: 52)\n",EjBestias.toString());
    }

    public void HeroesArmyCreation() {
        Heroes bs = new Heroes("Gandalf", 55, 55, Heroes.RAZAS.Humano);
        Heroes bs2 = new Heroes("Legolas", 62, 22, Heroes.RAZAS.Elfo);
        Heroes bs3 = new Heroes("Frodo", 47, 15, Heroes.RAZAS.Hobbit);

        EjHeroes.add(bs);
        EjHeroes.add(bs2);
        EjHeroes.add(bs3);

        assertSame("Ejercito de Heroes\n" +
                "\tGandalf : Humano (HP: 50) (RES: 65)\n" +
                "\tLegolas : Elfo (HP:62) (RES: 22)\n" +
                "\tFrodo : Hobbit (HP: 47) (RES: 15)\n",EjHeroes.toString());
    }

    @Test
    void Testd () {
      new RNG();
      var game = new Game(EjHeroes, EjBestias);
    }
}